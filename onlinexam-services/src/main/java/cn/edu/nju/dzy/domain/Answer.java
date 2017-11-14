package cn.edu.nju.dzy.domain;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 一道试题的回答
 */
@Entity
@Table(name = "answer")
@IgnoreSizeOf
public class Answer extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //试题
    @NotNull
    private Question question;

    //选项
    @ManyToMany
    private Set<Option> options;

}
