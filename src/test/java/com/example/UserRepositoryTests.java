package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("nguyenducbao1@gmail.com");
        user.setPassword("abcd1234");
        user.setFirstName("Bao");
        user.setLastName("Nguyen");

        User savedUser = repository.save(user);
        User exitsUser = repository.getById(savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(exitsUser.getEmail());
    }
}
