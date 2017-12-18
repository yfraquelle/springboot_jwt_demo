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

    @RequestMapping(value = "/exam/{examId}/query", method = RequestMethod.GET)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.student})
    public ResponseEntity<?> exam_query(@PathVariable long examId, HttpServletRequest request, HttpServletResponse response) {
        Exam exam=new Exam();
        exam.setName("软件过程与管理期末考");
        exam.setCourse("软件过程与管理");
        exam.setEasy(7);
        exam.setHard(8);
        exam.setStart("2017-11-12 14:00:00");
        exam.setEnd("2017-11-12 16:00:00");
        exam.setTime(120);
        return super.getOKResponse(exam,"OK");
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


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.admin})
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String filePath ="template.csv";
        File file = new File(filePath);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(file.getPath());
            int len = 0;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            while((len = in.read(buffer)) > 0) {
                out.write(buffer,0,len);
            }

        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(in != null) {
                try {
                    in.close();
                }catch(Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
//        return super.getOKResponse(null,"ok");
    }

    @RequestMapping("/upload")
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.admin})
    public void uploadFile(@RequestParam(value = "file" , required = true) MultipartFile file) {
        log.info(file.getName());
        log.info(file.getSize()+"");
    }
}
