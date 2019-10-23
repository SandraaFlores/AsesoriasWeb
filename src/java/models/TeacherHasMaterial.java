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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandr
 */
@Entity
@Table(name = "teacher_has_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeacherHasMaterial.findAll", query = "SELECT t FROM TeacherHasMaterial t")
    , @NamedQuery(name = "TeacherHasMaterial.findById", query = "SELECT t FROM TeacherHasMaterial t WHERE t.id = :id")
    , @NamedQuery(name = "TeacherHasMaterial.findByApproval", query = "SELECT t FROM TeacherHasMaterial t WHERE t.approval = :approval")
    , @NamedQuery(name = "TeacherHasMaterial.findByCreateTime", query = "SELECT t FROM TeacherHasMaterial t WHERE t.createTime = :createTime")
    , @NamedQuery(name = "TeacherHasMaterial.findByUpdateTime", query = "SELECT t FROM TeacherHasMaterial t WHERE t.updateTime = :updateTime")})
public class TeacherHasMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "approval")
    private short approval;
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
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Material materialId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public TeacherHasMaterial() {
    }

    public TeacherHasMaterial(Integer id) {
        this.id = id;
    }

    public TeacherHasMaterial(Integer id, short approval, Date createTime, Date updateTime) {
        this.id = id;
        this.approval = approval;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getApproval() {
        return approval;
    }

    public void setApproval(short approval) {
        this.approval = approval;
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

    public Material getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Material materialId) {
        this.materialId = materialId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof TeacherHasMaterial)) {
            return false;
        }
        TeacherHasMaterial other = (TeacherHasMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TeacherHasMaterial[ id=" + id + " ]";
    }
    
}
