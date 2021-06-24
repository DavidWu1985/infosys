package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.MenuResource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuNode extends MenuResource {

    private List<MenuNode> nodes = new ArrayList<>();

}
