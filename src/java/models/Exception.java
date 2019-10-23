/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandr
 */
@Entity
@Table(name = "exception")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exception.findAll", query = "SELECT e FROM Exception e")
    , @NamedQuery(name = "Exception.findById", query = "SELECT e FROM Exception e WHERE e.id = :id")
    , @NamedQuery(name = "Exception.findByYear", query = "SELECT e FROM Exception e WHERE e.year = :year")
    , @NamedQuery(name = "Exception.findByMonth", query = "SELECT e FROM Exception e WHERE e.month = :month")
    , @NamedQuery(name = "Exception.findByDay", query = "SELECT e FROM Exception e WHERE e.day = :day")
    , @NamedQuery(name = "Exception.findByHourStart", query = "SELECT e FROM Exception e WHERE e.hourStart = :hourStart")
    , @NamedQuery(name = "Exception.findByHourEnd", query = "SELECT e FROM Exception e WHERE e.hourEnd = :hourEnd")})
public class Exception implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "month")
    private int month;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day")
    private int day;
    @Size(max = 45)
    @Column(name = "hour_start")
    private String hourStart;
    @Size(max = 45)
    @Column(name = "hour_end")
    private String hourEnd;

    public Exception() {
    }

    public Exception(Integer id) {
        this.id = id;
    }

    public Exception(Integer id, int year, int month, int day) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getHourStart() {
        return hourStart;
    }

    public void setHourStart(String hourStart) {
        this.hourStart = hourStart;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
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
        if (!(object instanceof Exception)) {
            return false;
        }
        Exception other = (Exception) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Exception[ id=" + id + " ]";
    }
    
}
