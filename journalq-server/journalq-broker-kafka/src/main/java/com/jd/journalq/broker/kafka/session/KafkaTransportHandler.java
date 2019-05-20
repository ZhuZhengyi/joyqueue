/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jd.journalq.broker.kafka.session;

import com.jd.journalq.broker.kafka.KafkaCommandType;
import com.jd.journalq.network.transport.ChannelTransport;
import com.jd.journalq.network.transport.RequestBarrier;
import com.jd.journalq.network.transport.TransportHelper;
import com.jd.journalq.network.transport.command.Command;
import com.jd.journalq.network.transport.command.support.DefaultCommandDispatcher;
import com.jd.journalq.network.transport.handler.CommandInvocation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * KafkaTransportHandler
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2019/5/7
 */
@ChannelHandler.Sharable
public class KafkaTransportHandler extends ChannelDuplexHandler {

    protected static final Logger logger = LoggerFactory.getLogger(KafkaTransportHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        RequestBarrier requestBarrier = null;
        ChannelTransport transport = TransportHelper.getTransport(channel);

        if (transport == null) {
            if (ctx.pipeline().last() instanceof CommandInvocation) {
                CommandInvocation commandInvocation = (CommandInvocation) ctx.pipeline().last();
                if (commandInvocation.getCommandDispatcher() instanceof DefaultCommandDispatcher) {
                    requestBarrier = ((DefaultCommandDispatcher) commandInvocation.getCommandDispatcher()).getRequestBarrier();
                }
            }

            if (requestBarrier != null) {
                transport = TransportHelper.getOrNewTransport(channel, requestBarrier);
            }
        }

        if (!(transport instanceof KafkaChannelTransport)) {
            transport = new KafkaChannelTransport(transport);
            TransportHelper.setTransport(channel, transport);
        }

        int type = ((Command) msg).getHeader().getType();
        if (type == KafkaCommandType.METADATA.getCode()) {
            ((KafkaChannelTransport) transport).acquire();
        } else {
            ((KafkaChannelTransport) transport).tryAcquire();
        }

        super.channelRead(ctx, msg);
    }
}