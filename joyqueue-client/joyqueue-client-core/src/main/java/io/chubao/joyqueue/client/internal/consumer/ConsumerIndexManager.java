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
package io.chubao.joyqueue.client.internal.consumer;

import com.google.common.collect.Table;
import io.chubao.joyqueue.client.internal.consumer.domain.ConsumeReply;
import io.chubao.joyqueue.client.internal.consumer.domain.FetchIndexData;
import io.chubao.joyqueue.exception.JoyQueueCode;
import io.chubao.joyqueue.toolkit.lang.LifeCycle;

import java.util.List;
import java.util.Map;

/**
 * ConsumerIndexManager
 *
 * author: gaohaoxiang
 * date: 2018/12/12
 */
public interface ConsumerIndexManager extends LifeCycle {

    JoyQueueCode resetIndex(String topic, String app, short partition, long timeout);

    FetchIndexData fetchIndex(String topic, String app, short partition, long timeout);

    JoyQueueCode commitReply(String topic, List<ConsumeReply> replyList, String app, long timeout);

    // batch

    Table<String, Short, FetchIndexData> batchFetchIndex(Map<String, List<Short>> topicMap, String app, long timeout);

    Map<String, JoyQueueCode> batchCommitReply(Map<String, List<ConsumeReply>> replyMap, String app, long timeout);
}