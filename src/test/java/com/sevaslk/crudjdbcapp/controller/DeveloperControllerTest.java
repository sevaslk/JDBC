package com.sevaslk.crudjdbcapp.controller;

import com.sevaslk.crudjdbcapp.model.Developer;
import com.sevaslk.crudjdbcapp.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class DeveloperControllerTest {
    private static final Developer DEVELOPER_TEST_INSTANCE = new Developer(1, "John Doe", null, null);

    @InjectMocks
    private DeveloperRepository developerRepository = mock(DeveloperRepository.class);
    @InjectMocks
    private DeveloperController controllerUnderTest = mock(DeveloperController.class);

    @Test
    void getAll_Developers() {
        List<Developer> developers = controllerUnderTest.getAll();
        when(developerRepository.getAll()).thenReturn(Collections.emptyList());
        assertTrue(developers.isEmpty());
    }

    @Test
    void getAllTest_Exception() {
        given(controllerUnderTest.getAll()).willAnswer(invocation -> {
            throw new SQLExpectedException("testException_developer");
        });
        Exception exception = assertThrows(SQLExpectedException.class, () -> controllerUnderTest.getAll());
        assertTrue(exception.getMessage().contains("testException_developer"));
    }

    @Test
    void getById() {
        when(controllerUnderTest.getById(1)).thenReturn(DEVELOPER_TEST_INSTANCE);
        Developer receivedDeveloper = controllerUnderTest.getById(1);
        assertEquals(DEVELOPER_TEST_INSTANCE, receivedDeveloper);
    }

    @Test
    void save() {
        when(controllerUnderTest.save(1, "John Doe")).thenReturn(DEVELOPER_TEST_INSTANCE);
        Developer savedDeveloper = controllerUnderTest.save(1, "John Doe");
        assertEquals(DEVELOPER_TEST_INSTANCE, savedDeveloper);
    }

    @Test
    void update() {
        when(controllerUnderTest.update(1, "John Doe")).thenReturn(DEVELOPER_TEST_INSTANCE);
        Developer updatedDeveloper = controllerUnderTest.update(1, "John Doe");
        assertEquals(DEVELOPER_TEST_INSTANCE, updatedDeveloper);
    }

    @Test
    void deleteById() {
        controllerUnderTest.deleteById(1);
        verify(controllerUnderTest, times(1)).deleteById(1);
    }

}
