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
package io.chubao.joyqueue.broker.consumer.filter;

import io.chubao.joyqueue.exception.JoyQueueException;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * 消息过滤回调
 *
 * Created by chengzhiliang on 2019/2/20.
 */
public interface FilterCallback {

    void callback(List<ByteBuffer> list) throws JoyQueueException;
}
