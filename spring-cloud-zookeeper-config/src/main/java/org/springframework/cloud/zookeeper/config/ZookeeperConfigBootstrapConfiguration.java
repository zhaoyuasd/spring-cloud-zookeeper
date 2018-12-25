/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.zookeeper.config;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.RetryPolicy;
import org.apache.curator.ensemble.EnsembleProvider;
import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.zookeeper.ConditionalOnZookeeperEnabled;
import org.springframework.cloud.zookeeper.ZookeeperAutoConfiguration;
import org.springframework.cloud.zookeeper.ZookeeperProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.fastjson.JSONObject;

/**
 * Bootstrap Configuration for Zookeeper Configuration
 *
 * @author Spencer Gibb
 * @since 1.0.0
 */
@Configuration
@ConditionalOnZookeeperEnabled
@Import(ZookeeperAutoConfiguration.class)
public class ZookeeperConfigBootstrapConfiguration {
public ZookeeperConfigBootstrapConfiguration() {
	System.out.println("ZookeeperConfigBootstrapConfiguration -----------");
}
private static final Log log = LogFactory.getLog(ZookeeperConfigBootstrapConfiguration.class);

   @Autowired(required = false)
    private EnsembleProvider ensembleProvider;
	@Bean
	@ConditionalOnMissingBean
	public ZookeeperPropertySourceLocator zookeeperPropertySourceLocator(
			CuratorFramework curator, ZookeeperConfigProperties properties) {
		System.out.println("ZookeeperConfigBootstrapConfiguration -----------ZookeeperPropertySourceLocator");
		System.out.println(JSONObject.toJSONString(properties));
		return new ZookeeperPropertySourceLocator(curator, properties);
	}

	@Bean
	@ConditionalOnMissingBean
	public ZookeeperConfigProperties zookeeperConfigProperties() {
		System.out.println("ZookeeperConfigBootstrapConfiguration -----------ZookeeperPropertySourceLocator");
		return new ZookeeperConfigProperties();
	}
	
	//  Access Control Lists
	 @Bean
	  public CuratorFramework curatorFramework(RetryPolicy retryPolicy, ZookeeperProperties properties) throws Exception {
		 CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
		 if (this.ensembleProvider != null) {
				builder.ensembleProvider(this.ensembleProvider);
			} else {
				builder.connectString(properties.getConnectString());
			}
		     builder.authorization(Arrays.asList(new AuthInfo("digest", "user:password".getBytes())));
			CuratorFramework curator = builder.retryPolicy(retryPolicy).build();
			curator.start();
			log.trace("blocking until connected to zookeeper for " + properties.getBlockUntilConnectedWait() + properties.getBlockUntilConnectedUnit());
			curator.blockUntilConnected(properties.getBlockUntilConnectedWait(), properties.getBlockUntilConnectedUnit());
			//curator.addAuthInfo("digest", "user:password".getBytes());
		    // builder.authorization(Arrays.asList(new AuthInfo("digest", "user:password".getBytes())));
		    return curator;
		 /*curator.addAuthInfo("digest", "user:password".getBytes());
	    return curator;*/
	  }
}
