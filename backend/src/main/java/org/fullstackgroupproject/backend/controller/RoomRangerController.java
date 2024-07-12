package org.fullstackgroupproject.backend.controller;

import lombok.RequiredArgsConstructor;
import org.fullstackgroupproject.backend.exceptions.InvalidIdException;
import org.fullstackgroupproject.backend.model.DtoItem;
import org.fullstackgroupproject.backend.model.Item;
import org.fullstackgroupproject.backend.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor

public class RoomRangerController {
    private final RoomService roomService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Item addItem(@RequestBody DtoItem dtoItem){
        return roomService.addItem(dtoItem);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Item> getAllItems(){
        return roomService.getAllItems();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable String id) throws InvalidIdException {
        return roomService.getItemById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Item updateItemById(@PathVariable String id, @RequestBody DtoItem dtoItem) throws InvalidIdException {
        return roomService.updateItemById(id, dtoItem);
    }
}
