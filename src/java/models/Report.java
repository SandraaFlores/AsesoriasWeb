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
import javax.persistence.Lob;
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
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findById", query = "SELECT r FROM Report r WHERE r.id = :id")
    , @NamedQuery(name = "Report.findByPlace", query = "SELECT r FROM Report r WHERE r.place = :place")
    , @NamedQuery(name = "Report.findByCreateTime", query = "SELECT r FROM Report r WHERE r.createTime = :createTime")
    , @NamedQuery(name = "Report.findByUpdateTime", query = "SELECT r FROM Report r WHERE r.updateTime = :updateTime")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "place")
    private String place;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "comment")
    private String comment;
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
    @JoinColumn(name = "status_advice_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ReportStatus statusAdviceId;
    @JoinColumn(name = "student_assistant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentAssistantId;
    @JoinColumn(name = "student_learner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentLearnerId;
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Schedule scheduleId;
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Topic topicId;

    public Report() {
    }

    public Report(Integer id) {
        this.id = id;
    }

    public Report(Integer id, String place, String comment, Date createTime, Date updateTime) {
        this.id = id;
        this.place = place;
        this.comment = comment;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public ReportStatus getStatusAdviceId() {
        return statusAdviceId;
    }

    public void setStatusAdviceId(ReportStatus statusAdviceId) {
        this.statusAdviceId = statusAdviceId;
    }

    public Student getStudentAssistantId() {
        return studentAssistantId;
    }

    public void setStudentAssistantId(Student studentAssistantId) {
        this.studentAssistantId = studentAssistantId;
    }

    public Student getStudentLearnerId() {
        return studentLearnerId;
    }

    public void setStudentLearnerId(Student studentLearnerId) {
        this.studentLearnerId = studentLearnerId;
    }

    public Schedule getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Schedule scheduleId) {
        this.scheduleId = scheduleId;
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
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Report[ id=" + id + " ]";
    }
    
}
