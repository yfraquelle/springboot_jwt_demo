package cn.edu.nju.dzy.domain;

import cn.edu.nju.dzy.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 考生
 */
@Entity
@Table(name = "examee")
@IgnoreSizeOf
public class Examee extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //姓名
    @NotNull
    @Column(length = 50)
    private String name;

    //学号
    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(name = "student_no",length = 50,unique = true, nullable = false)
    private String studentNo;

    //邮箱
    @NotNull
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private String email;

    //班级
    @Size(min = 1, max = 50)
    @Column(length = 10)
    private String classId;

    //年级
    @Size(min = 1, max = 50)
    @Column(length = 10)
    private String gradeId;

//    //用户
//    @OneToOne(mappedBy="user")
//    private User user;
    
//    public User getUser(){
//    	return user;
//    }
//
//    public void setUser(User user){
//    	this.user=user;
//    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Examee user = (Examee) o;

        if (!studentNo.equals(user.studentNo)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return studentNo.hashCode();
    }

    @Override
    public String toString() {
        return "Examee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", email='" + email + '\'' +
//                ", user=" + user +
                '}';
    }
}
