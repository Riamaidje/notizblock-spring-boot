package de.htwberlin.webtech.repository;
import de.htwberlin.webtech.model.Noteblock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteblockRepository extends JpaRepository<Noteblock, UUID> {
}
