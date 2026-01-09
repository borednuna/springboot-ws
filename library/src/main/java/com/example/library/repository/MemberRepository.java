package com.example.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.library.entity.Member;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByEmail(String email); // optional helper method
}
