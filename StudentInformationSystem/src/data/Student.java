package data;

import java.sql.Date;

public final class Student {
	
	private String name;
	private long id;
	private int sex;
	private int level;
	private Date dateOfBirth;
	
	private Student(String name, long id, int sex, int level, Date dateOfBirth) {
		this.name = name;
		this.id = id;
		this.sex = sex;
		this.level = level;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getSex() {
		return sex;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public static class Builder {
		private String name;
		private long id;
		private int sex;
		private int level;
		private Date dateOfBirth;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder id(long id) {
			this.id = id;
			return this;
		}
		
		public Builder sex(int sex) {
			this.sex = sex;
			return this;
		}
		
		public Builder level(int level) {
			this.level = level;
			return this;
		}
		
		public Builder dateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}
		
		public Student build() {
			return new Student(name, id, sex, level, dateOfBirth);
		}
	}

}
