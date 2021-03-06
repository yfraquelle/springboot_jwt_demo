package cn.edu.nju.dzy.api.web.webportal.controller;

import cn.edu.nju.dzy.common.PiBaseResource;
import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.AuthoritiesConstants;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import com.codahale.metrics.annotation.Timed;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdw
 */
@RestController
@RequestMapping("/student")
public class StudentController extends PiBaseResource{
    private final static Logger log = LoggerFactory.getLogger(StudentController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    UserInfoRepository userInfoRepository;


    @Inject
    JHipsterProperties jhipsterProperteis;

    @RequestMapping(value = "/student/query", method = RequestMethod.GET)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.student})
    public ResponseEntity<?> student_info(HttpServletRequest request, HttpServletResponse response) {
        Student student=new Student();
        student.setName("张三");
        student.setStudentId("141250000");
        student.setEmail("141250000@smail.nju.edu.cn");
        return super.getOKResponse(student,"OK");
    }

    @RequestMapping(value = "/allExams", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.student})
    public AllExamsResponse allExams(HttpServletRequest request, HttpServletResponse response) {
        AllExamsResponse allExamsResponse=new AllExamsResponse();
        List<Exam> exams=new ArrayList<Exam>();
        for(int i=1;i<=5;i++)
        {
            Exam exam=new Exam();
            exam.setId(1000+i);
            exam.setName("考试"+i);
            exam.setCourse("课程"+i);
            exam.setStart("2017-10-01 14:00:00");
            exam.setEnd("2017-10-01 16:00:00");
            exams.add(exam);
        }
        allExamsResponse.setExams(exams);
        return allExamsResponse;
    }

    @RequestMapping(value = "/examScore", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.student})
    public ExamPaperResponse examScore(@RequestParam long examId, HttpServletRequest request, HttpServletResponse response) {
        ExamPaperResponse examPaperResponse = new ExamPaperResponse();
        ExamPaper examPaper = new ExamPaper();
        List<Question> questions = new ArrayList<Question>();
        for (int i = 1; i <= 10; i++) {
            Question question = new Question();
            question.setOrder(i);
            question.setLevel(1);
            question.setType("single");
            question.setQuestionScore(20);
            question.setActualQuestionScore(0);
            question.setStem("java是？");
            question.setMarked(false);
            List<String> answers=new ArrayList<String>();
            answers.add("A");
            question.setAnswers(answers);
            List<String> choices=new ArrayList<String>();
            choices.add("A");
            question.setActualchoices(choices);
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
            question.setActualQuestionScore(0);
            question.setStem("3>?");
            question.setMarked(false);
            List<String> answers=new ArrayList<String>();
            answers.add("A");
            answers.add("B");
            question.setAnswers(answers);
            List<String> choices=new ArrayList<String>();
            choices.add("A");
            question.setActualchoices(choices);
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
        examPaper.setActualScore(0);
        examPaper.setExamQuestions(questions);

        examPaperResponse.setExamPaper(examPaper);
        return examPaperResponse;
    }

}
