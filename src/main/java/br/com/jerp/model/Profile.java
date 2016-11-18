package br.com.jerp.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.NaturalId;
import org.primefaces.model.UploadedFile;
 
@Entity
public class Profile implements Serializable {
 
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @NaturalId
    private String email;
    private String sexy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birth;
    private String function;
    private byte[] avatar;
    
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getSexy() {
        return sexy;
    }
 
    public void setSexy(String sexy) {
        this.sexy = sexy;
    }
 
    public Date getBirth() {
        return birth;
    }
 
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
    
    
    
}