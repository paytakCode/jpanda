package com.kakao.jPanda.jst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
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
	
	
	/**
	 * @return trade페이지 url 매핑
	 */
	@GetMapping("/trade")
	public String trade() {
		return "trade/trade";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "memberId") String memberId,
						HttpServletRequest request) {
		HttpSession session = request.getSession();
		log.info("TradeController memberId check : " + memberId);
		session.setAttribute("memberId", memberId);
		return "trade/trade";
	}
	
	/**
	 * ID를 통한 판매리스트 조회
	 * @param memberId
	 * @param model
	 * @return 거래관리 페이지
	 */
	@GetMapping("/trade/sellList")
	public String tradeSellList(HttpSession session, Model model) {
		String memberId = (String)session.getAttribute("memberId");
		log.info("tradeSell id check : " + memberId);
		List<SellListDto> sellList = tradeService.getSellList(memberId);
		model.addAttribute("listType", "sell");
		model.addAttribute("list", sellList);
		return "trade/trade";
	}
	
	/**
	 * ID를 통한 구매리스트 조회
	 * @param memberId
	 * @param model
	 * @return 거래관리 페이지
	 */
	@GetMapping("/trade/buyList")
	public String tradeBuyList(HttpSession session, Model model) {
		String memberId = (String)session.getAttribute("memberId");
		List<BuyListDto> buyList = tradeService.getBuyList(memberId);
		model.addAttribute("listType", "buy");
		model.addAttribute("list", buyList);
		return "trade/trade";
	}
	
	
	@GetMapping("/trade/refundList")
	public String tradeRegfundList(HttpSession session, Model model) {
		String memberId = (String)session.getAttribute("memberId");
		List<RefundListDto> refundList = tradeService.getRefundList(memberId);
		model.addAttribute("listType", "refund");
		model.addAttribute("list", refundList);
		
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

}//end class
