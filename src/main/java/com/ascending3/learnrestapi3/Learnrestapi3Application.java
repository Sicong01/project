package com.ascending3.learnrestapi3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
/*
 * The following is for development using spring boot embedded Tomcat
 */
public class Learnrestapi3Application {
	private static final Logger logger = LoggerFactory.getLogger(Learnrestapi3Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Learnrestapi3Application.class, args);
	}

}


/*
 * The following is for deploy the app to external Tomcat
 */
//public class Learnrestapi3Application extends SpringBootServletInitializer {
//	private static final Logger logger = LoggerFactory.getLogger(Learnrestapi3Application.class);
//
//	public static void main(String[] args) {
//
//		SpringApplication.run(Learnrestapi3Application.class, args);
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//		return builder.sources(Learnrestapi3Application.class);
//	}
//
//}
