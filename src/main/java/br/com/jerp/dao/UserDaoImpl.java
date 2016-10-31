package br.com.jerp.dao;

import br.com.jerp.dao.utils.HibernateUtil;
import br.com.jerp.model.Profile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.jerp.model.User;
import br.com.jerp.utils.security.GenerateMD5;
import javax.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

public class UserDaoImpl implements UserDao {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public User existsUser(User user) {
        System.out.println("Entrei para verificar se o usuário existe no DaoUser");
        Criteria criteria = session.createCriteria(User.class);
        Criterion criterionLogin = Restrictions.eq("login", user.getLogin());
        Criterion criterionPasswd = Restrictions.eq("password", GenerateMD5.generate(user.getPassword()));

        LogicalExpression expressionAnd = Restrictions.and(criterionLogin, criterionPasswd);
        criteria.add(expressionAnd);
        try {
            User userLogged = (User) criteria.uniqueResult();
            System.out.println("Encontrei o usuário no banco: " + userLogged.toString() + " - " + userLogged.getLogin());
            System.out.println("Encontrei o usuário viu?");
            return userLogged;
        } catch (NoResultException nre) {
            System.out.println("Não Encontrei nenhum usuário.");
            nre.printStackTrace();
            return null;
        }catch(Exception e){
            System.out.println("Encontrei uma exception");
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean existsValidation(String validation) {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("validation", validation));
        User user = (User) criteria.uniqueResult();

        if (user != null) {
            user.setActive(true);
            user.setValidation("");
            save(user);
            return true;
        }
        return false;
    }

    public void save(User user) {
        session.saveOrUpdate(user);
    }
}
