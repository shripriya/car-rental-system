package com.carrental.user.repository;

import com.carrental.user.domain.User;
import java.util.*;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    User update(User user);
}
