package com.hillel.multi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.model.StudentDTO;
import com.hillel.multi.persistent.entities.Student;
import com.hillel.multi.persistent.repositories.StudentsManagerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class StudentsManagerTests {

    @Autowired
    private StudentsManagerRepository repository;

    @Container
    final static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @AfterEach
    public void setUp() {
        repository.deleteAll();
    }

    // -------------------- Get endpoint --------------------
    @Test
    public void testGetPositive() throws Exception {
        fillDatabase();
        mockMvc.perform(MockMvcRequestBuilders.get("/students/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNegative() throws Exception {
        fillDatabase();
        mockMvc.perform(MockMvcRequestBuilders.get("/students/get/asdjbn"))
                .andExpect(status().isNotFound());
    }
    // -------------------- Get endpoint --------------------


    // -------------------- Add endpoint --------------------
    @Test
    public void testAddPositive() throws Exception {
        String stringStudent = objectMapper.writeValueAsString(getStudentPositive());

        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringStudent))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testAddNegative() throws Exception {
        String stringStudent = objectMapper.writeValueAsString(getStudentNegative());

        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringStudent))
                .andExpect(status().isBadRequest());
        Assertions.assertEquals(0, repository.findAll().size());
    }
    // -------------------- Add endpoint --------------------


    // -------------------- Update endpoint --------------------
    @Test
    public void testUpdatePositive() throws Exception {
        fillDatabase();
        String stringStudent = objectMapper.writeValueAsString(getStudentPositive());
        mockMvc.perform(MockMvcRequestBuilders.put("/students/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringStudent))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateWrongId() throws Exception {
        fillDatabase();
        String stringStudent = objectMapper.writeValueAsString(getStudentPositive());
        mockMvc.perform(MockMvcRequestBuilders.put("/students/update/avxda")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringStudent))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateWrongParams() throws Exception {
        fillDatabase();
        String stringStudent = objectMapper.writeValueAsString(getStudentNegative());
        mockMvc.perform(MockMvcRequestBuilders.put("/students/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringStudent))
                .andExpect(status().isBadRequest());
    }
    // -------------------- Update endpoint --------------------


    // -------------------- Delete endpoint --------------------
    @Test
    public void testDeletePositive() throws Exception {
        fillDatabase();

        Assertions.assertTrue(repository.existsById("1"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/delete/1"))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(repository.existsById("1"));
    }

    @Test
    public void testDeleteWrongId() throws Exception {
        fillDatabase();

        mockMvc.perform(MockMvcRequestBuilders.delete("/homework/delete/asdcxz"))
                .andExpect(status().isNotFound());
    }
    // -------------------- Delete endpoint --------------------


    private static StudentDTO getStudentPositive() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("Luke");
        studentDTO.setLastName("Skywalker");
        studentDTO.setGroup("GM-122");
        return studentDTO;
    }

    private static StudentDTO getStudentNegative() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setGroup("abcasd");
        return studentDTO;
    }

    private void fillDatabase() {
        for (int i = 1; i < 6; i++) {
            Student student = new Student(
                    String.valueOf(i),
                    "Name " + i,
                    "Surname " + i,
                    "GM-122");
            repository.save(student);
        }
    }
}
