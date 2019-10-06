import java.util.*;

public class Main {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		var list = new ArrayList<Person>();
		
		System.out.print("Nhap so luong nhan vien: ");
		var numEmployee = Integer.parseInt(in.nextLine());
		System.out.print("Nhap so luong sinh vien: ");
		var numStudent = Integer.parseInt(in.nextLine());
		
		for (int i = 0; i < numEmployee; i++) {
			System.out.printf("\n[Nhap thong tin nhan vien thu %d]:\n", i + 1);
			
			System.out.print("- Nhap ho ten: ");
			var name = in.nextLine();
			
			System.out.print("- Nhap ma nhan vien: ");
			var id = in.nextLine();
			
			System.out.print("- Nhap so gio lam: ");
			var workHour = Integer.parseInt(in.nextLine());
			
			System.out.print("- Nhap luong moi gio: ");
			var salaryPerHour = Double.parseDouble(in.nextLine());
			
			list.add(new Employee(name, id, workHour, salaryPerHour));
		}

		for (int i = 0; i < numStudent; i++) {
			System.out.printf("\n[Nhap thong tin sinh vien thu %d]:\n", i + 1);
			
			System.out.print("- Nhap ho ten: ");
			var name = in.nextLine();
			
			System.out.print("- Nhap ma sinh vien: ");
			var id = in.nextLine();
			
			System.out.print("- Nhap diem toan: ");
			var mathMark = Double.parseDouble(in.nextLine());
			System.out.print("- Nhap diem ly: ");
			
			var physicMark = Double.parseDouble(in.nextLine());
			
			System.out.print("- Nhap diem hoa: ");
			var chemistryMark = Double.parseDouble(in.nextLine());
			
			list.add(new Student(name, id, mathMark, physicMark, chemistryMark));
		}
		in.close();
		
		double totalSalary = 0;
		double totalMark = 0;
		
		for (int i = 0; i < numEmployee; i++) {
			var employee = (Employee) list.get(i);
			totalSalary += employee.valuate();
		}
		
		for (int i = 0; i < numStudent; i++) {
			var student = (Student) list.get(i + numEmployee);
			totalMark += student.valuate();
		}		
		
		System.out.println();
		System.out.println("-> Luong trung binh cac nhan vien: " + totalSalary / numEmployee);
		System.out.println("-> Diem trung binh cac sinh vien: " + totalMark / numStudent);
	}
}
