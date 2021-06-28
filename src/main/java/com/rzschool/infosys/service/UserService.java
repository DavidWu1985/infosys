package com.rzschool.infosys.service;


import com.rzschool.infosys.constant.RoleConstant;
import com.rzschool.infosys.constant.ServiceException;
import com.rzschool.infosys.db.dto.UserDto;
import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.SchoolTeacher;
import com.rzschool.infosys.db.entity.UserRole;
import com.rzschool.infosys.db.repository.SchoolTeacherRepository;
import com.rzschool.infosys.db.repository.UserRepository;
import com.rzschool.infosys.db.repository.UserRoleRepository;
import com.rzschool.infosys.db.vo.UserVo;
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

    @Autowired
    private SchoolTeacherRepository schoolTeacherRepository;


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
    public boolean addSchoolMaster(RzUser rzUser) {
        boolean update = rzUser.getId() != null;
        RzUser user = addOrUpdateUser(rzUser);
        if (!update) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(RoleConstant.schoolId);//校长ROLEId
            userRoleRepository.save(userRole);
        }
        return true;
    }

    public RzUser getAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Transactional
    public boolean saveTeacher(UserDto teacher) {
        boolean update = teacher.getId() != null;
        RzUser save = new RzUser();
        BeanUtils.copyProperties(teacher,save);
        RzUser user = addOrUpdateUser(save);
        if(!update){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(RoleConstant.teacherId);//教师ROLEID
            userRoleRepository.save(userRole);
        }
        if(teacher.getId() != null){
            schoolTeacherRepository.deleteByUserIdAndSchoolId(teacher.getId(), teacher.getSchoolId());
        }
        SchoolTeacher teacherAdd = new SchoolTeacher();
        teacherAdd.setUserId(user.getId());
        teacherAdd.setSchoolId(teacher.getSchoolId());
        schoolTeacherRepository.save(teacherAdd);
        return true;
    }



    public RzUser addOrUpdateUser(RzUser user){
        RzUser saved = userRepository.findByAccount(user.getAccount());
        if(user.getId() == null){
            if(saved != null){
                throw new ServiceException("用户账户重复");
            }
        } else {
            if(saved != null && saved.getId() != user.getId()){
                throw new ServiceException("用户账户重复");
            }
        }
        RzUser r = userRepository.save(user);
        return r;
    }
}
