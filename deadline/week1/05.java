package deadline;

import java.util.*;

public class IntArray {

	int[] arr;

	// -- enum handle --
	public enum IntArrayHandle {
		OUTPUT, ADD, REMOVE, UPDATE, SORT, GET_AVERAGE, GET_NEAREST_AVERAGE, ERROR;

		public static IntArrayHandle fromInteger(int num) {
			switch (num) {
			case 1: return OUTPUT;
			case 2: return ADD;
			case 3: return REMOVE;
			case 4: return UPDATE;
			case 5:	return SORT;
			case 6:	return GET_AVERAGE;
			case 7:	return GET_NEAREST_AVERAGE;
			default: return ERROR;
			}
		}
	}

	// -- input --
	public void input(Scanner in) {
		System.out.print("Nhap so luong phan tu: ");
		int n = Integer.parseInt(in.nextLine());

		arr = new int[n];
		for (int i = 0; i < n; i++) {
			System.out.printf("Nhap phan tu thu %d: ", i + 1);
			arr[i] = Integer.parseInt(in.nextLine());
		}
	}

	// -- output --
	public void output() {
		System.out.println("Mang hien tai: " + Arrays.toString(arr));
	}

	// -- add with index --
	public void add(int index, int value) {
		if (index > arr.length)
			return;

		int[] newArr = new int[arr.length + 1];
		for (int i = 0, j = 0; i < newArr.length; i++, j++) {
			if (i == index) {
				newArr[i] = value;
				i++;
			}
			if (i < newArr.length) {
				newArr[i] = arr[j];
			}
		}
		arr = newArr;
	}

	// -- remove by index --
	public void remove(int index) {
		if (index > arr.length)
			return;

		int[] newArr = new int[arr.length - 1];
		for (int i = 0, j = 0; i < newArr.length; i++, j++) {
			if (i == index) {
				j++;
			}
			newArr[i] = arr[j];
		}
		arr = newArr;
	}

	// -- update by index --
	public void update(int index, int newValue) {
		if (index > arr.length)
			return;
		arr[index] = newValue;
	}

	// -- sort --
	public void sort() {
		Arrays.sort(arr);
	}

	// -- get average --
	public float getAverage() {
		int total = 0;
		for (int i : arr) {
			total += i;
		}
		return (float) total / arr.length;
	}

	// -- get element nearest average
	public int getNearestAverage() {
		float average = this.getAverage();
		float minDifference = Math.abs(arr[0] - average);
		int output = arr[0];

		for (int i = 1; i < arr.length; i++) {
			float difference = Math.abs(arr[i] - average);
			if (difference < minDifference) {
				minDifference = difference;
				output = arr[i];
			}
		}
		return output;
	}

	public static void main(String[] args) {
		IntArray intArray = new IntArray();
		Scanner in = new Scanner(System.in);
		int choose = 1;
		intArray.input(in);
		System.out.println("---");

		do {
			System.out.print("Cac chuc nang quan li:\n" 
					+ " 1. Xuat mang.\n" 
					+ " 2. Them phan tu.\n"
					+ " 3. Xoa phan tu.\n" 
					+ " 4. Sua phan tu.\n" 
					+ " 5. Sap xep tang dan.\n"
					+ " 6. Tinh gia tri trung binh.\n" 
					+ " 7. Tim phan tu gan gia tri trung binh nhat.\n"
					+ " 0. Thoat chuong trinh.\n" + "Chon chuc nang: ");
			choose = Integer.parseInt(in.nextLine());
			System.out.println("---");

			switch (IntArrayHandle.fromInteger(choose)) {
			case OUTPUT: {
				intArray.output();
				break;
			}
			case ADD: {
				System.out.print("Nhap vi tri can them: ");
				int index = Integer.parseInt(in.nextLine());
				System.out.print("Nhap gia tri can them: ");
				int value = Integer.parseInt(in.nextLine());
				intArray.add(index, value);
				break;
			}

			case REMOVE: {
				System.out.print("Nhap vi tri can xoa: ");
				int index = Integer.parseInt(in.nextLine());
				intArray.remove(index);
				break;
			}

			case UPDATE: {
				System.out.print("Nhap vi tri can sua: ");
				int index = Integer.parseInt(in.nextLine());
				System.out.print("Nhap gia tri moi: ");
				int newValue = Integer.parseInt(in.nextLine());
				intArray.update(index, newValue);
				break;
			}

			case SORT: {
				intArray.sort();
				intArray.output();
				break;
			}

			case GET_AVERAGE: {
				float average = intArray.getAverage();
				System.out.println("Gia tri trung binh = " + average);
				break;
			}

			case GET_NEAREST_AVERAGE: {
				int nearestElement = intArray.getNearestAverage();
				System.out.println("Phan tu gan gia tri trung binh nhat = " + nearestElement);
				break;
			}

			case ERROR: {
				break;
			}

			default: {
				System.out.println("Co loi!");
				break;
			}
			}
			System.out.println("---");
		} while (choose >= 0 || choose <= 8);
		in.close();
	}
}