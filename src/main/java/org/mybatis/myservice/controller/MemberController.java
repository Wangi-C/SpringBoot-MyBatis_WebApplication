package org.mybatis.myservice.controller;

import org.mybatis.myservice.model.MemberVO;
import org.mybatis.myservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/member/form")
    public ModelAndView insertForm() {
        ModelAndView mav = new ModelAndView("insert");
        return mav;
    }

    @GetMapping("/member/form/{id}")
    public ModelAndView editForm(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("edit");
        MemberVO member = memberService.selectById(id);
        mav.addObject("member", member);

        return mav;
    }

    @GetMapping("/member/{id}")
    public ModelAndView selectById(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("view");
        MemberVO member = memberService.selectById(id);
        mav.addObject("member", member);

        System.out.println("member = " + member);

        return mav;
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

    @DeleteMapping("/member")
    public List<MemberVO> deleteMember(@RequestBody MemberVO member) {
        System.out.println(member);
//        memberService.deleteMember(member.getId());
        return memberService.selectAllMembers();
    }
}
