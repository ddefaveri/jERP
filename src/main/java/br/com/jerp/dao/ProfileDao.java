package br.com.jerp.dao;

import br.com.jerp.model.Profile;
import br.com.jerp.model.User;
 
public interface ProfileDao {
     
    public void save(Profile profile);
    public Profile getProfileByUser(User user);
 
}