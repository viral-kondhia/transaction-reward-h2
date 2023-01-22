package com.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.APIResponse;
import com.entity.Transaction;
import com.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	public List<Transaction> getAllTransactions() {
		return repository.findAll();
	}

	public APIResponse getRewardsByCustomerId(Long customerId) {
		return calculateRewardsResponse(customerId);
	}

	public APIResponse getRewards() {
		return calculateRewardsResponse(null);
	}

	private APIResponse calculateRewardsResponse(Long customerId) {
		List<Transaction> transactions = new ArrayList<>();
		transactions = getAllTransactions();
		if (customerId != null) {

			transactions = transactions.stream().filter(e -> e.getCustomerId().equals(customerId))
					.collect(Collectors.toList());

		}
		Map<String, Integer> mapRewardsByMonth = new HashMap<>();
		int grandTotalOfRewards = 0;
		for (Transaction transaction : transactions) {
			double amount = transaction.getAmount();
			int rewardPoints = 0;
			if (amount > 100) {
				rewardPoints += 2 * (amount - 100);
				rewardPoints += 50;
			} else if (amount > 50) {
				rewardPoints += 1 * amount - 50;
			}
			grandTotalOfRewards += rewardPoints;
			LocalDate date = transaction.getDate();
			String rewardMonth = date.getMonth().toString();
			mapRewardsByMonth.put(rewardMonth, mapRewardsByMonth.getOrDefault(rewardMonth, 0) + rewardPoints);
		}
		return new APIResponse(grandTotalOfRewards, mapRewardsByMonth);
	}
}
