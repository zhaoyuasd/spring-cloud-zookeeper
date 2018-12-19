package org.spring.cloud.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.zookeeper.discovery.RibbonZookeeperAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@Configuration
@EnableAutoConfiguration
public class SpringClouldZookeeper {
	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private DiscoveryClient discovery;
	
   public static void main(String[] args) {
	SpringApplication.run(SpringClouldZookeeper.class, args);
}
  
   
    @LoadBalanced //开启负载均衡客户端
	@Bean //注册一个具有容错功能的RestTemplate
	RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}
    
  
   @RequestMapping({"/hello","/"})
   public String hello(String msg) {
	   return "get info :"+msg;
   }
   
}
