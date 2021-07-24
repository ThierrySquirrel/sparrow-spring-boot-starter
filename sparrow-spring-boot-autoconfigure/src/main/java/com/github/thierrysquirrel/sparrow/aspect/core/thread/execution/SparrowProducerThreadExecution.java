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
package com.github.thierrysquirrel.sparrow.aspect.core.thread.execution;

import com.github.thierrysquirrel.sparrow.aspect.core.thread.AbstractSparrowProducerThread;
import com.github.thierrysquirrel.sparrow.server.common.netty.producer.init.client.SparrowProducerCluster;

/**
 * ClassName: SparrowProducerThreadExecution
 * Description:
 * date: 2020/12/8 5:50
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowProducerThreadExecution extends AbstractSparrowProducerThread {
	public SparrowProducerThreadExecution(String sparrowServerUrl, String topic, byte[] message) {
		super(sparrowServerUrl, topic, message);
	}

	@Override
	protected void sendMessage(String sparrowServerUrl, String topic, byte[] message) {
		SparrowProducerCluster.getSparrowProducer(sparrowServerUrl, topic).sendMessage(message);
	}
}
