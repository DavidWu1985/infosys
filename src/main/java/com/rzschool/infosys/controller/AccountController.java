package com.rzschool.infosys.controller;

import com.rzschool.infosys.config.SchoolAccount;
import com.rzschool.infosys.service.AccountProvider;
import com.usthe.sureness.util.JsonWebTokenUtil;
import com.usthe.sureness.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AccountController {

    @Autowired
    private AccountProvider accountProvider;

    /**
     * login, this provider a get jwt api, convenient to test other api with jwt
     * @param requestBody request
     * @return response
     *
     */
    @PostMapping("/account/auth")
    public ResponseEntity<Object> login(@RequestBody Map<String,String> requestBody) {
        if (requestBody == null || !requestBody.containsKey("username")
                || !requestBody.containsKey("password")) {
            return ResponseEntity.badRequest().build();
        }
        String userName = requestBody.get("username");
        String password = requestBody.get("password");
        SchoolAccount account = accountProvider.loadAccount(userName);
        if (account == null || account.isDisabledAccount() || account.isExcessiveAttempts()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (account.getPassword() != null) {
            if (account.getSalt() != null) {
                password = Md5Util.md5(password + account.getSalt());
            }
            if (!account.getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        // Get the roles the user has - rbac
        List<String> roles = account.getOwnRoles();
        long refreshPeriodTime = 36000L;
        // issue jwt
        Map<String, Object> customMap = new HashMap<>();
        customMap.put("userId", account.getUserId());
        String jwt = JsonWebTokenUtil.issueJwt(UUID.randomUUID().toString(), userName,
                "token-server", refreshPeriodTime >> 1, roles, customMap);
        Map<String, String> body = Collections.singletonMap("token", jwt);
        return ResponseEntity.ok().body(body);
    }

}
