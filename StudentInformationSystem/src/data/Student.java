package data;

import java.sql.Date;

public final class Student {
	
	private String name;
	private long id;
	private Sex sex;
	private Level level;
	private Date dateOfBirth;
	
	private Student(String name, long id, Sex sex, Level level, Date dateOfBirth) {
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
	
	public Sex getSex() {
		return sex;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
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
		private Sex sex;
		private Level level;
		private Date dateOfBirth;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder id(long id) {
			this.id = id;
			return this;
		}
		
		public Builder sex(Sex sex) {
			this.sex = sex;
			return this;
		}
		
		public Builder level(Level level) {
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
	
	enum Sex {
		Male, Female;
		
		@Override
		public String toString() {
			switch(this) {
			case Male:
				return "男";
			case Female:
				return "女";
			default:
				return "";
			}
		}
	};
	
	enum Level {
		Freshman, Sophomore, Junior, Senior;
		
		@Override
		public String toString() {
			switch(this) {
			case Freshman:
				return "大一";
			case Sophomore:
				return "大二";
			case Junior:
				return "大三";
			case Senior:
				return "大四";
			default:
				return "";
			}
		}
	};

}
