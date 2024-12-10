package de.htwberlin.webtech.repository;
import de.htwberlin.webtech.model.Noteblock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteblockRepository extends JpaRepository<Noteblock, UUID> {
}
