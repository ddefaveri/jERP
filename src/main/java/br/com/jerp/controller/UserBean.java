package br.com.jerp.controller;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.com.jerp.business.ProfileBusiness;
import br.com.jerp.business.UserBusiness;
import br.com.jerp.dao.UserDao;
import br.com.jerp.dao.UserDaoImpl;
import br.com.jerp.dao.utils.HibernateUtil;
import br.com.jerp.model.Profile;
import br.com.jerp.model.User;
import br.com.jerp.utils.GenerateValidation;
import br.com.jerp.utils.Role;
import br.com.jerp.utils.ManipulateDate;
import br.com.jerp.utils.security.GenerateMD5;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "userMB")
@RequestScoped
public class UserBean {

    private User user = null;
    private Profile profile = null;

    private int day = 0;
    private int month = 0;
    private int year = 0;

    @PostConstruct
    public void init() {
        user = new User();
        profile = new Profile();
    }

    public void isLogged() {
        System.out.println("Estou verificando se o usuário está logado.");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Profile profile = (Profile) context.getExternalContext().getSessionMap().get("profile");
            if (profile != null) {
                System.out.println("O usuário está logado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("O usuário não está logado. Exc.");

        }
        System.out.println("O usuário não está logado.");
        //String uri = "/site/login.xhtml?faces-redirect=true";
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        try {
            extContext.redirect(extContext.getRequestContextPath() + "/site/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String doLogin() {
        System.out.println("Entrei no MB User");
        FacesContext context = FacesContext.getCurrentInstance();

        UserBusiness userBusiness = new UserBusiness();
        System.out.println("Estanciei o objeto UserBusiness: " + userBusiness);
        User userLogged = userBusiness.existsUser(user);
        if (userLogged != null) {
            ProfileBusiness profileBusiness = new ProfileBusiness();
            Profile profileUser = profileBusiness.getProfileByUser(userLogged);
            if (profileUser != null) {
                context.getExternalContext().getSessionMap().put("user", userLogged);
                context.getExternalContext().getSessionMap().put("profile", profileUser);

                return "/sistema/jerp/indexjerp.xhtml?faces-redirect=true";
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocorreu um erro para carregar o profile do usuário."));
            return null;
        }
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuário ou senha não encontrados."));
        return null;
    }

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/site/login.xhtml?faces-redirect=true";
    }

    public String doRecuperarSenha() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "O e-mail não está cadastrado ainda, tente outro e-mail." + user.getLogin()));
        return null;
        //return "login.xhtml?faces-redirect=true";
    }

    public String save() {

        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            User userUnique = (User) session.createQuery("SELECT u FROM User u WHERE login=:login")
                    .setParameter("login", user.getLogin())
                    .uniqueResult();
            Profile profileUnique = (Profile) session.createQuery("SELECT p FROM Profile p WHERE email=:email")
                    .setParameter("email", profile.getEmail())
                    .uniqueResult();
            tx.commit();
            session.close();

            if (userUnique == null && profileUnique == null) {
                ProfileBusiness profileBusiness = new ProfileBusiness();

                user.setPassword(GenerateMD5.generate(user.getPassword()));
                user.setValidation(GenerateValidation.keyValidation());
                user.getPermissions().add(Role.ROLE_COMMON.getValue());
                user.setActive(true);

                profile.setUser(user);
                profile.setBirth(ManipulateDate.getDate(year, month, day));

                profileBusiness.save(profile);

                return "/site/feedback_login";
            } else {
                System.out.println("Não é unico!");
                return "";
            }
        } catch (Exception e) {
            System.out.println("Fudeu tudo: " + e.getMessage());
            return "";
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Map<String, Object> getDays() {
        return ManipulateDate.getDays();
    }

    public Map<String, Object> getMonths() {
        return ManipulateDate.getMonths();
    }

    public Map<String, Object> getYears() {
        return ManipulateDate.getYears();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
