package br.com.jerp.controller;

import br.com.jerp.business.ProfileBusiness;
import br.com.jerp.model.Profile;
import br.com.jerp.model.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "colaboradorMB")
@RequestScoped
public class ColaboradorMB {

    private List<Profile> profiles = null;
    private Profile selectedProfile;

    @PostConstruct
    public void init() {
        System.out.println("Entrei no MB do Calaborador.");
        this.setProfiles();
    }

    public void testeMB() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Your message: "));
        System.out.println("Teste de MB.");
    }
    
    public List<Profile> getProfiles() {
        return this.profiles;
    }
    
    public void setProfiles() {
        ProfileBusiness bnsProfile = new ProfileBusiness();
        profiles = bnsProfile.getProfiles();
    }

    public Profile getSelectedProfile() {
        System.out.println("Retornei a linha selecionada. ");
        return selectedProfile;
    }

    public void setSelectedProfile(Profile p_selectedProfile) {
        System.out.println("Selecionei a linha no data grid.");
        this.selectedProfile = p_selectedProfile;
    }
}
