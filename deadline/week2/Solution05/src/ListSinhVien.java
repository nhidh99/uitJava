import java.util.*;

public class ListSinhVien {
	List<SinhVien> sinhVienList = new ArrayList<SinhVien>();
	
	public static ListSinhVien copyFromList(ListSinhVien other) {
		var output = new ListSinhVien();
		for (var sv : other.sinhVienList) {
			output.sinhVienList.add(sv);
		}
		return output;
	}
	
	public void input(Scanner in) {
		System.out.print("Nhap so luong sinh vien: ");
		var numSinhVien = Integer.parseInt(in.nextLine());
		for (int i = 0; i < numSinhVien; i++) {
			System.out.printf("[Thong tin sinh vien thu %d]:\n", i + 1);
			System.out.print("- Nhap ho ten: ");
			var hoTen = in.nextLine();
			System.out.print("- Nhap diem trung binh: ");
			var diemTB = Float.parseFloat(in.nextLine());
			sinhVienList.add(new SinhVien(hoTen, diemTB));
		}
	}
	
	public void output() {
		var count = 1;
		for (var sv : sinhVienList) {
			System.out.printf("Thong tin sinh vien thu %d:\n", count++);
			System.out.println(sv.toString());
		}
	}
	
	public void sortByMark() {
		Collections.sort(sinhVienList, new Comparator<SinhVien>() {
			@Override
			public int compare(SinhVien sv1, SinhVien sv2) {
				return Float.compare(sv1.getDiemTB(), sv2.getDiemTB());
			}			
		});
	}
	
	public List<SinhVien> findByFullName(String hoTen) {
		var output = new ArrayList<SinhVien>();
		for (var sv : sinhVienList) {
			if (sv.getHoTen().equals(hoTen)) {
				output.add(sv);
			}
		}
		return output;
	}
	
	public List<SinhVien> findByLastName(String ho) {
		var output = new ArrayList<SinhVien>();
		for (var sv : sinhVienList) {
			var hoSinhVien = sv.getHoTen().split(" ")[0];
			if (hoSinhVien.equals(ho)) {
				output.add(sv);
			}
		}
		return output;
	}
}
