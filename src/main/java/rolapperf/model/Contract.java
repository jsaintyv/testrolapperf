package rolapperf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contracts")
public class Contract  {
	// PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private Integer companyId;
       
    private String name;
    
    public Contract() {}
    	
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	
	
	
}