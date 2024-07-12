package org.fullstackgroupproject.backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class IdServiceTest {

    @Autowired
    private IdService idService;

    @Test
    void idGenerator_shouldReturnUniqueString_whenCalled() {
        // Generiere eine neue ID
        String id = idService.idGenerator();

        // Überprüfen, dass die ID nicht leer ist
        assertFalse(id.isEmpty(), "Die generierte ID sollte nicht leer sein");

        // Überprüfen, dass die ID das richtige Format hat
        assertTrue(id.matches("^[0-9a-fA-F-]{36}$"), "Die generierte ID sollte das UUID-Format haben");
    }
}