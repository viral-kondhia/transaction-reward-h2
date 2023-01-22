package com.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dto.APIResponse;
import com.entity.Transaction;
import com.repository.TransactionRepository;
import com.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@Mock
	private TransactionRepository repository;

	@InjectMocks
	private TransactionService service;

	@Test
	public void testGetRewardsByCustomerId() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1L, 1L, 110.0, LocalDate.parse("2022-12-12")));
		transactions.add(new Transaction(2L, 1L, 90.0, LocalDate.parse("2022-12-12")));
		transactions.add(new Transaction(3L, 2L, 49.0, LocalDate.parse("2022-11-12")));
		transactions.add(new Transaction(4L, 2L, 75.0, LocalDate.parse("2022-11-12")));

		when(repository.findAll()).thenReturn(transactions);

		APIResponse response = service.getRewardsByCustomerId(1L);

		assertEquals(110, response.getTotalRewards());
	}

	@Test
	public void testGetRewards() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1L, 1L, 110.0, LocalDate.parse("2022-12-12")));
		transactions.add(new Transaction(2L, 1L, 90.0, LocalDate.parse("2022-12-12")));
		transactions.add(new Transaction(3L, 2L, 49.0, LocalDate.parse("2022-11-12")));
		transactions.add(new Transaction(4L, 2L, 75.0, LocalDate.parse("2022-11-12")));

		when(repository.findAll()).thenReturn(transactions);

		APIResponse response = service.getRewards();

		assertEquals(135, response.getTotalRewards());
	}
}
