package com.Design;

import java.util.List;

import com.Data.Account;
import com.Data.Alert;
import com.Data.Transaction;
import com.Data.User;

public interface FraudRepository {
	 int logic1_save(User u);
	 int logic2_login(User u);
	 int  logic3_account(Account a);
	 int logic4_save(Transaction t);
	 
	 int logic5_saveHistory(Transaction t);

	 List<Transaction> logic6_viewHistory(String accountNumber);
	 
	 int logic7_saveAlert(Alert a);
	 
	 List<Alert> logic8_viewAlerts(String accountNumber);

	}

