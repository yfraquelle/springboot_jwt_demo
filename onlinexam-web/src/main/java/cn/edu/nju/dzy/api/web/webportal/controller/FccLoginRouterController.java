package cn.edu.nju.dzy.api.web.webportal.controller;

import cn.edu.nju.dzy.common.PiBaseResource;
import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.AuthoritiesConstants;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdw
 */
@RestController
@RequestMapping("/api")
public class FccLoginRouterController extends PiBaseResource{
    private final static Logger log = LoggerFactory.getLogger(FccLoginRouterController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    UserInfoRepository userInfoRepository;


    @Inject
    JHipsterProperties jhipsterProperteis;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @Secured({AuthoritiesConstants.USER})
    public ResponseEntity<?> test(HttpServletResponse response) {
        return super.getOKResponse("ok","ok");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Timed
    @Transactional
    public ResponseEntity<?> login(@RequestBody Credential credential, HttpServletRequest request, HttpServletResponse response) {
        log.info(credential.username);
        return super.getOKResponse(credential,"OK");
    }

    @RequestMapping(value = "/registerY", method = RequestMethod.POST)
    @Timed
    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterInfo registerInfo,HttpServletRequest request, HttpServletResponse response) {
        log.info(registerInfo.email);
        return super.getOKResponse(registerInfo,"OK");
    }

    @RequestMapping(value = "/exam/{examId}", method = RequestMethod.GET)
    @Timed
    @Transactional
    public ResponseEntity<?> exam_list(@PathVariable long examId,HttpServletRequest request, HttpServletResponse response) {
        List<Question> questions=new ArrayList<Question>();
        for(int i=1;i<=20;i++)
        {
            Question question=new Question();
//            question.setId((int)(i+(pageId-1)*5));
            question.setId(i);
            question.setStem("1+1在标准的情况下等于几");
            question.setAnswer("A");
            question.setSanswer("");
            question.setScore(0);
            List<Option> options=new ArrayList<Option>();

            Option optionA=new Option();
            optionA.setStufix("A");
            optionA.setContent("2");
            options.add(optionA);
            Option optionB=new Option();
            optionB.setStufix("B");
            optionB.setContent("3");
            options.add(optionB);
            Option optionC=new Option();
            optionC.setStufix("C");
            optionC.setContent("4");
            options.add(optionC);
            Option optionD=new Option();
            optionD.setStufix("D");
            optionD.setContent("5");
            options.add(optionD);

            question.setOptions(options);
            questions.add(question);
        }

        return super.getOKResponse(questions,"OK");
    }

    @RequestMapping(value = "/exam/{examId}/cache", method = RequestMethod.POST)
    @Timed
    @Transactional
    public ResponseEntity<?> exam_cache(@PathVariable long examId,HttpServletRequest request, HttpServletResponse response) {

        return super.getOKResponse(null,"OK");
    }

    @RequestMapping(value = "/exam/{examId}/submit", method = RequestMethod.POST)
    @Timed
    @Transactional
    public ResponseEntity<?> exam_submit(@PathVariable long examId, @RequestBody List<Question> questions, HttpServletRequest request, HttpServletResponse response) {

        return super.getOKResponse(null,"OK");
    }

    @RequestMapping(value = "/exam/{examId}/query", method = RequestMethod.GET)
    @Timed
    @Transactional
    public ResponseEntity<?> exam_query(@PathVariable long examId, HttpServletRequest request, HttpServletResponse response) {
        Exam exam=new Exam();
        exam.setName("软件过程与管理期末考");
        exam.setCourse("软件过程与管理");
        exam.setEasy(7);
        exam.setHard(8);
        return super.getOKResponse(exam,"OK");
    }

    @RequestMapping(value = "/report/{examId}", method = RequestMethod.GET)
    @Timed
    @Transactional
    public ResponseEntity<?> report_query(@PathVariable long examId, HttpServletRequest request, HttpServletResponse response) {
        Report report=new Report();
        report.setName("张三");
        report.setStudentId("141250000");
        report.setEmail("141250000@smail.nju.edu.cn");
        report.setEasy(5);
        report.setNormal(8);
        report.setHard(7);
        report.setTime(45);
        report.setScore(98);
        List<Question> questions=new ArrayList<Question>();
        for(int i=1;i<=20;i++) {
            Question question = new Question();
//            question.setId((int)(i+(pageId-1)*5));
            question.setId(i);
            question.setStem("1+1在标准的情况下等于几");
            question.setAnswer("A");
            question.setSanswer("B");
            question.setScore(5);
            List<Option> options = new ArrayList<Option>();

            Option optionA = new Option();
            optionA.setStufix("A");
            optionA.setContent("2");
            options.add(optionA);
            Option optionB = new Option();
            optionB.setStufix("B");
            optionB.setContent("3");
            options.add(optionB);
            Option optionC = new Option();
            optionC.setStufix("C");
            optionC.setContent("4");
            options.add(optionC);
            Option optionD = new Option();
            optionD.setStufix("D");
            optionD.setContent("5");
            options.add(optionD);

            question.setOptions(options);
            questions.add(question);
        }
        report.setQuestions(questions);
        return super.getOKResponse(report, "OK");

    }
}
