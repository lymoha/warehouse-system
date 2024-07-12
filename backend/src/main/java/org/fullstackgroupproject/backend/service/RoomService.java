package org.fullstackgroupproject.backend.service;

import lombok.RequiredArgsConstructor;
import org.fullstackgroupproject.backend.exceptions.InvalidIdException;
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

    public Item addItem(DtoItem dtoItem) {

        Item newItem = new Item(idService.idGenerator(),
                dtoItem.getName(), dtoItem.getAmount());
        itemRepository.save(newItem);

        return newItem;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(String id) throws InvalidIdException {
        return itemRepository.findById(id).orElseThrow(() -> new InvalidIdException("Item with Id " + id + " not found"));
    }

    public Item updateItemById(String id, DtoItem dtoItem) throws InvalidIdException {
        Item foundItem = itemRepository.findById(id).orElseThrow(() -> new InvalidIdException("Item with Id " + id + " not found"));
        foundItem.setName(dtoItem.getName());
        foundItem.setAmount(dtoItem.getAmount());
        return itemRepository.save(foundItem);
    }
}
