package com.kakao.jPanda.njb.controller;



import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kakao.jPanda.njb.dao.MemberDao;
import com.kakao.jPanda.njb.domain.BankDto;
import com.kakao.jPanda.njb.domain.MemberDto;
import com.kakao.jPanda.njb.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
	
	private final MemberService memberservice;
	private final MemberDao memberDao;
	

    @GetMapping("/signup")
    public String signupForm(Model model) {
    	System.out.println("signup Start...");
 	    List<BankDto> findBankList = memberservice.selectBankList();
 	    System.out.println("findBankList.size() --> "+ findBankList.size());
 	    model.addAttribute("bankList", findBankList);
    	return "njb/signup";
    }
    
    @PostMapping("/members/signup")
    public String join(MemberDto memberInfo) {
    	System.out.println("/members/signup start...");
    	memberservice.joinMember(memberInfo);
    	return "njb/login";	
    }
    
    @GetMapping("/members/id/id")
    @ResponseBody
    public Map<String, String> checkId(@RequestParam String memberId) {
        Map<String, String> resultMap = new HashMap<>();
        System.out.println("checkId Start...");
        try {
            // 아이디 중복체크를 위해 DAO 호출
            int count = memberDao.checkId(memberId);

            if (count == 0) {
                // 아이디가 중복되지 않은 경우
                resultMap.put("result", "success");
            } else {
                // 아이디가 중복된 경우
                resultMap.put("result", "fail");
            }
        } catch (Exception e) {
            // 예외 발생 시 에러 처리
            resultMap.put("result", "error");
            resultMap.put("errorMessage", e.getMessage());
        }
        return resultMap;
    }


    @GetMapping("/findid")
    public ResponseEntity<String> findIdByNameAndEmail(@RequestParam String name, @RequestParam String email) {
    	System.out.println("/findid start....");
        String memberId = memberservice.findIdByNameAndEmail(name, email);
        System.out.println(memberId);
        if(memberId == null) {
            return new ResponseEntity<String>("해당하는 정보가 없습니다.", HttpStatus.OK);
        } else {
        	String hiddenId = memberId.substring(0, memberId.length()-3) + "***"; // 마지막 3글자를 ***로 대체
            return new ResponseEntity<String>("아이디 : "+ "[ "+hiddenId+" ]" , HttpStatus.OK);
        }
    }

    @Autowired
    private JavaMailSender mailSender;
  
    @GetMapping("/findpw")
    @ResponseBody
    public ResponseEntity<String> findPwByIdAndEmail(@RequestParam String memberId, @RequestParam String email) {
        String pw = memberservice.findPwByIdAndEmail(memberId, email);
        System.out.println(pw);
        if(pw == null || pw.isEmpty()) {
            return new ResponseEntity<String>("해당하는 정보가 없습니다.", HttpStatus.OK);
        } else {
        	System.out.println(pw);
            String newPw = generateNewPassword();
            memberservice.updatePasswordById(memberId, newPw);
            String to = email; // 수신자 이메일 주소
            String subject = "비밀번호 찾기 결과"; // 이메일 제목
            String body = "비밀번호 : " + "[ " + newPw + " ]"; // 이메일 본문
            sendEmail(to, subject, body); // 이메일 발송
            return new ResponseEntity<String>("비밀번호를 초기화 했습니다. 이메일을 확인해주세요.", HttpStatus.OK);
        }
    }
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    public String generateNewPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;

        SecureRandom random = new SecureRandom();
        char[] password = new char[random.nextInt(8) + 1];
        for (int i = 0; i < password.length; i++) {
            password[i] = alphanum.charAt(random.nextInt(alphanum.length()));
        }
        return new String(password);
    }

    
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session,RedirectAttributes redirectAttributes) {
    	boolean loginResult = memberservice.login(memberDto);
    	if(loginResult) {
    		session.setAttribute("memberId", memberDto.getMemberId());
    		String memberId = (String) session.getAttribute("memberId");   		
            MemberDto member = memberservice.findMember(memberId);
            System.out.println(member);
            if(memberId.equals("admin")) {
            	return "redirect:/admin";
            } 
           
            redirectAttributes.addFlashAttribute("memberInfo", member);
    		return "redirect:/main";
    	}else {
    		redirectAttributes.addFlashAttribute("alertMsg", "로그인 할 수 없습니다.");
    		return "redirect:/login";
    	}
    }
    
    @ResponseBody
    @DeleteMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout Success";
    }
   
    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(HttpSession session, @RequestParam String password) {
        String memberId = (String) session.getAttribute("memberId");
        boolean isWithdrawalSuccessful = memberservice.withdrawal(memberId, password);

        if (isWithdrawalSuccessful) {
            session.invalidate();
            return ResponseEntity.ok("탈되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호를 확인해주세요.");
        }
    }

    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        if (memberId != null) {
            // 로그인이 되어있는 경우
            MemberDto memberInfo = memberservice.findMember(memberId);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("editMode", false);
        }
        return "njb/mypage";
    }
    @PostMapping("/updateMember")
    public String editMemberInfo(@ModelAttribute("memberInfo") MemberDto memberInfo, HttpSession session) {
        
    	memberservice.editMemberInfo(memberInfo);
        session.setAttribute("memberInfo", memberInfo);
        return "redirect:/mypage";
    }

  }



