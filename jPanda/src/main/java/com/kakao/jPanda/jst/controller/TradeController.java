package com.kakao.jPanda.jst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeDto;
import com.kakao.jPanda.jst.service.TradeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/trade")
public class TradeController {
	
	private TradeService tradeService;
	
	@Autowired
	public TradeController(TradeService tradeService) {
		this.tradeService = tradeService;
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "memberId") String memberId,
						HttpServletRequest request) {
		HttpSession session = request.getSession();
		log.info("TradeController memberId check : " + memberId);
		session.setAttribute("memberId", memberId);
		return "redirect:/trade/";
	}
	
	/**
	 * Model에 statDto setting
	 * @param session
	 * @param model
	 * @return trade페이지 url 매핑
	 */
	@GetMapping("/")
	public String statList(HttpSession session, Model model) {
		if (session.getAttribute("memberId") == null) {
			return "redirect:/trade/loginForm";
		}
		String memberId = (String)session.getAttribute("memberId");
		log.info("statList id check : " + memberId);
		List<StatDto> statList = tradeService.findStatListByMemberId(memberId);
		model.addAttribute("statList", statList);
		return "trade/trade";
	}
	
	/**
	 * @param login정보
	 * @return login정보수집용 임시 페이지
	 */
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		log.info("loginForm called");
		return "trade/loginForm";
	}
	
	/**
	 * trade페이지에서의 ajax요청 처리
	 * status에 따른 다른 service 호출
	 * @param session
	 * @param model
	 * @param status
	 * @return 
	 */
	@GetMapping("/trade-list/{listType}")
	@ResponseBody
	public List<TradeDto> tradeList(HttpSession session, Model model, @PathVariable String listType) {
		List<TradeDto> list = null;
		String memberId = (String)session.getAttribute("memberId");
		log.info("tradeList id check : " + memberId);
		log.info("tradeList listType : " + listType);
		list = tradeService.findTradeListByMemberId(memberId, listType);
		
		return list;
	}
	
	@PutMapping("/talent/status/{talentNo}")
	@ResponseBody
	public String tradeStatusModifyByTalentNo(@PathVariable String talentNo) {
		log.info("tradeModifyStatusByTalentNo talentNo : " + talentNo);
		int result = tradeService.modifyTalentStatusByTalentNo(talentNo);
		
		if (result > 0) {
			return "판매 종료 요청이 완료되었습니다.";
		} else {
			return "판매 종료 요청에 실패하였습니다.";
		}
		
	}
	
	@DeleteMapping("/refund/{refundPurchaseNo}")
	@ResponseBody
	public String refundRemoveByrefundPurchaseNo(@PathVariable String refundPurchaseNo) {
		log.info("refundRemoveByrefundPurchaseNo purchaseNo : " + refundPurchaseNo);
		int result = tradeService.removeRefundByrefundPurchaseNo(refundPurchaseNo);
		
		if (result > 0) {
			return "환불 취소 요청이 완료되었습니다.";
		} else {
			return "환불 취소 요청에 실패하였습니다.";
		}
	}
	
	@PostMapping("/exchange/{talentNo}")
	@ResponseBody
	public String exchangeAddByTalentNo(@PathVariable String talentNo) {
		log.info("exchangeAddByTalentNo purchaseNo : " + talentNo);
		TalentDto talentDto = tradeService.findTalentByTalentNo(talentNo);
		
		if ( talentDto != null) {
			int submitResult = tradeService.addExchangeByTalentNo(talentDto);
			if (submitResult > 0) {
				return "환전 신청이 완료되었습니다.";
			} else {
				return "환전 신청에 실패하였습니다.";
			}
			
		} else {
			return "fail submitExchange talentDto : null";
		}
	}
	
	@PostMapping("/refund")
	@ResponseBody
	public String refundAdd(HttpSession session, @RequestBody TradeDto tradeDto) {
		log.info("refundAdd tradeListDto : " + tradeDto.toString());
		int result = tradeService.addRefund(session, tradeDto);
		
		if (result > 0) {
			return "환불 신청이 완료되었습니다.";
		} else {
			return "환불 신청에 실패하였습니다.";
		}
		
	}
	
	
	
	
	
}//end class



//이전 코드
//		switch (status) {
//		
//			case "all":
//				list = tradeService.getAllList(memberId);
//				break;
//				
//			case "sell":
//				list = tradeService.getSellList(memberId);
//				break;
//	
//			case "buy":
//				list = tradeService.getBuyList(memberId);			
//				break;
//	
//			case "refund":
//				list = tradeService.getRefundList(memberId);
//				break;
//		}
		

	



