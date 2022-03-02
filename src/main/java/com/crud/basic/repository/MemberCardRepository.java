package com.crud.basic.repository;

import com.crud.basic.entity.MemberCard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCardRepository extends JpaRepository<MemberCard,Long>{
    
}
