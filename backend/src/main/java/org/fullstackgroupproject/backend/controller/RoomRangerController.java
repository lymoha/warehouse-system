package org.fullstackgroupproject.backend.controller;

import lombok.RequiredArgsConstructor;
import org.fullstackgroupproject.backend.model.DtoItem;
import org.fullstackgroupproject.backend.model.Item;
import org.fullstackgroupproject.backend.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor

public class RoomRangerController {
    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody DtoItem dtoItem) throws NullPointerException {
        return new ResponseEntity<>(roomService.addItem(dtoItem), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(roomService.getAllItems(),HttpStatus.OK);
    }
}
