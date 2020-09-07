/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.sparrow.aspect.core.factory;

import com.github.thierrysquirrel.sparrow.core.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.sparrow.netty.client.thread.execution.AsyncProducerThreadExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.execution.ThreadPoolFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.utils.SerializerUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: SparrowAspectFactory
 * Description:
 * date: 2020/6/11 8:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class SparrowAspectFactory {
    private SparrowAspectFactory() {
    }

    public static void sparrowAsyncProducerAround(String sparrowServerUrl, String topic, Object message) {
        AsyncProducerThreadExecution asyncProducerThreadExecution = new AsyncProducerThreadExecution (sparrowServerUrl, topic, SerializerUtils.serialize (message));
        ThreadPoolExecutor asyncProducerThreadPool = ThreadPoolFactoryConstant.ASYNC_PRODUCER_THREAD_POOL;
        ThreadPoolFactoryExecution.statsThread (asyncProducerThreadPool, asyncProducerThreadExecution);
    }

}
