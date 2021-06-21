package com.rzschool.infosys.db.dto;

import com.rzschool.infosys.db.entity.ClassResource;
import lombok.Data;

import java.util.List;

@Data
public class ClassResourceDto extends ClassResource {
     private List<Integer> fileIds;
}
