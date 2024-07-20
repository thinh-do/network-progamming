package De2_Cau2_TCP;

public class Student {
	private String name;
	private String dOB;
	private int id;
	private String birthPlace;
	public Student(String name, String dOB, int id, String birthPlace) {
		this.name = name;
		this.dOB = dOB;
		this.id = id;
		this.birthPlace = birthPlace;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getdOB() {
		return dOB;
	}
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", dOB=" + dOB + ", id=" + id + ", birthPlace=" + birthPlace + "]";
	}
	
	
	
	
	

}
