package com.geeknote.handler;

import com.geeknote.constant.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
		preparedStatement.setInt(i, sexEnum.getCode());
	}

	@Override
	public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return SexEnum.getByCode(resultSet.getInt(s));
	}

	@Override
	public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return SexEnum.getByCode(resultSet.getInt(i));
	}

	@Override
	public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return SexEnum.getByCode(callableStatement.getInt(i));
	}
}
