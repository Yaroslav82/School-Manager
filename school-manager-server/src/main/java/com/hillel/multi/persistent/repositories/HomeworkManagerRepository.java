package com.hillel.multi.persistent.repositories;

import com.hillel.multi.persistent.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkManagerRepository extends JpaRepository<Homework, Long> {

    @Query("SELECT h FROM Homework h WHERE h.id = :id")
    Homework getHomeworkById(@Param("id") Long id);

    @Query("SELECT h FROM Homework h WHERE (:groupName is null or h.groupName = :groupName) and " +
            "(:subject is null or h.subject = :subject)")
    List<Homework> getHomeworkByGroupAndSubject(@Param("groupName") String groupName, @Param("subject") String subject);
}
