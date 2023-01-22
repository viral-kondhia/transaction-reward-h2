package com;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.entity.Transaction;
import com.repository.TransactionRepository;

@Configuration
public class AppConfig {

	@Bean
	CommandLineRunner initDatabase(TransactionRepository repository) {
		return args -> {
			List<Transaction> transactions = new ArrayList<>();
			transactions.add(new Transaction(1L, 1L, 110.0, LocalDate.parse("2022-12-12")));
			transactions.add(new Transaction(2L, 1L, 90.0, LocalDate.parse("2022-12-12")));
			transactions.add(new Transaction(3L, 1L, 49.0, LocalDate.parse("2022-11-12")));
			transactions.add(new Transaction(4L, 1L, 75.0, LocalDate.parse("2022-11-12")));
			transactions.add(new Transaction(5L, 2L, 110.0, LocalDate.parse("2022-12-12")));
			transactions.add(new Transaction(6L, 2L, 90.0, LocalDate.parse("2022-12-12")));
			transactions.add(new Transaction(7L, 2L, 49.0, LocalDate.parse("2022-11-12")));
			transactions.add(new Transaction(8L, 2L, 75.0, LocalDate.parse("2022-11-12")));
			repository.saveAll(transactions);

		};
	}
}
