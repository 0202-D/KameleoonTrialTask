package com.kameleoon.dmitriypetrov.kameleoontrialtask.dao;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    Optional<User> findById(Long id);
}
