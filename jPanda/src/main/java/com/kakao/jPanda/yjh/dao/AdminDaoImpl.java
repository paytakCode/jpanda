package com.kakao.jPanda.yjh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.Pagination;
import com.kakao.jPanda.yjh.domain.ReportDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {
	private final SqlSession sqlSession;
	
	//notice
	@Override
	public List<NoticeDto> selectNoticeByPagination(Pagination pagination) {
		log.info("Dao selectNoticeByPagination() start");
		List<NoticeDto> noticeList = null;
		
		try {
			noticeList = sqlSession.selectList("selectNoticeByPagination", pagination);
		} catch(Exception e) {
			log.debug("Dao selectNoticeByPagination() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return noticeList;
	}
	
	@Override
	public int insertNotice(NoticeDto notice) {
		log.info("Dao insertNotice() start");
		int result = 0;
		
		try {
			result = sqlSession.insert("insertNotice", notice);
		} catch(Exception e) {
			log.debug("Dao insertNotice() Exception : "+e.getMessage());
			e.printStackTrace();
		}
				
		return result;
	}
	
	@Override
	public NoticeDto selectNoticeByNoticeNo(Long noticeNo) {
		log.info("Dao selectNoticeByNoticeNo() start");
		NoticeDto notice = null;
		
		try {
			notice = sqlSession.selectOne("selectNoticeByNoticeNo", noticeNo);
		} catch(Exception e) {
			log.debug("Dao selectNoticeByNoticeNo() Exception : "+e.getMessage());
			e.printStackTrace();
		}
				
		return notice;
	}
	
	@Override
	public int updateNotice(NoticeDto notice) {
		log.info("Dao updateNotice() start");
		int result = 0;
		
		try {
			result = sqlSession.update("updateNotice", notice);
		} catch(Exception e) {
			log.debug("Dao updateNotice() Exception : "+e.getMessage());
			e.printStackTrace();
		}
				
		return result;
	}

	@Override
	public int selectNoticeForTotalCount() {
		log.info("Dao selectNoticeForTotalCount() start");
		int totalCount = 0;
		
		try {
			totalCount = sqlSession.selectOne("selectNoticeForTotalCount");
			log.info("totalCount : "+ totalCount);
		} catch(Exception e) {
			log.info("Pagination Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return totalCount;
	}
	
	//exchange	
	@Override
	public List<ExchangeDto> selectExchangeByPagination(Pagination pagination) {
		log.info("Dao selectExchangeByPagination() start");
		List<ExchangeDto> exList = null;
		
		try {
			exList = sqlSession.selectList("selectExchangeByPagination", pagination);
		} catch(Exception e) {
			
			log.info("Dao selectExchangeByPagination() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return exList;
	}
	
	@Override
	public int updateExchange(ExchangeDto exchange) {
		log.info("Dao updateExchange() start");
		int result = 0;
		
		try {
			result = sqlSession.update("updateExchange", exchange);
		} catch(Exception e) {
			log.debug("Dao updateExchnage() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	

	@Override
	public int selectExchangeForTotalCount() {
		log.info("Dao selectExchangeForTotalCount() start");
		int totalCount = 0;
		
		try {
			totalCount = sqlSession.selectOne("selectExchangeForTotalCount");
		} catch(Exception e) {
			log.debug("Dao selectExchangeForTotalCoun() Exception : "+e.getMessage());
			e.printStackTrace();
		}
				
		return totalCount;
	}
	
	//coupon
	@Override
	public List<CouponDto> selectCouponsByPagination(Pagination pagination) {
		log.info("Coupon Dao selectCouponsExpired() start");
		List<CouponDto> couponList = null;
		
		try {
			couponList = sqlSession.selectList("selectCouponsByPagination", pagination);
			
		} catch(Exception e) {
			log.info("Coupon Dao selectCouponsByPagination() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return couponList;
	}

	@Override
	public int selectCouponForTotalCount() {
		log.info("Dao selectCouponForTotalCount() start");
		int totalCount = 0;
		
		try {
			totalCount = sqlSession.selectOne("selectCouponForTotalCount");
		} catch(Exception e) {
			log.debug("Dao selectCouponForTotalCoun() Exception : "+e.getMessage());
			e.printStackTrace();
		}
				
		return totalCount;
	}
	
	@Override
	public List<CouponDto> selectCouponList() {
		log.info("Dao selectCouponList() start");
		List<CouponDto> couponList = null;
		
		try {
			couponList = sqlSession.selectList("selectCouponList");
		} catch(Exception e) {
			log.debug("Dao findCouponList() Exception : "+e.getMessage());
			e.printStackTrace();
		}
				
		return couponList;
	}

	
	@Override
	public int insertCoupon(CouponDto couponDto) {
		log.info("Dao insertCoupon() start");
		log.info("Dao couponDto : "+couponDto.toString());
		int result = 0;
		
		try {
			result = sqlSession.insert("insertCoupon", couponDto);
		} catch(Exception e) {
			log.debug("Dao insertCoupon() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	//company-sales
	@Override
	public List<CompanySalesDto> selectCompanySalesByYYYYDate(CompanySalesDto companySalesDto) {
		log.info("Dao selectCompanySalesByYYYYDate() start");
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanySalesByYYYYDate", companySalesDto);
		
		return csList;
	}
	
	@Override
	public List<CompanySalesDto> selectCompanySalesByMMDate(CompanySalesDto companySalesDto) {
		log.info("Dao selectCompanySalesByMMDate() start");
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanySalesByMMDate", companySalesDto);
		
		return csList;
	}
	
	@Override
	public List<CompanySalesDto> selectCompanySalesByDDDate(CompanySalesDto companySalesDto) {
		log.info("Dao selectCompanySalesByDDDate() start");
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanyByDDDate", companySalesDto);
		
		return csList;
	}
	
	//talent
	@Override
	public int selectTalentForTotalCount() {
		log.info("Dao selectTalentForTotalCount() start");
		int totalCount =  0;
		
		try {
			totalCount = sqlSession.selectOne("selectTalentForTotalCount");
		} catch(Exception e) {
			log.debug("Dao selectTalentForTotalCount() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return totalCount;
	}

	@Override
	public List<TalentDto> selectTalentByPagination(Pagination pagination) {
		log.info("Dao selectTalentByPagination() start");
		List<TalentDto> talentList = null;
		
		try {
			talentList = sqlSession.selectList("selectTalentByPagination", pagination);
		} catch(Exception e) {
			log.debug("Dao selectTalentByPagination() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return talentList;
	}


	@Override
	public int updateTalentByTalentNos(TalentDto talentDto) {
		log.info("Dao updateTalentByTalentNos() start");
		int returnValue = 0;
		
		try {
			returnValue = sqlSession.update("updateTalentByTalentNos", talentDto);
		} catch(Exception e) {
			log.debug("Dao updateTalentByTalentNos() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	@Override
	public TalentDto selectTalentByTalentNo(Long talentNo) {
		log.info("Dao selectTalentByTalentNo");
		TalentDto talentDto = null;
		
		try {
			talentDto = sqlSession.selectOne("selectTalentByTalentNo", talentNo);
		} catch(Exception e) {
			log.debug("Dao selectTalentBySellerId() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return talentDto;
	}

	//talent-refund
	@Override
	public int selectTalentRefundForTotalCount() {
		log.info("Dao selectTalentRefundForTotalCount() start");
		int totalCount = 0;
		
		try {
			totalCount = sqlSession.selectOne("selectTalentRefundForTotalCount");
		} catch(Exception e) {
			log.debug("Dao selectTalentRefundForTotalCount() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return totalCount;
	}

	@Override
	public List<TalentRefundDto> selectTalentRefunByPagination(Pagination pagination) {
		log.info("Dao selectTalentRefundByPagination() start");
		List<TalentRefundDto> refundList = null;
		
		try {
			refundList = sqlSession.selectList("selectTalentRefunByPagination", pagination);	
		} catch(Exception e) {
			log.debug("Dao selectTalentRefundByPagination() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return refundList;
	}

	@Override
	public int updateTalentRefundToSuccessByPurchaseNosAndStatus(TalentRefundDto paramDto) {
		log.info("TalentRefund Dao updateTalentRefundToSuccessByPurchaseNosAndStatus() start");
		int result = 0;
		
		try {
			result = sqlSession.update("updateTalentRefundToSuccessByPurchaseNosAndStatus", paramDto);
		} catch(Exception e) {
			log.info("TalentRefund Dao updateTalentRefundToSuccessByPurchaseNosAndStatus() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateTalentRefundToCompanionByPurchaseNosAndStatus(TalentRefundDto paramDto) {
		log.info("TalentRefund Dao updateTalentRefundToCompanionByPurchaseNosAndStarus() start");
		int result = 0;
		
		try {
			result = sqlSession.update("updateTalentRefundToCompanionByPurchaseNosAndStatus", paramDto);
		} catch(Exception e) {
			log.info("TalentRefund Dao updateTalentRefundToCompanionByPurchaseNosAndStatus() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}

	//report
	@Override
	public List<ReportDto> selectReport() {
		log.info("Dao selectReport() start");
		List<ReportDto> reportList = null;
		try {
			reportList = sqlSession.selectList("selectReport");
			
		} catch(Exception e) {
			log.info("Dao selectReport() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return reportList;
	}

	@Override
	public List<ReportDto> selectReportByBlackId(String blackId) {
		log.info("Dao selectReportByBlackId() start");
		List<ReportDto> reportList = null;
		
		try {
			reportList = sqlSession.selectList("selectReportByBlackId", blackId);
			
		} catch(Exception e) {
			log.info("Dao selectReportByBlackId() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return reportList;
	}

	@Override
	public int updateReportByMemberId(String memberId) {
		log.info("Dao updateReportByMemberId() start");
		int result = 0;
		
		try {
			result = sqlSession.update("updateReportByMemberId", memberId);
			
		} catch(Exception e) {
			log.info("Dao upateReportByMemberId() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
