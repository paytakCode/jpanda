package com.kakao.jPanda.kyg.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.service.ChargeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/charge")		//홈 http://localhost:8888/charge/
public class ChargeController {
	
	private final ChargeService chargeService;
	
	@Autowired
	public ChargeController(ChargeService chargeService) {
		this.chargeService = chargeService;
	}
	
//	private final ChargeService chargeService;
	
//	충전 메인페이지
	@GetMapping("/")
	public String chargePage() {
		return "kyg/chargePage";
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	충전 성공시 페이지	// 
	/*
	@ResponseBody
	@PostMapping("/test")
	public String test(@RequestBody Map<String, String> paymentRequestInfo) {
		log.info("paymentKey : {}, orderId : {}, amount : {}", paymentRequestInfo.get("paymentKey"), 
															   paymentRequestInfo.get("orderId"), 
														   paymentRequestInfo.get("amount"));

		HttpResponse<String> response = null;	
		HttpRequest request = HttpRequest.newBuilder()	
			    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
			    .header("Authorization", "Basic ==")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\"" + paymentRequestInfo.get("paymentKey") + "\",\"amount\":" + paymentRequestInfo.get("orderId") + ",\"orderId\":\"" + paymentRequestInfo.get("amount") + "\"}"))
			    .build();
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				log.info(response.body());
			} catch (Exception e) {
				log.info(e.getMessage());
			}
//		return "kyg/tossApiEx";		
		return response.body();		
	}
	*/
	
//	post요청을 하면 바디에 담아서 보낸다 json data는 맵으로 받을 수있다
	@ResponseBody
	@PostMapping("/test")
	public String responseToss(@RequestBody Map<String, String> paymentRequestInfomation) {
		log.info("paymentKey : {}, amount : {}, orderId, : {}", paymentRequestInfomation.get("paymentKey"),
																paymentRequestInfomation.get("amount"),
																paymentRequestInfomation.get("orderId")
																);
		
		HttpResponse<String> response = null; //이게 뭔지 알아내기
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
//			    https://developers.tosspayments.com/613834/accounts/787829/phases/test/api-keys 에서 자신들의 API 시크릿키 사용. / 시크릿키: (콜론포함) base64인코딩 후 사용
//			    .header("Authorization", "Basic SecretKey")***********************유출안되게 조심
			    .header("Authorization", "Basic SecretKey==")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\"" + paymentRequestInfomation.get("paymentKey") + 
			    													"\",\"amount\": " + paymentRequestInfomation.get("amount") + 
			    													",\"orderId\":\"" + paymentRequestInfomation.get("orderId") + 
			    													"\"}"))
			    .build();
			//HttpResponse<String> response;
			
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println(response.body());
			} catch (IOException | InterruptedException e) {
				log.error("Error occurred while sending the HTTP request to Toss Payments API.", e);
				log.error("e.getMessage --> ", e.getMessage());
			}
			
		//return 	"kyg/tossApi";
		return response.body(); //이게 뭔지 알아내기
	}
	
	
	
	
	


	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

//	충전 성공시 페이지로 이동
	@GetMapping("/test")
	public String test() {
		return "kyg/test";		
	}
	
	
	
//	밤부 충전
	@ResponseBody
	@PostMapping("/charge") // /charge로 수정하기
	public Map<String, String> chargeAdd(@RequestBody ChargeDto chargeDto, Model model) {
		
		log.info("ChargeContoller charge() Start...");
		System.out.println("ChargeController charge() Start...");
		
		int resultCharge = chargeService.addCharge(chargeDto);
		Map<String, String> resultMap = new HashMap<>();
		
		
		if(resultCharge > 0) {
			System.out.println("ChargeController charge() resultCharge 완료 -> redirect:test");
			 resultMap.put("result", "success");
			return resultMap;
		// ajax에서 거르지 못한 잘못된 충전 출력
		} else {
			System.out.println("ChargeContoller charge() resultCharge 실패 -> kyg/chargePage");
			resultMap.put("result", "fail");
			model.addAttribute("chargeFailMsg", "충전실패 다시 시도해주세요");
			return resultMap;
		}
		
	}
	
	
	// 원본
