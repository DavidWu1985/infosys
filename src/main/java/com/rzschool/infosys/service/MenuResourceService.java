package com.rzschool.infosys.service;


import com.rzschool.infosys.db.entity.MenuResource;
import com.rzschool.infosys.db.repository.MenuResourceRepository;
import com.rzschool.infosys.db.repository.RoleMenuRepository;
import com.rzschool.infosys.db.vo.MenuNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuResourceService {

    @Autowired
    private MenuResourceRepository menuResourceRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    /**
     * 需要鉴权的url
     *
     * @return
     */
    public Set<String> getAllEnableResourcePath() {
        Set<String> set = new HashSet<>();
        List<MenuResource> menus = menuResourceRepository.findAllByIsDeletedAndExcluded(0, 0);
        List<Object[]> roleMenus = roleMenuRepository.getRoleMenuByMenuIds(menus.stream().map(MenuResource::getId).collect(Collectors.toList()));
        menus.forEach(m -> {
            StringBuilder builder = new StringBuilder();
            List<String> roles = roleMenus.stream().filter(r -> {
                return m.getId().equals((Integer) r[1]);
            }).map(f -> {
                return (String) f[0];
            }).collect(Collectors.toList());
            builder.append(m.getAction()).append("===").append(m.getMethod()).append("===[").append(StringUtils.join(roles, ",")).append("]");
            set.add(builder.toString());
        });
        return set;
    }

    /**
     * 不需要鉴权的url,直接在set中添加
     *
     * @return
     */
    public Set<String> getAllDisableResourcePath() {
        Set<String> set = new HashSet<>();
        set.add("/account/auth===post");
        set.add("/menus===get");
        return set;
    }


    public List<MenuNode> getMenus() {
//        List<MenuResource> menus = menuResourceRepository.findAllMenusByRoleId();
//        List<MenuNode> nodeLevel1 = menus.stream().filter(m -> {
//            return 1 == m.getMenuType();
//        }).map(m -> {
//            MenuNode node1 = new MenuNode();
//            BeanUtils.copyProperties(m, node1);
//            return node1;
//        }).collect(Collectors.toList());
//
//        nodeLevel1.forEach(n->{
//            menus.forEach(m->{
//                if(n.getId() == m.getParentId()){
//                    MenuNode node2 = new MenuNode();
//                    BeanUtils.copyProperties(m, node2);
//                    n.getNodes().add(node2);
//                }
//            });
//        });
//        return nodeLevel1;



        List<MenuResource> menusL1 = menuResourceRepository.findMenusLevel1ByRoleId();
        List<MenuResource> menusL2 = menuResourceRepository.findMenusLevel2ByRoleId();
        final Map<Integer, MenuNode> nodeMap = new HashMap<>();
        List<MenuNode> nodesL1 = menusL1.stream().map(m -> {
            MenuNode node1 = new MenuNode();
            BeanUtils.copyProperties(m, node1);
            nodeMap.put(m.getId(), node1);
            return node1;
        }).collect(Collectors.toList());
        menusL2.forEach(m->{
            MenuNode node2 = new MenuNode();
            BeanUtils.copyProperties(m, node2);
            MenuNode node1 = nodeMap.get(node2.getParentId());
            node1.getNodes().add(node2);
        });
        return nodesL1;
    }
}
