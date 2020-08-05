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
package com.github.thierrysquirrel.sparrow.init;

import com.github.thierrysquirrel.sparrow.autoconfigure.SparrowProperties;
import com.github.thierrysquirrel.sparrow.init.core.factory.execution.SparrowConsumerInitFactoryExecution;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * ClassName: SparrowConsumerInit
 * Description:
 * date: 2020/6/11 7:14
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
@Slf4j
public class SparrowConsumerInit implements ApplicationContextAware {
    @Resource
    private SparrowProperties sparrowProperties;
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        SparrowConsumerInitFactoryExecution.init (sparrowProperties, applicationContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
