package com.kakao.jPanda.jst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.jPanda.jst.domain.BambooUseDto;
import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.MemberJoinDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TalentRefundDto;
import com.kakao.jPanda.jst.service.MemberService;
import com.kakao.jPanda.jst.service.TradeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TradeController {
	
	private TradeService tradeService;
	private MemberService memberService;
	
	@Autowired
	public TradeController(TradeService tradeService, MemberService memberService) {
		this.tradeService = tradeService;
		this.memberService = memberService;
//		dummy();
	}
	
	
	/**
	 * @return trade페이지 url 매핑
	 */
	@GetMapping("/trade")
	public String trade() {
		return "trade/trade";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "memberId") String memberId,
						Model model) {
		log.info("TradeController memberId check : " + memberId);
		model.addAttribute("memberId", memberId);
		return "trade/trade";
	}
	
	@GetMapping("/trade/sellList")
	public String tradeSell(@RequestParam(name = "memberId") String memberId,
							Model model) {
		model.addAttribute("memberId", memberId);
		log.info("tradeSell id check : " + memberId);
		List<SellListDto> sellList = tradeService.getSellList(model);
		model.addAttribute("listType", "sell");
		model.addAttribute("list", sellList);
		return "trade/trade";
	}
	
	@GetMapping("/trade/buyList")
	public String tradeBuy(@RequestParam(name = "memberId") String memberId,
						   Model model) {
		model.addAttribute("memberId", memberId);
		List<BuyListDto> buyList = tradeService.getBuyList(model);
		model.addAttribute("listType", "buy");
		model.addAttribute("list", buyList);
		return "trade/trade";
	}
	
	/**
	 * @param login정보
	 * @return login정보수집용 임시 페이지
	 */
	@GetMapping("/")
	public String loginForm(Model model) {
		log.info("loginForm called");
		return "trade/loginForm";
	}

	
	/**
	 * 
	 * @return 더미데이터 생성 메서드
	 */
	private String randomGender() {
		if (Math.random() >= 0.5) {
			 return "남";
		 } else {
			return "여";
		 }
	}
	
	private String randomTalentStatus() {
		if (Math.random() >= 0.5) {
			 return "판매중";
		 } else {
			return "판매완료";
		 }
	}
	
	private String randomTalentRefundStatus() {
		if (Math.random() >= 0.5) {
			 return "환불신청중";
		 } else {
			return "환불완료";
		 }
	}
	
	public void dummy() {
		for (int i = 0; i < 100; i++) {
			memberService.addMember(MemberJoinDto.builder()
			 .id("dummy" + i)
			 .account(((int)(Math.random() * 10000000)) + "")
			 .email((char)i + "@" + (char)i)
			 .gender(randomGender())
			 .grade("Bronze")
			 .name("Dummy")
			 .password(i + "")
			 .tel(i + "-" + i + "-" + i)
			 .build());
			
			memberService.addBamboo(BambooUseDto.builder()
									.purchaseNo(Long.parseLong(i + ""))
									.buyerId("dummy" + ((int)(Math.random()*i) + 1))
									.talentNo(Long.parseLong(((int)(Math.random()*100) + 1)+""))
									.useBamboo(Long.parseLong(((int)(Math.random()*10) + 1)+""))
									.build());

			long salePrice = (long)Math.round(i * 10 * 0.1 * ((int)(Math.random()*10)) + 1);
			memberService.addTalent(TalentDto.builder()
											 .talentNo(Long.parseLong(i + ""))
											 .upperCategoryNo(Long.parseLong(((int)(Math.random()*i) + 1) + ""))
											 .lowerCategoryNo(Long.parseLong(((int)(Math.random()*i) + 1) + ""))
											 .content(((int)(Math.random()*1000) + 1) + "개의 글자로 된 내용")
											 .mainImg("image_path : 1" + ((int)(Math.random()*10000) + 1))
											 .bamboo(Long.parseLong((i * 10) + ""))
											 .saleBamboo(salePrice)
											 .sellerId("dummy" + ((int)(Math.random()*5) + 1))
											 .status(randomTalentStatus())
											 .summary(((int)(Math.random()*100) + 1) + "개의 글자로 된 요약")
											 .title(((int)(Math.random()*30) + 1) + "개의 글자로 된 제목")
											 .viewCount(Long.parseLong((i * 200) + ""))
											 .build());
			
			memberService.addTalentRefund(TalentRefundDto.builder()
														 .purchaseNo(Long.parseLong(i+""))
														 .issue(((int)(Math.random()*300) + 1) + "개의 글자로 된 이유")
														 .refundBamboo(Long.parseLong((i * 10) + ""))
														 .status(randomTalentRefundStatus())
														 .build());
			
		}
		
		
		
		
	}
	
	
	
}//end class
