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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")
    , @NamedQuery(name = "Student.findAllAsistant", query = "SELECT s FROM Student s WHERE s.studentTypeId.id = 1")
    , @NamedQuery(name = "Student.findBySemester", query = "SELECT s FROM Student s WHERE s.semester = :semester")
    , @NamedQuery(name = "Student.findByScore", query = "SELECT s FROM Student s WHERE s.score = :score")
    , @NamedQuery(name = "Student.findByPhone", query = "SELECT s FROM Student s WHERE s.phone = :phone")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "semester")
    private String semester;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private int score;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "phone")
    private String phone;
    @JoinColumn(name = "career_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Career careerId;
    @JoinColumn(name = "student_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StudentType studentTypeId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<Schedule> scheduleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentAssistantId")
    private Collection<Report> reportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentLearnerId")
    private Collection<Report> reportCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<StudentHasTopic> studentHasTopicCollection;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String semester, int score, String phone) {
        this.id = id;
        this.semester = semester;
        this.score = score;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Career getCareerId() {
        return careerId;
    }

    public void setCareerId(Career careerId) {
        this.careerId = careerId;
    }

    public StudentType getStudentTypeId() {
        return studentTypeId;
    }

    public void setStudentTypeId(StudentType studentTypeId) {
        this.studentTypeId = studentTypeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection1() {
        return reportCollection1;
    }

    public void setReportCollection1(Collection<Report> reportCollection1) {
        this.reportCollection1 = reportCollection1;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return userId.getControlNumber();
    }
    
}
