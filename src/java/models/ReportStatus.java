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
@Table(name = "report status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportStatus.findAll", query = "SELECT r FROM ReportStatus r")
    , @NamedQuery(name = "ReportStatus.findById", query = "SELECT r FROM ReportStatus r WHERE r.id = :id")
    , @NamedQuery(name = "ReportStatus.findByName", query = "SELECT r FROM ReportStatus r WHERE r.name = :name")
    , @NamedQuery(name = "ReportStatus.findByDescription", query = "SELECT r FROM ReportStatus r WHERE r.description = :description")
    , @NamedQuery(name = "ReportStatus.findByActive", query = "SELECT r FROM ReportStatus r WHERE r.active = :active")
    , @NamedQuery(name = "ReportStatus.findByCreateTime", query = "SELECT r FROM ReportStatus r WHERE r.createTime = :createTime")
    , @NamedQuery(name = "ReportStatus.findByUpdateTime", query = "SELECT r FROM ReportStatus r WHERE r.updateTime = :updateTime")})
public class ReportStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusAdviceId")
    private Collection<Report> reportCollection;

    public ReportStatus() {
    }

    public ReportStatus(Integer id) {
        this.id = id;
    }

    public ReportStatus(Integer id, String name, String description, short active, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
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
        if (!(object instanceof ReportStatus)) {
            return false;
        }
        ReportStatus other = (ReportStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ReportStatus[ id=" + id + " ]";
    }
    
}
