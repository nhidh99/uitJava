import java.util.*;
import java.util.stream.Collectors;

public class Solution05 {
    public void solve(Scanner in) {
        var accountList = new AccountList();
        accountList.input(in);
        accountList.output();
        in.close();
    }

    class Account {
        String username;
        String password;

        public String getUsername() {
            return username;
        }

        void input(Scanner in) {
            boolean isValidUsername = false;
            boolean isValidPassword = false;

            while (!isValidUsername) {
                // Kiểm tra tài khoản
                System.out.print("- Nhập tên tài khoản: ");
                username = in.nextLine();
                isValidUsername = username.matches("[a-zA-Z][a-zA-Z0-9]*");

                // Nếu tài khoản không hợp lệ, không bắt đầu bằng số => gợi ý tài khoản
                boolean isBeginWithNumber = Character.isDigit(username.charAt(0));
                if (!(isValidUsername || isBeginWithNumber)) {
                    System.out.println("- Gợi ý tên tài khoản: " + suggestUsername(username));
                }
            }

            while (!isValidPassword) {
                System.out.print("- Nhập mật khẩu: ");
                password = in.nextLine();
                isValidPassword = password.matches("^.{3,}$");
            }
        }

        String suggestUsername(String username) {
            var tokens = new StringTokenizer(username, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
            var output = new StringBuilder();
            var invalidChars = new HashSet<Character>();

            while (tokens.hasMoreTokens()) {
                invalidChars.add(tokens.nextToken().charAt(0));
            }

            for (var chr : username.toCharArray()) {
                if (!invalidChars.contains(chr)) {
                    output.append(chr);
                }
            }
            return output.toString();
        }

        @Override
        public String toString() {
            return String.format("- Tên đăng nhập: %s\n- Mật khẩu: %s",
                    username, password);
        }
    }

    class AccountList {
        List<Account> list;

        public AccountList() {
            list = new ArrayList<>();
        }

        public void input(Scanner in) {
            System.out.print("Nhập số lượng tài khoản: ");
            int numOfUser = Integer.parseInt(in.nextLine());

            for (int i = 0; i < numOfUser; i++) {
                System.out.println(String.format("[NHẬP TÀI KHOẢN THỨ %d]", i + 1));
                var account = new Account();
                account.input(in);

                boolean isContainsUsername = list.stream().anyMatch(acc -> acc.username.equals(account.username));
                if (isContainsUsername) {
                    System.out.println("-> Tên tài khoản đã tồn tại");
                    i--;
                } else list.add(account);
            }
        }

        public void output() {
            System.out.println("\n--- DANH SÁCH TÀI KHOẢN ---");
            list.stream().forEach(account -> {
                System.out.println("[TÀI KHOẢN " + (list.indexOf(account) + 1) + "]");
                System.out.println(account.toString());
            });
        }
    }
}