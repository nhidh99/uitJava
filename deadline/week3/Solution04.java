import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Solution04 {
    public void solve(Scanner in) {
        var tourList = new TourList();
        var done = false;

        while (!done) {
            System.out.println("\n--- CHỨC NĂNG ---");
            System.out.println("1. Nhập vào danh sách các tour.\n" +
                    "2. Thêm một tour vào danh sách.\n" +
                    "3. Tính tổng doanh thu và đếm số lượng của từng loại tour.\n" +
                    "4. Tính tổng doanh thu của tất cả các tour.\n" +
                    "5. Kiểm tra một tour có mã do người dùng nhập có trong danh sách tour không.\n" +
                    "6. Xóa một tour có mã do người dùng nhập.\n" +
                    "7. Sắp xếp các tour theo loại tour.\n" +
                    "8. Sắp xếp các tour theo loại tour, doanh thu.\n" +
                    "9. Xuất danh sách các tour.\n" +
                    "10. Thoát");
            System.out.print("-> Chọn chức năng: ");
            var choose = Choose.fromInt(Integer.parseInt(in.nextLine()));
            if (choose == Choose.EXIT) done = true;
            else {
                System.out.println();
                tourList.handleChoose(in, choose);
            }
        }
        in.close();
    }

    abstract class Tour {
        String id;
        String name;
        int maxCustomer;
        int realCustomer;
        long price;
        LocalDate beginDate;
        LocalDate endDate;

        public void input(Scanner in) {
            System.out.print("- Nhập mã tour: ");
            this.id = in.nextLine();
            System.out.print("- Nhập tên tour: ");
            this.name = in.nextLine();
            System.out.print("- Nhập số hành khách tối đa: ");
            this.maxCustomer = Integer.parseInt(in.nextLine());
            System.out.print("- Nhập số hành khách thực tế: ");
            this.realCustomer = Integer.parseInt(in.nextLine());
            System.out.print("- Nhập giá tour: ");
            this.price = Integer.parseInt(in.nextLine());
            System.out.print("- Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String beginDateString = in.nextLine();
            System.out.print("- Nhập ngày kết thúc (dd/MM/yyyy): ");
            String endDateString = in.nextLine();

            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.beginDate = LocalDate.parse(beginDateString, formatter);
            this.endDate = LocalDate.parse(endDateString, formatter);
        }

        @Override
        public String toString() {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return String.format("- Mã tour: %s\n" +
                            "- Tên tour: %s\n" +
                            "- Loại tour: %s \n" +
                            "- Số hành khách tối đa: %d\n" +
                            "- Số hành khách thực tế: %d\n" +
                            "- Giá tour: %d\n" +
                            "- Ngày bắt đầu: %s\n" +
                            "- Ngày kết thúc: %s\n" +
                            "- Doanh thu: %d\n",
                    this.id,
                    this.name,
                    this.getType().equals("Domestic") ? "Trong nước" : "Quốc tế",
                    this.maxCustomer,
                    this.realCustomer,
                    this.price,
                    this.beginDate.format(formatter),
                    this.endDate.format(formatter),
                    this.getRevenue());
        }

        public abstract long getRevenue();

        public abstract String getType();
    }

    class DomesticTour extends Tour {
        String[] locations;

        @Override
        public void input(Scanner in) {
            super.input(in);
            System.out.print("- Nhập số lượng tỉnh thành đi qua: ");
            int numOfLocation = Integer.parseInt(in.nextLine());
            locations = new String[numOfLocation];

            for (int i = 0; i < numOfLocation; i++) {
                System.out.print(" + Tên tỉnh/thành thứ " + (i + 1) + ": ");
                locations[i] = in.nextLine();
            }
        }

        @Override
        public String toString() {
            return super.toString() + "- Các tỉnh thành đi qua: " + String.join("_", locations) + "\n";
        }

        @Override
        public long getRevenue() {
            return (realCustomer * price) - (maxCustomer - realCustomer) * (3 / 100) * price;
        }

        @Override
        public String getType() {
            return "Domestic";
        }
    }

    class ForeignTour extends Tour {
        String[] locations;

        @Override
        public void input(Scanner in) {
            super.input(in);
            System.out.print("- Nhập số lượng các quốc gia đi qua: ");
            int numOfLocation = Integer.parseInt(in.nextLine());
            locations = new String[numOfLocation];

            for (int i = 0; i < numOfLocation; i++) {
                System.out.print(" + Tên quốc gia thứ " + (i + 1) + ": ");
                locations[i] = in.nextLine();
            }
        }

        @Override
        public String toString() {
            return super.toString() + "- Các quốc gia đi qua: " + String.join("_", locations) + "\n";
        }

        @Override
        public long getRevenue() {
            return (realCustomer * price) - (maxCustomer - realCustomer) * (1 / 10) * price;
        }

        @Override
        public String getType() {
            return "Foreign";
        }
    }

    enum Choose {
        INPUT,
        INSERT,
        REVENUE_TOUR,
        REVENUE_TOTAL,
        CHECK,
        DELETE,
        SORT_TOUR,
        SORT_TOUR_REVENUE,
        OUTPUT,
        EXIT;

        static Choose fromInt(int id) {
            switch (id) {
                case 1:
                    return INPUT;
                case 2:
                    return INSERT;
                case 3:
                    return REVENUE_TOUR;
                case 4:
                    return REVENUE_TOTAL;
                case 5:
                    return CHECK;
                case 6:
                    return DELETE;
                case 7:
                    return SORT_TOUR;
                case 8:
                    return SORT_TOUR_REVENUE;
                case 9:
                    return OUTPUT;
                case 10:
                    return EXIT;
                default:
                    return null;
            }
        }
    }

    class TourList {
        List<Tour> list;

        public TourList() {
            list = new ArrayList<>();
        }

        public void input(Scanner in) {
            System.out.print("- Nhập số lượng tour: ");
            int numOfTour = Integer.parseInt(in.nextLine());

            for (int i = 0; i < numOfTour; i++) {
                System.out.println(String.format("[Nhập tour thứ %d]", i + 1));
                insert(in);
            }
        }

        public void insert(Scanner in) {
            System.out.print("- Chọn loại tour (1. Trong nước, 2. Quốc tế): ");
            int choose = Integer.parseInt(in.nextLine());
            Tour tour;
            switch (choose) {
                case 1: {
                    tour = new DomesticTour();
                    break;
                }
                case 2: {
                    tour = new ForeignTour();
                    break;
                }
                default:
                    return;
            }
            tour.input(in);
            list.add(tour);
        }

        public void delete(Scanner in) {
            System.out.print("Nhập mã tour cần xoá: ");
            var tourId = in.nextLine();
            delete(tourId);
        }

        public void delete(String tourId) {
            var check = list.stream().filter(tour -> tour.id.equals(tourId)).findAny();
            if (check.isPresent()) {
                var tour = check.get();
                list.remove(tour);
            } else {
                System.out.println("Mã tour không tồn tại!");
            }
        }

        public void output() {
            System.out.println("\n--- DANH SÁCH CÁC TOUR ---");
            list.stream().forEach(tour -> {
                System.out.println(String.format("[Tour thứ %d]\n%s", list.indexOf(tour) + 1, tour.toString()));
            });
        }

        public void check(Scanner in) {
            System.out.print("Nhập mã tour cần kiểm tra: ");
            var tourId = in.nextLine();
            var check = list.stream().anyMatch(tour -> tour.id.equals(tourId));
            System.out.println("=> Kết quả: " + (check ? "Có" : "Không"));
        }

        public void getRevenueByTourType() {
            long domesticTourRevenue = list.stream()
                    .filter(tour -> tour.getType().equals("Domestic"))
                    .mapToLong(Tour::getRevenue).sum();

            long foreignTourRevenue = list.stream()
                    .filter(tour -> tour.getType().equals("Foreign"))
                    .mapToLong(Tour::getRevenue).sum();

            System.out.println(String.format(
                    "=> Doanh thu tour trong nước: %d\n" +
                            "=> Doanh thu tour quốc tế: %d",
                    domesticTourRevenue,
                    foreignTourRevenue));
        }

        public void getTotalRevenue() {
            long totalRevenue = list.stream().mapToLong(Tour::getRevenue).sum();
            System.out.println(String.format("=> Tổng doanh thu: %d\n", totalRevenue));
        }

        public void sortByTourType() {
            var copyList = new TourList();
            copyList.list = list;
            copyList.list.sort(Comparator.comparing(Tour::getType));
            copyList.output();
        }

        public void sortByTourTypeAndRevenue() {
            var copyList = new TourList();
            copyList.list = list;
            copyList.list.sort(Comparator.comparing(Tour::getType)
                    .thenComparing(Tour::getRevenue, Comparator.reverseOrder()));
            copyList.output();
        }

        public void handleChoose(Scanner in, Choose choose) {
            switch (choose) {
                case INPUT: {
                    input(in);
                    break;
                }
                case INSERT: {
                    insert(in);
                    break;
                }
                case REVENUE_TOUR: {
                    getRevenueByTourType();
                    break;
                }
                case REVENUE_TOTAL: {
                    getTotalRevenue();
                    break;
                }
                case DELETE: {
                    delete(in);
                    break;
                }
                case SORT_TOUR: {
                    sortByTourType();
                    break;
                }
                case SORT_TOUR_REVENUE: {
                    sortByTourTypeAndRevenue();
                    break;
                }
                case CHECK: {
                    check(in);
                    break;
                }
                case OUTPUT: {
                    output();
                    break;
                }
            }
        }
    }
}
