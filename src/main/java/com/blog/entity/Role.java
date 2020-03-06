package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name="role",uniqueConstraints=@UniqueConstraint(columnNames={"roleCode","roleType"}))
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id; // 编号
    private String roleName;    //角色名称
    private String roleCode;    //角色标识符 程序中使用
    private Byte roleType;      //角色类型  1-后台角色 2-前台角色
    private String description; //角色描述
    private Boolean available;  //是否可用,如果不可用将不会添加给用户

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", roleType=" + roleType +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="user_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> userInfoList;// 一个角色对应多个用户

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "role_permission",joinColumns ={@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    @OrderBy("sort asc ")
    private Set<Permission> PermissionList;


    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="manager_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="manager_id")})
    // 一个角色对应多个用户
    private List<Manager> managers;


}
