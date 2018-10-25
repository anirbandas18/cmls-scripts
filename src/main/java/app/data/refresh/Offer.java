package app.data.refresh;

import java.util.Comparator;

import com.opencsv.bean.CsvBindByName;

public class Offer implements Comparable<Offer>{
	
	@CsvBindByName(column = "SKU")
	private String sku;
	@CsvBindByName(column = "TermType")
	private String termType;
	@CsvBindByName(column = "PricePerUnit")
	private Double pricePerUnit;
	@CsvBindByName(column = "Unit")
	private String unit;
	@CsvBindByName(column = "Currency")
	private String currency;
	@CsvBindByName(column = "Instance Type")
	private String instanceType;
	@CsvBindByName(column = "Operating System")
	private String operatingSystem;
	@CsvBindByName(column = "vCPU")
	private Integer vcpu;
	@CsvBindByName(column = "Memory")
	private String memory;
	
	@CsvBindByName(column = "Tenancy")
	private String tenancy;
	@CsvBindByName(column = "UsageType")
	private String usage;
	@CsvBindByName(column = "CapacityStatus")
	private String capacityStatus;
	@CsvBindByName(column = "operation")
	private String operation;
	@CsvBindByName(column = "Pre Installed S/W")
	private String software;
	
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public Double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	@Override
	public int compareTo(Offer o) {
		return Comparator.comparing(Offer::getSku)
	              .compare(this, o);
	}
	public Integer getVcpu() {
		return vcpu;
	}
	public void setVcpu(Integer vcpu) {
		this.vcpu = vcpu;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getTenancy() {
		return tenancy;
	}
	public void setTenancy(String tenancy) {
		this.tenancy = tenancy;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getCapacityStatus() {
		return capacityStatus;
	}
	public void setCapacityStatus(String capacityStatus) {
		this.capacityStatus = capacityStatus;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
	@Override
	public String toString() {
		return sku + ", " + termType + ", " + pricePerUnit + ", " + unit + ", " + currency + ", " + instanceType + ", "
				+ operatingSystem + ", " + vcpu + ", " + memory + ", " + tenancy + ", " + usage + ", " + capacityStatus
				+ ", " + operation + ", " + software;
	}
		

}
