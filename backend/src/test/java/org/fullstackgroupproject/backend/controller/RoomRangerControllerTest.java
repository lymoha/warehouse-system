package org.fullstackgroupproject.backend.controller;

import org.fullstackgroupproject.backend.model.Item;
import org.fullstackgroupproject.backend.repo.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RoomRangerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addItem_ShouldReturnItem_WhenCalledWithDtoItems() throws Exception {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(""" 

                                    {
                                "name": "Test",
                                "amount": 27
                                }"""))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("""
                             {
                           "name": "Test",
                           "amount": 27
                           }
                           """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());


        } catch (NullPointerException e ){
            throw new NullPointerException(e.getMessage());
        }
    }
    @Test
    void getAllItems_ShouldReturnItemList_whenCalledInitially() throws Exception {
        itemRepository.saveAll(List.of(
                (new Item("1","test",4))
        ));

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            [{
                            id: "1",
                            name: "test",
                            amount: 4
                             }]
                           """));
        } catch (NullPointerException e ){
            throw new NullPointerException(e.getMessage());
        }
    }

    @Test
    void getItemById_ShouldReturnItem_withGivenId() throws Exception {
        itemRepository.saveAll(List.of(
                (new Item("1","test",4))
        ));
            mockMvc.perform(MockMvcRequestBuilders.get("/api/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                            id: "1",
                            name: "test",
                            amount: 4
                             }
                           """));
    }

    @Test
    void updateItemById_ShouldReturnUpdatedItem_withGivenId() throws Exception {
        itemRepository.saveAll(List.of(
                (new Item("1","test",4))
        ));
            mockMvc.perform(MockMvcRequestBuilders.put("/api/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                            {
                            "name": "test",
                            "amount": 4
                             }
                            """))

                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                            id: "1",
                            name: "test",
                            amount: 4
                             }
                           """));
    }



}