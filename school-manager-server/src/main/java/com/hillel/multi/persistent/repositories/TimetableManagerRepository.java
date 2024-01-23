package com.hillel.multi.persistent.repositories;

import com.hillel.multi.persistent.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableManagerRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT l FROM Lesson l WHERE l.id = :id")
    Lesson getLessonById(@Param("id") Long id);

    @Query("SELECT l FROM Lesson l WHERE (:groupName is null or l.groupName = :groupName) and " +
            "(:subject is null or l.subject = :subject)")
    List<Lesson> getLessonsByGroupAndSubject(@Param("groupName") String groupName, @Param("subject") String subject);
}
