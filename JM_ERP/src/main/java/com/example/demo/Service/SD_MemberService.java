package com.example.demo.Service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Repository.SD_MemberRepository;
import com.example.demo.Repository.SD_PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_MemberService {

	private final SD_MemberRepository memberRepository;
	private final SD_PurchaseRepository purchaseRepository;
	
	// 고객 등록
	public void RegiMember(String memberId, String memberName, String memberPhoneNumber, String memberEmail, String memberAddress, LocalDate memberJoinDate
			) {
		
		SD_Member m = new SD_Member();
		m.setMemberId(memberId);
		memberRepository.save(m);
	}
	
	public Page<SD_Member> searchMember(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return memberRepository.findAll(pageable);
		
		
	}
	
//	public void RegPurchase(SD_Member member, SD_Seller seller, SD_Product product, int productEA, int purchaseAmount,
//			boolean calcellation, String cardType, String cardNumber) {
//		
//		SD_Purchase p = new SD_Purchase();
//		p.setMember(member);
//		p.setSeller(seller);
//		p.setProduct(product);
//		p.setProductEA(productEA);
//		p.setPurchaseAmount((int) (product.getPrice() * productEA));
//		p.setTransactionTime(LocalDateTime.now());
//		p.setCancellationReturnInfo(calcellation);
//		p.setCardType(cardType);
//		p.setCardNumber(cardNumber);
//		purchaseRepository.save(p);
//	}
	
	public Page<SD_Purchase> searchPurchase(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findAll(pageable);
	}

	
//	public Page<SD_Member> searchMemberbyname(int page,String name){
//		Pageable pageable = PageRequest.of(page,10);
//		return memberRepository.findByName(name,pageable);
//		
//	}
}