//	@PostMapping("/charge") // /charge로 수정하기
//	public String chargeAdd(ChargeDto chargeDto, Model model) {
//		
//		log.info("ChargeContoller charge() Start...");
//		System.out.println("ChargeController charge() Start...");
//		
//		int resultCharge = chargeService.addCharge(chargeDto);
//		
//		if(resultCharge > 0) {
//			System.out.println("ChargeController charge() resultCharge 완료 -> redirect:test");
//			return "redirect:test";
//		// ajax에서 거르지 못한 잘못된 충전 출력
//		} else {
//			System.out.println("ChargeContoller charge() resultCharge 실패 -> kyg/chargePage");
//			model.addAttribute("chargeFailMsg", "충전실패 다시 시도해주세요");
//			return "kyg/chargePage";
//		}
//		
//	}
	
	
//	이용 가능한 쿠폰 체크
	@GetMapping(value = "/check-available-coupon")
	@ResponseBody
	public CouponUseDto checkAvailableCoupon(CouponUseDto couponUseDto) {
		String resultStr = "0";
		CouponUseDto couponUseDto3 = new CouponUseDto();	// 쿠폰사용유무 결과값을 dto에 담아서 보냄
		log.info("ChargeContoller couponDetails() Start...");
		log.info(couponUseDto.toString());
		
		// 사용 결과만 가져옴 -> 사용 가능한 쿠폰, 사용 했던 쿠폰을 비교하여, 회원이 사용했던 이력이 있는 쿠폰의 결과를 가져와 사용가능 여부를 따짐
		// 사용 결과만 가져옴
		int resultInt = chargeService.checkAvailableCoupon(couponUseDto);
		
		// 충전 금액과 쿠폰의 금액을 차감해 실제 충전에 사용되는 금액을 구하기 위해 쿠폰의 금액을 가져옴
		// 쿠폰의 금액을 가져옴
		Long couponValue = chargeService.getAvailAmountCoupon(couponUseDto);
		
		couponUseDto3.setResult(resultInt);
		couponUseDto3.setCouponValue(couponValue);
		
		System.out.println("Controller checkAvailableCoupon resultInt->"+resultInt);
		System.out.println("Controller checkAvailableCoupon couponValue->"+couponValue);
		
		return couponUseDto3;
	}
	
	/*
	 * @GetMapping(value = "/check-available-coupon")
	 * 
	 * @ResponseBody public Map<String, Integer> checkAvailableCoupon(CouponUseDto
	 * couponUseDto) { log.info("ChargeContoller couponDetails() Start...");
	 * log.info(couponUseDto.toString()); Integer result =
	 * chargeService.checkAvailableCoupon(couponUseDto); Map<String, Integer> map =
	 * new HashMap<String, Integer>(); map.put("result", result); return map; }
	 * 
	 */
	
	// GetMapping pathvariable 생각하고 검색하기
	
	/*
	 * // 사용 가능한 쿠폰 입력 처리(중복처리 아님)
	 * 
	 * @GetMapping(value = "/checkCoupon")
	 * 
	 * @ResponseBody public Map<String, String> checkCoupon(CouponCheckDto
	 * couponCheckDto) { log.info("ChargeContoller checkCoupon() Start..."); String
	 * result = null; int getCoupon = chargeService.checkCoupon("qyyswylcav4" ,
	 * couponNo);
	 * 
	 * // 0보다 크면 사용 가능한 쿠폰 if(getCoupon > 0) { result = "YES"; }
	 * System.out.println("getCoupon -> " + getCoupon);
	 * System.out.println("result -> " + result); // 쿠폰이 있을시 YES 으로 client 로 보냄
	 * Map<String, String> map = new HashMap<String, String>(); map.put("result",
	 * result); return map; }
	 */
	
//	가지고있는 밤부의 총 합계
	
//	@GetMapping(value = "/members/#{memberId}/total-bamboo")
//	@ResponseBody
//	public int totalBamboo(@PathVariable String memberId) {
//			log.info("ChargeController totalBamboo() Start...");
//			
//			int calculateTotalBamboo = chargeService.calculateTotalBamboo(memberId);
//		
//			log.info("ChargeController totalBamboo() calculateTotalBamboo {} -> ", calculateTotalBamboo);
//		return calculateTotalBamboo;
//	}
	
	
}
	


