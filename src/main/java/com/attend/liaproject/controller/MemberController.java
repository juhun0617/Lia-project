package com.attend.liaproject.controller;

import com.attend.liaproject.domain.Admin;
import com.attend.liaproject.entity.Member;
import com.attend.liaproject.service.MemberService;
import com.attend.liaproject.service.AttendService;
import com.attend.liaproject.web.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
/*컨트롤러 어노테이션 선언*/


public class MemberController {

    @Autowired /*필요한 의존객체의 "타입"에 해당하는 @Bean을 찾아 주입해줌*/
    private MemberService memberService; /*멤버서비스 클래스를 호출한다*/

    @Autowired
    private AttendService attendService;

    @GetMapping("/")/* "/"으로 접속하였을때  */
    public String main() {
        return "main";
    } /* "main.html"이라는 파일로 리다이렉트 시켜준다 */

    @GetMapping("/teacher")
    public String home(@SessionAttribute(name = SessionConstants.LOGIN_ADMIN, required = false) Admin loginAdmin, Model model) {
        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginAdmin == null) {
            return "home";
        }

        // 세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("admin", loginAdmin);

        return "teacherselect";
    }


    @GetMapping("/teacher/select")
    public String teacherselect() {
        return "teacherselect";
    }

    @GetMapping("/teacher/member/registration")
    public String registration() {
        return "MemberWrite";
    }

    @PostMapping("/teacher/member/registrationpro")
    public String memberRegistrationPro(Member member) {
        memberService.registration(member);
        return "memberRegistrationPro";
    }

    @GetMapping("/teacher/member/list")
    public String memberList(Model model) {
        model.addAttribute("list", memberService.memberList());
        return "attendlist";
    }


    @GetMapping("/teacher/member/delete")
    public String memberdelete(Model model) {
        model.addAttribute("list", memberService.memberList());
        return "memberdelete";
    }

    @PostMapping("/teacher/member/deletepro")
    public String memberdeletepro(String name) {
        memberService.memberdelete(name);


        return "memberdeletepro";
    }

    @GetMapping("/parents/select")
    public String parentsselect() {
        return "parentsSelect";
    }



    @PostMapping("/parents/mychild") //오타문제 해결//
    public String findmychild(String name, String birth, Model model) {
        model.addAttribute("list", attendService.findMyChild(name, birth));
        return "mychild";
    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public String nullPointerExceptionCatch(Exception exception) {
        return "생년월일과 이름이 비어있습니다.";
    }
}
