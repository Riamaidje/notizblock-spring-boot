package de.htwberlin.webtech.controller;
import de.htwberlin.webtech.model.Noteblock;
import de.htwberlin.webtech.service.NotizblockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@AllArgsConstructor
@RequestMapping("/notizblock")
public class NotizblockController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NotizblockController.class);
    private final NotizblockService service;

    @GetMapping
    public ResponseEntity<List<Noteblock>> getAllNoteblocks() {
        List<Noteblock> notes = service.getAllNoteblocks();
        logger.info("Retrieved {} notes", notes.size());
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noteblock> getNoteblockById(@PathVariable UUID id) {
        Optional<Noteblock> noteblock = service.getNoteblockById(id);
        return noteblock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<Noteblock> createNoteblock(@Valid @RequestBody Noteblock noteblock) {
        Noteblock savedNoteblock = service.createNoteblock(noteblock);
        logger.info("Note created with ID: {}", savedNoteblock.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNoteblock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Noteblock> updateNoteblock(@PathVariable UUID id, @Valid @RequestBody Noteblock updatedNoteblock) {
        Optional<Noteblock> updated = service.updateNoteblock(id, updatedNoteblock);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteblock(@PathVariable UUID id) {
        boolean deleted = service.deleteNoteblock(id);
        if (deleted) {
            logger.info("Note deleted with ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found with ID: " + id);
        }
    }
}