package default_package;
import my_exception.*;

import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        solve07();
    }

    public static void solve01() {
        System.out.print("Nhập số nguyên: ");
        String input = in.nextLine();
        try {
            int parsed = Integer.parseInt(input);
            System.out.println("Chuỗi -> số: " + parsed);
        } catch (Exception ex) {
            System.err.println("Chuỗi nhập vào phải là số nguyên");
        } finally {
            in.close();
        }
    }

    public static void solve02() {
        System.out.print("Nhập số nguyên dương: ");
        String input = in.nextLine();

        try {
            if (!input.matches("\\d+$") || Integer.parseInt(input) == 0) {
                throw new UnsignedIntegerException();
            } else {
                int parsed = Integer.parseUnsignedInt(input);
                System.out.println("Chuỗi -> số dương: " + parsed);
            }
        } catch (UnsignedIntegerException ex) {
            System.err.println("Chuỗi nhập vào phải là một số nguyên dương");
        } finally {
            in.close();
        }
    }

    public static void solve03() {
        System.out.print("Nhập vào một địa chỉ web: ");
        String input = in.nextLine();
        String regex = "(https?://(www\\.)?|(www\\.))[A-z0-9_-]{3,20}(\\.[A-z]{1,4}){1,2}";

        try {
            if (!input.matches(regex)) {
                throw new WebAddressException();
            } else {
                System.out.println("Địa chỉ web hợp lệ.");
            }
        } catch (
                WebAddressException ex) {
            System.err.println("Địa chỉ web không hợp lệ");
        } finally {
            in.close();
        }
    }

    public static void solve04() {
        System.out.print("Nhập tuổi: ");
        String input = in.nextLine();

        try {
            if (!input.matches("\\d+$")) {
                throw new AgeException();
            } else {
                System.out.println("Tuổi hợp lệ.");
            }
        } catch (AgeException ex) {
            System.err.println("Tuổi không hợp lệ.");
        } finally {
            in.close();
        }
    }

    public static void solve05() {
        System.out.print("Nhập mã PIN: ");
        String input = in.nextLine();

        try {
            if (!input.matches("\\d{4}$")) {
                throw new PINException();
            } else {
                System.out.println("Mã PIN hợp lệ.");
            }
        } catch (PINException ex) {
            System.err.println("Mã PIN không hợp lệ.");
        } finally {
            in.close();
        }
    }

    public static void solve06() {
        System.out.print("Nhập số điện thoại (6 - 12 số): ");
        String input = in.nextLine();

        try {
            if (!input.matches("\\d{6,12}$")) {
                throw new PhoneException();
            } else {
                System.out.println("Số điện thoại hợp lệ.");
            }
        } catch (PhoneException ex) {
            System.err.println("Số điện thoại không hợp lệ.");
        } finally {
            in.close();
        }
    }

    public static void solve07() {
        System.out.print("Nhập độ dài cạnh a = ");
        String inputA = in.nextLine();
        System.out.print("Nhập độ dài cạnh b = ");
        String inputB = in.nextLine();
        System.out.print("Nhập độ dài cạnh c = ");
        String inputC = in.nextLine();

        String regex = "^(?:[0-9]\\d*|0)?(?:\\.\\d+)?$";
        boolean isFloatValues = inputA.matches(regex) && inputB.matches(regex) && inputC.matches(regex);

        try {
            if (!isFloatValues) {
                throw new TriangleEdgesException();
            } else {
                float edgeA = Float.parseFloat(inputA);
                float edgeB = Float.parseFloat(inputB);
                float edgeC = Float.parseFloat(inputC);
                boolean isValidEdges =
                        (edgeA > 0 && edgeB > 0 && edgeC > 0)
                        && (edgeA + edgeB > edgeC)
                        && (edgeA + edgeC > edgeB)
                        && (edgeB + edgeC > edgeA);

                if (!isValidEdges) {
                    throw new TriangleEdgesException();
                }
                else {
                    System.out.println("Ba cạnh hợp lệ");
                }
            }
        } catch (TriangleEdgesException ex) {
            System.err.println("Ba cạnh không hợp lệ.");
        } finally {
            in.close();
        }
    }
}
