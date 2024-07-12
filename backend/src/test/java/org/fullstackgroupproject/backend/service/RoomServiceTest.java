package org.fullstackgroupproject.backend.service;

import org.fullstackgroupproject.backend.exceptions.InvalidIdException;
import org.fullstackgroupproject.backend.model.DtoItem;
import org.fullstackgroupproject.backend.model.Item;
import org.fullstackgroupproject.backend.repo.ItemRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RoomServiceTest {

    private static RoomService roomService;
    // Dependencies
    private static ItemRepository mockItemRepository;
    private static IdService mockIdService;
    private static List<Item> testItems;

    @BeforeAll
    static void setup(){
        mockItemRepository = mock(ItemRepository.class);
        mockIdService = mock(IdService.class);
        roomService = new RoomService(mockItemRepository, mockIdService);
        testItems = new ArrayList<>(){{
            add(new Item("1","test",3));
            add(new Item("2","test",4));
            add(new Item("3","test",5));
        }};
    }

    @Test
    void addItem_shouldCreateNewItem_WithRandomId() {
        // GIVEN
        DtoItem newItem = new DtoItem("Test", 5);
        Item expectedItem = new Item("123", "Test", 5);

        // WHEN & THEN
        when(mockIdService.idGenerator()).thenReturn("123");
        when(mockItemRepository.save(expectedItem)).thenReturn(expectedItem);
        roomService.addItem(newItem);
        verify(mockItemRepository).save(expectedItem);
        verify(mockIdService).idGenerator();
    }

    @Test
    void addItem_shouldThrowException_WhenWentWrong() {
        // GIVEN
        DtoItem newItem = new DtoItem("Test", 5);

        // Mock-Objekt so konfigurieren, dass es eine Ausnahme wirft
        when(mockItemRepository.save(any(Item.class))).thenThrow(new NullPointerException("Error message"));

        // WHEN & THEN
        try{
            roomService.addItem(newItem);
            verify(mockItemRepository).save(any(Item.class));
            fail("Expected exception, but was not thrown");
        } catch (NullPointerException e){
            assertEquals("Error message", e.getMessage());
        }

    }
    @Test
    void getAllItems_shouldReturnAllItems_whenCalled() {
        //WHEN
        when(mockItemRepository.findAll()).thenReturn(testItems);
        List<Item> actual = roomService.getAllItems();
        //THEN
        verify(mockItemRepository).findAll();
        assertEquals(testItems, actual);
    }
    @Test
    void getAllItems_shouldThrowException_WhenWentWrong() {
        // Mock-Objekt so konfigurieren, dass es eine Ausnahme wirft
        when(mockItemRepository.findAll()).thenThrow(new NullPointerException("Error message"));

        // WHEN & THEN
        try{
            roomService.getAllItems();
            verify(mockItemRepository).findAll();
            verify(roomService).getAllItems();
            fail("Expected exception, but was not thrown");
        } catch (Exception e){
            assertEquals("Error message", e.getMessage());
        }
    }

    @Test
    void getItemById_shouldReturnItem_withGivenId() throws InvalidIdException {
        //WHEN
        when(mockItemRepository.findById("2")).thenReturn(Optional.of(testItems.get(1)));
        Item actual = roomService.getItemById("2");
        //THEN
        verify(mockItemRepository).findById("2");
        assertEquals(testItems.get(1), actual);
    }

    @Test
    void getItemById_shouldThrowException_WhenWentWrong() {
        // Mock-Objekt so konfigurieren, dass es eine Ausnahme wirft
        when(mockItemRepository.findById(any(String.class))).thenReturn(Optional.empty());

        assertThrows(InvalidIdException.class, () -> roomService.getItemById("2"));
        verify(mockItemRepository).findById("2");
    }

    @Test
    void updateItemById_shouldReturnUpdatedItem_withGivenId() throws InvalidIdException {
        //WHEN
        when(mockItemRepository.findById("2")).thenReturn(Optional.of(testItems.get(1)));
        Item actual = roomService.updateItemById("2", new DtoItem("test-tasse", 26));
        when(mockItemRepository.save(any(Item.class))).thenReturn(actual);
        //THEN
        verify(mockItemRepository).findById("2");
        verify(mockItemRepository).save(any(Item.class));
        assertNotEquals(testItems.get(1), actual);
    }

    @Test
    void updateItemById_shouldThrowException_WhenWentWrong() {
        // Mock-Objekt so konfigurieren, dass es eine Ausnahme wirft
        when(mockItemRepository.findById(any(String.class))).thenReturn(Optional.empty());

        assertThrows(InvalidIdException.class, () -> roomService.updateItemById("2", new DtoItem("test-tasse", 26)));
        verify(mockItemRepository).findById("2");
    }
}