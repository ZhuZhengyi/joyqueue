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
package io.chubao.joyqueue.model.exception;

/**
 * 引用异常
 */
public class ReferenceException extends BusinessException {

    protected ReferenceException() {
    }

    public ReferenceException(String message) {
        super(message);
    }

    public ReferenceException(int status, String message) {
        super(status, message);
    }

    public ReferenceException(String code, String message) {
        super(code, message);
    }

    public ReferenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferenceException(int status, String code, String message, Throwable cause) {
        super(status, code, message, cause);
    }
}