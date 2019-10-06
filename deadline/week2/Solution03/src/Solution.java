import java.util.*;

public class Solution {
	public static void main(String[] args) {
		var employeeList = new ArrayList<Employee>();
		var in = new Scanner(System.in);
		
		System.out.print("Nhap so luong nhan vien: ");
		int numEmployee = Integer.parseInt(in.nextLine());
		
		for (int i = 0; i < numEmployee; i++) {
			System.out.printf("Nhap nhan vien thu %d:\n", i + 1);
			
			System.out.print("- Nhap ten: ");
			var name = in.nextLine();
			
			System.out.print("- Nhap tuoi: ");
			var age = Integer.parseInt(in.nextLine());
			
			System.out.print("- Nhap dia chi: ");
			var address = in.nextLine();
			
			System.out.print("- Nhap luong co ban: ");
			var defaultSalry = Integer.parseInt(in.nextLine());
			
			System.out.print("- Nhap he so luong: ");
			var coefficient = Integer.parseInt(in.nextLine());
			
			employeeList.add(new Employee(name, age, address, defaultSalry, coefficient));
		}
		in.close();
		
		var maxSalaryEmployee = employeeList.get(0);
		var totalSalary = 0;
		
		for (var employee : employeeList) {
			if (maxSalaryEmployee.getSalary() < employee.getSalary()) {
				maxSalaryEmployee = employee;
			}
			totalSalary += employee.getSalary();
		}
		
		System.out.println("\n-> Tong luong: " + totalSalary);
		System.out.println("-> Thong tin nhan vien co luong cao nhat:");
		maxSalaryEmployee.showInfo();
	}
}
