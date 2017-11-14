package cn.edu.nju.dzy.domain;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * 一道试题
 */
@Entity
@Table(name = "question")
@IgnoreSizeOf
public class Question extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //题干和正确选项
    @ManyToOne
    private Stem stem;

    //错误选项
    @ManyToMany
    private Set<Option> wrong;

    //在试卷中的编号
    @NotNull
    private Integer qid;

    //分值
    @NotNull
    private Double value;

//    //类型
//    private Type type;
//    enum Type{
//        Single,Multiple;
//    }
}
