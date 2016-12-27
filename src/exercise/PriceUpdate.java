package exercise;

public class PriceUpdate {
	
	private final String companyName;
	private final double price;
	
	public PriceUpdate(String companyName, double price) {
		this.companyName = companyName;
		this.price = price;
	}
	
	public String getCompanyName() {
		return this.companyName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return companyName + " - " + price;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Please implement this method
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Please implement this method
		return super.hashCode();
	}
}
