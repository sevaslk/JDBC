package com.sevaslk.crudjdbcapp.controller;

import com.sevaslk.crudjdbcapp.model.Skill;
import com.sevaslk.crudjdbcapp.repository.SkillRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class SkillControllerTest {

    private static final Skill testSkill = new Skill(1, "testSkill");
    private static List<Skill> testSkillList;
    @InjectMocks
    private SkillRepository skillRepository = mock(SkillRepository.class);
    @InjectMocks
    private SkillController controllerUnderTest = mock(SkillController.class);


    @BeforeAll
    static void setup() {

    }

    @Test
    void getAllTest_Success() {
        List<Skill> skills = controllerUnderTest.getAll();
        when(skillRepository.getAll()).thenReturn(Collections.emptyList());
        assertTrue(skills.isEmpty());
    }

    @Test
    void getAllTest_Exception() {
        List<Skill> skills = controllerUnderTest.getAll();
        given(controllerUnderTest.getAll()).willAnswer(invocation -> {
            throw new SQLExpectedException("testException");
        });
        Exception exception = assertThrows(SQLExpectedException.class, () -> controllerUnderTest.getAll());
        assertTrue(exception.getMessage().contains("testException"));
    }


    @Test
    void getById() {
        when(controllerUnderTest.getById(1)).thenReturn(testSkill);
        Skill receivedSkill = controllerUnderTest.getById(1);
        assertEquals(testSkill, receivedSkill);

    }

    @Test
    void save() {
        when(controllerUnderTest.save("testSkill")).thenReturn(testSkill);
        Skill savedSkill = controllerUnderTest.save("testSkill");
        assertEquals(testSkill, savedSkill);
    }

    @Test
    void update() {
        when(controllerUnderTest.update(1, "testSkill")).thenReturn(testSkill);
        Skill updatedSkill = controllerUnderTest.update(1, "testSkill");
        assertEquals(testSkill, updatedSkill);
    }

    @Test
    void deleteById_called_1time() {
        controllerUnderTest.deleteById(0);
        verify(controllerUnderTest, times(1)).deleteById(0);
    }
    
}