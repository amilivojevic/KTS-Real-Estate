package kts.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represent Authority class
 */
@Entity
public class Authority {
	@Id
	@GeneratedValue
	private Long id;
	
	String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
