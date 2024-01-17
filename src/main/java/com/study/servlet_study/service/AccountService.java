package com.study.servlet_study.service;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.repository.AccountRepository;

public class AccountService {
	
	private static AccountService instance;
	private AccountRepository accountRepository;
	
	private AccountService() {		
		accountRepository = AccountRepository.getInstance();
	}
	
	public static AccountService getInstance(){
	    if(instance == null) { //처음 null일때만 생성
	        instance = new AccountService();
	    	}
	    return instance;
		}
	
	public int addAccount(Account account) {
		return 0;
	}

}
