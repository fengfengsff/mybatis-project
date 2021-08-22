package com.geeknote.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public enum SexEnum {

	MAN(1), WOMAN(2) ;

	private Integer code ;

	SexEnum(Integer code) {
		this.code = code ;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static SexEnum getByCode(Integer code) {
		SexEnum[] values = SexEnum.values();
		for (SexEnum sexEnum : values) {
			if (sexEnum.code.equals(code)) {
				return sexEnum ;
			}
		}
		throw new RuntimeException("性别类型不存在") ;
	}
}
