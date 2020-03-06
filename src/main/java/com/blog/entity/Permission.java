package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "permission")
public class Permission {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;//主键.
    private String name;//名称.
    @Column(columnDefinition="enum('menu','button')")
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private String purview; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private String icon;
    private Long parentId; //父编号
    private String parentIds; //父编号列表
    private Integer sort; //排序

    @ManyToMany
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "permission_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;


}
