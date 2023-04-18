package com.kakao.jPanda.njb.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.njb.dao.CheckIdDao;
import com.kakao.jPanda.njb.domain.Bank;
import com.kakao.jPanda.njb.domain.JoinDto;
import com.kakao.jPanda.njb.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
	
	private final MemberService memberservice;
	private final CheckIdDao checkIdDao;
	
    @GetMapping("/join")
    public String joinForm(Model model) {
    	System.out.println("join Start...");
 	    List<Bank> bankList = memberservice.getBankList();
 	    System.out.println(bankList.size());
 	    model.addAttribute("bankList", bankList);
    	return "njb/join";
    }
    @PostMapping("/join")
    public String join(JoinDto memberInfo) {
    	System.out.println("PostJoin start...");
    	System.out.println(memberInfo.getCode());
    	memberservice.joinMember(memberInfo);
    	return "redirect:/join";	
    }
    
    
//    @ResponseBody
//    @PostMapping("/checkId/{id}")
//    public String checkId(@PathVariable String id, Model model) {
//    	System.out.println("id" + id);
//        int count = checkIdDao.checkId(id);
//        System.out.println(count);
//        if (count > 0) {
//            return "아이디가 이미 존재합니다."; 
//        } else {
//            return "사용 가능한 아이디입니다."; 
//        }
//    }
//    
    @PostMapping("/checkId")
    @ResponseBody
    public Map<String, String> checkId(@RequestParam("id") String id) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            // 아이디 중복체크를 위해 DAO 호출
            int count = checkIdDao.checkId(id);

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
    
}
