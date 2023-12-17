package edu.school21.models;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private Boolean authenticationSuccessStatus;

    public User (Long id, String login, String password, Boolean authenticationSuccessStatus) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authenticationSuccessStatus = authenticationSuccessStatus;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAuthenticationSuccessStatus() {
        return authenticationSuccessStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthenticationSuccessStatus(Boolean authenticationSuccessStatus) {
        this.authenticationSuccessStatus = authenticationSuccessStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(authenticationSuccessStatus, user.authenticationSuccessStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, authenticationSuccessStatus);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authenticationSuccessStatus=" + authenticationSuccessStatus +
                '}';
    }
}
