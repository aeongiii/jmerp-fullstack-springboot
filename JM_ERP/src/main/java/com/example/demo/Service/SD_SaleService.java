package com.example.demo.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_NBProduct;
import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Repository.SD_MemberRepository;
import com.example.demo.Repository.SD_NBProductRepository;
import com.example.demo.Repository.SD_PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
// 그래프 출력 관련 메서드 모아둠!!
public class SD_SaleService {

	private final SD_PurchaseRepository purchaseRepository;
	private final SD_NBProductRepository nbRepository;
	private final SD_MemberRepository memberRepository;
	private final ObjectMapper objectMapper;

	public List<SD_Purchase> getList() {
		return this.purchaseRepository.findAll();
	}

	// 파이썬과 스프링부트를 연결

// 1. 카테고리별 총 판매량 비교
	public String graph_nb(String categoryName) {

		String outputPath = "src/main/resources/static/img/SD_graphNB.png"; // 그래프를 이미지로 저장할 파일 경로

		try {

			// SD_NBProduct 데이터를 JSON 문자열 데이터로 변환
			String jsonData = changeToJson(categoryName);

			// JSON 데이터를 임시 파일에 저장
			Path tempFile = Files.createTempFile(null, ".json");
			Files.write(tempFile, jsonData.getBytes(StandardCharsets.UTF_8)); // CP949 오류 있어서 UTF-8로 변경

			// 임시 파일의 경로를 파이썬 스크립트에 인자로 전달 // 파이썬 파일 경로 지정 // 인자 1 // 인자 2
			ProcessBuilder processBuilder = new ProcessBuilder("python", "python/graphNB.py", tempFile.toString(),
					outputPath, categoryName);
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// 표준 출력 읽기 : 로그에서 오류 확인용
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream())); // 표준 오류 스트림
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
			// 표준 오류 출력 읽기 : 로그에서 오류 확인용
			while ((s = stdError.readLine()) != null) {
				System.err.println(s);
			}

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			int exitCode = process.waitFor();
			System.out.println("\nExited with error code : " + exitCode);

			// 임시 파일 삭제하는게 깔끔
			Files.delete(tempFile);

