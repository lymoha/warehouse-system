package org.fullstackgroupproject.backend.service;

import lombok.RequiredArgsConstructor;
import org.fullstackgroupproject.backend.model.DtoItem;
import org.fullstackgroupproject.backend.model.Item;
import org.fullstackgroupproject.backend.repo.ItemRepository;
import org.springframework.stereotype.Service;

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

}
