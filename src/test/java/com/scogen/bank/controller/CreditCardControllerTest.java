package com.scogen.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scogen.bank.entity.CreditCard;
import com.scogen.bank.service.CreditCardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private CreditCardController creditCardController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(creditCardController)
                .build();
    }

    @Test
    public void getCreditCardTest() throws Exception{
        mockMvc.perform(get("/all-credit-card")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void addReportTest() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setStatus("testStatus");
        creditCard.setCardName("TestCard");
        mockMvc.perform(post("/apply-new-card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(creditCard)))
                .andExpect(status().isOk());

    }
    private static String asJsonString(Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception ex){
            throw new RuntimeException();
        }
    }
}
