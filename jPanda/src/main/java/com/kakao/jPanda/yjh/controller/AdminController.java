package com.kakao.jPanda.yjh.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;
import com.kakao.jPanda.yjh.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 * 	기능에 참조되는 DTO별로 //notice, //talent 등등 으로 구분지어 놓았습니다.
 * 	form -> ajax로 전송방식 통합 후 변경 예정입니다
 * 	전체적으로 통합한 뒤에 단순 insert, update, delete작업에 대한 return 재작업 예정입니다
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {
	private final AdminService adminService;
	
	//home
	@GetMapping(value = "")
	public String home() {
		
		return "yjh/home";
	}
	
	//notice
	@GetMapping(value = "/notices")
	public String noticeList(Model model) {
		log.info("Notice Controller noticeList() start");
		List<NoticeDto> noticeList = adminService.findNotice();
		model.addAttribute("noticeList", noticeList);
		
		return "yjh/notice";
	}
	
	/*
	 *	noticeNo의 세부항목을  출력하는 기능
	 *	해당 화면에는 수정 버튼이 존재, 삭제 버튼은 이야기 후 결정
	 *	시퀀스 생성전이라 필수 입력란에 noticeNo존재, 통합후 시퀀스 생성하면 수정 예정
	 */
	@GetMapping(value = "/notices/{noticeNo}")
	public String noticeDetails(@PathVariable String noticeNo, Model model) {
		log.info("Notice Controller noticeDetails() start");
		log.info("noticeNo : "+noticeNo);
		NoticeDto notice = adminService.findNoticeByNoticeNo(noticeNo);	
		model.addAttribute("notice", notice);
		
		return "yjh/notice-content";
	}
	
	/*
	 * 	공지사항 작성을 위한 화면으로 이동
	 */
	@GetMapping(value = "/notice-form")
	public String noticeForm() {
		
		return "yjh/notice-form";
	}
	
	/*
	 * 	공지사항을 입력하는 기능
	 * 	시퀀스 생성 전이라 물리적으로 입력하게 구현, 통합 후 시퀀스 작업 후에 수정 예정
	 */
	@ResponseBody
	@PostMapping(value = "/notice")
	public String noticeAdd(NoticeDto notice) {
		log.info("Notice Controller noticeAdd() start");
		String resultStr = adminService.addNotice(notice);
		
		return resultStr;
	}
	
	/*
	 * 	noticeDetail에서 수정 버튼을 눌러 수정할 페이지에 보내는 기능
	 */
	@GetMapping(value = "/notice/{noticeNo}/modify-form")
	public String noticeModifyForm(@PathVariable String noticeNo, Model model) {
		log.info("Notice Controller noticeModifyForm() start");
		log.info("noticeNo : "+noticeNo);
		NoticeDto notice = adminService.findNoticeByNoticeNo(noticeNo);
		model.addAttribute("notice", notice);
		
		return "yjh/notice-modifyFrom";
	}
	
	/*
	 * 	수정된 사항을 DTO로 받아서 수정하는 기능
	 */
	@ResponseBody
	@PutMapping(value = "/notice/{noticeNo}/modify")
	public String noticeModifyByNoticeNo(NoticeDto notice) {
		log.info("Notice Controller noticeModifyByNoticeNo() start");
		log.info("notice : "+notice.toString());
		String resultStr = adminService.modifyNotice(notice);
		
		return resultStr;
	}
	
	//exchange	
	@GetMapping(value = "/exchanges")
	public String exchangeList(Model model) {
		log.info("Exchange Controller exchangeList() start");
		List<ExchangeDto> exList = adminService.findExchange();
		model.addAttribute("exList", exList);
				
		return "yjh/exchange";
	}
	
	/*
	 * 	검토중인 환전신청 건에 대해 조회한 페이지에서 체크박스로 체크 후 버튼을 누르면 상태 업데이트가 되는 기능
	 * 	view에서 완료와 반려 버튼에 따라 각각 다른 상태가 업데이트 되게 구현
	 * 	코드를 조금 더 간결하게 통합 후 수정 예정
	 */
	@ResponseBody
	@PutMapping(value = "/exchange")
	public String exchangeModifyByExchangeNos(@RequestParam(name = "exchangeNo") String[] exchangeNoArray, @RequestParam String status) {
		log.info("Exchange Controller exchangeModifyByExchangeNos() start");
		
		for(int i = 0; i < exchangeNoArray.length; i++) {
			log.info("exchangeNoArray : "+exchangeNoArray[i]);
		}
		String returnStr = adminService.modifyExchangeStatusByExchangeNos(exchangeNoArray, status);
		
		return returnStr;
	}

	//coupon
	@GetMapping(value = "/coupons-form")
	public String couponsList(Model model) {
		log.info("Coupon Controller couponsForm() start");
		List<CouponDto> couponList = adminService.findCouponsExpired();
		
		model.addAttribute("couponList", couponList);
		
		return "yjh/createCoupon";
	}
	
	
	/*
	 * 	쿠폰 생성 페이지에서 생성하기 버튼을 누르면 쿠폰번호가 자동 생성되는 기능
	 */
	@ResponseBody
	@PostMapping(value = "/coupons/coupons-no")
	public String genetateCouponNo() {
		log.info("Coupon Controller genetateCouponNo() start");
		
		return adminService.generateCouponNo();
	}
	
	/*
	 * 	적용 버튼을 누르면 위에서 생성한 쿠폰번호가 업데이트 되는기능
	 * 	쿠폰 기간에 대한 논의가 부족해서 쿠폰 생성일과 사용기한을 우선 sysdate로 구현
	 * 	통합 후 사용기한에 대한 논의 후 관련 로직 추가 예정
	 */
	@ResponseBody
	@PostMapping(value = "/coupons/{couponNo}") 
	public Map<String, Integer> couponAdd(@PathVariable("couponNo") String couponNo, @RequestBody CouponDto couponDto) {
		log.info("Coupon Controller couponAdd() start");
		log.info("couponDto : "+couponDto.toString());
		
		Map<String, Integer> returnMap = adminService.addCoupon(couponDto);
		
		return returnMap;
	}
	
	//company-sales
	@GetMapping(value = "/company-sales")
	public String companySales() {
		
		return "yjh/company-sales";
	}
	
	/*
	 *  해당 페이지의 날짜 입력란에서 startDate와 endDate를 입력 받아 그래프api를 통해 매출을 출력하는 기능
	 */
	@ResponseBody
	@GetMapping(value = "/company-sales/years")
	public List<CompanySalesDto> companySalesListByYears(HttpServletRequest request) throws ParseException {
		log.info("Company-sales Controller companySalesListByYears() start");
		log.info("startDate : "+request.getParameter("startDate"));
		log.info("endDate : "+request.getParameter("endDate"));
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		List<CompanySalesDto> csList = adminService.findCompanySalesByStartDateAndEndDate(startDate, endDate);
		log.info("csList : "+csList.toString());
		
		return csList;
	}
	
	//talent
	@GetMapping(value = "/talents") // talents
	public String talentList(Model model) {
		log.info("Talent Controller talentList() start");
		List<TalentDto> talentList =  adminService.findTalent();
		model.addAttribute("talentList", talentList);
		
		return "yjh/talent";
	}
	
	/*
	 * 	판매등록 대기중인 건에 대해 조회한 페이지에서 체크박스로 체크 후 버튼을 누르면 상태와 게시일이 업데이트가 되는 기능
	 */
	@ResponseBody
	@PatchMapping(value = "/talents/{sellerId}")
	public String talentModifyBySellerIds(@PathVariable("sellerId") List<String> sellerId) {
		log.info("Talent Controller talentModifyBySellerIds() start");
		log.info("SellerIds : "+sellerId);
		
		return adminService.modifyTalentBySellerIds(sellerId);
	}

	//talent-refund
	@GetMapping(value = "/talent-refund")
	public String talentRefundList(Model model) {
		log.info("TalentRefund Controller talentRefundList() start");
		
		List<TalentRefundDto> refundList = adminService.findTalentRefund();
		log.info("refundList : "+refundList);
		
		model.addAttribute("refundList", refundList);
		
		return "yjh/talent-refund";
	}
	
	/*
	 * 	검토중인 불신청 건에 대해 조회한 페이지에서 체크박스로 체크 후 버튼을 누르면 상태 업데이트가 되는 기능
	 * 	view에서 완료와 반려 버튼에 따라 각각 다른 상태가 업데이트 되게 구현
	 */
	@ResponseBody
	@PatchMapping(value = "/talent-refunds/{purchaseNos}")
	public int talentRefundModifyByPurchaseNosAndStatus(@PathVariable("purchaseNos") List<String> purchaseNo, 
														@RequestBody List<TalentRefundDto> talentRefundDto)
	{
		log.info("TalentRefund Controller talentRefundModifyTosuccessByPurchaseNos() start");
		log.info("purchaseNos : "+purchaseNo);
		log.info("Dto : "+talentRefundDto.toString());
		
		int result = adminService.modifyTalentRefundByPurchaseNosAndStatus(talentRefundDto);
				
		return result;
	}

}	
