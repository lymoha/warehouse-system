package org.fullstackgroupproject.backend.exceptions;

import org.fullstackgroupproject.backend.controller.RoomRangerController;
import org.fullstackgroupproject.backend.model.DtoItem;
import org.fullstackgroupproject.backend.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RoomRangerController.class)
@Import(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void testHandleNullPointerException() throws Exception {
        // Wenn der Service mit der Fake-Datenbank (Mockito) aufgerufen wird und ein Item vom Typ DtoItem hinzugefügt
        // wird eine NullPointerException geworfen
        Mockito.when(roomService.addItem(Mockito.any(DtoItem.class))).thenThrow(new NullPointerException("This is a NullPointerException"));

        // Fake-Post auf route /api/add
        mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"testItem\"}"))
                // Nullpointer schickt den Status badRequest
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.errorMsg").value("This is a NullPointerException"))
                .andExpect(jsonPath("$.errorCode").value("Forbidden"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.apiPath").exists());
    }

    @Test
    public void testHandleGeneralException() throws Exception {
        Mockito.when(roomService.addItem(Mockito.any(DtoItem.class))).thenThrow(new RuntimeException("This is a general exception"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"testItem\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").value("This is a general exception"));
    }
}