package com.crud.basic.controller;

import java.util.List;
import java.util.Optional;

import com.crud.basic.entity.MemberCard;
import com.crud.basic.repository.MemberCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
@Slf4j

@RestController
@RequestMapping("/api/v1/cards")
public class MemberCardController {
    
    @Autowired
    MemberCardRepository memberCardRepository;
    
    @PostMapping
    private ResponseEntity<MemberCard> createMemberCard(@RequestBody MemberCard memberCard){
        try{
            MemberCard newMemberCard = memberCardRepository.save(memberCard);
            return new ResponseEntity<>(newMemberCard, HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    private ResponseEntity<MemberCard> updateMemberCard(@RequestBody MemberCard memberCard){
        try{
            MemberCard newMemberCard = memberCardRepository.save(memberCard);
            return new ResponseEntity<>(newMemberCard, HttpStatus.OK);
        } catch (Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    private ResponseEntity<List<MemberCard>> getAllMemberCards(MemberCard memberCard){
        try{
            List<MemberCard> memberCards = memberCardRepository.findAll();
            if(memberCards == null || memberCards.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }
            return new ResponseEntity<>(memberCards, HttpStatus.OK);
        } catch (Exception e){
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/card")
    private ResponseEntity<Optional<MemberCard>> getOneMemberCard(@RequestParam Long id){
        try{
            Optional<MemberCard> memberCard = memberCardRepository.findById(id);
            if(memberCard == null || memberCard.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }
            return new ResponseEntity<>(memberCard, HttpStatus.OK);
        } catch (Exception e){
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteMemberCard(@PathVariable String id){
        MemberCard memberCard = memberCardRepository.getById(Long.valueOf(id));
        if(memberCard == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
            memberCardRepository.deleteById(Long.valueOf(id));
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
