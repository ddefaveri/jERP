package br.com.jerp.dao;

import org.hibernate.Session;
import br.com.jerp.model.Profile;
import br.com.jerp.model.User;
import br.com.jerp.utils.security.GenerateMD5;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

public class ProfileDaoImpl implements ProfileDao {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void save(Profile profile) {
        this.session.saveOrUpdate(profile);
    }

    @Override
    public Profile getProfileByUser(User user) {
        try {
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.add(Restrictions.eq("user", user));
            Profile profile = (Profile) criteria.uniqueResult();
            return profile;
        } catch (NoResultException nre) {
            nre.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<Profile> getProfiles(){
        try {
            Criteria criteria = session.createCriteria(Profile.class);
            List<Profile> profile = criteria.list();
            return profile;
        } catch (NoResultException nre) {
            nre.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
