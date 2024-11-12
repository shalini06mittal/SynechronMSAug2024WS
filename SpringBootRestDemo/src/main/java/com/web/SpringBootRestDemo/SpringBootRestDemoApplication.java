package com.web.SpringBootRestDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.web.SpringBootRestDemo.entity.FictionalCharacter;
import com.web.SpringBootRestDemo.entity.Wand;
import com.web.SpringBootRestDemo.repo.CharacterRepo;
import com.web.SpringBootRestDemo.repo.WandRepo;

@SpringBootApplication
public class SpringBootRestDemoApplication {
	
	
	public static void main(String[] args) {
		
		
		
		ConfigurableApplicationContext context=
		SpringApplication.run(SpringBootRestDemoApplication.class, args);
		CharacterRepo characterRepo = context.getBean(CharacterRepo.class);
		WandRepo wandRepo = context.getBean(WandRepo.class);
		
		Wand wand = new Wand();
		//wand.setId(1);
		wand.setCore("hp core");
		wand.setLength("11 inch");
		wand.setWood("hardwood");
	
		//wand = wandRepo.save(wand);
		
		FictionalCharacter ob1 = new FictionalCharacter(1, "HP", "Gryffinder", "harry potter bio", "", wand);
		characterRepo.save(ob1);
		
		FictionalCharacter ob2 = characterRepo.findById(ob1.getId()).get();
		System.out.println(ob2.getBio());
		System.out.println(ob2.getWand().getId());
		System.out.println(ob2);
		
	}

}
