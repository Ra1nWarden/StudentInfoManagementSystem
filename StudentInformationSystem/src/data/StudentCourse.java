package data;

public final class StudentCourse {
	
	private int id;
	private String courseName;
	private int courseId;
	private String studentName;
	private int studentId;
	private double score;
	private Origin origin;
	
	public int getId() {
		return id;
	}
	
	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	private StudentCourse(String name, int courseId, String studentName, int studentId, double score, Origin origin) {
		this.courseName = name;
		this.courseId = courseId;
		this.studentName = studentName;
		this.studentId = studentId;
		this.score = score;
		this.origin = origin;
	}
	
	private StudentCourse(int id, String name, int courseId, String studentName, int studentId, double score, Origin origin) {
		this(name, courseId, studentName, studentId, score, origin);
		this.id = id;
	}
	
	public static class Builder {
		private int id = -1;
		private String courseName;
		private int courseId;
		private String studentName;
		private int studentId;
		private double score;
		private Origin origin;
		
		public Builder id(int id) {
			this.id = id;
			return this;
		}
		
		public Builder courseName(String name) {
			this.courseName = name;
			return this;
		}
		
		public Builder courseId(int id) {
			this.courseId = id;
			return this;
		}
		
		public Builder studentName(String studentName) {
			this.studentName = studentName;
			return this;
		}
		
		public Builder studentId(int studentId) {
			this.studentId = studentId;
			return this;
		}
		
		public Builder score(double score) {
			this.score = score;
			return this;
		}
		
		public Builder origin(Origin origin) {
			this.origin = origin;
			return this;
		}
		
		public StudentCourse build() {
			if(id == -1) {
				return new StudentCourse(courseName, courseId, studentName, studentId, score, origin);
			} else {
				return new StudentCourse(id, courseName, courseId, studentName, studentId, score, origin);
			}
		}
		
	}
	
	public enum Origin {
		Local, Overseas;
		@Override
		public String toString() {
			switch(this) {
			case Local:
				return "国内";
			case Overseas:
				return "国外";
			}
			return "";
		}
	}
	
}
