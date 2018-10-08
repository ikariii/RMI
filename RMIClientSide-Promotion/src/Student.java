import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {

	private String sname;
	private int score;
	private int coefficient;

	public Student() {

	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

}
