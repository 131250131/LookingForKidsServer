package dao.impl.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import bean.User;
import dao.user.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public void register(User user) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByEmail(String email) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		return (List<User>)hibernateTemplate.findByNamedParam("from bean.User u where u.email=:email", "email", email);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		return (List<User>)hibernateTemplate.findByNamedParam("from bean.User u where u.phonenumber=:phoneNumber", "phoneNumber", phoneNumber);
	}

	@SuppressWarnings("unchecked")
	public User getInfo(int userID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<User> users = (List<User>) hibernateTemplate.findByNamedParam("from bean.User u where u.userID=:id", "id", userID);
		if (users.size()>0) {
			return users.get(0);
		}
		return null;
	}

}
