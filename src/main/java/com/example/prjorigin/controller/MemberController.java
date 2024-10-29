package com.example.prjorigin.controller;

import com.example.prjorigin.dto.Member;
import com.example.prjorigin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    @GetMapping("signup")
    public void signup() {

    }

    @PostMapping("signup")
    public String signupProcess(Member member, RedirectAttributes rttr) {
        service.addMember(member);
        rttr.addFlashAttribute("message", Map.of("type", "success",
                "text", "회원가입 되었습니다."));
        return "redirect:/member/list";
    }

    @GetMapping("list")
    public void list(Model model) {
        model.addAttribute("memberList", service.list());
    }

    @GetMapping("view")
    public void view(String id, Model model) {
        Member member = service.info(id);
        model.addAttribute("member", member);
    }

    @PostMapping("delete")
    public String delete(String id, String password, RedirectAttributes rttr) {
        if (service.remove(id, password)) {
            rttr.addFlashAttribute("message", Map.of("type", "dark",
                    "text", "탈퇴 하셨습니다."));
            return "redirect:/member/signup";
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "dark",
                    "text", "패스워드 가 일치 하지 않습니다.."));
            return "redirect:/member/view";
        }
    }
}