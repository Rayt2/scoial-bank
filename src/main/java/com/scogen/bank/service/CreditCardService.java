package com.scogen.bank.service;


import com.scogen.bank.entity.CreditCard;
import com.scogen.bank.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EmailSenderService emailSenderService;

    public void applyCreditCard(CreditCard creditCard) {
        emailSenderService.sendSimpleEmail(creditCard.getEmail(),
                "New Credit Card " +creditCard.getCardName(),
                "New " +creditCard.getCardName() + "Application is Initiated");
        creditCardRepository.save(creditCard);
    }

    public List<CreditCard> getAllCreditCard() {
       List<CreditCard> creditCardsList = creditCardRepository.findAll();
return  creditCardsList;
    }

    public List<CreditCard> approveCreditCard(CreditCard creditCard) {
        Optional<CreditCard> approvalCreditCardDetails =creditCardRepository.findByCardNameAndEmail(creditCard.getCardName(),creditCard.getEmail());
        if(!approvalCreditCardDetails.isPresent()){
            throw  new RuntimeException("Not a valid entry");
        }else{
            approvalCreditCardDetails.get().setStatus(creditCard.getStatus());
            creditCardRepository.save(approvalCreditCardDetails.get());
            if(creditCard.getStatus().equalsIgnoreCase("approve")){
                emailSenderService.sendSimpleEmail(creditCard.getEmail(),
                        "New Credit Card " +creditCard.getCardName(),
                        "New " +creditCard.getCardName() + "Application is Approved" + "Card will deliver soon to register address");
            } else if (creditCard.getStatus().equalsIgnoreCase("cancel")) {
                emailSenderService.sendSimpleEmail(creditCard.getEmail(),
                        "New Credit Card " +creditCard.getCardName(),
                        "New " +creditCard.getCardName() + "Application is Cancel due to invalid data");
            }

            return creditCardRepository.findAll();
        }

    }
}
