package br.com.jerp.business;

import br.com.jerp.dao.UserDao;
import br.com.jerp.dao.utils.DAOFactory;
import br.com.jerp.model.User;
 
public class UserBusiness {
 
    private UserDao userDao;      
     
    public UserBusiness() {
        super();
        setUserDao(DAOFactory.createUser());
        System.out.println("Setei o UserDao: "+userDao.toString());
    }
     
    public boolean existsValidation(String validation) {
        return userDao.existsValidation(validation);
    }
 
    public User existsUser(User user) {
        System.out.println("Entrei no método para verificar se o usuário existe no UserBusiness");
        return userDao.existsUser(user);
    }
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }     
}