package cn.edu.nju.dzy.domain;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 一张答卷
 */
@Entity
@Table(name = "sheet")
@IgnoreSizeOf
public class Sheet extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    //考试
//    @ManyToMany
//    private Exam exam;
//
//    //考生
//    @ManyToMany
//    private Examee examee;
//
//    //试卷
//    @ManyToMany
//    private Paper paper;

    //回答
    @ManyToMany
    private Set<Answer> answer;

    //成绩
    @NotNull
    private Double score;

}
