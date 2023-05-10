package com.kakao.jPanda.bsm.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kakao.jPanda.bsm.dao.TalentDao;
import com.kakao.jPanda.bsm.domain.CategoryDto;
import com.kakao.jPanda.bsm.domain.TalentDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistTalentServiceImpl implements TalentService{
	private final TalentDao talentDao;
	
	@Override
	public List<CategoryDto> findCategorys() {
		List<CategoryDto> categoryList = talentDao.selectCategorys();
		return categoryList;
	}
	
	@Override
	public String addTalent(TalentDto talent, HttpSession session) {
		String result = null;
		System.out.println("talent.getSellerId() " + talent.getSellerId());
		System.out.println("(String)session.getAttribute(\"memberId\") " + (String)session.getAttribute("memberId"));
		if(talent.getSellerId().equals((String) session.getAttribute("memberId"))) {
			result = "<script>" +
					"alert('비정상적인 재능 등록입니다. 다시 로그인해 주세요.');" + 
					"location.href = '/login';" + 
					"</script>";
		}else {
			int insertResult = talentDao.insertTalent(talent);
			if(insertResult > 0) {
				result = "<script> " +
						"alert('재능 등록이 완료되었습니다.');" + 
						"location.href = '/main';" + 
						"</script>";
			}else {
				result = "<script> " +
						"alert('재능 등록이 완료되지 않았습니다. 다시 확인해주세요.');" + 
						"history.back();" + 
						"</script>";
			}
		}
		return result;
	}
	
	
	@Override
	public TalentDto findTalentByTalentNo(Long talentNo) {
		TalentDto talent = talentDao.selectTalentBytalentNo(talentNo);
		
		return talent;
	}

	@Override
	public String modifyTalent(TalentDto talent, HttpSession session) {
		String result = null;;
		String sellerId = findSellerIdByTalent(talent);
		
		if(sellerId.equals((String) session.getAttribute("memberId"))) {
			result = "<script>" +
					"alert('비정상적인 재능 수정입니다. 다시 로그인해 주세요.');" + 
					"location.href = '/login';" + 
					"</script>";
		}else {
			int updateResult = talentDao.updateTalent(talent);
			if(updateResult > 0) {
				result = "<script> " +
						"alert('재능 수정 완료되었습니다.');" + 
						"location.href = '/talent/';" + 
						"</script>";
			}else {
				result = "<script> " +
						"alert('재능 수정이 완료되지 않았습니다. 다시 확인해주세요.');" + 
						"history.back();" + 
						"</script>";
			}
		}
		return result;
	}
	
	@Override
	public ModelAndView talentImageUpload(MultipartHttpServletRequest request) {
		
		// ckeditor는 이미지 업로드 후 이미지 표시하기 위해 uploaded 와 url을 json 형식으로 받아야 함
		// modelandview를 사용하여 json 형식으로 보내기위해 모델앤뷰 생성자 매개변수로 jsonView 라고 써줌
		// jsonView 라고 쓴다고 무조건 json 형식으로 가는건 아니고 @Configuration 어노테이션을 단 
		// WebConfig 파일에 MappingJackson2JsonView 객체를 리턴하는 jsonView 매서드를 만들어서 bean으로 등록해야 함 
		ModelAndView mav = new ModelAndView("jsonView");
		
		// ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서 uploadFile에 저장함
		MultipartFile uploadFile = request.getFile("upload");
		
		// 파일의 오리지널 네임
		String originalFileName = uploadFile.getOriginalFilename();
		
		// 파일의 확장자 추출
		String ext = originalFileName.substring(originalFileName.indexOf("."));
		
		// 서버에 저장될 때 중복된 파일 이름인 경우를 방지하기 위해 UUID에 확장자를 붙여 새로운 파일 이름을 생성
		String newFileName = UUID.randomUUID() + ext;
		
		int index = System.getProperty("user.dir").indexOf("\\jPanda");
		String path = System.getProperty("user.dir").substring(0, index);
		
		// 현재경로/talentImage/파일명이 저장 경로
		String savePath = path + "/uploadImage/" + newFileName;
		
		
		// 해당 파일 경로에 폴더가 없을시 폴더 생성
		File fileDirectory = new File(savePath);
		if(!fileDirectory.exists()) {
			// 신규 폴더(Directory)생성
			fileDirectory.mkdirs();
		}
		
		String uploadPath = "/uploadImage/" + newFileName; 
		
		// 저장 경로로 파일 객체 생성
		File file = new File(savePath);
		
		// 파일 업로드
		try {
			uploadFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// uploaded, url 값을 Modelandview를 통해 보냄
		mav.addObject("uploaded", true); // 업로드 완료
		mav.addObject("url", uploadPath); // 업로드 완료
		return mav;
	}
	
	@Override
	public List<TalentDto> findBestSellerTalents() {
		return talentDao.selectBestSellerTalents();
	}
	
	@Override
	public List<TalentDto> findTopRatedTalents() {
		return talentDao.selectTopRatedTalents();
	}
	
	@Override
	public List<TalentDto> findNewTrendTalents() {
		return talentDao.selectNewTrendTalents();
	}
	
	@Override
	public List<TalentDto> findRandomTalents() {
		return talentDao.selectRandomTalents();
	}

	private String findSellerIdByTalent(TalentDto talent) {
		return talentDao.selectSellerIdByTalent(talent);
	}

}
