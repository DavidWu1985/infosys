package com.rzschool.infosys.result;

import lombok.Getter;

/**
 * 结果返回类
 *
 * @param <T>
 */
@Getter
public class RtnResult<T> {
	/**
	 * 操作标识
	 */
	private int code;
	
	/**
	 * 操作信息
	 */
	private String message;
	
	/**
	 * 数据
	 */
	private T data;

	/**
	 * 成功时调用
	 * @param data 返回数据
	 * @return 封装的结果集
	 */
	public static <T> RtnResult<T> success(T data) {
		return new RtnResult<>(data);
	}
	
	/**
	 * 失败时调用
	 * @param cm 待返回的操作码及操作提示
	 * @return
	 */
	public static <T> RtnResult<T> error(CodeMsg cm) {
		return new RtnResult<T>(cm);
	}

	public static <T> RtnResult<T> errorWithData(CodeMsg cm) {
		return new RtnResult<T>(cm);
	}
	
	/**
	 * 操作成功构造方法
	 * @param data 待返回的数据
	 */
	private RtnResult(T data) {
		this.code = 0;
		this.message = "success";
		this.data = data;
	}
	
	/**
	 * 操作失败构造方法
	 * @param cm 待返回的操作码及操作提示
	 */
	private RtnResult(CodeMsg cm) {
		if (cm == null) {
			return;
		}
		this.code = cm.getCode();
		this.message = cm.getMessage();
		if(cm.getData() != null){
			this.data = (T)cm.getData();
		}
	}

}