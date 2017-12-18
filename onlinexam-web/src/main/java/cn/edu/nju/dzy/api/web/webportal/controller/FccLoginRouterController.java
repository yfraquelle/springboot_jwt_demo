package cn.edu.nju.dzy.api.web.webportal.controller;

import cn.edu.nju.dzy.common.PiBaseResource;
import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.AuthoritiesConstants;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import com.codahale.metrics.annotation.Timed;
import com.sun.rowset.internal.Row;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
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

    @RequestMapping(value = "/student/query", method = RequestMethod.POST)
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

    @RequestMapping(value = "/exam/list", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.student})
    public ResponseEntity<?> exam_info(HttpServletRequest request, HttpServletResponse response) {
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

        return super.getOKResponse(exams,"OK");
    }

    @RequestMapping(value = "/exam/{examId}/cache", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.student})
    public ResponseEntity<?> exam_cache(@PathVariable long examId,HttpServletRequest request, HttpServletResponse response) {

        return super.getOKResponse(null,"OK");
    }

    @RequestMapping(value = "/exam/{examId}/submit", method = RequestMethod.POST)
    @Timed
    @Transactional
    @Secured({AuthoritiesConstants.student})
    public ResponseEntity<?> exam_submit(@PathVariable long examId, @RequestBody List<Question> questions, HttpServletRequest request, HttpServletResponse response) {

        return super.getOKResponse(null,"OK");
    }

    @RequestMapping(value = "/exam/{examId}/query", method = RequestMethod.POST)
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


    @RequestMapping(value = "/download", method = RequestMethod.POST)
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
