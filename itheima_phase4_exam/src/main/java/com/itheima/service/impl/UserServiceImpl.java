package com.itheima.service.impl;

import com.itheima.domain.User;
import com.itheima.repository.UserRepository;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 查找所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 通过主键查找用户
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    /**
     * 根据用户名和密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    public User findByIdAndName(String username, String password) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> userNamePath = root.get("username");
                Path<Object> passwordPath = root.get("password");
                Predicate predicateUserName = criteriaBuilder.equal(userNamePath, username);
                Predicate predicatePassword = criteriaBuilder.equal(passwordPath, password);
                Predicate predicate = criteriaBuilder.and(predicateUserName, predicatePassword);
                return predicate;
            }
        };
        return userRepository.findOne(specification).orElse(null);
    }
}
