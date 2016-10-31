package br.com.jerp.dao.utils;
 
import br.com.jerp.dao.ProfileDao;
import br.com.jerp.dao.ProfileDaoImpl;
import br.com.jerp.dao.UserDao;
import br.com.jerp.dao.UserDaoImpl;
  
public class DAOFactory {
  
    public static ProfileDao createProfile(){
        ProfileDaoImpl profileDaoImpl = new ProfileDaoImpl();
        profileDaoImpl.setSession(HibernateUtil.getSessionFactory().openSession());
        return profileDaoImpl;
    }
    
    public static UserDao createUser(){
        System.out.println("Entrei no método para CreateUser");
        UserDaoImpl userImpl = new UserDaoImpl();
        userImpl.setSession(HibernateUtil.getSessionFactory().openSession());
        System.out.println("Iniciei a sessão para o User");
        return userImpl;
    }
}
