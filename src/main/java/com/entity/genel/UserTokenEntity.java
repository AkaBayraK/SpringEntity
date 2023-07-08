package com.entity.genel;

import java.util.Objects;

import com.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER_ROLE_TBL", schema = "CayDB" )
public class UserTokenEntity extends BaseEntity {
	

	private static final long serialVersionUID = 9118752422768473121L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ROLE_ID")
    private Long roleId;
 
	@SuppressWarnings("unchecked")
	public Class<?> getEntityClass() {
		return UserTokenEntity.class;
	}
	
    public UserTokenEntity() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTokenEntity other = (UserTokenEntity) obj;
		return Objects.equals(id, other.id) && Objects.equals(roleId, other.roleId)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "UserRoleEntity [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}	
    
}