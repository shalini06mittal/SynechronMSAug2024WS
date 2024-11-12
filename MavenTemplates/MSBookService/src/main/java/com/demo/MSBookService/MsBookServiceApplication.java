package com.demo.MSBookService;

import com.demo.MSBookService.model.Book;
import com.demo.MSBookService.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class MsBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBookServiceApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(BookRepository repository)
	{
		return args->{
			Stream.of(new Book("B1", "HarryPotter", "Adventure Book", 123.23),
							new Book("B2", "Adventures of Sherlock Homes", "Adventure Book", 400),
							new Book("B3", "nancy Drew", "Suspense Book", 250.50)
					)
					.forEach(book-> repository.save(book));
			repository.findAll().forEach(System.out::println);
		};
	}
}
