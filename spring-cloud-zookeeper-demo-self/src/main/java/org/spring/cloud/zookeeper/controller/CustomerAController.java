package org.spring.cloud.zookeeper.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@RestController
public class CustomerAController {
	@Autowired
	private RestTemplate rest;
/*	@Autowired
	private  LoadBalancerClient loadBalancer;
	@RequestMapping("hello2")
    public String helloService(String name) {
        //Get请求调用服务，restTemplate被@LoadBalanced注解标记，Get方法会自动进行负载均衡
		System.out.println(loadBalancer.getClass().getName());
		final ServiceInstance instance = loadBalancer.choose("HAS-ZK-APP");
		System.out.println(JSONObject.toJSONString(instance));
		URI uu=URI.create("http://HAS-ZK-APP/hello?name="+name);
		URI requestUrl = loadBalancer.reconstructURI(instance,uu);
		System.out.println(uu);
		return restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/hello?name="+name, String.class);
    }*/
	@RequestMapping("hello3")
	 public String helloService3(String name) {
	        //Get请求调用服务，restTemplate被@LoadBalanced注解标记，Get方法会自动进行负载均衡
			/*System.out.println(loadBalancer.getClass().getName());
			final ServiceInstance instance = loadBalancer.choose("HAS-ZK-APP");
			System.out.println(JSONObject.toJSONString(instance));
			URI uu=URI.create("http://HAS-ZK-APP/hello?name="+name);
			URI requestUrl = loadBalancer.reconstructURI(instance,uu);*/
			//System.out.println(uu);
			return rest.getForObject("http://has-zk-app/hello?name="+name, String.class);
	    }

} 
