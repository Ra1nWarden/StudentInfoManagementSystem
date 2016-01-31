package data;

public final class Course {
	
	private String name;
	private long id;
	private String studentName;
	private long studentId;
	private double score;
	
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
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public long getStudentId() {
		return studentId;
	}
	
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	private Course(String name, long id, String studentName, long studentId, double score) {
		this.name = name;
		this.id = id;
		this.studentName = studentName;
		this.studentId = studentId;
		this.score = score;
	}
	
	public static class Builder {
		private String name;
		private long id;
		private String studentName;
		private long studentId;
		private double score;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder id(long id) {
			this.id = id;
			return this;
		}
		
		public Builder studentName(String studentName) {
			this.studentName = studentName;
			return this;
		}
		
		public Builder studentId(long studentId) {
			this.studentId = studentId;
			return this;
		}
		
		public Builder score(double score) {
			this.score = score;
			return this;
		}
		
		public Course build() {
			return new Course(name, id, studentName, studentId, score);
		}
		
	}
	
}
