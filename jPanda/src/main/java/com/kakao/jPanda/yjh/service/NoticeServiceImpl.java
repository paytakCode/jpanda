package com.kakao.jPanda.yjh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.NoticeDao;
import com.kakao.jPanda.yjh.domain.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeDao noticeDao;
	
	@Override
	public String addNotice(Notice notice) {
		System.out.println("===== NoticeService addNotice() start =====");
		String resultStr = "";
		
		int result = noticeDao.insertNotice(notice);
		if(result > 0) {
			resultStr = "<script>alert('공지사항 적용이 완료 되었습니다.'); location.href='/admin';</script>";
		} else {
			resultStr = "<script>alert('공지사항 적용 오류'); history.back();</script>";
		}
		return resultStr;
	}

	@Override
	public List<Notice> findNotice() {
		log.info("===== NoticeService findNotice start =====");
		List<Notice> noticeList = noticeDao.selectNotice();
		
		return noticeList;
	}

	@Override
	public Notice findNoticeByNoticeNo(String noticeNo) {
		System.out.println("===== NoticeService findNoticeByNoticeNo =====");
		Notice notice = noticeDao.selectNoticeByNoticeNo(Long.parseLong(noticeNo));
		return notice;
	}

	@Override
	public String modifyNotice(Notice notice) {
		System.out.println("===== NoticeService modifyNotice() start =====");
		String resultStr = "";
		
		int result = noticeDao.updateNotice(notice);
		if(result > 0) {
			resultStr = "<script>alert('성공적으로 수정 되었습니다.'); location.href='/admin/notice'; </script>";
		} else {
			resultStr = "<script>alert('수정에 실패하였습니다.'); history.back(); </script>";
		}
		return resultStr;
	}

}
