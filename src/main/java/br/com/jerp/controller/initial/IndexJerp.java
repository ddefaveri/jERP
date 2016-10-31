package br.com.jerp.controller.initial;

import br.com.jerp.model.Profile;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@RequestScoped
@ManagedBean
public class IndexJerp {

    @PostConstruct
    public void init() {
        System.out.println("Jerp executado!");
    }

    public Profile getProfile() {
        FacesContext context = FacesContext.getCurrentInstance();
        Profile profile = (Profile) context.getExternalContext().getSessionMap().get("profile");
        
        return profile;
    }
}
