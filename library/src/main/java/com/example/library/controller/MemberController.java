package com.example.library.controller;

import org.springframework.web.bind.annotation.*;

import com.example.library.entity.Member;
import com.example.library.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // GET all members
    @GetMapping
    public List<Member> getAllMembers() {
        return service.getAllMembers();
    }

    // GET member by ID
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return service.getMemberById(id);
    }

    // POST create new member
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return service.addMember(member);
    }

    // PUT update member by ID
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable String id, @RequestBody Member member) {
        return service.updateMember(id, member);
    }

    // DELETE member by ID
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id) {
        service.deleteMember(id);
    }
}
