package controllers;

import models.User;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    private UploadedFile file;

    private User current;
    private DataModel items = null;
    @EJB
    private controllers.UserFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<User> usr;

    public UserController() {
    }

    public User getSelected() {
        if (current == null) {
            current = new User();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        current = (User) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new User();
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String create() throws IOException {

        String ruta = "C:/Users/sandr/Documents/GitHub/AsesoriasWeb/web/images/";
        current.setUrlImage("/images/" + file.getFileName());
        InputStream input = file.getInputstream();
        Path folder = Paths.get(ruta);
        Path fileToCreatePath = folder.resolve(file.getFileName());
         
        
        try{
        Path newFilePath = Files.createFile(fileToCreatePath);
       
            Files.copy(input, newFilePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(FileAlreadyExistsException e){
            
        }
        

        getFacade().edit(current);
        JsfUtil.addSuccessMessage("Usuario creado");
        items = null;
        return prepareCreate();

    }
    
    public String extention(String fileName) {
        String extention = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extention = fileName.substring( i + 1);
        }
        return extention;
    }

    public String prepareEdit() {
        current = (User) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        FacesMessage msg = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String update() throws IOException {
        if (file != null) {
        //String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("../resources/img/");
            String path = "C:/Users/sandr/Documents/GitHub/AsesoriasWeb/web/images/";

            String fileName = "profile-image-" + current.getId() + "." + extention(file.getFileName());
            current.setUrlImage("/images/" + fileName);
            File newFile = new File(path, fileName);
            InputStream input = file.getInputstream();
            OutputStream output = new FileOutputStream(newFile);
            byte[] bytes = new byte[1024];
            int read;
            while((read = input.read(bytes)) != (-1)) {
                output.write(bytes, 0, read);
            }
        }
        getFacade().edit(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
        return "View";

    }

    public String destroy() {
        current = (User) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public List<User> getItemsProf() {

        usr = ejbFacade.asesor();

        return usr;
    }


    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public SelectItem[] getUsersActive()
    {
        return ejbFacade.findAllActive();
    }

    public User getUser(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }

    public String ingresar() {
        User usu = null;
        try {

            String ok = "start";
            List<User> lista = ejbFacade.validar(current);
            for (User a : lista) {
                usu = a;
            }
            //usu.getNivel();

            if (usu != null) {
                ok = "Correcto";
                System.out.println("entraste");
            } else {
                JsfUtil.addErrorMessage("Correo o contraseña incorecta");
                ok = "Error";
                System.out.println("No entraste");
            }
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
            return ok;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

}
