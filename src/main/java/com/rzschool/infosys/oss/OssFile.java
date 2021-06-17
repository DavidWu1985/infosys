package com.rzschool.infosys.oss;

import lombok.Data;

@Data
public class OssFile {
	/**
	 * 文件地址
	 */
	private String link;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 初始文件名
	 */
	private String originalName;
}
