package com.eazyBank.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eazyBank.cards.feign.AccountsServiceClient;
import com.eazyBank.cards.model.Cards;
import com.eazyBank.cards.model.Customer;
import com.eazyBank.cards.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    private final AccountsServiceClient accountsServiceClient;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam String email) {
        Customer customer = accountsServiceClient.getCustomerByEmail(email);
        if (customer != null) {
            List<Cards> cards = cardsRepository.findByCustomerId(customer.getId());
            if (cards != null ) {
                return cards;
            }
        }
        return null;
    }

}
