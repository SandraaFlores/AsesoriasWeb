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
@Table(name = "career")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Career.findAll", query = "SELECT c FROM Career c")
    , @NamedQuery(name = "Career.findById", query = "SELECT c FROM Career c WHERE c.id = :id")
    , @NamedQuery(name = "Career.findByName", query = "SELECT c FROM Career c WHERE c.name = :name")
    , @NamedQuery(name = "Career.findByAcronym", query = "SELECT c FROM Career c WHERE c.acronym = :acronym")
    , @NamedQuery(name = "Career.findByActive", query = "SELECT c FROM Career c WHERE c.active = :active")
    , @NamedQuery(name = "Career.findByCreateTime", query = "SELECT c FROM Career c WHERE c.createTime = :createTime")
    , @NamedQuery(name = "Career.findByUpdateTime", query = "SELECT c FROM Career c WHERE c.updateTime = :updateTime")})
public class Career implements Serializable {

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
    @Size(min = 1, max = 45)
    @Column(name = "acronym")
    private String acronym;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = true)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Basic(optional = true)
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "careerId")
    private Collection<Student> studentCollection;

    public Career() {
    }

    public Career(Integer id) {
        this.id = id;
    }

    public Career(Integer id, String name, String acronym, boolean active, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getActive() {
        if(active == false) {
            return "Desactivado";
        } else {
            return "Activado";
        }
    }

    public void setActive(String active) {
        this.active = !active.equals("false");
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
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
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
        if (!(object instanceof Career)) {
            return false;
        }
        Career other = (Career) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
