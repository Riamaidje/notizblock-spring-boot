package de.htwberlin.webtech.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteblockTest {

    @Test
    void testSettersAndGetters() {
        UUID id = UUID.randomUUID();
        Noteblock noteblock = new Noteblock();
        noteblock.setId(id);
        noteblock.setTitle("Test Title");
        noteblock.setContent("Test Content");

        assertEquals(id, noteblock.getId());
        assertEquals("Test Title", noteblock.getTitle());
        assertEquals("Test Content", noteblock.getContent());
    }
}
