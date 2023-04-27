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
	@GetMapping("")
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
	@GetMapping("/trades")
	@ResponseBody
	public List<TradeDto> tradeList(HttpSession session, Model model, @RequestParam(value = "list-type") String listType) {
		String memberId = (String)session.getAttribute("memberId");
		log.info("tradeList id check : " + memberId);
		log.info("tradeList listType : " + listType);
		
		return tradeService.findTradeListByMemberId(memberId, listType);
	}
		
	@PutMapping("/talents/{talent-no}")
	@ResponseBody
	public String tradesByTalentNo(@PathVariable(name = "talent-no") String talentNo, 
								   @RequestBody TalentDto talentDto) {
		log.info("tradeModifyStatusByTalentNo talentNo, status : " + talentNo + ", " + talentDto);
		String resultMessage = tradeService.modifyTalentByTalentNo(talentNo, talentDto);
		log.info("tradeModifyStatusByTalentNo resultMessage : " + resultMessage);
		
		return resultMessage; 
	}
	
	@PostMapping("/exchanges/{talent-no}")
	@ResponseBody
	public String exchangeAddByTalentNo(@PathVariable(name = "talent-no") String talentNo) {
		log.info("exchangeAddByTalentNo purchaseNo : " + talentNo);
		TalentDto talentDto = tradeService.findTalentByTalentNo(talentNo);
		String resultMessage = tradeService.addExchangeByTalentNo(talentDto);
		log.info("exchangeAddByTalentNo resultMessage : " + resultMessage);
		
		return resultMessage;
	}
	
	@PutMapping("/exchanges/{talent-no}/status")
	@ResponseBody
	public String exchangeStatusModifyByTalentNo(@PathVariable(name = "talent-no") String talentNo) {
		log.info("exchangeStatusModifyByTalentNo talentNo : " + talentNo);
		String resultMessage = tradeService.modifyExchangeStatusByTalentNo(talentNo);
		log.info("exchangeStatusModifyByTalentNo resultMessage : " + resultMessage);
		
		return resultMessage;
	}

	@PostMapping("/refund")
	@ResponseBody
	public String refundAdd(HttpSession session, @RequestBody TradeDto tradeDto) {
		log.info("refundAdd tradeListDto : " + tradeDto.toString());
		String resultMessage = tradeService.addRefund(session, tradeDto);
		log.info("refundAdd resultMessage : " + resultMessage);
		
		return resultMessage;
	}
	
	@DeleteMapping("/refunds/{refund-purchase-no}")
	@ResponseBody
	public String refundRemoveByrefundPurchaseNo(@PathVariable(name = "refund-purchase-no") String refundPurchaseNo) {
		log.info("refundRemoveByrefundPurchaseNo purchaseNo : " + refundPurchaseNo);
		String resultMessage = tradeService.removeRefundByrefundPurchaseNo(refundPurchaseNo);
		log.info("refundRemoveByrefundPurchaseNo resultMessage : " + resultMessage);
		
		return resultMessage;
		
	}
	
	/**
	 * TEST PAGE
	 */
	@GetMapping("/test")
	public String test() {
		
		return "trade/adminTest";
		
	}

	
}//end class

//이전 코드

//@PostMapping("/trades/tradeSearch")
//@ResponseBody
//public CompletableFuture<ResponseEntity<List<TradeDto>>> changedTradeListByTradeSearchDto(@RequestBody TradeSearchDto tradeSearchDto){
//	log.info("userId, standardTime : {}", tradeSearchDto.toString());
//	CompletableFuture<List<TradeDto>> future = tradeService.tradeChangeListener(tradeSearchDto);
//    return future.thenApply(tradeList -> {
//        if (tradeList != null) {
//            return ResponseEntity.ok(tradeList);
//        } else {
//            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(null);
//        }
//    });
//}

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
		

	



