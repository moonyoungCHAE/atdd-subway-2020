package wooteco.subway.members.member.domain;


import wooteco.security.core.userdetails.UserDetails;

public class LoginMember implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Integer age;

    public LoginMember(Long id, String email, String password, Integer age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return this.email;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public boolean checkCredentials(Object credentials) {
        String password = (String) credentials;
        if (this.password == null || password == null) {
            return false;
        }

        return this.password.equals(password);
    }
}
