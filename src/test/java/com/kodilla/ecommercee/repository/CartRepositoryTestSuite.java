package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CartRepositoryTestSuite {
    @Autowired
    CartRepository repository;

    @Test
    void testFindAll() {
        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        Long id1;
        Long id2;
        Long id3;
        repository.save(cart1);
        repository.save(cart2);
        repository.save(cart3);
        id1 = cart1.getId();
        id2 = cart2.getId();
        id3 = cart3.getId();

        //When
        List<Cart> carts = repository.findAll();

        //Then
//        Assertions.assertEquals(3, carts.size());
        Assertions.assertTrue(carts.size() >= 3);

        //CleanUp
        repository.deleteById(id1);
        repository.deleteById(id2);
        repository.deleteById(id3);
    }
    @Test
    void testSave() {
        //Given
        Cart cart4 = new Cart();

        //When
        repository.save(cart4);
        Long id4 = cart4.getId();
        Optional<Cart> retrievedCart = repository.findById(id4);

        //Then
        Assertions.assertTrue(retrievedCart.isPresent());

        //CleanUp
        repository.deleteById(id4);
    }
    @Test
    void testFindById() {
        //Given
        Cart cart5 = new Cart();
        repository.save(cart5);
        Long id5 = cart5.getId();

        //When
        Optional<Cart> retrievedCart = repository.findById(id5);

        //Then
        Assertions.assertTrue(retrievedCart.isPresent());

        //CleanUp
        repository.deleteById(id5);
    }
    @Test
    void testDeleteById() {
        //Given
        Cart cart6 = new Cart();
        repository.save(cart6);
        Long id6 = cart6.getId();

        //When
        repository.deleteById(id6);
        Optional<Cart> retrievedCart = repository.findById(id6);

        //Then
        Assertions.assertFalse(retrievedCart.isPresent());

        //CleanUp
        try {
            repository.deleteById(id6);
        } catch (Exception e) {

        }
    }
}
