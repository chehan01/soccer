package vo;

//用户的宠物
public class UserPetItem {

	private int number;
	private String kind;
	private String varieties;
	private int unit;
	private double total;
	
	public UserPetItem() {}

	public UserPetItem(int number, String kind, String varieties, int unit, double total) {
		super();
		this.number = number;
		this.kind = kind;
		this.varieties = varieties;
		this.unit = unit;
		this.total = total;
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

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "UserPetItem [number=" + number + ", kind=" + kind + ", varieties=" + varieties + ", unit=" + unit
				+ ", total=" + total + "]";
	}
	
	

}
