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
package io.chubao.joyqueue.client.internal.producer;

import io.chubao.joyqueue.client.internal.metadata.domain.PartitionMetadata;
import io.chubao.joyqueue.client.internal.metadata.domain.TopicMetadata;
import io.chubao.joyqueue.client.internal.producer.domain.ProduceMessage;
import com.jd.laf.extension.Type;

import java.util.List;

/**
 * PartitionSelector
 *
 * author: gaohaoxiang
 * date: 2018/12/10
 */
public interface PartitionSelector extends Type<String> {

    PartitionMetadata select(ProduceMessage message, TopicMetadata topicMetadata, List<PartitionMetadata> partitions);
}