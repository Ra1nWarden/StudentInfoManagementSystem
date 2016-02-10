package data;

public final class StudentCourse {
	
	private String courseName;
	private int courseId;
	private String studentName;
	private int studentId;
	private double score;
	private Origin origin;
	
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

	private StudentCourse(String name, int id, String studentName, int studentId, double score, Origin origin) {
		this.courseName = name;
		this.courseId = id;
		this.studentName = studentName;
		this.studentId = studentId;
		this.score = score;
		this.origin = origin;
	}
	
	public static class Builder {
		private String courseName;
		private int courseId;
		private String studentName;
		private int studentId;
		private double score;
		private Origin origin;
		
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
			return new StudentCourse(courseName, courseId, studentName, studentId, score, origin);
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
