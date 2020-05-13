package ua.testing.repairagency.model;

import ua.testing.repairagency.dao.Identified;

public class User implements Identified<Long> {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    @Override
    public Long getId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public static class Builder{
        private User newUser;

        public Builder(){
            newUser = new User();
        }

        public Builder userId(Long userId){
            newUser.userId = userId;
            return this;
        }

        public Builder en_name(String en_name){
            newUser.en_name = en_name;
            return this;
        }

        public Builder ua_name(String ua_name){
            newUser.ua_name = ua_name;
            return this;
        }

        public Builder username(String username){
            newUser.username = username;
            return this;
        }

        public Builder password(String password){
            newUser.password = password;
            return this;
        }

        public Builder enabled(boolean enabled ){
            newUser.enabled = enabled;
            return this;
        }

        public Builder idAuthority(int idAuthority){
            newUser.idAuthority = idAuthority;
            return this;
        }

        public User build(){
            return newUser;
        }


    }
}
