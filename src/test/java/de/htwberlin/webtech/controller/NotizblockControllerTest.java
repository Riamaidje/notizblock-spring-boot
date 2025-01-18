package de.htwberlin.webtech.controller;

import de.htwberlin.webtech.model.Noteblock;
import de.htwberlin.webtech.service.NotizblockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NotizblockControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NotizblockService notizblockService;

    @InjectMocks
    private NotizblockController notizblockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notizblockController).build();
    }

    @Test
    void getAllNoteblocks_ReturnsAllNotes() throws Exception {
        Noteblock note1 = new Noteblock(UUID.randomUUID(), "Test Note 1", "Content of note 1");
        Noteblock note2 = new Noteblock(UUID.randomUUID(), "Test Note 2", "Content of note 2");

        when(notizblockService.getAllNoteblocks()).thenReturn(List.of(note1, note2));

        mockMvc.perform(get("/notizblock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Note 1"))
                .andExpect(jsonPath("$[1].title").value("Test Note 2"));
    }

    @Test
    void getNoteblockById_ReturnsNote() throws Exception {
        UUID noteId = UUID.randomUUID();
        Noteblock note = new Noteblock(noteId, "Test Note", "Test content");

        when(notizblockService.getNoteblockById(noteId)).thenReturn(Optional.of(note));

        mockMvc.perform(get("/notizblock/" + noteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Note"))
                .andExpect(jsonPath("$.content").value("Test content"));
    }

    @Test
    void getNoteblockById_ReturnsNotFound() throws Exception {
        UUID noteId = UUID.randomUUID();

        when(notizblockService.getNoteblockById(noteId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/notizblock/" + noteId))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateNoteblock_ReturnsNotFound() throws Exception {
        UUID noteId = UUID.randomUUID();
        Noteblock updatedNote = new Noteblock(noteId, "Updated Note", "Updated content");

        when(notizblockService.updateNoteblock(noteId, updatedNote)).thenReturn(Optional.empty());

        mockMvc.perform(put("/notizblock/" + noteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Updated Note\", \"content\": \"Updated content\" }"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNoteblock_ReturnsNoContent() throws Exception {
        UUID noteId = UUID.randomUUID();

        when(notizblockService.deleteNoteblock(noteId)).thenReturn(true);

        mockMvc.perform(delete("/notizblock/" + noteId))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteNoteblock_ReturnsNotFound() throws Exception {
        UUID noteId = UUID.randomUUID();

        when(notizblockService.deleteNoteblock(noteId)).thenReturn(false);

        mockMvc.perform(delete("/notizblock/" + noteId))
                .andExpect(status().isNotFound());
    }
}