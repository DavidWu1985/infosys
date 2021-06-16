package com.rzschool.infosys.service;


import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.UserRole;
import com.rzschool.infosys.db.repository.UserRepository;
import com.rzschool.infosys.db.repository.UserRoleRepository;
import com.rzschool.infosys.db.vo.UserVo;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    public List<UserVo> getSchoolMaster() {
        List<RzUser> users = userRepository.getRzUserByRole(4);
        return users.stream().map(u -> {
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(u, vo);
            vo.setUserId(u.getId());
            vo.setUserPwd("");
            return vo;
        }).collect(Collectors.toList());
    }

    @Transactional
    public boolean save(RzUser rzUser) {
        boolean update = rzUser.getId() != null;
        RzUser user = userRepository.save(rzUser);
        if (!update) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(4);//校长Id
            userRoleRepository.save(userRole);
        }
        return true;
    }

    public RzUser getAccount(String account) {
        return userRepository.findByAccount(account);
    }
}
