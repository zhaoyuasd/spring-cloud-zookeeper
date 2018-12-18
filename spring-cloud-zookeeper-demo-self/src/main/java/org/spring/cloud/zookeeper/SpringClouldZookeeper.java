package org.spring.cloud.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class SpringClouldZookeeper {
   public static void main(String[] args) {
	SpringApplication.run(SpringClouldZookeeper.class, args);
}
  
   @RequestMapping({"/hello","/"})
   public String hello(String msg) {
	   return "get info :"+msg;
   }
   
}
