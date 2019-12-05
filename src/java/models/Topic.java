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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t")
    , @NamedQuery(name = "Topic.findBySubjectId", query = "SELECT t FROM Topic t WHERE t.subjectId.id = :subjectId")
    , @NamedQuery(name = "Topic.findById", query = "SELECT t FROM Topic t WHERE t.id = :id")
    , @NamedQuery(name = "Topic.findByName", query = "SELECT t FROM Topic t WHERE t.name = :name")
    , @NamedQuery(name = "Topic.findByDescription", query = "SELECT t FROM Topic t WHERE t.description = :description")
    , @NamedQuery(name = "Topic.findByUrlImage", query = "SELECT t FROM Topic t WHERE t.urlImage = :urlImage")
    , @NamedQuery(name = "Topic.findByActive", query = "SELECT t FROM Topic t WHERE t.active = :active")
    , @NamedQuery(name = "Topic.findByCreateTime", query = "SELECT t FROM Topic t WHERE t.createTime = :createTime")
    , @NamedQuery(name = "Topic.findByUpdateTime", query = "SELECT t FROM Topic t WHERE t.updateTime = :updateTime")})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
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
    @Size(min = 1, max = 255)
    @Column(name = "url_image")
    private String urlImage;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topicId")
    private Collection<Material> materialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topicId")
    private Collection<Report> reportCollection;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subjectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topicId")
    private Collection<Topic> topicCollection;
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Topic topicId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topicId")
    private Collection<StudentHasTopic> studentHasTopicCollection;

    public Topic() {
    }

    public Topic(Integer id) {
        this.id = id;
    }

    public Topic(Integer id, String name, String description, String urlImage, short active, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    @XmlTransient
    public Collection<Topic> getTopicCollection() {
        return topicCollection;
    }

    public void setTopicCollection(Collection<Topic> topicCollection) {
        this.topicCollection = topicCollection;
    }

    public Topic getTopicId() {
        return topicId;
    }

    public void setTopicId(Topic topicId) {
        this.topicId = topicId;
    }

    @XmlTransient
    public Collection<StudentHasTopic> getStudentHasTopicCollection() {
        return studentHasTopicCollection;
    }

    public void setStudentHasTopicCollection(Collection<StudentHasTopic> studentHasTopicCollection) {
        this.studentHasTopicCollection = studentHasTopicCollection;
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
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
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
