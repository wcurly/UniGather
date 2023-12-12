package com.we.UniGather.repositories;

import com.we.UniGather.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//用来操作User实体
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
