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
package com.github.thierrysquirrel.sparrow.aspect.core.factory.execution;

import com.github.thierrysquirrel.sparrow.annotation.SparrowAsyncProducer;
import com.github.thierrysquirrel.sparrow.aspect.core.factory.SparrowAspectFactory;
import com.github.thierrysquirrel.sparrow.error.SparrowException;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ClassName: SparrowAspectFactoryExecution
 * Description:
 * date: 2020/6/11 8:50
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowAspectFactoryExecution {
    private SparrowAspectFactoryExecution() {
    }

    public static Object sparrowAsyncProducerAround(SparrowAsyncProducer sparrowAsyncProducer, String sparrowServerUrl, ProceedingJoinPoint point) throws SparrowException {
        String topic = sparrowAsyncProducer.value ();
        Object proceed;
        try {
            proceed = point.proceed ();
        } catch (Throwable e) {
            throw new SparrowException (e);
        }
        SparrowAspectFactory.sparrowAsyncProducerAround (sparrowServerUrl, topic, proceed);
        return proceed;
    }
}
