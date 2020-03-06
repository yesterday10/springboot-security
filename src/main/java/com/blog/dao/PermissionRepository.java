package com.blog.dao;

import com.blog.entity.Permission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {

    @Query("select t from Permission t order by t.sort asc")
    List<Permission> findMenuList();

//    @Modifying
//    @Query("delete from Permission t where t.parentIds like :parentId%")
//    void deleteMenus(@Param("parentId") String parentId);

    void deletePermissionByParentIdsStartingWith(String parentId);
}
