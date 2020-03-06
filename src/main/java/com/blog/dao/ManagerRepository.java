package com.blog.dao;

import com.blog.entity.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,String> , JpaSpecificationExecutor<Manager> {

    boolean existsByUsernameAndIdIsNot(String username,String userId);

    @Query("select t from manager t where t.username=:name")
    Manager findUserByName(@Param("name") String name);

    /**
     * 动态查询管理员数据
     */
    static Specification<Manager> getManagerList(String username, Byte state, Byte gender, Date beginTime, Date endTime) {
        return (Root<Manager> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (username != null && !"".equals(username)) {
                predicates.add(cb.like(root.get("username"), "%" + username + "%"));
            }

            if (state != null) {
                predicates.add(cb.equal(root.get("state"), state));
            }

            if (gender != null) {
                predicates.add(cb.equal(root.get("gender"), gender));
            }

            if (beginTime != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdTime"), beginTime));
            }

            if (endTime != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdTime"), endTime));
            }
            return query.where(cb.and(predicates.toArray(new Predicate[predicates.size()]))).getRestriction();
        };
    }
}
