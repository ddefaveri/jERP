package br.com.jerp.controller.initial;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class Index {

    @PostConstruct
    public void init() {
        System.out.println("Bean executado!");
    }

    public String getMessage() {
        return "Hello World JSF!";
    }

    public String register() {
        return "site/register.xhtml";
    }

    public String index() {
        return "site/index.xhtml";
    }

    public String login() {
        return "site/login.xhtml";
    }
}
