package com.scogen.bank;

import com.scogen.bank.entity.User;
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
    private EmailSenderService senderService;
    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "user", "user", "swainp273@gmail.com"),
                new User(102, "user1", "user1", "swainp273@gmail.com"),
                new User(103, "user2", "user2", "swainp273@gmail.com"),
                new User(104, "user3", "user3", "swainp273@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
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
