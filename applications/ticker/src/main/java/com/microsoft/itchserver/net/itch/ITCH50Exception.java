/*
 * Copyright 2015 Juncture authors
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
package com.microsoft.itchserver.net.itch;

import java.io.IOException;

/**
 * Indicates a protocol error while handling the NASDAQ TotalView-ITCH 5.0
 * protocol.
 */
public class ITCH50Exception extends IOException {

    /**
     * Construct an instance with the specified detail message.
     *
     * @param message the detail message
     */
    public ITCH50Exception(String message) {
        super(message);
    }

}
