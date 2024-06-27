package com.hillel.multi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.model.LessonDTO;
import com.hillel.multi.persistent.repositories.TimetableManagerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class TimetableManagerTests {

    @Autowired
    private TimetableManagerRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void setUp() {
        repository.deleteAll();
    }

    // -------------------- Get endpoint --------------------
    @Test
    @Sql("/test-data.sql")
    public void testGetPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/timetable/get?group=GM-122&subject=Math"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].subject").value("Math"))
                .andExpect(jsonPath("$[*].group").value("GM-122"));

    }
    // -------------------- Get endpoint --------------------


    // -------------------- Update endpoint --------------------
    @Test
    @Sql("/test-data.sql")
    public void testUpdatePositive() throws Exception {
        LessonDTO lessonDTO = getDTO();
        String requestContent = objectMapper.writeValueAsString(lessonDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/timetable/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isOk());
    }

    @Test
    @Sql("/test-data.sql")
    public void testUpdateWrongId() throws Exception {
        LessonDTO lessonDTO = getDTO();
        String requestContent = objectMapper.writeValueAsString(lessonDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/timetable/update/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("provideIncorrectUpdateParams")
    @Sql("/test-data.sql")
    public void testUpdateWrongParams(LessonDTO lessonDTO) throws Exception {
        String requestContent = objectMapper.writeValueAsString(lessonDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/timetable/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest());
    }
    // -------------------- Update endpoint --------------------


    // -------------------- Add endpoint --------------------
    @Test
    public void testAddPositive() throws Exception {
        LessonDTO lessonDTO = getDTO();
        String requestContent = objectMapper.writeValueAsString(lessonDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/timetable/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, repository.findAll().size());
    }

    @ParameterizedTest
    @MethodSource("provideIncorrectAddParams")
    public void testAddWrongParams(LessonDTO lessonDTO) throws Exception {
        String requestContent = objectMapper.writeValueAsString(lessonDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/timetable/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest());
        Assertions.assertEquals(0, repository.findAll().size());
    }
    // -------------------- Add endpoint --------------------


    // -------------------- Delete endpoint --------------------
    @Test
    @Sql("/test-data.sql")
    public void testDeletePositive() throws Exception {
        Assertions.assertTrue(repository.existsById(1L));
        mockMvc.perform(MockMvcRequestBuilders.delete("/timetable/delete/1"))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(repository.existsById(1L));
    }

    @Test
    @Sql("/test-data.sql")
    public void testDeleteWrongId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/timetable/delete/-1"))
                .andExpect(status().isNotFound());
    }
    // -------------------- Delete endpoint --------------------


    private static LessonDTO getDTO() {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.timestamp("2023-12-23 10:46:31");
        lessonDTO.group("GM-122");
        lessonDTO.subject("Math");
        return lessonDTO;
    }

    private static Stream<Arguments> provideIncorrectAddParams() {
        // Wrong group format
        LessonDTO dto1 = getDTO();
        dto1.group("ABC");

        // Wrong timestamp format
        LessonDTO dto2 = getDTO();
        dto2.timestamp("123");

        // Contains null fields
        LessonDTO dto3 = getDTO();
        dto3.setSubject(null);

        return Stream.of(
                Arguments.of(dto1),
                Arguments.of(dto2),
                Arguments.of(dto3)
        );
    }

    private static Stream<Arguments> provideIncorrectUpdateParams() {
        // Wrong group format
        LessonDTO dto1 = getDTO();
        dto1.group("ABC");

        // Wrong deadline format
        LessonDTO dto2 = getDTO();
        dto2.timestamp("123");

        return Stream.of(
                Arguments.of(dto1),
                Arguments.of(dto2)
        );
    }
}
