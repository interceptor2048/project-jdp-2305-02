package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    @Override
    public List<Item> findAll();

    @Override
    public Optional<Item> findById(Long itemId);

    List<Item> findByCart(Long cartId);

    @Override
    public Item save(Item item);

    @Override
    public void deleteById(Long itemId);
}
