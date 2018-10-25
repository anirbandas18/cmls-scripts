package app;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MachineInfo implements Comparable<MachineInfo>{
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("CoreCount")
	private Integer coreCount;
	@JsonProperty("Memory")
	private BigDecimal memory;
	@JsonProperty("PricePerUnit")
	private Double pricePerUnit;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCoreCount() {
		return coreCount;
	}
	public void setCoreCount(Integer coreCount) {
		this.coreCount = coreCount;
	}
	public BigDecimal getMemory() {
		return memory;
	}
	public void setMemory(BigDecimal memory) {
		this.memory = memory;
	}
	public Double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	@Override
	public String toString() {
		return id + ", " + name + ", " + coreCount + ", " + memory + ", " + pricePerUnit;
	}
	@Override
	public int compareTo(MachineInfo o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
