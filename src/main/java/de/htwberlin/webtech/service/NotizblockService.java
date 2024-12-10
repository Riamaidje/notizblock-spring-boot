package de.htwberlin.webtech.service;


import de.htwberlin.webtech.model.Noteblock;
import de.htwberlin.webtech.repository.NoteblockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotizblockService {

    @Autowired
    private final NoteblockRepository repository;

    public NotizblockService(NoteblockRepository repository) {
        this.repository = repository;
    }

    public List<Noteblock> getAllNoteblocks() {
        return repository.findAll();
    }

    public Optional<Noteblock> getNoteblockById(UUID id) {
        return repository.findById(id);
    }

    public Noteblock createNoteblock(Noteblock noteblock) {
        noteblock.setCreatedAt(LocalDateTime.now());
        noteblock.setUpdatedAt(LocalDateTime.now());
        return repository.save(noteblock);
    }

    public Optional<Noteblock> updateNoteblock(UUID id, Noteblock updatedNoteblock) {
        return repository.findById(id).map(existingNoteblock -> {
            existingNoteblock.updateNote(
                    updatedNoteblock.getTitle(),
                    updatedNoteblock.getContent(),
                    updatedNoteblock.getTags()
            );
            existingNoteblock.setUpdatedAt(LocalDateTime.now());
            return repository.save(existingNoteblock);
        });
    }

    public boolean deleteNoteblock(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}