package edu.swust.goods.service;

import java.util.List;

public interface IBaseService<T> {
	void saveOrUpdate(T t);

    void delete(Long id);
    
    T get(Long id);
    
    List<T> getAll();
    
    List<?> getList(Integer start, Integer size);
//    
//    List<KeyValueMessage> getContent(Long id);
}
