package vo;
//��д������
public class petItem {

	//��д�������Բ��Զ����ɹ��췽����getter��setter������toString����
	private int number = 0;
	private String kind;
	private String varieties;
	private double price;
	private int unit;
	public petItem() {}
	public petItem(int number, String kind, String varieties, double price, int unit) {
		super();
		this.number = number;
		this.kind = kind;
		this.varieties = varieties;
		this.price = price;
		this.unit = unit;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getVarieties() {
		return varieties;
	}

	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "petItem [number=" + number + ", kind=" + kind + ", varieties=" + varieties + ", price=" + price
				+ ", unit=" + unit + "]";
	}
	
	
}
