package com.literature.repository;


import com.literature.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes,String> {

    Notes findNotesById(String id);

    List<Notes> findNotesByUserId(String UserId);

    List<Notes> findNotesByBookId(String bookId);
}
