package com.geeknote.plugin;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.*;

/**
 * 自定义mybatis拦截器，实现打印执行的方法和sql
 *
 * @date 2021.08.22 12:00
 * @author feng
 */

@Slf4j
@Intercepts({
	@Signature(
		type = Executor.class,
		method = "update",
		args = {MappedStatement.class,Object.class}),
	@Signature(
		type = Executor.class,
		method = "query",
		args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}),
	@Signature(
			type = ParameterHandler.class,
			method = "setParameters",
			args = {PreparedStatement.class})
})
public class SqlIndicatorPlugin implements Interceptor {

	private Properties properties ;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 目标方法前处理
		String simpleMethodName = invocation.getMethod().getName();
		Object[] args = invocation.getArgs();
		for (Object arg : args) {
			if (arg instanceof MappedStatement){
				MappedStatement ms = (MappedStatement) arg;
				String id = ms.getId();
				log.info("执行的mapper中的方法是：{}", id);
				break;
			}
		}
		// 执行目标方法
		Object result = invocation.proceed();
		// 目标方法后处理
		if ("setParameters".equals(simpleMethodName)) {
			Object arg = args[0];
			Class<?> aClass = arg.getClass().getSuperclass();
			Field h = aClass.getDeclaredField("h");
			h.setAccessible(true);
			Object o = h.get(arg);
			PreparedStatementLogger psl = (PreparedStatementLogger) o;
			ClientPreparedStatement cpt = (ClientPreparedStatement)psl.getPreparedStatement();
			String sql = cpt.asSql();
			log.info("执行的mapper中的sql是：{}", sql.replaceAll("\\n" ,""));
		}
		return result;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties ;
	}
}
