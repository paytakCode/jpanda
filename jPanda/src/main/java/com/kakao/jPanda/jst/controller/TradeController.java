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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeListDto;
import com.kakao.jPanda.jst.service.TradeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
		return "redirect:/trade";
	}
	
	/**
	 * Model에 statDto setting
	 * @param session
	 * @param model
	 * @return trade페이지 url 매핑
	 */
	@GetMapping("/trade")
	public String trade(HttpSession session, Model model) {
		if (session.getAttribute("memberId") == null) {
			return "redirect:/";
		}
		String memberId = (String)session.getAttribute("memberId");
		log.info("trade id check : " + memberId);
		List<StatDto> statList = tradeService.getStatList(memberId);
		model.addAttribute("statList", statList);
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
	 * trade페이지에서의 ajax요청 처리
	 * status에 따른 다른 service 호출
	 * @param session
	 * @param model
	 * @param status
	 * @return 
	 */
	@PostMapping("/trade/list")
	@ResponseBody
	public List<?> tradeList(HttpSession session, Model model, @RequestBody TradeListDto tradeListDto) {
		List<?> list = null;
		String memberId = (String)session.getAttribute("memberId");
		log.info("tradeSell id check : " + memberId);
		log.info("TradeController tradeList status listDto.getListType() : " + tradeListDto.getListType());
		list = tradeService.getTradeList(memberId, tradeListDto);
		
		return list;
	}
	
	@PutMapping("/trade/talent/status/{talentNo}")
	@ResponseBody
	public String tradeEndSell(@PathVariable String talentNo) {
		log.info("talentNo : " + talentNo);
		int result = tradeService.endSell(talentNo);
		
		if (result > 0) {
			return "success endSell talentNo : " + talentNo;
		} else {
			return "fail endSell talentNo : " + talentNo;
		}
		
	}
	
	@DeleteMapping("/trade/refund/{purchaseNo}")
	@ResponseBody
	public String tradeCancleRefund(@PathVariable String purchaseNo) {
		log.info("purchaseNo : " + purchaseNo);
		int result = tradeService.cancleRefund(purchaseNo);
		
		if (result > 0) {
			return "success cancleRefund purchaseNo : " + purchaseNo;
		} else {
			return "fail cancleRefund purchaseNo : " + purchaseNo;
		}
	}
	
	@PostMapping("/trade/exchange/{talentNo}")
	@ResponseBody
	public String tradeSubmitExchange(@PathVariable String talentNo) {
		log.info("purchaseNo : " + talentNo);
		TalentDto talentDto = tradeService.getTalentByTalentNo(talentNo);
		
		if ( talentDto != null) {
			int submitResult = tradeService.submitExchange(talentDto);
			if (submitResult > 0) {
				return "success submitResult : " + submitResult;
			} else {
				return "fail submitResult : " + submitResult;
			}
			
		} else {
			return "fail submitExchange talentDto : null";
		}
	}
	
	@PostMapping("/trade/refund")
	@ResponseBody
	public String tradeTalentRefund(HttpSession session, @RequestBody TradeListDto tradeListDto) {
		tradeListDto.setBuyerId((String)session.getAttribute("memberId"));
		log.info("tradeTalentRefund talentNo, buyerId : " + tradeListDto.getTalentNo() + ", " + tradeListDto.getBuyerId() + ", " + tradeListDto.getRefundBamboo());
		tradeService.submitTalentRefund(tradeListDto);
		
		return "success";
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
		

	



