package dataAccess;

public class StudentPO {
	private String name;
	private String id;
	private String sex;
	private String college;
	private String grade;
	private String major;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public String getSex() {
		return this.sex;
	}
	public String getMajor(){
		return this.major;
	}
	public void setMajor(String major){
		this.major = major;
	}
	public String toString(){
		
		return String.format("%-10s\t%-5s\t%-3s\t", id,name,sex)
				+String.format("%-18s\t%-8s\t%-8s",college,major,grade);
		
	}
  
}
