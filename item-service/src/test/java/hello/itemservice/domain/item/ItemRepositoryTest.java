package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);
        // when
        List<Item> findAll = itemRepository.findAll();

        assertThat(findAll.size()).isEqualTo(2);

    }

    @Test
    void updateItem() {
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);

        //when
        Item updateItem = new Item("item1", 20000, 20);
        itemRepository.update(item1.getId(), updateItem);

        //then
        Item updatedItem = itemRepository.findById(item1.getId());
        assertThat(updatedItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}