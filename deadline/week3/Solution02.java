import java.util.Scanner;

public class Solution02 {
    enum Qua {
        NHO,
        TÁO,
        XOÀI,
        CHANH
    }

    class CQua {
        int id;
        Qua qua;

        public CQua(int id) {
            this.id = id;
            switch (id) {
                case 10:
                    this.qua = Qua.NHO;
                    break;
                case 24:
                    this.qua = Qua.TÁO;
                    break;
                case 11:
                    this.qua = Qua.XOÀI;
                    break;
                case 99:
                    this.qua = Qua.CHANH;
                    break;
                default:
                    this.qua = null;
                    break;
            }
        }

        @Override
        public String toString() {
            switch (qua) {
                case NHO:
                    return "Nho";
                case TÁO:
                    return "Táo";
                case XOÀI:
                    return "Xoài";
                case CHANH:
                    return "Chanh";
                default:
                    return null;
            }
        }

        public Integer getPrice() {
            switch (qua) {
                case NHO:
                    return 1000;
                case TÁO:
                    return 10000;
                case XOÀI:
                    return 5000;
                case CHANH:
                    return 500;
                default:
                    return 0;
            }
        }
    }

    public void solve(Scanner in) {
        System.out.print("Nhập số lượng quả: ");
        int N = Integer.parseInt(in.nextLine());

        long totalPrice = 0;
        for (int i = 0; i < N; i++) {
            System.out.println("[Nhập quả thứ " + (i + 1) + "]");
            System.out.print("Nhập mã loại quả (Nho = 10 / Táo = 24 / Xoài = 11 / Chanh = 99): ");
            int id = Integer.parseInt(in.nextLine());
            totalPrice += new CQua(id).getPrice();
        }
        System.out.println("=> Tổng tiền mua = " + totalPrice + "đ");
        in.close();
    }
}
