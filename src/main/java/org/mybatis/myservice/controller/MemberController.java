package org.mybatis.myservice.controller;

import org.mybatis.myservice.model.MemberVO;
import org.mybatis.myservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public ModelAndView selectListMember() {
        ModelAndView mav = new ModelAndView("list");
        List<MemberVO> allMembers = memberService.selectAllMembers();
        mav.addObject("list", allMembers);

        return mav;
    }

    @GetMapping("/member/{id}")
    public MemberVO selectById(@PathVariable int id) {
        return memberService.selectById(id);
    }

    @PostMapping("/member/new")
    public List<MemberVO> insertMember(@RequestBody MemberVO memberVO) {
        memberService.insertMember(memberVO);
        return memberService.selectAllMembers();
    }

    @PutMapping("/member/{id}")
    public List<MemberVO> updateMember(@PathVariable int id
            , @RequestBody MemberVO memberVO) {
        memberService.updateMember(id, memberVO);
        return memberService.selectAllMembers();
    }

    @DeleteMapping("/member/{id}")
    public List<MemberVO> deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
        return memberService.selectAllMembers();
    }
}
