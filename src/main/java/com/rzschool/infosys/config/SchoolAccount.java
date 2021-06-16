package com.rzschool.infosys.config;

import com.usthe.sureness.provider.SurenessAccount;
import lombok.Setter;

import java.util.List;

public class SchoolAccount implements SurenessAccount {
    private String appId;
    private String password;
    private String salt;
    private List<String> ownRoles;
    private boolean disabledAccount;
    private boolean excessiveAttempts;
    private Integer userId;

    private SchoolAccount(SchoolAccount.Builder builder) {
        this.appId = builder.appId;
        this.password = builder.password;
        this.salt = builder.salt;
        this.ownRoles = builder.ownRoles;
        this.disabledAccount = builder.disabledAccount;
        this.excessiveAttempts = builder.excessiveAttempts;
        this.userId = builder.userId;
    }

    @Override
    public String getAppId() {
        return this.appId;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getSalt() {
        return this.salt;
    }

    @Override
    public List<String> getOwnRoles() {
        return this.ownRoles;
    }

    @Override
    public boolean isDisabledAccount() {
        return this.disabledAccount;
    }

    @Override
    public boolean isExcessiveAttempts() {
        return this.excessiveAttempts;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public static SchoolAccount.Builder builder(String appId) {
        return new SchoolAccount.Builder(appId);
    }

    public static class Builder {
        private String appId;
        private String password;
        private String salt;
        private List<String> ownRoles;
        private boolean disabledAccount;
        private boolean excessiveAttempts;
        private Integer userId;

        public Builder(String appId) {
            this.appId = appId;
        }

        public SchoolAccount.Builder setAppId(String appId) {
            this.appId = appId;
            return this;
        }

        public SchoolAccount.Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public SchoolAccount.Builder setSalt(String salt) {
            this.salt = salt;
            return this;
        }

        public SchoolAccount.Builder setOwnRoles(List<String> ownRoles) {
            this.ownRoles = ownRoles;
            return this;
        }

        public SchoolAccount.Builder setDisabledAccount(boolean disabledAccount) {
            this.disabledAccount = disabledAccount;
            return this;
        }

        public SchoolAccount.Builder setExcessiveAttempts(boolean excessiveAttempts) {
            this.excessiveAttempts = excessiveAttempts;
            return this;
        }

        public SchoolAccount.Builder setUserId(Integer userId){
            this.userId = userId;
            return this;
        }


        public SchoolAccount build() {
            return new SchoolAccount(this);
        }
    }

}
