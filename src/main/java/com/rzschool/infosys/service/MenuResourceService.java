package com.rzschool.infosys.service;


import com.rzschool.infosys.db.entity.MenuResource;
import com.rzschool.infosys.db.repository.MenuResourceRepository;
import com.rzschool.infosys.db.repository.RoleMenuRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuResourceService {

    @Autowired
    private MenuResourceRepository menuResourceRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    /**
     * 需要鉴权的url
     * @return
     */
    public Set<String> getAllEnableResourcePath() {
        Set<String> set = new HashSet<>();
        List<MenuResource> menus = menuResourceRepository.findAllByIsDeletedAndExcluded(0, 0);
        List<Object[]> roleMenus = roleMenuRepository.getRoleMenuByMenuIds(menus.stream().map(MenuResource::getId).collect(Collectors.toList()));
        menus.forEach(m->{
            StringBuilder builder = new StringBuilder();
            List<String> roles = roleMenus.stream().filter(r->{return m.getId().equals((Integer) r[1]);}).map(f->{
                return (String)f[0];
            }).collect(Collectors.toList());
            builder.append(m.getAction()).append("===").append(m.getMethod()).append("===[").append(StringUtils.join(roles,",")).append("]");
            set.add(builder.toString());
        });
        return set;
    }

    /**
     * 不需要鉴权的url,直接在set中添加
     * @return
     */
    public Set<String> getAllDisableResourcePath() {
        Set<String> set = new HashSet<>();
        set.add("/account/auth===post");
        return set;
    }
}
