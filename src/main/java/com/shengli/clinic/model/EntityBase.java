package com.shengli.clinic.model;

import java.io.Serializable;

public abstract class EntityBase implements Serializable {
	
	private static final long serialVersionUID = 7212338481087070817L;

	public abstract Long getId();

	public abstract void setId(Long id);
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (getId() != null && o instanceof EntityBase)
			return this.getId().equals(((EntityBase) o).getId());
		return false;
	}

	@Override
	public int hashCode() {
		if (getId() == null)
			return super.hashCode();
		return getId().intValue();
	}

	@Override
	public String toString() {
		if (getId() == null)
			return super.toString();
		return getClass().getSimpleName() + "#" + getId();
	}


}
