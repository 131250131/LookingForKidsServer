package dao.impl;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.BaseDao;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	public void saveObject(Object obj) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(obj);
	}

}
