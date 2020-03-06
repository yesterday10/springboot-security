package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "uuid")
    private String id ;
    private String username;       //用户名
    private String password;       //密码
    @Column(name = "nick_name")
    private String nickName;       //昵称
    private Date birthday;         //生日
    private Byte gender ;          //性别
    private String address;        //地址
    private String realName;       //真实姓名
    private String personDesc;     //个人简介
    private String signature;      //个性签名
    private String avatar;         //头像
    private String phone;          //手机号
    private String email;          //邮箱地址
    private BigDecimal balance;    //账户余额
    private Integer level;         //等级
    private Integer credit;        //积分
    private Byte state;//用户状态,0:未激活 1:正常状态,2:异常.
    private String unionId;        //第三方账号唯一ID

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", realName='" + realName + '\'' +
                ", personDesc='" + personDesc + '\'' +
                ", signature='" + signature + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", level=" + level +
                ", credit=" + credit +
                ", state=" + state +
                ", unionId='" + unionId + '\'' +
                '}';
    }

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    @Where(clause = "available=true")
    private Set<Role> roleList;// 一个用户具有多个角色
}
