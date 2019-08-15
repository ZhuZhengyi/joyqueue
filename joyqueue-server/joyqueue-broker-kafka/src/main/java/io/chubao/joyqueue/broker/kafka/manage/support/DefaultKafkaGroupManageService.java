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
package io.chubao.joyqueue.broker.kafka.manage.support;

import io.chubao.joyqueue.broker.kafka.KafkaErrorCode;
import io.chubao.joyqueue.broker.kafka.coordinator.group.GroupCoordinator;
import io.chubao.joyqueue.broker.kafka.coordinator.group.domain.GroupJoinGroupResult;
import io.chubao.joyqueue.broker.kafka.coordinator.group.domain.GroupMemberMetadata;
import io.chubao.joyqueue.broker.kafka.coordinator.group.domain.GroupMetadata;
import io.chubao.joyqueue.broker.kafka.manage.KafkaGroupManageService;

/**
 * DefaultKafkaGroupManageService
 *
 * author: gaohaoxiang
 * date: 2018/11/13
 */
public class DefaultKafkaGroupManageService implements KafkaGroupManageService {

    private GroupCoordinator groupCoordinator;

    public DefaultKafkaGroupManageService(GroupCoordinator groupCoordinator) {
        this.groupCoordinator = groupCoordinator;
    }

    @Override
    public boolean removeGroup(String groupId) {
        GroupMetadata group = groupCoordinator.getGroup(groupId);
        if (group == null) {
            return false;
        }
        return groupCoordinator.removeGroup(group);
    }

    @Override
    public boolean rebalanceGroup(String groupId) {
        GroupMetadata group = groupCoordinator.getGroup(groupId);
        if (group == null) {
            return false;
        }

        groupCoordinator.removeGroup(group);

        for (GroupMemberMetadata groupMemberMetadata : group.getAllMembers()) {
            if (groupMemberMetadata.getAwaitingJoinCallback() != null) {
                groupMemberMetadata.getAwaitingJoinCallback().sendResponseCallback(GroupJoinGroupResult.buildError(groupMemberMetadata.getId(), KafkaErrorCode.UNKNOWN_MEMBER_ID.getCode()));
                groupMemberMetadata.setAwaitingJoinCallback(null);
            }
            if (groupMemberMetadata.getAwaitingSyncCallback() != null) {
                groupMemberMetadata.getAwaitingSyncCallback().sendResponseCallback(null, KafkaErrorCode.UNKNOWN_MEMBER_ID.getCode());
                groupMemberMetadata.setAwaitingSyncCallback(null);
            }
        }

        return true;
    }
}