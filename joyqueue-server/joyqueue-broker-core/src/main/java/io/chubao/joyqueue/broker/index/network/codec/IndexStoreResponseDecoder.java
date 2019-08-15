/**
 * Copyright 2019 The JoyQueue Authors.
 *
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
package io.chubao.joyqueue.broker.index.network.codec;

import io.chubao.joyqueue.broker.index.command.ConsumeIndexStoreResponse;
import io.chubao.joyqueue.network.transport.codec.JoyQueueHeader;
import io.chubao.joyqueue.network.transport.codec.PayloadDecoder;
import io.chubao.joyqueue.network.command.CommandType;
import io.chubao.joyqueue.network.serializer.Serializer;
import io.chubao.joyqueue.network.transport.command.Type;

import io.netty.buffer.ByteBuf;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by zhuduohui on 2018/9/7.
 */
public class IndexStoreResponseDecoder implements PayloadDecoder<JoyQueueHeader>, Type {

    @Override
    public Object decode(final JoyQueueHeader header, final ByteBuf buffer) throws Exception {
        Map<String, Map<Integer, Short>> indexStoreStatus = new HashedMap();
        int topics = buffer.readInt();
        for (int i = 0; i < topics; i++) {
            String topic = Serializer.readString(buffer, Serializer.SHORT_SIZE);
            int partitions = buffer.readInt();
            Map<Integer, Short> partitionStoreStatus = new HashedMap();
            for (int j = 0; j < partitions; j++) {
                int partition = buffer.readInt();
                short code = buffer.readShort();
                partitionStoreStatus.put(partition, code);
            }
            indexStoreStatus.put(topic, partitionStoreStatus);
        }
        return new ConsumeIndexStoreResponse(indexStoreStatus);
    }

    @Override
    public int type() {
        return CommandType.CONSUME_INDEX_STORE_RESPONSE;
    }
}
