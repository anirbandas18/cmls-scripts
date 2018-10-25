package app;

import com.opencsv.bean.CsvBindByName;

public class CloudMachine implements Comparable<CloudMachine>{
	
	@CsvBindByName(column = "AccountName")
	private String accountName;
	@CsvBindByName(column = "AdType", required = true)
	private String adType;
	@CsvBindByName(column = "AvailabilityZone")
	private String availabilityZone;
	@CsvBindByName(column = "InstanceType", required = true)
	private String instanceType;
	@CsvBindByName(column = "IsVpc")
	private Boolean isVPC;
	@CsvBindByName(column = "Name", required = true)
	private String name;
	@CsvBindByName(column = "OS", required = true)
	private String os;
	@CsvBindByName(column = "Price", required = true)
	private Double price;
	@CsvBindByName(column = "PricingModel", required = true)
	private String pricingModel;
	@CsvBindByName(column = "Provider", required = true)
	private String provider;
	@CsvBindByName(column = "Region", required = true)
	private String region;
	@CsvBindByName(column = "Tenancy", required = true)
	private String tenancy;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAdType() {
		return adType;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public String getAvailabilityZone() {
		return availabilityZone;
	}
	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public Boolean getIsVPC() {
		return isVPC;
	}
	public void setIsVPC(Boolean isVPC) {
		this.isVPC = isVPC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPricingModel() {
		return pricingModel;
	}
	public void setPricingMode(String pricingModel) {
		this.pricingModel = pricingModel;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTenancy() {
		return tenancy;
	}
	public void setTenancy(String tenancy) {
		this.tenancy = tenancy;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	/*@Override
	public String toString() {
		return Thread.currentThread().getName() + ": CloudMachine [accountName=" + accountName + ", instanceType=" + instanceType + ", isVPC=" + isVPC
				+ ", os=" + os + ", price=" + price + ", pricingModel=" + pricingModel + ", provider=" + provider
				+ ", region=" + region + "]";
	}*/
	@Override
	public String toString() {
		return accountName + ", " + adType + ", " + availabilityZone + ", " + instanceType + ", " + isVPC + ", " + name
				+ ", " + os + ", " + price + ", " + pricingModel + ", " + provider + ", " + region + ", " + tenancy;
	}
	@Override
	public int compareTo(CloudMachine o) {
		// TODO Auto-generated method stub
		return this.instanceType.compareTo(o.getInstanceType());
	}
	public CloudMachine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CloudMachine(String instanceType) {
		super();
		this.instanceType = instanceType;
	}
	

}
