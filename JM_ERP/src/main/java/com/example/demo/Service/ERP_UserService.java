package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ERP_boardA;
import com.example.demo.Entity.ERP_boardQ;
import com.example.demo.Entity.ERP_user;
import com.example.demo.Entity.ERP_userMailBox;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Repository.ERP_boardARepository;
import com.example.demo.Repository.ERP_boardQRepository;
import com.example.demo.Repository.ERP_userMailBoxRepository;
import com.example.demo.Repository.ERP_userRepository;
import com.example.demo.Repository.HR_memRepository;

import error.DataNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ERP_UserService {

	private final ERP_userRepository erp_userRepository;
	private final HR_memRepository hr_memRepository;
	private final PasswordEncoder passwordEncoder;
	private final ERP_userMailBoxRepository erp_userMailBoxRepository;
	private final ERP_boardQRepository questionrepository;
	private final ERP_boardARepository answerrepository;
	
	
	public boolean createuser(String userId, String password, String name, String employeeId) throws Exception {

		ERP_user user = new ERP_user();
		List<HR_mem> memList = hr_memRepository.findAll();

		Optional<HR_mem> matching = memList.stream().filter(obj -> obj.getEmployeeId().equals(employeeId)).findFirst();
		// 존재하는지만 확인하기때문에 더블체크 할 데이터 더 필요함.
		if (matching.isPresent()) {
			user.setUserId(userId);
			user.setPassword(passwordEncoder.encode(password));
			user.setName(name);

			HR_mem hr = new HR_mem();
			hr.setEmployeeId(employeeId);

			user.setHR_mem(hr);
			this.erp_userRepository.save(user);
			return true;

		} else {
			return false;
		}

	}

	public boolean sendnewmail(String reciveUser, String sendUser, String subject, String content, String image,
			String mediaFile) {
		try {
			ERP_userMailBox mail = new ERP_userMailBox();
			mail.setCheckStatus(false);
			mail.setSendTransaction(LocalDateTime.now());
			mail.setContent(content);
			mail.setImage(image);
			mail.setMediaFile(mediaFile);
			mail.setSubject(subject);

			Optional<ERP_user> user = erp_userRepository.findByuserId(sendUser);
			Optional<ERP_user> user2 = erp_userRepository.findByuserId(reciveUser);

			if (user.isPresent()) {
				ERP_user sendU = user.get();
				mail.setSendUser(sendU);
			}

			if (user2.isPresent()) {
				ERP_user reciveU = user2.get();
				mail.setReciveUser(reciveU);
			}

			erp_userMailBoxRepository.save(mail);

			System.out.println("성공");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
			return false;

		}
	}

	public Page<ERP_userMailBox> openmailbox(String username, int page) {
		Pageable pageable = PageRequest.of(page, 10);
		List<ERP_user> user = erp_userRepository.findAll();
		ERP_user b = new ERP_user();

		for (ERP_user a : user) {

			if (a.getUserId().equals(username)) {
				b = a;
			}
		}

		Page<ERP_userMailBox> mail = erp_userMailBoxRepository.findByReciveUser(b, pageable);

		return mail;
	}

	public Optional<ERP_userMailBox> findbynum(Long num) {
		return erp_userMailBoxRepository.findById(num);

	}

	public void checkmailstatus(Long num) {
		Optional<ERP_userMailBox> a = erp_userMailBoxRepository.findById(num);
		ERP_userMailBox mail = a.get();
		mail.setCheckStatus(true);
		erp_userMailBoxRepository.save(mail);
	}
	
//	================= board Method =======================
	
	public Page<ERP_boardQ> QuestionGetList(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return this.questionrepository.findAll(pageable);
	}
	
	public ERP_boardQ getQuestion(Integer id) {
		Optional<ERP_boardQ> question = this.questionrepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void createAnswer(ERP_boardQ question, String content) {
		ERP_boardA answer = new ERP_boardA();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		this.answerrepository.save(answer);
	}	
	
	public void createQuestion(String subject, String content) {
		ERP_boardQ q = new ERP_boardQ();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.questionrepository.save(q);
	}
}
