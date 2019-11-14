/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import com.sun.tools.jxc.ap.Const;
import controllers.UserFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import models.User;

/**
 *
 * @author sandr
 */
//@Named(value = "login")
@ManagedBean(name = "login", eager = true)
@RequestScoped
public class Login implements Serializable {

    private String email;
    private String password;

    private HttpServletRequest httpservlet;

    @EJB
    private UserFacade usufacade;
    private User usuautenticado;

    public static final int ADMIN = 1;
    public static final int PROFESSOR = 2;
    public static final int STUDENT = 3;

    /**
     * Creates a new instance of Login
     */
    public Login() {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HttpServletRequest getHttpservlet() {
        return httpservlet;
    }

    public void setHttpservlet(HttpServletRequest httpservlet) {
        this.httpservlet = httpservlet;
    }

    public UserFacade getUsufacade() {
        return usufacade;
    }

    public void setUsufacade(UserFacade usufacade) {
        this.usufacade = usufacade;
    }

    public User getUsuautenticado() {
        return usuautenticado;
    }

    public void setUsuautenticado(User usuautenticado) {
        this.usuautenticado = usuautenticado;
    }
    
    public int getStudent() {
        return STUDENT;
    }

    

    public void Acceso() throws IOException {

        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuautenticado = usufacade.buscar(email, password);
        if (usuautenticado != null) {
            httpservlet.getSession().setAttribute("usuario", usuautenticado);
            String viewString = "index.xhtml";
            switch (usuautenticado.getLevelId().getId()) {
                case ADMIN:
                    viewString = "admin.xhtml";
                    break;
                case PROFESSOR:
                    viewString = "super.xhtml";
                    break;
                case STUDENT:
                    viewString = "super.xhtml";
                    break;
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(viewString);
            //return "Acceder";
        } else {
            System.out.println("no se encuentra de la base");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o password incorrecto", null));
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            usuautenticado = null;
        } catch (Exception e) {
        }
    }

    public void verificaSesionynivel(int nivel) throws IOException {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        User usu = (User) httpservlet.getSession().getAttribute("usuario");
        if (usu != null) {
            if (usu.getLevelId().getId() != nivel) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("sin_privilegios.xhtml");
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../Ingresar.xhtml");
        }
    }

}
