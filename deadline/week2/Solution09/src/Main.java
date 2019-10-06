import java.util.*;

public class Main {
	public static void main(String[] args) {
		var list = new ArrayList<NguoiLam>();
		var in = new Scanner(System.in);
		
		// Nhập thông tin các nhân viên
		System.out.print("Nhap so luong nhan vien: ");
		int n = Integer.parseInt(in.nextLine());
		
		for (int i = 0; i < n; i++) {
			System.out.printf("\n[Nhap nhan vien thu %d]:\n", i + 1);
			
			System.out.print("- Nhap ho ten: ");
			var hoTen = in.nextLine();
			
			System.out.print("- Nhap ngay sinh (dd/mm/yyyy): ");
			var ngaySinh = in.nextLine();
			
			System.out.print("- Nhap he so luong: ");
			var heSoLuong = Integer.parseInt(in.nextLine());
			
			System.out.print("- Nhap chuc vu (1. Giam doc, 2. Quan li, 3. Nhan vien): ");
			var chucVu = Integer.parseInt(in.nextLine());
			
			switch (chucVu) {
			case 1: {
				System.out.print("- Nhap he so chuc vu: ");
				var heSoChucVu = Integer.parseInt(in.nextLine());
				list.add(new GiamDoc(hoTen, ngaySinh, heSoLuong, heSoChucVu));
				break;
			}
			
			case 2: {
				System.out.print("- Nhap so luong nhan vien quan li: ");
				var soNVQuanLy = Integer.parseInt(in.nextLine());
				list.add(new QuanLy(hoTen, ngaySinh, heSoLuong, soNVQuanLy));
				break;
			}
			
			case 3: {
				System.out.print("- Nhap ten don vi (phong/ban): ");
				var tenDonVi = in.nextLine();
				list.add(new NhanVien(hoTen, ngaySinh, heSoLuong, tenDonVi));
				break;
			}
			}
		}
		in.close();
		
		// Xuất thông tin các nhân viên
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("[Thong tin nhan vien thu %d]:\n", i + 1);
			System.out.println(list.get(i).toString());
		}
		
		// Hiển thị thông tin nhân viên sinh trong tháng 2
		System.out.println();
		System.out.println("--- Cac nhan vien sinh trong thang 2 ---");
		var count = 1;

		for (var nv : list) {
			int thangSinh = Integer.parseInt(nv.ngaySinh.split("/")[1]);
			if (thangSinh == 2) {
				System.out.printf("[Nhan vien thu %d]:\n", count++);
				System.out.println(nv.toString());
			}
		}
		
		// Xuất thông tin nhân viên thuộc phòng kế toán
		System.out.println();
		System.out.println("--- Thong tin cac nhan vien thuoc phong ke toan ---");
		count = 1;

		for (var nv : list) {
			if (nv.chucVu.equals("Nhan vien")) {
				var nhanVien = (NhanVien) nv;
				if (nhanVien.tenDonVi.equals("Ke toan")) {
					System.out.printf("[Nhan vien thu %d]:\n", count++);
					System.out.println(nv.toString());
				}
			}
		}
		
		// Đếm số lượng nhân viên tên An
		count = 0;
		for (var nv : list) {
			String[] cumTuTen = nv.hoTen.split(" ");
			String tenNV = cumTuTen[cumTuTen.length - 1];
			if (tenNV.equals("An")) count++;
		}
		System.out.println("-> So luong nhan vien ten \"An\": " + count);
	}
}
