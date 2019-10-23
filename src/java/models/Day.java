/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sandr
 */
@Entity
@Table(name = "day")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Day.findAll", query = "SELECT d FROM Day d")
    , @NamedQuery(name = "Day.findById", query = "SELECT d FROM Day d WHERE d.id = :id")
    , @NamedQuery(name = "Day.findByName", query = "SELECT d FROM Day d WHERE d.name = :name")
    , @NamedQuery(name = "Day.findByActive", query = "SELECT d FROM Day d WHERE d.active = :active")})
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dayId")
    private Collection<Schedule> scheduleCollection;

    public Day() {
    }

    public Day(Integer id) {
        this.id = id;
    }

    public Day(Integer id, String name, short active) {
        this.id = id;
        this.name = name;
        this.active = active;
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

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
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
        if (!(object instanceof Day)) {
            return false;
        }
        Day other = (Day) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Day[ id=" + id + " ]";
    }
    
}
