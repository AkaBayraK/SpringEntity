package com.entity.genel;

import java.util.Objects;

import com.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE_TBL", schema = "CayDB" )
public class RoleEntity extends BaseEntity {
	

	private static final long serialVersionUID = 5684434332210380012L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "ROLE")
    private String role;
 
	@SuppressWarnings("unchecked")
	public Class<?> getEntityClass() {
		return RoleEntity.class;
	}
	
    public RoleEntity() {
	}
    
	@Override
	public int hashCode() {
		return Objects.hash(id, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleEntity other = (RoleEntity) obj;
		return Objects.equals(id, other.id) && Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", role=" + role + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
