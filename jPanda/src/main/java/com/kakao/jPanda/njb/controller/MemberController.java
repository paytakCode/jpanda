package com.kakao.jPanda.njb.controller;



import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

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

import com.kakao.jPanda.common.annotation.NoLoginCheck;
import com.kakao.jPanda.njb.dao.MemberDao;
import com.kakao.jPanda.njb.domain.BankDto;
import com.kakao.jPanda.njb.domain.EmailVerifDto;
import com.kakao.jPanda.njb.domain.MemberDto;
import com.kakao.jPanda.njb.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
	
	private final MemberService memberservice;
	private final MemberDao memberDao;
	
	@NoLoginCheck
    @GetMapping("/signup")
    public String signupForm(Model model) {
 	    List<BankDto> findBankList = memberservice.selectBankList();
 	    model.addAttribute("bankList", findBankList);
    	return "njb/signup";
    }

	@NoLoginCheck
	@PostMapping("/members/signup")
	public String join(MemberDto memberInfo) {
	    boolean isJoinSuccessful = memberservice.joinMember(memberInfo);

	    if (isJoinSuccessful) {
	        // 회원가입 성공
	    	System.out.println(isJoinSuccessful);
	        return "redirect:/login";
	    } else {
	        // 회원가입 실패
	    	System.out.println(isJoinSuccessful);
	        return "redirect:/signup";
	    }
	}
	@NoLoginCheck
    @GetMapping("/members/id/id")
    @ResponseBody
    public Map<String, String> checkId(@RequestParam String memberId) {
        Map<String, String> resultMap = new HashMap<>();
        System.out.println("checkId Start...");
        try {
            int count = memberDao.checkId(memberId);

            if (count == 0) {
                resultMap.put("result", "success");
            } else {
                resultMap.put("result", "fail");
            }
        } catch (Exception e) {
            resultMap.put("result", "error");
            resultMap.put("errorMessage", e.getMessage());
        }
        return resultMap;
    }

	@NoLoginCheck
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
    
    @NoLoginCheck
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
    @NoLoginCheck
    @PostMapping("/sendVerificationCode")
    @ResponseBody
    public ResponseEntity<String> sendVerificationCode(@RequestParam("email") String email) {
        String verificationCode = generateVerificationCode();
        System.out.println(verificationCode);
        EmailVerifDto emailVerifDto = new EmailVerifDto();
        emailVerifDto.setEmail(email);
        emailVerifDto.setVerifCode(verificationCode);
        
        memberservice.insertVerificationCode(emailVerifDto);
        
        String to =email;
        String subject = "인증번호";
        String body = "인증번호 : " + verificationCode;
        sendEmail(to, subject, body);
        
        return ResponseEntity.ok("success");

        
        
    }
        
    private String generateVerificationCode() {
        int length = 5; // 생성할 랜덤 문자열의 길이
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
	
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

    @NoLoginCheck
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
        return "Logout success";
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
        System.out.println(memberId);
        if (memberId != null) {
            // 로그인이 되어있는 경우
            MemberDto memberInfo = memberservice.findMember(memberId);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("editMode", false);
            return "njb/mypage";
        }
        //로그인 안되어있으면 로그인페이지로 이동
        return "njb/login";
    }
    
    @PostMapping("/updateMember")
    public String editMemberInfo(@ModelAttribute("memberInfo") MemberDto memberInfo, HttpSession session) {
    	
    	session.setAttribute("memberInfo", memberInfo);
    	System.out.println("memberInfo " + memberInfo);
    	memberservice.editMemberInfo(memberInfo);
        return "redirect:/mypage";
    }
    @NoLoginCheck
    @GetMapping("/verifyCode")
    @ResponseBody
    public ResponseEntity<String> verifyCode(EmailVerifDto emailVerifDto) {
    	
    	EmailVerifDto foundedEmailVerif = memberservice.findEmailVerif(emailVerifDto);

        if (foundedEmailVerif != null) {
            return ResponseEntity.ok("일치");
        } else {
            return ResponseEntity.ok("불일치");
        }
    	
    }
  }





