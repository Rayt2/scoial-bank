package com.scogen.bank.service;

import com.scogen.bank.entity.CreditCard;
import com.scogen.bank.repository.CreditCardRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardServiceTest {

    @InjectMocks
    CreditCardService creditCardService;

    @Mock
    CreditCardRepository creditCardRepository;

    @Mock
    EmailSenderService emailSenderService;

    @Test
    public void applyCreditCardTest() {
        creditCardService.applyCreditCard(new CreditCard());
    }
    @Test
    public void getAllCreditCardTest(){
        List<CreditCard> creditCardList = Arrays.asList(new CreditCard(1,"Titu","Swain","TestCard","ee@gmail.com","123","Male","test"));
        creditCardService.getAllCreditCard();
        assertEquals(creditCardService.getAllCreditCard(), creditCardList);
    }
}
