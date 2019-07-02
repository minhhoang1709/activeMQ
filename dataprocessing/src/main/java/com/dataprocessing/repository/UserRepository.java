package com.dataprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataprocessing.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
