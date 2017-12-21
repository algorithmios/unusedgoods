package edu.swust.goods.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import edu.swust.goods.dao.IBaseDao;


public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
private Class<T> entityClass;
	
	public Class<T> getEntityClass() {
		return entityClass;
	}


	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}


	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}


	public void delete(Long id) {
		T t = getHibernateTemplate().get(entityClass, id);
		if (null != t) {
			getHibernateTemplate().delete(t);
		}
	}

	public T get(Long id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public List<T> getAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object executeUpdateHql(final String hql, final Object[] objects) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < objects.length; i++) {

					query.setParameter(i, objects[i]);
				}
				return (int)query.executeUpdate();
			}
			
		});
		return null;
	}
	
	public List<?> find(final String hql, final Object[] objects, final Integer start, final Integer size) {
		return (List<?>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (objects != null) {
					int j = 0;
					for (int i = 0; i < objects.length; i++) {
						if (objects[i] == null) {
							break;
						}
						query.setParameter(j, objects[i]);
						j++;
					}
				}
				if (null != start && null != size ) {
					query.setMaxResults(size);
					query.setFirstResult(start);
				}
				
				return query.list();
			}
			
		});
	}
	
}