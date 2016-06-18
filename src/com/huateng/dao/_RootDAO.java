package com.huateng.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class _RootDAO<T> extends HibernateDaoSupport {

	protected abstract Class getReferenceClass();

	protected Object load(final Class refClass, final Serializable key) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.load(refClass, key);
			}
		});
	}

	protected List loadAll(Class refClass) {
		return getHibernateTemplate().loadAll(refClass);
	}

	protected Object get(final Class refClass, final Serializable key) {
		return getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.get(refClass, key);
			}
		},false);
	}

	protected Serializable save(final Object obj) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,SQLException {
				session.save(obj);
				return null;
			}
		},false);
		return null;
	}
	protected void saveOrUpdate(final Object obj) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,SQLException {
				session.saveOrUpdate(obj);
				return null;
			}
		},false);
	}

	protected void update(final Object obj) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.update(obj);
				return null;
			}
		});
	}
	protected void merge(final Object obj) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.merge(obj);
				return null;
			}
		});
	}

	protected void delete(final Object obj) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.delete(obj);
				return null;
			}
		});
	}

	protected void refresh(final Object obj) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.refresh(obj);
				return null;
			}
		});
	}

	public void flush() {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.flush();
				return null;
			}
		});
	}

	public List findByNamedQuery(String name) {
		return getHibernateTemplate().findByNamedQuery(name);
	}

	public List findByNamedQuery(String name, Map params) {
		return findByNamedQuery(name, params, -1, -1);
	}

	public List findByNamedQuery(final String name, final Map params,
			final int begin, final int count) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.getNamedQuery(name);
				if (null != params) {
					for (Iterator i = params.entrySet().iterator(); i.hasNext();) {
						Map.Entry entry = (Map.Entry) i.next();
						query.setParameter((String) entry.getKey(),
								entry.getValue());
					}
				}
				if (begin >= 0) {
					query.setFirstResult(begin);
					query.setMaxResults(count);
				}
				return query.list();
			}
		});
	}

	public List findByNamedQuery(String name, Serializable[] params) {
		return findByNamedQuery(name, params, -1, -1);
	}

	public List findByNamedQuery(final String name,
			final Serializable[] params, final int begin, final int count) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.getNamedQuery(name);
				if (null != params) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				if (begin >= 0) {
					query.setFirstResult(begin);
					query.setMaxResults(count);
				}
				return query.list();
			}
		});
	}

	/**
	 * 根据字段查询
	 * 
	 * @param params
	 *            字段列表
	 * @param values
	 *            值列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryByProperty(final Class<T> className,final Map<String, Object> params) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(className);
				for (String field : params.keySet())
					criteria.add(Property.forName(field).eq(params.get(field)));
				return criteria.list();
			}
		});

	}

	public <T> T queryByField(final Class<T> cls,final String sqlWhe,final String [] params) {
		List<T> data =queryByFieldList(cls, sqlWhe, params);
		if(data.size() > 0)
			return data.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryByFieldList(final Class<T> cls,final String sqlWhe,final String [] params) {
		List<T> data =getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,SQLException {
				String hql = "from "+cls.getName()+" as t where 1=1 "+sqlWhe;
				Query query = session.createQuery(hql);
				int i=0;
				for(String param : params){
					query.setParameter(i++, param);
				}
				return query.list();
			}
		});
		return data;
	}
	
	
	protected void forBatchSave(final List<T> objList) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,SQLException {
				int size = objList.size() ;
				for ( int i=0; i<size; i++ ) {   
				    session.save(objList.get(i));   
				    if ( i % 50 == 0 ) {   
				          session.flush();   
				          session.clear();   
				    }   
				}   
				return null;
			}
		});
	}
	
	
	protected void forBatchUpdate(final List<T> objList) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,SQLException {
				int size = objList.size() ;
				for ( int i=0; i<size; i++ ) {   
				    session.update(objList.get(i));   
				    if ( i % 50 == 0 ) {   
				          session.flush();   
				          session.clear();   
				    }   
				}
				return null;
			}
		});
	}
}
