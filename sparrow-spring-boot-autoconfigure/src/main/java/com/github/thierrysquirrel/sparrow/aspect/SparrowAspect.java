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
package com.github.thierrysquirrel.sparrow.aspect;

import com.github.thierrysquirrel.sparrow.annotation.Producer;
import com.github.thierrysquirrel.sparrow.aspect.core.execution.SparrowAspectExecution;
import com.github.thierrysquirrel.sparrow.aspect.utils.SparrowAspectUtils;
import com.github.thierrysquirrel.sparrow.autoconfigure.SparrowProperties;
import com.github.thierrysquirrel.sparrow.core.exception.SparrowException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;

/**
 * ClassName: SparrowAspect
 * Description:
 * date: 2020/12/8 5:38
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Aspect
@Data
@Slf4j
public class SparrowAspect {
	@Resource
	private SparrowProperties sparrowProperties;

	@Pointcut("@annotation(com.github.thierrysquirrel.sparrow.annotation.Producer)")
	public void producerPointcut() {
		log.debug("Start Producer");
	}

	@Around("producerPointcut()")
	public Object sparrowProducerAround(ProceedingJoinPoint point) throws SparrowException {
		return SparrowAspectExecution.sendMessage(point,
				SparrowAspectUtils.getAnnotation(point, Producer.class), sparrowProperties.getSparrowServerUrl());
	}
}
