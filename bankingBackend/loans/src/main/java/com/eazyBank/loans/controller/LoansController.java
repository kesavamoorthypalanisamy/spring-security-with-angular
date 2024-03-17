package com.eazyBank.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eazyBank.loans.model.Customer;
import com.eazyBank.loans.model.Loans;
import com.eazyBank.loans.repository.CustomerRepository;
import com.eazyBank.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    private final CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customers.get(0).getId());
            if (loans != null ) {
                return loans;
            }
        }
        return null;
    }

}