			return "/img/SD_graphNB.png"; // 그래프 이미지 파일 경로 반환

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

// 2. 카테고리 내 상품별 판매량 비교		// imageName = "SD_gonggu", categoryName = "공구"
	public String graph_category(String imageName, String categoryName) {
		String outputPath = String.format("src/main/resources/static/img/SD_%s.png", categoryName); // 그래프를 이미지(SD_공구)로
																									// 저장할 파일 경로
		try {

			// SD_NBProduct 데이터를 JSON 문자열 데이터로 변환
			String jsonData = changeToJson(categoryName);

			// JSON 데이터를 임시 파일에 저장
			Path tempFile = Files.createTempFile(null, ".json");
			Files.write(tempFile, jsonData.getBytes(StandardCharsets.UTF_8)); // CP949 오류 있어서 UTF-8로 변경

			// 임시 파일의 경로를 파이썬 스크립트에 인자로 전달 // 파이썬 파일 경로 지정 // 인자 1~3
			ProcessBuilder processBuilder = new ProcessBuilder("python", "python/graphCategory.py", tempFile.toString(),
					outputPath, categoryName);
			Process process = processBuilder.start();

			BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// 표준 출력 읽기 : 로그에서 정보 확인용
			String line;
			while ((line = stdOutput.readLine()) != null) {
			    System.out.println(line);
			}

			// 표준 오류 출력 읽기 : 로그에서 오류 확인용
			while ((line = stdError.readLine()) != null) {
			    System.err.println(line);
			}

			int exitCode = process.waitFor();
			System.out.println("\nExited with error code : " + exitCode);

			// 임시 파일 삭제하는게 깔끔
			Files.delete(tempFile);

			return String.format("/img/SD_%s.png", categoryName); // 그래프 이미지 파일 경로 반환

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

// 3. 자체제작 상품별 총 판매량 비교
	public String graph_pb(String pbProductName) {

		String outputPath = "src/main/resources/static/img/SD_graphPB.png"; // 그래프를 이미지로 저장할 파일 경로

		try {

			// SD_PBProduct 데이터를 JSON 문자열 데이터로 변환
			String jsonData = changeToJson(pbProductName);

			// JSON 데이터를 임시 파일에 저장
			Path tempFile = Files.createTempFile(null, ".json");
			Files.write(tempFile, jsonData.getBytes(StandardCharsets.UTF_8)); // CP949 오류 있어서 UTF-8로 변경

			// 임시 파일의 경로를 파이썬 스크립트에 인자로 전달 // 파이썬 파일 경로 지정 // 인자 1 // 인자 2
			ProcessBuilder processBuilder = new ProcessBuilder("python", "python/graphPB.py", tempFile.toString(),
					outputPath, pbProductName);
			Process process = processBuilder.start();

			BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// 표준 출력 읽기 : 로그에서 정보 확인용
			String line;
			while ((line = stdOutput.readLine()) != null) {
			    System.out.println(line);
			}

			// 표준 오류 출력 읽기 : 로그에서 오류 확인용
			while ((line = stdError.readLine()) != null) {
			    System.err.println(line);
			}
			
			int exitCode = process.waitFor();
			System.out.println("\nExited with error code : " + exitCode);

			// 임시 파일 삭제하는게 깔끔
			Files.delete(tempFile);

			return "/img/SD_graphPB.png"; // 그래프 이미지 파일 경로 반환

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

// 4. 모든 고객에 대한 구매 카테고리 분석
	public String graph_member() {

		// 모든 멤버 ID 리스트
		List<SD_Member> memberList = memberRepository.findAll();

		// 각 멤버별 구매기록 리스트 구하기
		for (SD_Member member : memberList) {
			String memberId = member.getMemberId();
			List<SD_Purchase> purchaseList = purchaseRepository.findByMemberId(memberId);

			Map<String, Integer> categoryCountMap = new HashMap<>();
			
			// 각 구매기록(SD_Purchase)의 상품코드를 보고 카테고리 분류하기 (SD_NBProduct 참조)
			for (SD_Purchase purchase : purchaseList) {
				String productCode = purchase.getProductCode();
				SD_NBProduct nbProduct = nbRepository.findByProductCode(productCode);

				if (nbProduct != null) {
					String category = nbProduct.getCategory();
					categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
				}
			}
			
			// 이미지를 저장할 파일 경로
			String outputPath = "src/main/resources/static/img/SD_graphMember_" + memberId + ".png";

			try {

				// 데이터를 JSON 문자열 데이터로 변환
				String jsonData = changeToJson(categoryCountMap);

				// JSON 데이터를 임시 파일에 저장
				Path tempFile = Files.createTempFile(null, ".json");
				Files.write(tempFile, jsonData.getBytes(StandardCharsets.UTF_8)); // CP949 오류 있어서 UTF-8로 변경

				// 임시 파일의 경로를 파이썬 스크립트에 인자로 전달 					// 파이썬 파일 경로 지정 // 인자 1 // 인자 2
				ProcessBuilder processBuilder = new ProcessBuilder("python", "python/graphMember.py", tempFile.toString(),
						outputPath);
				Process process = processBuilder.start();

				BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				// 표준 출력 읽기 : 로그에서 정보 확인용
				String line;
				while ((line = stdOutput.readLine()) != null) {
				    System.out.println(line);
				}

				// 표준 오류 출력 읽기 : 로그에서 오류 확인용
				while ((line = stdError.readLine()) != null) {
				    System.err.println(line);
				}
				
				int exitCode = process.waitFor();
				System.out.println("\nExited with error code : " + exitCode);

				// 임시 파일 삭제하는게 깔끔
				Files.delete(tempFile);

//				return "/img/SD_graphMember.png"; // 그래프 이미지 파일 경로 반환

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				return null;
			}


		}
		return "/img/SD_graphMember.png";

	}


//매개변수에 따른 카테고리 데이터를 조회하고 JSON 문자열로 변환
	public String changeToJson(String categoryName) {
		try {
//			List<SD_NBProduct> categoryList = nbRepository.findByCategory(categoryName); // 해당 카테고리에 포함된 모든 상품 데이터 조회
			List<SD_NBProduct> categoryList = nbRepository.findAll(); // 모든 상품 데이터 조회 -> 파이썬에서 카테고리별로 걸러서 사용함.
			return objectMapper.writeValueAsString(categoryList); // JSON 문자열로 변환
		} catch (Exception e) {
			e.printStackTrace();
			return null; // 오류 발생 시 null 반환 또는 적절한 예외 처리
		}
	}

	// 매개변수에 따른 카테고리 데이터를 조회하고 JSON 문자열로 변환
	public String changeToJson(Map<String, Integer> categoryCountMap) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			 // Map 객체를 JSON 문자열로 변환
	        String json = objectMapper.writeValueAsString(categoryCountMap);
	        return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null; // 오류 발생 시 null 반환 또는 적절한 예외 처리
		}
	}
}
