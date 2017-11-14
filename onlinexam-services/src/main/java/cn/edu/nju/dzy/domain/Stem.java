package cn.edu.nju.dzy.domain;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 题库中的一道题干及对应的答案
 */
@Entity
@Table(name = "stem")
@IgnoreSizeOf
public class Stem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //内容
    @NotNull
    private String content;

    //正确答案
    @OneToMany
    private Set<Option> right;

}
