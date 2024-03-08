package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ERP_user;
import com.example.demo.Repository.ERP_userRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ERP_userSecurityService implements UserDetailsService {

	private final ERP_userRepository erp_userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		Optional<ERP_user> user = this.erp_userRepository.findByUserId(userId);
		
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("사용자 찾기 불가");

		}

		ERP_user user2 = user.get();

		String dept = user2.getHR_mem().getDeptName().getDeptName().toString();
		String position = user2.getHR_mem().getPosition();
		List<GrantedAuthority> authorities = new ArrayList<>();
		// TODO 권한설정 공부 후 내용 적기

		if ("관리자".equals(dept)) {
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.관리자.getValue()));
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.팀장.getValue()));
		} else if ("구매팀".equals(dept)) {
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.구매.getValue()));
			if ("팀장".equals(position)) {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.팀장.getValue()));
			} else {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.사원.getValue()));
			}

		} else if ("회계팀".equals(dept)) {
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.회계.getValue()));
			if ("팀장".equals(position)) {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.팀장.getValue()));
			} else {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.사원.getValue()));
			}
		} else if ("인사팀".equals(dept)) {
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.인사.getValue()));
			if ("팀장".equals(position)) {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.팀장.getValue()));
			} else {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.사원.getValue()));
			}
		} else if ("재고팀".equals(dept)) {
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.재고.getValue()));
			if ("팀장".equals(position)) {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.팀장.getValue()));
			} else {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.사원.getValue()));
			}
		} else if ("생산팀".equals(dept)) {
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.생산.getValue()));
			if ("팀장".equals(position)) {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.팀장.getValue()));
			} else {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.사원.getValue()));
			}
		} else if ("영업팀".equals(dept)) {
			authorities.add(new SimpleGrantedAuthority(ERP_userRole.영업.getValue()));
			if ("팀장".equals(position)) {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.팀장.getValue()));
			} else {
				authorities.add(new SimpleGrantedAuthority(ERP_userRole.사원.getValue()));
			}
		}
		return new User(user2.getUserId(), user2.getPassword(), authorities);
	}

}
