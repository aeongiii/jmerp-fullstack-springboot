package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ERP_user;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Repository.ERP_userRepository;
import com.example.demo.Repository.HR_memRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ERP_UserService {

	private final ERP_userRepository erp_userRepository;
	private final HR_memRepository hr_memRepository;
	private final PasswordEncoder passwordEncoder;

	public boolean create(String userId, String password, String name, String employeeId) {
		
		ERP_user user = new ERP_user();
		List<HR_mem> memList = hr_memRepository.findAll();

		Optional<HR_mem> matching = memList.stream().filter(obj -> obj.getEmployeeId().equals(employeeId)).findFirst();

		if (matching.isPresent()) {
			user.setUserId(userId);
			user.setPassword(passwordEncoder.encode(password));
			user.setName(name);
			this.erp_userRepository.save(user);
			return true;

		} else {
			return false;
		}

	}
}
