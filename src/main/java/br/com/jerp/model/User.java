package br.com.jerp.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NaturalId;
  
@Entity
@Table(name="user")
public class User implements Serializable{
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String login;
    private String password;    
    private Boolean active;
    private String validation;
      
    @ElementCollection(targetClass = String.class)
    @JoinTable(name = "user_permission",
               uniqueConstraints = { @UniqueConstraint (columnNames = {"id", "permission" })},
               joinColumns = @JoinColumn(name = "id"))
    @Column(name = "permission", length = 40)
    @Cascade(CascadeType.DELETE)
    private Set<String> permissions = new HashSet<String>();    
  
    public String getLogin() {
        return login;
    }
  
    public void setLogin(String login) {
        this.login = login;
    }
  
    public String getPassword() {
        return password;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }
  
    public Boolean isActive() {
        return active;
    }
  
    public void setActive(Boolean active) {
        this.active = active;
    }    
  
    public Set<String> getPermissions() {
        return permissions;
    }
  
    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
  
    public Boolean getActive() {
        return active;
    }
  
    public String getValidation() {
        return validation;
    }
  
    public void setValidation(String validation) {
        this.validation = validation;
    }    
    public Long getId() {
        return id;
    }
  
    public void setId(Long id) {
        this.id = id;
    }    
}