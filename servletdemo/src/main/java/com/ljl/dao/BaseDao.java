package com.ljl.dao;

import java.util.List;

/**
 * 公共DAO接口设计
 * @author ljl
 * @version 1.0
 */
public interface BaseDao<T> {
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	int save(T entity) throws Exception;
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	int update(T entity) throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Object id) throws Exception;
	/**
	 * 查询全部
	 * @return
	 */
	List<T> findAll() throws Exception;
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	T findById(Object id) throws Exception;

}
