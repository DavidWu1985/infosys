package com.rzschool.infosys.service;

import com.rzschool.infosys.config.SchoolAccount;
import com.rzschool.infosys.db.entity.RzRole;
import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.repository.RoleRepository;
import com.usthe.sureness.provider.SurenessAccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountProvider implements SurenessAccountProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public SchoolAccount loadAccount(String account) {
        RzUser user = userService.getAccount(account);
        if(user == null){
            return null;
        }
        List<String> roles = roleRepository.findByUserId(user.getId()).stream().map(RzRole::getRoleCode).collect(Collectors.toList());
        SchoolAccount sAccount = SchoolAccount.builder(user.getAccount()).setPassword(user.getUserPwd()).setOwnRoles(roles).setUserId(user.getId()).build();
        return sAccount;
    }
}
