package org.fullstackgroupproject.backend.controller;

import lombok.RequiredArgsConstructor;
import org.fullstackgroupproject.backend.model.DtoItem;
import org.fullstackgroupproject.backend.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor

public class RoomRangerController {
    private  final RoomService roomService;

    @PostMapping("/add")
    public  String addItem(@RequestBody DtoItem dtoItem){
        roomService.addItem(dtoItem);
        return "OK!";
    }



}
