package com.scogen.bank;

import com.scogen.bank.constants.AppConstants;
import com.scogen.bank.entity.CreditCard;
import com.scogen.bank.entity.User;
import com.scogen.bank.repository.CreditCardRepository;
import com.scogen.bank.repository.UserRepository;
import com.scogen.bank.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ScoGenBankApplication {
    @Autowired
    private UserRepository repository;

    @Autowired
    private CreditCardRepository creditCardRepository;
    private String firstName;
    private String lastName;
    private String cardName;
    private String email;
    private String phone;
    private String gender;
    @Autowired
    private EmailSenderService senderService;
    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(100, "admin", "admin", "swainp273@gmail.com", AppConstants.AD),
                new User(101, "user", "user", "swainp273@gmail.com", AppConstants.RT),
                new User(102, "user1", "user1", "swainp273@gmail.com", AppConstants.RT),
                new User(103, "user2", "user2", "swainp273@gmail.com", AppConstants.RT),
                new User(104, "user3", "user3", "swainp273@gmail.com", AppConstants.RT)
        ).collect(Collectors.toList());
        List<CreditCard> creditCards = Stream.of(
                new CreditCard(100,"Pritranjan","Swain","Business","swainp273@gmail.com","987678987","Male","approve"),
                new CreditCard(101,"Titu","Swain","Student","swainp273@gmail.com","987678987","Male","approve"),
                new CreditCard(102,"Bitu","Swain","co-operate","swainp273@gmail.com","987678987","Male","pending"),
                new CreditCard(103,"Mamali","Swain","Reward","swainp273@gmail.com","987678987","Male","pending"),
                new CreditCard(104,"Chiku","Swain","Tour","swainp273@gmail.com","987678987","Male","pending"),
                new CreditCard(105,"Sam","Swain","Travel","swainp273@gmail.com","987678987","Male","cancel")

        ).collect(Collectors.toList());
        repository.saveAll(users);
        creditCardRepository.saveAll(creditCards);
    }

    public static void main(String[] args) {
        SpringApplication.run(ScoGenBankApplication.class, args);
    }


  /*  @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {
        senderService.sendSimpleEmail("swainpri447@gmail.com",
                "This is email body",
                "This is email subject");

    }*/
}
