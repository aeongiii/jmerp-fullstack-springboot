package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_NBProduct;
import com.example.demo.Entity.SD_PBProduct;
import com.example.demo.Service.SD_MemberService;
import com.example.demo.Service.SD_NBProductService;
import com.example.demo.Service.SD_PBProductService;
import com.example.demo.Service.SD_PurchaseService;
import com.example.demo.Service.SD_SaleService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/sale")
public class SD_SaleController {

	@Autowired
	private final SD_MemberService memberService;
	private final SD_SaleService saleService;
	private final SD_PBProductService pbService;
	private final SD_NBProductService nbService;
	private final SD_PurchaseService purchaseService;

// 1. 카테고리별 총 판매량 비교 

    @GetMapping("/nb")
    public String predictGraph(Model model) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            // result.json 파일 읽기
            File file = new File("python/result.json");
            Map<String, List<Double>> results = objectMapper.readValue(file, Map.class);

            // 카테고리 목록
            List<String> categories = List.of("도서", "패션", "공구", "리빙", "식품", "전자기기");

            // 각 카테고리별로 모델에 데이터 추가
            for(String category : categories) {
                List<Double> categoryData = results.get(category);
                List<String> xLabels = IntStream.rangeClosed(1, 6) // 1월부터 6월까지
                	    .boxed() // IntStream을 Stream<Integer>로 박싱
                	    .flatMap(month -> 
                	        IntStream.rangeClosed(1, 3) // 각 월의 1분기부터 3분기까지
                	            .mapToObj(quarter -> String.format("%d월 %d분기", month, quarter)) // 라벨 포맷팅
                	    )
                	    .collect(Collectors.toList()); // 최종적으로 List<String> 형태로 수집

                	// xLabels를 모델에 추가
                model.addAttribute(category + "Labels", xLabels);
                model.addAttribute(category + "Data", categoryData);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 읽기 오류 처리
        }

        return "SD/SD_saleNB"; // HTML 템플릿 이름
    }

// 2. 카테고리 내 상품별 판매량 비교
	
	@GetMapping("/category")
	public String categoryGraph(Model model) {
		
		// 카테고리 목록
	    List<String> categories = Arrays.asList("도서", "패션", "공구", "리빙", "식품", "전자기기");

	    // NBList에서 정보를 가져오는 방식을 예시로 하여 각 카테고리별로 데이터 준비
	    for(String category : categories) {
	        List<SD_NBProduct> nbProducts = nbService.findByCategory(category); // 카테고리에 맞는 상품 조회
	        List<String> labels = nbProducts.stream()
	                                         .map(SD_NBProduct::getProductName)
	                                         .collect(Collectors.toList());
	        List<Integer> data = nbProducts.stream()
	                                       .map(nbProduct -> purchaseService.getAllTotalPurchaseEA(nbProduct.getProductCode()))
	                                       .collect(Collectors.toList());
	        
	        // 모델에 각 카테고리별 라벨과 데이터 추가
	        model.addAttribute(category + "Labels", labels);
	        model.addAttribute(category + "Data", data);
	    }

	    return "SD/SD_saleCategory";
	}

// 3. 자체제작 상품별 판매량 비교	 
	@GetMapping("/pb")
	public String pbGraph(Model model) {
		
		// X축 라벨과 Y축 값을 저장할 리스트 생성
	    List<String> labels = new ArrayList<>();
	    List<Integer> data = new ArrayList<>();

		List<SD_PBProduct> PBList = pbService.getList();

	 // PBList에서 데이터 추출
	    for(SD_PBProduct product : PBList) {
	    	labels.add(product.getProductName());
	     // 해당 상품의 총 구매수량을 계산하여 values에 추가
	    	data.add(purchaseService.getAllTotalPurchaseEA(product.getProductCode())); 
            
	    }
	    
	 // 모델에 추가
	    model.addAttribute("labels", labels);
	    model.addAttribute("data", data);
	    
		return "SD/SD_salePB";
	}


	
	
// 4. 회원별 구매 카테고리 분석
	@GetMapping("/member")
	public String memberGraph(Model model) {
		
		List<SD_Member> memberList = memberService.getList();
	    Map<String, Map<String, Double>> membersCategoryProportions = new HashMap<>();
	    
	 // 해당 멤버의 구매내역 --> 카테고리 비율을 반환
	    for (SD_Member member : memberList) {
	        Map<String, Double> categoryProportions = purchaseService.getProportion(member.getMemberId());
	        membersCategoryProportions.put(member.getMemberId(), categoryProportions);
	    }
	    model.addAttribute("memberList", memberList);
	    model.addAttribute("membersCategoryProportions", membersCategoryProportions);
		
		return "SD/SD_saleCategoryProportions";
	}
	
	
}
