/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandr
 */
@Entity
@Table(name = "student_has_topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentHasTopic.findAll", query = "SELECT s FROM StudentHasTopic s")
    , @NamedQuery(name = "StudentHasTopic.findById", query = "SELECT s FROM StudentHasTopic s WHERE s.id = :id")
    , @NamedQuery(name = "StudentHasTopic.findByTeacher", query = "SELECT s FROM StudentHasTopic s WHERE s.teacher = :teacher")
    , @NamedQuery(name = "StudentHasTopic.findByActive", query = "SELECT s FROM StudentHasTopic s WHERE s.active = :active")
    , @NamedQuery(name = "StudentHasTopic.findByCreateTime", query = "SELECT s FROM StudentHasTopic s WHERE s.createTime = :createTime")
    , @NamedQuery(name = "StudentHasTopic.findByUpdateTime", query = "SELECT s FROM StudentHasTopic s WHERE s.updateTime = :updateTime")})
public class StudentHasTopic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "teacher")
    private String teacher;
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
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Topic topicId;

    public StudentHasTopic() {
    }

    public StudentHasTopic(Integer id) {
        this.id = id;
    }

    public StudentHasTopic(Integer id, String teacher, short active, Date createTime, Date updateTime) {
        this.id = id;
        this.teacher = teacher;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Topic getTopicId() {
        return topicId;
    }

    public void setTopicId(Topic topicId) {
        this.topicId = topicId;
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
        if (!(object instanceof StudentHasTopic)) {
            return false;
        }
        StudentHasTopic other = (StudentHasTopic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.StudentHasTopic[ id=" + id + " ]";
    }
    
}
