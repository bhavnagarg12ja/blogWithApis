package com.codeWithBhavna.blogWithApis.repositories;

import com.codeWithBhavna.blogWithApis.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer>{
}
