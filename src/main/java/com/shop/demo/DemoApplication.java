package com.shop.demo;

import com.shop.demo.Users.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.lang.String;


@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args)  {


		SpringApplication.run(DemoApplication.class, args);
	}


}