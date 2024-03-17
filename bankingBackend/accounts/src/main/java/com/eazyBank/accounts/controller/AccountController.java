package com.eazyBank.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eazyBank.accounts.model.Accounts;
import com.eazyBank.accounts.model.Customer;
import com.eazyBank.accounts.repository.AccountsRepository;
import com.eazyBank.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;

    private final CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            Accounts accounts = accountsRepository.findByCustomerId(customers.get(0).getId());
            if (accounts != null) {
                return accounts;
            }
        }
        return null;
    }
    @GetMapping("/customer")
    public Customer getCustomerByEmail(@RequestParam String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        if (!customers.isEmpty()) {
            return customers.get(0);
        }
        return null; 
    }
}
