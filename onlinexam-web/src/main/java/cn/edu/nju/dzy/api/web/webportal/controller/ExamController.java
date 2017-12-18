package cn.edu.nju.dzy.api.web.webportal.controller;

import cn.edu.nju.dzy.common.PiBaseResource;
import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.AuthoritiesConstants;
import cn.edu.nju.dzy.security.jwt.JWTConfigurer;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import cn.edu.nju.dzy.web.rest.errors.ErrorDTO;
import com.codahale.metrics.annotation.Timed;
import org.codehaus.groovy.classgen.asm.sc.StaticTypesBinaryExpressionMultiTypeDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mdw
 */
@RestController
@RequestMapping("/exam")
public class ExamController extends PiBaseResource {
    private final static Logger log = LoggerFactory.getLogger(ExamController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    UserInfoRepository userInfoRepository;


    @Inject
    JHipsterProperties jhipsterProperteis;

    @RequestMapping(value = "/examPaper", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.exam})
    public ExamPaperResponse examPaper(HttpServletRequest request, HttpServletResponse response) {
        ExamPaperResponse examPaperResponse = new ExamPaperResponse();
        ExamPaper examPaper = new ExamPaper();
        List<Question> questions = new ArrayList<Question>();
        for (int i = 1; i <= 10; i++) {
            Question question = new Question();
            question.setOrder(i);
            question.setLevel(1);
            question.setType("single");
            question.setQuestionScore(20);
            question.setStem("java是？");
            question.setMarked(false);
            List<String> options = new ArrayList<String>();
            options.add("面向对象");
            options.add("结构化");
            options.add("类型不严格");
            options.add("多返回值");

            question.setOptions(options);
            questions.add(question);
        }
        for (int i = 11; i <= 20; i++) {
            Question question = new Question();
            question.setOrder(i);
            question.setLevel(1);
            question.setType("multiple");
            question.setQuestionScore(10);
            question.setStem("3>?");
            question.setMarked(false);
            List<String> options = new ArrayList<String>();
            options.add("1");
            options.add("2");
            options.add("3");
            options.add("4");

            question.setOptions(options);
            questions.add(question);
        }
        examPaper.setExamId(4396);
        examPaper.setStudentId("170");
        examPaper.setCourseName("语文");
        examPaper.setStartTime("2017-12-14 08:00:00");
        examPaper.setEndTime("2017-12-14 10:00:00");
        examPaper.setTotalScore(50);
        examPaper.setExamQuestions(questions);

        examPaperResponse.setExamPaper(examPaper);
        return examPaperResponse;
    }

    @RequestMapping(value = "/saveChoice", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.exam})
    public void saveChoice(@RequestBody SaveChoiceInfo saveChoiceInfo, HttpServletRequest request, HttpServletResponse response) {
        log.info(saveChoiceInfo.choices.toString());
        Set<String> set=new HashSet<String>();
        set.add("A");
        set.add("B");
        if(saveChoiceInfo.choices.equals(set)){
            log.info("equal");
        }
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.exam})
    public void commit(HttpServletRequest request, HttpServletResponse response) {

    }

}