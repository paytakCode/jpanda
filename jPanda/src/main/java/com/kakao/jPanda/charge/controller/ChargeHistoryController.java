package com.kakao.jPanda.charge.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.charge.domain.PaginationDto;
import com.kakao.jPanda.charge.service.ChargeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/charge-history")		
public class ChargeHistoryController {

	private final ChargeService chargeService;
	
	@Autowired
	public ChargeHistoryController(ChargeService chargeService) {
		this.chargeService = chargeService;
	}
	
	
	/*
	 * 결제내역페이지
	 * 총 충전횟수를 전달
	 * Model	총 충전횟수 -> totalChargeCnt 전달
	 * @param	Session, model
	 * @return	charge/charge-history
	 */
	@GetMapping(value = "")
	public String chargeHistory(HttpSession session, Model model) {
		log.info("[chargeHistory] Start...");
		
		String chargerId = (String) session.getAttribute("memberId");
		log.info("[chargeHistory] chargerId -> {}", chargerId);
		
		int totalChargeCnt = chargeService.totalChargeCntByChargerId(chargerId);
		log.info("[chargeHistory] totalChargeCntByChargerId -> {}", totalChargeCnt);
		
		model.addAttribute("totalChargeCnt", totalChargeCnt);
		 
		return "charge/charge-history";
	}
	
	/*
	 * 페이징 처리된 결제내역 List를 Map형식으로 리턴하여 ajax처리
	 * 결제내역을 pagination을 통해 값을 할당받은 후 chargeHistoryMapByPagination에 저장 후 리턴
	 * @param	Session, pagination
	 * @return	chargeHistoryMapByPagination
	 */
	@ResponseBody
	@GetMapping(value = "/history-list")
	public Map<String, Object> chargeHistoryByPagination(PaginationDto paginationDto, HttpSession session) {
		log.info("[chargeHistoryByPagination] start");
		log.info("[chargeHistoryByPagination] pagination -> {}", paginationDto);
		
		String chargerId = (String) session.getAttribute("memberId");
		paginationDto.setChargerId(chargerId);
		log.info("[chargeHistoryByPagination] chargerId -> {}", chargerId);
		Map<String, Object> chargeHistoryMapByPagination = chargeService.findchargeHistoryMapByPagination(paginationDto);
		log.info("[chargeHistoryByPagination] returnMap -> {} ", chargeHistoryMapByPagination);
		log.info("[chargeHistoryByPagination] returnMap.size() -> {}", chargeHistoryMapByPagination.size());
		
		return chargeHistoryMapByPagination;
	}
	
}
