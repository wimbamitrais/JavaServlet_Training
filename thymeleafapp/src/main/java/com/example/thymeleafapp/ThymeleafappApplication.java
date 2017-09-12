package com.example.thymeleafapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.thymeleafapp.domain.Post;
import com.example.thymeleafapp.domain.PostRepository;

@SpringBootApplication
public class ThymeleafappApplication implements CommandLineRunner {

	@Autowired
	private PostRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(ThymeleafappApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		for (int i = 0; i < 5; i++) {
			repository.save(new Post ("My post number #" + (i+1)));
		}
	}
}
