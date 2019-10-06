import java.util.*;

public class Main {
	public static void main(String[] args) {
		var svList = new ListSinhVien();
		var in = new Scanner(System.in);
		svList.input(in);

		boolean done = false;
		while (!done) {
			System.out.println("---");
			System.out.print(
					"1. Xem danh sach sinh vien.\n"
					+ "2. Sap xep va hien thi danh sach sinh vien theo DTB tang dan.\n"
					+ "3. Tim kiem sinh vien theo ten.\n" 
					+ "4. Xuat thong tin sinh vien co ho la \"Le\"\n"
					+ "5. Thoat chuong trinh.\n" 
					+ "Nhap yeu cau: ");

			var request = Integer.parseInt(in.nextLine());
			System.out.println();
			
			switch (request) {
			case 1: { 
				// Hiện thị list
				svList.output();
				break;
			}

			case 2: {
				// Sắp xếp và hiển thị list theo dtb tăng dần
				var svCopyList = ListSinhVien.copyFromList(svList);
				svCopyList.sortByMark();
				svCopyList.output();
				break;
			}

			case 3: {
				// Tìm kiếm sinh viên theo tên
				System.out.println("- Nhap ho ten can tim: ");
				var hoTen = in.nextLine();
				var output = svList.findByFullName(hoTen);
				var count = 1;

				System.out.println("-> Ket qua:");
				for (var sv : output) {
					System.out.printf("Thong tin sinh vien thu %d:\n%s\n", count++, sv.toString());
				}
				break;
			}

			case 4: {
				// Xuất sinh viên có họ "Lê"
				var output = svList.findByLastName("Le");
				var count = 1;
				
				System.out.println("-> Ket qua:");
				for (var sv : output) {
					System.out.printf("Thong tin sinh vien thu %d:\n%s\n", count++, sv.toString());
				}
				break;
			}
			
			case 5: {
				done = true;
				break;
			}
			}
		}
		in.close();
	}
}
