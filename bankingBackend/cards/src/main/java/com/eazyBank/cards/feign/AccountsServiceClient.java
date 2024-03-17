package com.eazyBank.cards.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eazyBank.cards.model.Customer;

@FeignClient(name = "account", url = "${accounts.service.url}")
public interface AccountsServiceClient {
    @GetMapping("/customer")
    public Customer getCustomerByEmail(@RequestParam String email);
}
