package cn.edu.nju.dzy.domain;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 一张试卷
 */
@Entity
@Table(name = "paper")
@IgnoreSizeOf
public class Paper extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //试题
    @ManyToMany
    private Set<Question> question;

    //密码
    @NotNull
    private String passcode;

    //考生
    @ManyToMany
    private Examee examee;

    //答卷
    @ManyToMany
    private Sheet sheet;

}
