package de.htwberlin.webtech.service;

import de.htwberlin.webtech.model.Noteblock;
import de.htwberlin.webtech.repository.NoteblockRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class) // Ajout de cette annotation
class NotizblockServiceTest {

    @Mock
    private NoteblockRepository noteblockRepository; // Le mock du repository

    @InjectMocks
    private NotizblockService notizblockService; // Service à tester, avec le mock injecté

    @Test
    void getNoteblockById_ReturnsNoteblock_WhenIdExists() {
        UUID noteId = UUID.randomUUID();
        Noteblock note = new Noteblock();
        note.setId(noteId);
        note.setTitle("Sample Title");
        note.setContent("Sample Content");

        // Simulation du comportement du repository
        when(noteblockRepository.findById(noteId)).thenReturn(Optional.of(note));

        // Appel de la méthode du service
        Optional<Noteblock> result = notizblockService.getNoteblockById(noteId);

        // Assertions pour vérifier que le résultat est correct
        assertTrue(result.isPresent());
        assertEquals(note, result.get());
    }
}
