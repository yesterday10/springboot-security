package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity(name = "manager")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Manager extends DateAudit{
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "uuid")
    @Column(name="id")
    private String id;
    @Column(unique = true)
    private String username;  //用户名
    private String password;  //密码
    private Byte gender;      //性别 1-男 2-女
    private String realName;  //真实姓名
    private String avatar;    //头像
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;    //生日
    private Byte state;       //状态,0:创建未认证 1:正常状态,2：用户被锁定.

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", realName='" + realName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                ", state=" + state +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "manager_role", joinColumns = { @JoinColumn(name = "manager_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    @Where(clause = "available=true")
    private Set<Role> roleList;// 一个用户具有多个角色

    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @Transient
    private String[] roleIds;
}
