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
package io.chubao.joyqueue.broker.limit.support;

import io.chubao.joyqueue.broker.limit.RateLimiter;

/**
 * NoneRateLimiter
 *
 * author: gaohaoxiang
 * date: 2019/5/17
 */
public class NoneRateLimiter implements RateLimiter {

    @Override
    public boolean tryAcquireTps() {
        return true;
    }

    @Override
    public boolean tryAcquireTps(int tps) {
        return true;
    }

    @Override
    public boolean tryAcquireTraffic(int traffic) {
        return true;
    }
}