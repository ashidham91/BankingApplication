package com.bank.BankingApplication;

import com.bank.BankingApplication.dto.TransactionResponse;


import com.bank.BankingApplication.dto.UserDto;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.entity.Transaction;

import com.bank.BankingApplication.service.DashboardService;
import com.bank.BankingApplication.service.TransactionService;
import com.bank.BankingApplication.service.TransferService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(YourController.class)
class ApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private DashboardService dashboardService;

	@MockitoBean
	private TransactionService transactionService;

	@MockitoBean
	private TransferService transferService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	 void testGetAccounts_Integration() throws Exception {

		mockMvc.perform(get("/api/accounts/1/ADMIN")
						.contentType(String.valueOf(MediaType.APPLICATION_JSON)))
				.andExpect(status().isOk());
	}



	@Test
	void testSaveUser_Success() throws Exception {


		UserDto user = new UserDto();
		user.setId(1);
		user.setUsername("John");
		user.setEmail("ashi@gmail.com");
		user.setRole("Admin");
		user.setPassword("1234");


		mockMvc.perform(post("/api/user")
						.contentType(String.valueOf(MediaType.APPLICATION_JSON))
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("John"));

		verify(dashboardService).saveUser(any(UserDto.class));
	}


	@Test
	void testSaveTransaction_Success() throws Exception {

		TransactionResponse request = new TransactionResponse();
		request.setId(1);
		request.setFrom_account("ACC1001");
		request.setTo_account("ACC1002");
		request.setAmount(500.0);
		request.setStatus("SUCCESS");

		mockMvc.perform(post("/api/transaction")
						.contentType(String.valueOf(MediaType.APPLICATION_JSON))
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value(500.0))
				.andExpect(jsonPath("$.status").value("SUCCESS"));

		verify(transactionService).saveUserTrasaction(any(TransactionResponse.class));
	}


	@Test
	void testGetTransactionList_Success() throws Exception {

		Transaction t1 = new Transaction(1, 500.0, "SUCCESS");
		Transaction t2 = new Transaction(2, 300.0, "FAILED");

		List<Transaction> mockList = Arrays.asList(t1, t2);

		when(transactionService.getTranscationdetails(1, "ADMIN"))
				.thenReturn(mockList);

		mockMvc.perform(get("/api/transactionHistory/1/ADMIN"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].amount").value(500.0))
				.andExpect(jsonPath("$[1].status").value("FAILED"));
	}

	@Test
	void testSaveAccount_Success() throws Exception {

		Account account = new Account();
		account.setAccount_number("ACC1001");
		account.setUser_id(1);
		account.setBalance(1000.0);

		mockMvc.perform(post("/api/account")
						.contentType(String.valueOf(MediaType.APPLICATION_JSON))
						.content(objectMapper.writeValueAsString(account)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.account_number").value("ACC1001"))
				.andExpect(jsonPath("$.balance").value(1000.0));

		verify(transferService).saveAccount(any(Account.class));
	}

	@Test
	void testGetAccountList_Success() throws Exception {

		Account a1 = new Account();
		a1.setAccount_number("ACC1001");
		a1.setUser_id(1);
		a1.setBalance(1000.0);

		Account a2 = new Account();
		a2.setAccount_number("ACC1002");
		a2.setUser_id(2);
		a2.setBalance(2000.0);

		List<Account> mockAccounts = Arrays.asList(a1, a2);

		when(transferService.getAccountDetails()).thenReturn(mockAccounts);

		mockMvc.perform(get("/api/accountDetails"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].account_number").value("ACC1001"))
				.andExpect(jsonPath("$[1].user_id").value(2));
	}

}
