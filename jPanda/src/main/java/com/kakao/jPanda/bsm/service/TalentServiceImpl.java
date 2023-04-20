package com.kakao.jPanda.bsm.service;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kakao.jPanda.bsm.dao.TalentDao;
import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService{
	private final TalentDao talentDao;
	
	@Override
	public List<Category> findCategorys() {
		List<Category> categoryList = talentDao.selectCategorys();
		System.out.println("TalentDao.categoryList() categoryList.size() -> " + categoryList.size());
		return categoryList;
	}
	
	@Override
	public String addTalent(Talent talent) {
		String resultStr = "";
		int result = talentDao.insertTalent(talent);
		if(result > 0) {
			resultStr = "<script> " +
					"alert('재능 업로드가 완료되었습니다.');" + 
					"location.href = '/talent/';" + 
					"</script>";
		}else {
			resultStr = "<script> " +
					"alert('재능 업로드가 완료되지 않았습니다. 다시 확인해주세요.');" + 
					"history.back();" + 
					"</script>";
		}
		return resultStr;
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
		System.out.println("MainController.image() 파일의 오리지널 네임 -> " + originalFileName);
		
        // 파일의 확장자 추출
		String ext = originalFileName.substring(originalFileName.indexOf("."));
		System.out.println("MainController.image() 파일의 확장자 -> " + ext);
		
		HashSet<String> checkFileType = new HashSet<>(Arrays.asList(".jpg", ".gif", ".png", ".jpeg", ".bmp"));
		checkFileType.add(ext);
		if (checkFileType.size() != 5) {
			return null;
		}
        // 서버에 저장될 때 중복된 파일 이름인 경우를 방지하기 위해 UUID에 확장자를 붙여 새로운 파일 이름을 생성
		String newFileName = UUID.randomUUID() + ext;
		System.out.println("MainController.image() 서버에 저장될 파일 이름 -> " + newFileName);

		// 이미지를 현재 경로와 연관된 파일에 저장하기 위해 현재 경로를 알아냄
		String realPath = request.getServletContext().getRealPath("/talentImage/");
		System.out.println("MainController.image() 현재 파일 경로 -> " + realPath);

		// 현재경로/upload/파일명이 저장 경로
		String savePath = realPath + newFileName;
		System.out.println("MainController.image() 파일 저장 경로 + 파일 이름 -> " + savePath);
		
		// 해당 파일 경로에 폴더가 없을시 폴더 생성
		File fileDirectory = new File(savePath);
		if(!fileDirectory.exists()) {
			// 신규 폴더(Directory)생성
			fileDirectory.mkdirs();
		}
		
		// 브라우저에서 이미지 불러올 때 절대 경로로 불러오면 보안의 위험 있어 상대경로를 쓰거나 이미지 불러오는 jsp 또는 클래스 파일을 만들어 가져오는 식으로 우회해야 함
		// 때문에 savePath와 별개로 상대 경로인 uploadPath 만들어줌
		String uploadPath = "../talentImage/" + newFileName; 
		System.out.println("MainController.image() 보안을 위한 상대 경로 출력 -> " + uploadPath);

		// 저장 경로로 파일 객체 생성
		File file = new File(savePath);

		// 파일 업로드
		try {
			uploadFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// uploaded, url 값을 modelandview를 통해 보냄
		mav.addObject("uploaded", true); // 업로드 완료
		mav.addObject("url", uploadPath); // 업로드 파일의 경로 // 일단 절대경로

		return mav;
	}
	
	@Override
	public Talent findTalentByTalentNo(Long talentNo) {
		// dto 새로 만들 것
		Talent talent = talentDao.selectTalentBytalentNo(talentNo);
		
		return talent;
	}

	@Override
	public String modifyTalent(Talent talent) {
		String resultStr = "";
		int result = talentDao.updateTalent(talent);
		if(result > 0) {
			resultStr = "<script> " +
					"alert('재능 수정 완료되었습니다.');" + 
					"location.href = '/talent/';" + 
					"</script>";
		}else {
			resultStr = "<script> " +
					"alert('재능 수정이 완료되지 않았습니다. 다시 확인해주세요.');" + 
					"history.back();" + 
					"</script>";
		}
		return resultStr;
		
	}

	@Override
	public List<Talent> findBestSellerTalents() {
		return talentDao.selectBestSellerTalents();
	}

	@Override
	public List<Talent> findTopRatedTalentTalents() {
		return talentDao.selectTopRatedTalentTalents();
	}

	@Override
	public List<Talent> findNewTrendTalents() {
		return talentDao.selectNewTrendTalents();
	}

	@Override
	public List<Talent> findRandomTalents() {
		return talentDao.selectRandomTalents();
	}
	
}
