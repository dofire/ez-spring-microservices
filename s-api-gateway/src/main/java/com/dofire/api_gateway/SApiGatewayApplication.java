package com.dofire.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SApiGatewayApplication.class, args);
	}

}
