package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.SD_Member;
import com.example.demo.jmerpfullstackspringboot2.entity.SD_PBProduct;
import com.example.demo.jmerpfullstackspringboot2.service.SD_MemberService;
import com.example.demo.jmerpfullstackspringboot2.service.SD_PBProductService;
import com.example.demo.jmerpfullstackspringboot2.service.SD_SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/sale")
public class SD_SaleController {

    @Autowired
    private final SD_MemberService memberService;
    private final SD_SaleService saleService;
    private final SD_PBProductService pbService;

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
            String imagePath = "src/main/resources/static/img/SD_" + categoryName + ".png"; // 이미지 이름 변경하면서 imageName 안
            // 쓰게 됨
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
    @GetMapping("/pb")
    public String pbGraph(Model model) {

        List<SD_PBProduct> pbProducts = pbService.getList();

        // 리스트에서 productName 뽑아서 쉼표로 연결해서 String으로 만들기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pbProducts.size(); i++) {
            sb.append(pbProducts.get(i).getProductName());

            // 마지막 요소가 아니면 쉼표와 공백 추가
            if (i < pbProducts.size() - 1) {
                sb.append(", ");
            }
        }
        String pbProductName = sb.toString();

        String imagePath = "src/main/resources/static/img/SD_graphPB.png";
        String imageWebPath = "/img/SD_graphPB.png";

        // 이미지 존재하지 않을 경우, 그래프 생성 로직 실행
        if (!Files.exists(Paths.get(imagePath))) {
            // 이미지가 존재하지 않는 경우, 그래프 생성 로직 실행
            imageWebPath = saleService.graph_pb(pbProductName);
        }
        // 모델에 그래프 추가
        model.addAttribute("SD_graphPB", imageWebPath);

        return "SD/SD_salePB";
    }


// 4. 회원별 구매 카테고리 분석

    @GetMapping("/member")
    public String memberGraph(Model model) {

        List<SD_Member> memberList = memberService.getList();
        model.addAttribute("memberList", memberList);

        String imagePath = "src/main/resources/static/img/SD_graph_choihaeun456.png";
        String imageWebPath = "/img/SD_graph_choihaeun456.png";    // 첫번째 회원의 그래프가 있다면 전체 다 존재한다고 봄

        // 이미지 존재하지 않을 경우, 그래프 생성 로직 실행
        if (!Files.exists(Paths.get(imagePath))) {
            // 이미지가 존재하지 않는 경우, 그래프 생성 로직 실행
            imageWebPath = saleService.graph_member();
        }
        // 모델에 그래프 추가
        model.addAttribute("SD_graphMember", imageWebPath);

        return "SD/SD_saleMember";


    }

}
