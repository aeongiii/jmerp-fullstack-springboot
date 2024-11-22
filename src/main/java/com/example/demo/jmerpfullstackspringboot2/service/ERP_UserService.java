package com.example.demo.jmerpfullstackspringboot2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.ERP_approval;
import com.example.demo.jmerpfullstackspringboot2.entity.ERP_boardA;
import com.example.demo.jmerpfullstackspringboot2.entity.ERP_boardQ;
import com.example.demo.jmerpfullstackspringboot2.entity.ERP_user;
import com.example.demo.jmerpfullstackspringboot2.entity.ERP_userMailBox;
import com.example.demo.jmerpfullstackspringboot2.entity.HR_mem;
import com.example.demo.jmerpfullstackspringboot2.repository.ERP_approvalRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.ERP_boardARepository;
import com.example.demo.jmerpfullstackspringboot2.repository.ERP_boardQRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.ERP_userMailBoxRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.ERP_userRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.HR_memRepository;

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
    private final ERP_approvalRepository approvalrepository;

    public Optional<ERP_user> getName(String userid) {
        Optional<ERP_user> user = erp_userRepository.findByUserId(userid);
        return user;
    }

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

            Optional<ERP_user> user = erp_userRepository.findByUserId(sendUser);
            Optional<ERP_user> user2 = erp_userRepository.findByUserId(reciveUser);

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

    public Page<ERP_boardQ> QuestionGetList(int page) {
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

//================= approval Method =======================

    public Page<ERP_approval> approvalList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.approvalrepository.findAll(pageable);
    }

    public ERP_approval getApproval(Integer id) {
        Optional<ERP_approval> approval = this.approvalrepository.findById(id);
        if (approval.isPresent()) {
            return approval.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void createApproval(String subject, String content) {
        ERP_approval a = new ERP_approval();
        a.setSubject(subject);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        a.setDewDate(LocalDateTime.now().plusDays(3));
        this.approvalrepository.save(a);
    }
}
