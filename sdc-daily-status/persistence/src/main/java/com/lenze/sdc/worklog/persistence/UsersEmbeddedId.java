package com.lenze.sdc.worklog.persistence;

import java.io.Serializable;
import java.util.Objects;

public class UsersEmbeddedId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
    private String userName;

    // Default constructor
    public UsersEmbeddedId() {}

    public UsersEmbeddedId(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEmbeddedId that = (UsersEmbeddedId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName);
    }
}
