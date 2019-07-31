package com.han;

import com.han.spi.IAresService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class App {



	public static void main(String[] args) {

		// 启动Sprign Boot
		ApplicationContext ctx = SpringApplication.run(App.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
		System.out.println("\n\n-----------------IAresService-----------------------");
		String[] beanNamesForType = ctx.getBeanNamesForType(IAresService.class);
		for (String s : beanNamesForType)
		{
			IAresService aresService = (IAresService) ctx.getBean(s);
			System.out.println(aresService.getServiceInfo());
		}
	}
}
