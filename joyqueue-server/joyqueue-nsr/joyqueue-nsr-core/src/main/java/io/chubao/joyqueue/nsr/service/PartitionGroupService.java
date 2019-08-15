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
package io.chubao.joyqueue.nsr.service;


import io.chubao.joyqueue.domain.PartitionGroup;
import io.chubao.joyqueue.domain.TopicName;
import io.chubao.joyqueue.nsr.model.PartitionGroupQuery;

import java.util.List;

/**
 * @author lixiaobin6
 * 下午3:11 2018/8/13
 */
public interface PartitionGroupService extends DataService<PartitionGroup, PartitionGroupQuery, String> {
    /**
     * 根据Topic和PartitionGroup查找
     * * @param namespace
     *
     * @param topic
     * @param group
     * @return
     */
    PartitionGroup findByTopicAndGroup(TopicName topic, int group);

    /**
     * 根据Topic查找
     *
     * @param topic
     * @return
     */
    List<PartitionGroup> getByTopic(TopicName topic);
}
