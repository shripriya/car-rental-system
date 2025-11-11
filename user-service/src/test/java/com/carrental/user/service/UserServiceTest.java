package com.carrental.user.service;

import com.carrental.user.domain.User;
import com.carrental.user.repository.InMemoryUserRepository;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(new InMemoryUserRepository());
    }

    @Test
    void testCreateAndGetUser() {
        User user = new User(null, "John Doe", "john@example.com", "1234567890");
        User saved = userService.createUser(user);
        assertNotNull(saved.getId());
        User found = userService.getUserById(saved.getId());
        assertEquals("John Doe", found.getName());
    }

    @Test
    void testUpdateUser() {
        User user = new User(null, "Jane Doe", "jane@example.com", "0987654321");
        User saved = userService.createUser(user);
        saved.setName("Jane Smith");
        User updated = userService.updateUser(saved.getId(), saved);
        assertEquals("Jane Smith", updated.getName());
    }

    @Test
    void testDeleteUser() {
        User user = new User(null, "Alice", "alice@example.com", "5555555555");
        User saved = userService.createUser(user);
        userService.deleteUser(saved.getId());
        assertNull(userService.getUserById(saved.getId()));
    }
}
