package br.com.jerp.business;
 
import br.com.jerp.dao.ProfileDao;
import br.com.jerp.dao.utils.DAOFactory;
import br.com.jerp.model.Profile;
import br.com.jerp.model.User;
  
public class ProfileBusiness {
  
    private ProfileDao profileDao;
      
      
    public ProfileBusiness() {
        super();
        setProfileDao(DAOFactory.createProfile());
    }    
      
    private void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }
  
    public Profile getProfileByUser(User user)
    {
        return profileDao.getProfileByUser(user);
    }
    
    public void save(Profile Profile){
        profileDao.save(Profile);
    }    
}
