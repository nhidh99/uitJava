import java.util.Scanner;

public class Complex {
	double real;
	double img;

	public Complex() {}
	
	public Complex(double real, double img) {
		this.real = real;
		this.img = img;
	}
	
	public void input(Scanner in) {
		System.out.print("- Nhap phan thuc: ");
		this.real = Double.parseDouble(in.nextLine());
		System.out.print("- Nhap phan ao: ");
		this.img = Double.parseDouble(in.nextLine());
	}
	
	public String toString() {
		return String.format("%.2f + %.2fi", real, img);
	}

	public Complex add(Complex other) {
		return new Complex(real + other.real, img + other.img);
	}

	public Complex minus(Complex other) {
		return new Complex(real - other.real, img - other.img);
	}

	public Complex multiply(Complex other) {
		double real = this.real * other.real - this.img * other.img;
		double img = this.real * other.img + this.img * other.real;
		return new Complex(real, img);
	}

	public Complex divide(Complex other) {
		double real = (this.real * other.real + this.img * other.img)
				/ (Math.pow(other.real, 2) + Math.pow(other.img, 2));
		
		double img = (other.real * this.img - other.img * this.real)
				/ (Math.pow(other.real, 2) + Math.pow(other.img, 2));
		
		return new Complex(real, img);
	}
}
