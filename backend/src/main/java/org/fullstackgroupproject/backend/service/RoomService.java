package org.fullstackgroupproject.backend.service;

import lombok.RequiredArgsConstructor;
import org.fullstackgroupproject.backend.model.DtoItem;
import org.fullstackgroupproject.backend.model.Item;
import org.fullstackgroupproject.backend.repo.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RoomService {
    private final ItemRepository itemRepository;
    private final IdService idService;

    public Item addItem(DtoItem dtoItem) throws NullPointerException {

        Item newItem = new Item(idService.idGenerator(),
                dtoItem.getName(), dtoItem.getAmount());
        itemRepository.save(newItem);

        return newItem;
    }

    public List<Item> getAllItems() throws NullPointerException {
        List<Item> response = itemRepository.findAll();
        if (response.isEmpty())
            throw new NullPointerException("No items found");
        else
            return response;
    }
}
