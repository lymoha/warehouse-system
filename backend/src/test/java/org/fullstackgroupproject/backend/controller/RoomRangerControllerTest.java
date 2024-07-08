package org.fullstackgroupproject.backend.controller;

import org.fullstackgroupproject.backend.repo.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RoomRangerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addItem_ShouldReturnString_WhenCalledWithDtoItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                                {
                                "name": "Test",
                                "amount": 27
                                }"""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                           "name": "Test",
                           "amount": 27
                           }    
                           """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());


    }
}