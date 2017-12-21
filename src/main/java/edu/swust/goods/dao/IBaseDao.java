package edu.swust.goods.dao;

import java.util.List;

public interface IBaseDao<T> {
	void saveOrUpdate(T t);

    void delete(Long id);
    
    T get(Long id);
    
    List<T> getAll();
	
	Object executeUpdateHql(final String hql, final Object[] objects);
	
	List<?> find(final String hql, final Object[] objects, final Integer start, final Integer size);
}
