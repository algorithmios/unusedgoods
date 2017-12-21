package edu.swust.goods.service.impl;

import java.util.List;

import edu.swust.goods.dao.IBaseDao;
import edu.swust.goods.service.IBaseService;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	protected IBaseDao<T> baseDao;
	
	public IBaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public void saveOrUpdate(T t) {
		baseDao.saveOrUpdate(t);
	}

	public void delete(Long id) {
		baseDao.delete(id);
	}

	public T get(Long id) {
		return baseDao.get(id);
	}

	public List<T> getAll() {
		return baseDao.getAll();
	}
	
	public List<?> getList(Integer start, Integer size) {
        return null;
    }
	
}
