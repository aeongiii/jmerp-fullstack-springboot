package com.example.demo.Controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.SD_SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/sale")
public class SD_SaleController {

	@Autowired
	private final SD_SaleService saleService;
	
// 1. 카테고리별 총 판매량 비교
	@GetMapping("/nb")
	public String nbGraph(Model model) {
		
		String categoryName = "공구,도서,리빙,식품,전자기기,패션";
		String imagePath = "src/main/resources/static/img/SD_graphNB.png";	// 이미지 이름 변경하면서 imageName 안 쓰게 됨
        String imageWebPath = "/img/SD_graphNB.png";
		
		// 이미지 존재하지 않을 경우, 그래프 생성 로직 실행
		 if (!Files.exists(Paths.get(imagePath))) {
             // 이미지가 존재하지 않는 경우, 그래프 생성 로직 실행
             imageWebPath = saleService.graph_nb(categoryName);
         }
         // 모델에 그래프 추가
         model.addAttribute("SD_graphNB", imageWebPath);
		return "SD/SD_saleNB";
	}
	

	

// 2. 카테고리 내 상품별 판매량 비교
	@GetMapping("/category")
	public String categoryGraph(Model model) {

		// 카테고리 + 이미지 파일명 매핑
		Map<String, String> categoryMap = new HashMap<>();
		categoryMap.put("공구", "SD_gonggu");
		categoryMap.put("도서", "SD_book");
		categoryMap.put("리빙", "SD_living");
		categoryMap.put("식품", "SD_food");
		categoryMap.put("전자기기", "SD_electronic");
		categoryMap.put("패션", "SD_fashion");
		
		// 카테고리별 이미지 유무 확인
		categoryMap.forEach((categoryName, imageName) -> {
            String imagePath = "src/main/resources/static/img/SD_" + categoryName + ".png";	// 이미지 이름 변경하면서 imageName 안 쓰게 됨
            String imageWebPath = "/img/SD_" + categoryName + ".png";

            if (!Files.exists(Paths.get(imagePath))) {
                // 이미지가 존재하지 않는 경우, 그래프 생성 로직 실행
                imageWebPath = saleService.graph_category(imageName, categoryName);
            }
            // 모델에 그래프 추가
            model.addAttribute("SD_" + categoryName, imageWebPath);
        });
		
		return "SD/SD_saleCategory";
	}
	
	
// 3. 자체제작 상품별 판매량 비교	 
	
	
	
}
