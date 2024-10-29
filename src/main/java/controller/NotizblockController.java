package controller;

import model.Noteblock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notizblock")
public class NotizblockController {

    private List<Noteblock> noteblocks = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Noteblock>> getAllNoteblocks() {
        return ResponseEntity.ok(noteblocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noteblock> getNoteblockById(@PathVariable String id) {
        Optional<Noteblock> noteblock = noteblocks.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst();
        return noteblock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Noteblock> createNoteblock(@RequestBody Noteblock noteblock) {
        noteblock.setCreatedAt(LocalDateTime.now());
        noteblock.setUpdatedAt(LocalDateTime.now());
        noteblocks.add(noteblock);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteblock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Noteblock> updateNoteblock(@PathVariable String id, @RequestBody Noteblock updatedNoteblock) {
        for (Noteblock noteblock : noteblocks) {
            if (noteblock.getId().equals(id)) {
                noteblock.updateNote(updatedNoteblock.getTitle(), updatedNoteblock.getContent(), updatedNoteblock.getTags());
                noteblock.setUpdatedAt(LocalDateTime.now());
                return ResponseEntity.ok(noteblock);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteblock(@PathVariable String id) {
        boolean removed = noteblocks.removeIf(noteblock -> noteblock.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}