package controllers;

import models.Material;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import java.io.File;
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
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.model.UploadedFile;

@Named("materialController")
@SessionScoped
public class MaterialController implements Serializable {

    private Material current;
    private DataModel items = null;
    private UploadedFile file;
    @EJB
    private controllers.MaterialFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MaterialController() {
    }

    public Material getSelected() {
        if (current == null) {
            current = new Material();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MaterialFacade getFacade() {
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

    public String prepareList2() {
        recreateModel();
        return "List_1?faces-redirect=true";
    }
    
    public String prepareList3() {
        recreateModel();
        return "List_1_1?faces-redirect=true";
    }

    public String prepareView() {
        current = (Material) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareView2() {
        current = (Material) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View_1?faces-redirect=true";
    }
    public String prepareView3() {
        current = (Material) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View_1_1?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Material();
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String create() throws IOException {
        /*if (file != null) {
            String ruta = "C:/Users/sandr/Documents/GitHub/AsesoriasWeb/web/images/";
            current.setUrl("/images/" + file.getFileName());
            InputStream input = file.getInputstream();
            Path folder = Paths.get(ruta);
            Path fileToCreatePath = folder.resolve(file.getFileName());
            try {
                Path newFilePath = Files.createFile(fileToCreatePath);
                
                Files.copy(input, newFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (FileAlreadyExistsException e) {
                
            }
        } else {
            JsfUtil.addErrorMessage("Se requiere archivo");
            return null;
        }*/
        
        if (file != null) {
            String ruta = "C:/Users/sandr/Documents/GitHub/AsesoriasWeb/web/images/";
            current.setUrl("/images/" + file.getFileName());
            InputStream input = file.getInputstream();
            Path folder = Paths.get(ruta);
            Path fileToCreatePath = folder.resolve(file.getFileName());
            
            try {
                Path newFilePath = Files.createFile(fileToCreatePath);
                
                Files.copy(input, newFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (FileAlreadyExistsException e) {
                
            }
        } else {
            JsfUtil.addErrorMessage("Se requiere archivo");
            return null;
        }

        getFacade().create(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MaterialCreated"));
        items = null;
        return prepareCreate();

    }

    public String prepareEdit() {
        current = (Material) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String update() throws IOException {

        /*if (file != null) {
            String ruta = "C:/Users/sandr/Documents/GitHub/AsesoriasWeb/web/images/";
            current.setUrl("/images/" + file.getFileName());
            InputStream input = file.getInputstream();

            Path folder = Paths.get(ruta);
            Path fileToCreatePath = folder.resolve(file.getFileName());
            
            try {
                Path newFilePath = Files.createFile(fileToCreatePath);
                
                Files.copy(input, newFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (FileAlreadyExistsException e) {
                
            }
        }*/
        if (file != null) {
            String path = "C:/Users/sandr/Documents/GitHub/AsesoriasWeb/web/images/";
            
            String fileName = "material-" + current.getId() + "." + extention(file.getFileName());
            current.setUrl("/images/" + fileName);
            File newFile = new File(path, fileName);
            InputStream input = file.getInputstream();
            OutputStream output = new FileOutputStream(newFile);
            byte[] bytes = new byte[1024];
            int read;
            while ((read = input.read(bytes)) != (-1)) {
                output.write(bytes, 0, read);
            }
        }

        getFacade().edit(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MaterialUpdated"));
        items = null;
        return "View";

    }
    
    public String extention(String fileName) {
        String extention = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extention = fileName.substring(i + 1);
        }
        return extention;
    }

    public String destroy() {
        current = (Material) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MaterialDeleted"));
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

    public Material getMaterial(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Material.class)
    public static class MaterialControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaterialController controller = (MaterialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materialController");
            return controller.getMaterial(getKey(value));
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
            if (object instanceof Material) {
                Material o = (Material) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Material.class.getName());
            }
        }

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
