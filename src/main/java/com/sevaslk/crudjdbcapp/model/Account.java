package com.sevaslk.crudjdbcapp.model;

import java.util.Objects;

public class Account {
    private Long id;
    private AccountStatus accountStatus;

    public Account(Long id, AccountStatus accountStatus) {
        this.id = id;
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                accountStatus == account.accountStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
