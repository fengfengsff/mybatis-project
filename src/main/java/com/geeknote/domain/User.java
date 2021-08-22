package com.geeknote.domain;

import com.geeknote.constant.SexEnum;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

	private String name ;

	private Integer age ;

	private SexEnum sex ;
}
