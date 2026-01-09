package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.library.entity.Member;
import com.example.library.repository.MemberRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MemberService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final MemberRepository repository;
    private final String PREFIX = "member:";

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Member getMemberById(String id) {
        // 1. Try Redis
        Member member = (Member) redisTemplate.opsForValue().get(PREFIX + id);
        if (member != null) {
            System.out.println("Cache hit!");
            return member;
        }

        // 2. Cache miss → fetch from MongoDB
        System.out.println("Cache miss! Fetching from MongoDB...");
        member = repository.findById(id).orElse(null);
        if (member != null) {
            // 3. Store in Redis for future requests
            redisTemplate.opsForValue().set(PREFIX + id, member, 1, TimeUnit.HOURS);
        }
        return member;
    }

    public Member getMemberByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public Member addMember(Member member) {
        return repository.save(member);
    }

    public Member updateMember(String id, Member updatedMember) {
        return repository.findById(id)
                .map(member -> {
                    member.setName(updatedMember.getName());
                    member.setEmail(updatedMember.getEmail());
                    return repository.save(member);
                }).orElse(null);
    }

    public void deleteMember(String id) {
        repository.deleteById(id);
    }
}
