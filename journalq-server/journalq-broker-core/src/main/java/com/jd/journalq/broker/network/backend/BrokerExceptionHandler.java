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
package com.jd.journalq.broker.network.backend;

import com.jd.journalq.network.transport.Transport;
import com.jd.journalq.network.transport.command.Command;
import com.jd.journalq.network.transport.command.handler.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常处理
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/9/17
 */
public class BrokerExceptionHandler implements ExceptionHandler {

    protected static final Logger logger = LoggerFactory.getLogger(BrokerExceptionHandler.class);

    @Override
    public void handle(Transport transport, Command command, Throwable throwable) {
        logger.error("broker transport exception, transport: {}, command: {}", transport, command, throwable);
    }
}