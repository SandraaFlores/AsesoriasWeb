/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sandr
 */
@Entity
@Table(name = "type_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeMaterial.findAll", query = "SELECT t FROM TypeMaterial t")
    , @NamedQuery(name = "TypeMaterial.findById", query = "SELECT t FROM TypeMaterial t WHERE t.id = :id")
    , @NamedQuery(name = "TypeMaterial.findByName", query = "SELECT t FROM TypeMaterial t WHERE t.name = :name")
    , @NamedQuery(name = "TypeMaterial.findByActive", query = "SELECT t FROM TypeMaterial t WHERE t.active = :active")
    , @NamedQuery(name = "TypeMaterial.findByCreateTime", query = "SELECT t FROM TypeMaterial t WHERE t.createTime = :createTime")
    , @NamedQuery(name = "TypeMaterial.findByUpdateTime", query = "SELECT t FROM TypeMaterial t WHERE t.updateTime = :updateTime")})
public class TypeMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @Basic(optional = true)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Basic(optional = true)
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeMaterialId")
    private Collection<Material> materialCollection;

    public TypeMaterial() {
    }

    public TypeMaterial(Integer id) {
        this.id = id;
    }

    public TypeMaterial(Integer id, String name, short active, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @XmlTransient
    public Collection<Material> getMaterialCollection() {
        return materialCollection;
    }

    public void setMaterialCollection(Collection<Material> materialCollection) {
        this.materialCollection = materialCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeMaterial)) {
            return false;
        }
        TypeMaterial other = (TypeMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TypeMaterial[ id=" + id + " ]";
    }
    
}
