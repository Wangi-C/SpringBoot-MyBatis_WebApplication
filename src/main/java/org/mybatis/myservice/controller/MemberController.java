package org.mybatis.myservice.controller;

import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.mybatis.myservice.model.MemberVO;
import org.mybatis.myservice.service.MemberService;
import org.mybatis.myservice.validation.InsertMemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login/form")
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }

    @PostMapping("/logInOut")
    public ModelAndView loginMember(MemberVO member, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        if (memberService.selectByName(member.getName()) != null) {
            session.setAttribute("memberName", member.getName());
            mav.setViewName("redirect:/members");
        } else {
            mav.setViewName("redirect:/login/form");
        }

        return mav;
    }

    @GetMapping("/logInOut")
    public ModelAndView logoutMember(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/login/form");
    }

    @GetMapping("/members")
    public ModelAndView selectListMember() {
        return new ModelAndView("list");
    }

    @PostMapping("/members")
    public List<MemberVO> selectMembers() {
        return memberService.selectAllMembers();
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

        return mav;
    }

    @PostMapping("/member")
    public MemberVO selectById(@RequestBody MemberVO member) {
        return memberService.selectById(member.getId());
    }

    @PostMapping("/member/new")
    public ModelAndView insertMember(MemberVO memberVO, Errors errors) {
        new InsertMemberValidator().validate(memberVO, errors);
        ModelAndView mav = new ModelAndView();
        System.out.println("memberVO = " + memberVO);

        if (errors.hasErrors()) {
            mav.addObject("errorMsg", "입력된 값이 없습니다.");
            mav.setViewName("redirect:/member/form");

            return mav;
        }

        try {
            memberService.insertMember(memberVO);
            mav.setViewName("redirect:/members");

            memberService.insertMember(memberVO);
        } catch (Exception e) {
            mav.addObject("errorMsg", "등록 실패");
            mav.setViewName("redirect:/member/form");
        }

        return mav;
    }

    @PutMapping("/member/{id}")
    public List<MemberVO> updateMember(@PathVariable int id
            , @RequestBody MemberVO memberVO) {
        memberService.updateMember(id, memberVO);
        return memberService.selectAllMembers();
    }

    @DeleteMapping("/member")
    public List<MemberVO> deleteMember(@RequestBody MemberVO member) {
        memberService.deleteMember(member.getId());
        return memberService.selectAllMembers();
    }
}
