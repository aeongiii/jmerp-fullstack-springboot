package com.example.demo.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Repository.SD_PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_PurchaseService {

	private final SD_PurchaseRepository purchaseRepository;
	private final ObjectMapper objectMapper; // SD_Purchase 리스트를 JSON 문자열로 변환

	// 고객 모두 조회 (페이징)
	public Page<SD_Purchase> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findAll(pageable);
	}

	public List<SD_Purchase> getList() {
		return this.purchaseRepository.findAll();
	}

	// 고객 조회 로직 구현
	public Page<SD_Purchase> searchByMemberId(String memberId, Pageable pageable) {
		return purchaseRepository.findByMemberId(memberId, pageable);
	}

	// 고객 아이디로 구매내역 찾기
	public Page<SD_Purchase> findByMemberId(String memberId, int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findByMemberId(memberId, pageable);
	}

	// 파이썬과 스프링부트를 연결
	public String executePythonScript() {
		String outputPath = "src/main/resources/static/img/SD_output.png"; // 그래프를 이미지(SD_output)로 저장할 파일 경로
		try {
			
			// SD_Purchase 데이터를 JSON 문자열 데이터로 변환
	        String jsonData = getSDPurchaseDataAsJson();
			
			// JSON 데이터를 임시 파일에 저장
	        Path tempFile = Files.createTempFile(null, ".json");
	        Files.write(tempFile, jsonData.getBytes(StandardCharsets.UTF_8));	// CP949 오류 있어서 UTF-8로 변경

	        // 임시 파일의 경로를 파이썬 스크립트에 인자로 전달						// 파이썬 파일 경로 지정	  // 인자 1				// 인자 2
	        ProcessBuilder processBuilder = new ProcessBuilder("python", "python/purchase.py", tempFile.toString(), outputPath);
	        Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			
			
			// 표준 출력 읽기 : 로그에서 오류 확인용
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));  // 표준 오류 스트림
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

	        return "/img/SD_output.png"; // 그래프 이미지 파일 경로 반환

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// SD_Purchase 데이터를 조회하고 JSON 문자열로 변환
	public String getSDPurchaseDataAsJson() {
		try {
			List<SD_Purchase> purchases = this.getList(); // 모든 구매 데이터 조회
			return objectMapper.writeValueAsString(purchases); // JSON 문자열로 변환
		} catch (Exception e) {
			e.printStackTrace();
			return null; // 오류 발생 시 null 반환 또는 적절한 예외 처리
		}
	}
}