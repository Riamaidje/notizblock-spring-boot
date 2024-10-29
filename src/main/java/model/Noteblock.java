package model;

import java.time.LocalDateTime;
import java.util.List;

public class Noteblock {

        private String id;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<String> tags;
        // Konstruktor
        public Noteblock (String id, String title, String content, LocalDateTime createdAt, List<String> tags) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.updatedAt = createdAt;
            this.tags = tags;
        }

        // Getter und Setter
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        // Methode für das Aktualisieren der Notiz (wird das updatedAt-Datum ändern)
        public void updateNote (String title, String content, List<String> tags) {
            this.title = title;
            this.content = content;
            this.tags = tags;
            this.updatedAt = LocalDateTime.now();
        }
    }

