package cn.edu.nju.dzy.domain;

import cn.edu.nju.dzy.config.Constants;
import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * 一次考试
 */
@Entity
@Table(name = "exam")
@IgnoreSizeOf
public class Exam extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //开始时间
    @NotNull
    private ZonedDateTime startDateTime;

    //结束时间
    @NotNull
    private ZonedDateTime endDateTime;

    //科目
    @NotNull
    private String course;

    //试卷
    @ManyToMany
    private Set<Paper> paper;
}
