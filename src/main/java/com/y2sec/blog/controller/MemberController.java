package com.y2sec.blog.controller;


import com.y2sec.blog.domain.Member;
import com.y2sec.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "member/loginForm";
    }

    @GetMapping("/member/result")
    public String loginSuccess() {
        return "redirect:/";
    }

    @GetMapping("/member/y2sec")
    public String createMember() {
        Member member = Member.createMember("y2sec", "rkdgus@3835");
        memberService.join(member);

        return "redirect:/";
    }

}
