package com.sevaslk.crudjdbcapp.repository.jdbc;

import com.sevaslk.crudjdbcapp.controller.DeveloperController;
import com.sevaslk.crudjdbcapp.model.Developer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JDBCSkillRepositoryImplTest {

    private static final Developer testDeveloper = new Developer(1, "Bob", null, null);
    private static final DeveloperController DEVELOPER_CONTROLLER_MOCK = mock(DeveloperController.class);
    private DeveloperController developerController = new DeveloperController();

    @BeforeAll
    static void setup() {
        when(DEVELOPER_CONTROLLER_MOCK.save(1, "Bob")).thenReturn(testDeveloper);
        when(DEVELOPER_CONTROLLER_MOCK.update(1, "Bob")).thenReturn(testDeveloper);
    }


    @Test
    void getAll() {
        assertNotNull(developerController.getAll());
    }

    @Test
    void getById() {
        Developer developer = new Developer(5, "Billy", null, null);
        assertEquals(developerController.getById(5), developer);

    }

    @Test
    void save() {
        assertEquals(testDeveloper, DEVELOPER_CONTROLLER_MOCK.save(1, "Bob"));
    }

    @Test
    void update() {
        assertEquals(testDeveloper, DEVELOPER_CONTROLLER_MOCK.update(1, "Bob"));

    }

    @Test
    void deleteById() {
    }
}