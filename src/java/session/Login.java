/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import controllers.UserFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import models.User;

/**
 *
 * @author sandr
 */
//@Named(value = "login")
@ManagedBean(name = "login", eager = true)
@SessionScoped
public class Login implements Serializable {

    private String email;
    private String password;

    private HttpServletRequest httpservlet;

    @EJB
    private UserFacade usufacade;
    private User usuautenticado;

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

    public void Acceso() throws IOException {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuautenticado = usufacade.buscar(email, password);
        if (usuautenticado != null) {
            httpservlet.getSession().setAttribute("usuario", usuautenticado);
            String viewString = "index.xhtml";
            switch (usuautenticado.getLevelId().getId()) {
                case 1:
                    viewString = "admin/templates/content.xhtml";
                    break;
                case 2:
                    viewString = "professor/templates/content.xhtml";
                    break;
                case 3:
                    viewString = "student/templates/content.xhtml";
                    break;
            }

            FacesContext.getCurrentInstance().getExternalContext().redirect(viewString);
            //return "Acceder";
        } else {
            System.out.println("No se encuentra registrado en la base");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo o contraseña incorrecta.", null));
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/AsesoriasWeb/faces/login.xhtml");
            usuautenticado = null;
        } catch (Exception e) {
        }
    }

    public void verificaSesionynivel(int nivel) throws IOException {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        User usu = (User) httpservlet.getSession().getAttribute("usuario");
        if (usu != null) {
            if (usu.getLevelId().getId() != nivel && (usu.getLevelId().getId())!= 1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/AsesoriasWeb/faces/sin_privilegios.xhtml");
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/AsesoriasWeb/faces/error.xhtml");
        }
    }
    


}
