package br.com.jerp.dao;

import br.com.jerp.model.User;
 
public interface UserDao {
     public boolean existsValidation(String validation);
     public void save(User user);
     public User existsUser(User user);
}