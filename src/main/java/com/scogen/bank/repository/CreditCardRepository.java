package com.scogen.bank.repository;

import com.scogen.bank.entity.CreditCard;
import com.scogen.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {


    Optional<CreditCard> findByCardNameAndEmail(String cardName, String email);


    Optional<List<CreditCard>> findByUserName(String userName);
}
