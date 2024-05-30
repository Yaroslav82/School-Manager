package com.hillel.multi.persistent.repositories;

import com.hillel.multi.persistent.entities.Student;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentManagerRepository extends MongoRepository<Student, String> {

    Optional<Student> findById(@NotNull String id);

}