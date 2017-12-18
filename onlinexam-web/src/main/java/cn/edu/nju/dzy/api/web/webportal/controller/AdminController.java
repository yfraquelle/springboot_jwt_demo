package cn.edu.nju.dzy.api.web.webportal.controller;

import cn.edu.nju.dzy.common.PiBaseResource;
import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.AuthoritiesConstants;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import com.codahale.metrics.annotation.Timed;
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
@RequestMapping("/admin")
public class AdminController extends PiBaseResource{
    private final static Logger log = LoggerFactory.getLogger(AdminController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    UserInfoRepository userInfoRepository;


    @Inject
    JHipsterProperties jhipsterProperteis;

    @RequestMapping(value = "/allCourses", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.admin})
    public void allCourses(HttpServletRequest request, HttpServletResponse response) {

    }
    @RequestMapping(value = "/levelAndCount", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.admin})
    public void allCourses(@RequestParam String courseName, HttpServletRequest request, HttpServletResponse response) {

    }
    @RequestMapping(value = "/generateExam", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.admin})
    public void allCourses(@RequestBody GenerateExamInfo generateExamInfo, HttpServletRequest request, HttpServletResponse response) {

    }

    @RequestMapping("/uploadQuestion")
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.admin})
    public void uploadFile(@RequestParam(value = "file" , required = true) MultipartFile file) {
        log.info(file.getName());
        log.info(file.getSize()+"");
    }

    @RequestMapping(value = "/downloadTemplate")
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

    @RequestMapping(value = "/examScore", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.admin})
    public ExamPaperResponse examScore(@RequestParam long examId,@RequestParam long studentId, HttpServletRequest request, HttpServletResponse response) {
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
