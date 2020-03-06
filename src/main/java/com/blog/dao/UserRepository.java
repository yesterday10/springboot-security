package com.blog.dao;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.blog.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> , JpaSpecificationExecutor<User> {

    @Override
    Optional<User> findById(String s);

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    /**
     * 动态查询管理员数据
     */
    static Specification<User> getUserList(String username, String nickName, String phone, String email, Byte state){
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)->{
            List<Predicate> predicates = new ArrayList<>();
            if(!StringUtils.isEmpty(username)) {
                predicates.add(cb.like(root.get("username"),"%"+username+"%"));
            }

            if(!StringUtils.isEmpty(nickName)) {
                predicates.add(cb.like(root.get("nickName"),"%"+nickName+"%"));
            }

            if(!StringUtils.isEmpty(phone)) {
                predicates.add(cb.like(root.get("phone"),"%"+phone+"%"));
            }

            if(!StringUtils.isEmpty(email)) {
                predicates.add(cb.like(root.get("email"),"%"+email+"%"));
            }

            if(state!=null) {
                predicates.add(cb.equal(root.get("state"), state));
            }

            return query.where(cb.and(predicates.toArray(new Predicate[predicates.size()]))).getRestriction();
        };
    }

}
