package deadline;

import java.util.*;

public class PhanSo {

	private int tuSo;
	private int mauSo;

	public PhanSo(int tuSo, int mauSo) {
		this.setTuSo(tuSo);
		this.setMauSo(mauSo);
	}

	public int getTuSo() {
		return tuSo;
	}

	public void setTuSo(int tuSo) {
		this.tuSo = tuSo;
	}

	public int getMauSo() {
		return mauSo;
	}

	public void setMauSo(int mauSo) {
		this.mauSo = mauSo;
	}

	public int soSanh(PhanSo ps) {
		Float ps1 = (float) this.tuSo / this.mauSo;
		Float ps2 = (float) ps.tuSo / ps.mauSo;
		return ps1.compareTo(ps2);
	}
		
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Nhap so luong phan so: ");
		int n = in.nextInt();
		PhanSo[] mangPhanSo = new PhanSo[n];

		for (int i = 0; i < n; i++) {
			System.out.printf("\n- Nhap phan so thu %d:\n", i + 1);
			System.out.print("Nhap tu so: ");
			int tuSo = in.nextInt();
			System.out.print("Nhap mau so: ");
			int mauSo = in.nextInt();
			mangPhanSo[i] = new PhanSo(tuSo, mauSo);
		}
		System.out.println();
		in.close();
		
		PhanSo maxPhanSo = mangPhanSo[0];
		PhanSo minPhanSo = mangPhanSo[0];
		
		for (PhanSo phanSo : mangPhanSo) {
			if (maxPhanSo.soSanh(phanSo) < 0) {
				maxPhanSo = phanSo;
			}
			else if (minPhanSo.soSanh(phanSo) > 0) {
				minPhanSo = phanSo;
			}
		}
		
		System.out.printf("Phan so lon nhat = %d/%d\n", maxPhanSo.getTuSo(), maxPhanSo.getMauSo());
		System.out.printf("Phan so nho nhat = %d/%d\n", minPhanSo.getTuSo(), minPhanSo.getMauSo());
	}

}