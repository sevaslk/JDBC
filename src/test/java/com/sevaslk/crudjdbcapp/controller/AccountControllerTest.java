package com.sevaslk.crudjdbcapp.controller;

import com.sevaslk.crudjdbcapp.model.Account;
import com.sevaslk.crudjdbcapp.model.AccountStatus;
import com.sevaslk.crudjdbcapp.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AccountControllerTest {
    private static final Account ACCOUNT_TEST_INSTANCE = new Account(111, AccountStatus.DELETED);

    @InjectMocks
    private AccountRepository accountRepository = mock(AccountRepository.class);
    @InjectMocks
    private AccountController controllerUnderTest = mock(AccountController.class);

    @Test
    void getAll_Accounts() {
        List<Account> accounts = controllerUnderTest.getAll();
        when(accountRepository.getAll()).thenReturn(Collections.emptyList());
        assertTrue(accounts.isEmpty());
    }

    @Test
    void getAllTest_Exception() {
        given(controllerUnderTest.getAll()).willAnswer(invocation -> {
            throw new SQLExpectedException("testException_Accounts");
        });
        Exception exception = assertThrows(SQLExpectedException.class, () -> controllerUnderTest.getAll());
        assertTrue(exception.getMessage().contains("testException_Accounts"));
    }

    @Test
    void getById() {
        when(controllerUnderTest.getById(1)).thenReturn(ACCOUNT_TEST_INSTANCE);
        Account receivedAccount = controllerUnderTest.getById(1);
        assertEquals(ACCOUNT_TEST_INSTANCE, receivedAccount);
    }

    @Test
    void save() {
        when(controllerUnderTest.save(111)).thenReturn(ACCOUNT_TEST_INSTANCE);
        Account savedSkill = controllerUnderTest.save(111);
        assertEquals(ACCOUNT_TEST_INSTANCE, savedSkill);
    }

    @Test
    void update() {
        when(controllerUnderTest.update(111, "DELETED")).thenReturn(ACCOUNT_TEST_INSTANCE);
        Account updatedAccount = controllerUnderTest.update(111, "DELETED");
        assertEquals(ACCOUNT_TEST_INSTANCE, updatedAccount);
    }

    @Test
    void deleteById() {
        controllerUnderTest.deleteById(111);
        verify(controllerUnderTest, times(1)).deleteById(111);
    }

}