package com.eazyBank.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eazyBank.accounts.model.AccountTransactions;
import com.eazyBank.accounts.model.Customer;
import com.eazyBank.accounts.repository.AccountTransactionsRepository;
import com.eazyBank.accounts.repository.CustomerRepository;
import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                    findByCustomerIdOrderByTransactionDtDesc(customers.get(0).getId());
            if (accountTransactions != null ) {
                return accountTransactions;
            }
        }
        return null;
    }
}
