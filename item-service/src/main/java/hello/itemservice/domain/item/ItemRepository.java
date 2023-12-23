package hello.itemservice.domain.item;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> repository = new HashMap<>();
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        repository.put(item.getId(), item);
        return item;
    }
    public Item findById(long id) {
        return repository.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(repository.values());
    }

    public void update(Long itemId,Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        repository.clear();
    }
}
