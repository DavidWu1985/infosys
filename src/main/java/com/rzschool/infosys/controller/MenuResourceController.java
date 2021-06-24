package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.vo.MenuNode;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.MenuResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus/**")
public class MenuResourceController {

    @Autowired
    private MenuResourceService menuResourceService;

    @GetMapping
    public RtnResult<List<MenuNode>> getMenusByUserRole(){
        return RtnResult.success(menuResourceService.getMenus());
    }

}
