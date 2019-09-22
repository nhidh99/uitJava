public abstract class Service {
	protected String name;
	protected int numUnits;
	protected long unitPrice;

	public String getName() {
		return name;
	}
	public abstract void inputInfo();
	public abstract long getPayment();
}
