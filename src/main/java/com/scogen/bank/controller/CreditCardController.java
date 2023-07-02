package com.scogen.bank.controller;

import com.scogen.bank.Model.BearerToken;
import com.scogen.bank.entity.AuthRequest;
import com.scogen.bank.entity.CreditCard;
import com.scogen.bank.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;
    @PostMapping("/apply-new-card")
    public void applyCreditCard(@RequestBody CreditCard creditCard) throws Exception {
        try {
             creditCardService.applyCreditCard(creditCard);
        } catch (Exception ex) {
            throw new Exception("Error while applying new creditcard");
        }
    }

    @GetMapping("/all-credit-card")
    public List<CreditCard> getCreditCard() throws Exception {
        try{
           return creditCardService.getAllCreditCard();
        }catch (Exception ex){
            throw new Exception("Error while fetching");

        }
    }

    @PostMapping("/approve")
    public List<CreditCard> approveCreditCard(@RequestBody CreditCard creditCard) throws Exception {
        try {
           return creditCardService.approveCreditCard(creditCard);
        } catch (Exception ex) {
            throw new Exception("Error while applying new creditcard");
        }
    }
}
