
public class Student extends Person implements Measurable {

	double mathMark;
	double physicMark;
	double chemistryMark;
	double averageMark;

	public Student(String name, String id, double mathMark, double physicMark, double chemistryMark) {
		super(name, id);
		this.mathMark = mathMark;
		this.physicMark = physicMark;
		this.chemistryMark = chemistryMark;
		this.averageMark = (mathMark + physicMark + chemistryMark) / 3;
	}

	@Override
	public double valuate() {
		return averageMark;
	}
}
