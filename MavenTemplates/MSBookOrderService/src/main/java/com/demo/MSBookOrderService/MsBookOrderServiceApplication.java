package com.demo.MSBookOrderService;

import com.demo.MSBookOrderService.model.BookOrder;
import com.demo.MSBookOrderService.repository.BookOrderRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@SpringBootApplication
public class MsBookOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBookOrderServiceApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(BookOrderRepository repository)
	{
		return args->{
			Stream.of(new BookOrder(1, "priya@yahoo.com", "B1", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss"))),
							new BookOrder(2, "riya@gmail.com", "B3", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss"))),
							new BookOrder(3, "riya@gmail.com", "B1", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss")))
					)
					.forEach(book-> repository.save(book));
			repository.findAll().forEach(System.out::println);

		};
	}
}
