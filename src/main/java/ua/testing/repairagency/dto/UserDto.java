package ua.testing.repairagency.dto;

import ua.testing.repairagency.util.Constants;

import javax.validation.constraints.Pattern;

public class UserDto {
    private Long userId;
    private String ua_name;
    private String en_name;
    private String username;
    private String password;
    private boolean enabled;
    private int idAuthority;


    public Long getUserId() {
        return userId;
    }

    public String getUa_name() {
        return ua_name;
    }

    public void setUa_name(String ua_name) {
        this.ua_name = ua_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    @Pattern(message = Constants.USERNAME_VALIDATION_PROPERTY, regexp = Constants.USERNAME_VALIDATION_REGEX)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Pattern(message = Constants.PASSWORD_VALIDATION_PROPERTY,regexp = Constants.PASSWORD_VALIDATION_REGEX)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getIdAuthority() {
        return idAuthority;
    }

    public void setIdAuthority(int idAuthority) {
        this.idAuthority = idAuthority;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
