package com.example.demo.Controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_NBProduct;
import com.example.demo.Entity.SD_PBProduct;
import com.example.demo.Service.SD_MemberService;
import com.example.demo.Service.SD_NBProductService;
import com.example.demo.Service.SD_PBProductService;
import com.example.demo.Service.SD_PurchaseService;
import com.example.demo.Service.SD_SaleService;

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
	public String nbGraph(Model model) {

		String categoryName = "공구,도서,리빙,식품,전자기기,패션";
		String imagePath = "src/main/resources/static/img/SD_graphNB.png"; // 이미지 이름 변경하면서 imageName 안 쓰게 됨
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

	    return "SD/SD_test";
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
	    
		return "SD/SD_pb";
	}


	
	
// 4. 회원별 구매 카테고리 분석
	
//	@GetMapping("/member")
//	public String memberGraph(Model model) {
//		
//		List<SD_Member> memberList = memberService.getList();
//		model.addAttribute("memberList", memberList);
//
//		String imagePath = "src/main/resources/static/img/SD_graph_choihaeun456.png";
//		String imageWebPath = "/img/SD_graph_choihaeun456.png";	// 첫번째 회원의 그래프가 있다면 전체 다 존재한다고 봄
//
//		// 이미지 존재하지 않을 경우, 그래프 생성 로직 실행
//		if (!Files.exists(Paths.get(imagePath))) {
//			// 이미지가 존재하지 않는 경우, 그래프 생성 로직 실행
//			imageWebPath = saleService.graph_member();
//		}
//		// 모델에 그래프 추가
//		model.addAttribute("SD_graphMember", imageWebPath);
//
//		return "SD/SD_saleMember";
//	}
	
	//==================== 새로 변경중인 코드... ===============================
	
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
		
		return "SD/SD_categoryProportions";
	}
	
	
	// AJAX를 사용하려고 했으나... 실패
	
//	@GetMapping("/member")
//    @ResponseBody
//	public Map<String, Object> getCategoryProportion(@RequestParam("memberId") String memberId) {
//        Map<String, Integer> categoryProportions = purchaseService.getProportion(memberId);
//        Map<String, Object> response = new HashMap<>();
//        response.put("labels", new ArrayList<>(categoryProportions.keySet()));
//        response.put("data", new ArrayList<>(categoryProportions.values()));
//        return response;
//    }
	
	@GetMapping("/test")
	public String testGraph(Model model) {
		
		List<SD_PBProduct> PBList = pbService.getList();
		
		// X축 라벨과 Y축 값을 저장할 리스트 생성
	    List<String> names = new ArrayList<>();
	    List<Integer> values = new ArrayList<>();
	    
	    // PBList에서 데이터 추출
	    for(SD_PBProduct product : PBList) {
	        names.add(product.getProductName()); // 가정: getName()이 X축 라벨을 반환
	        values.add(product.getPriceEA()); // 가정: getValue()가 Y축 값을 반환
	    }
	    
	    // 모델에 추가
	    model.addAttribute("labels", names);
	    model.addAttribute("data", values);

		return "SD/SD_test";
		
		
		
	}
	
}
