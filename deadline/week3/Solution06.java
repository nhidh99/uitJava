import java.util.Random;

public class Solution06 {
    enum Month {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER;

        public int getDayOfMonth() {
            switch (this) {
                case JANUARY:
                case MARCH:
                case MAY:
                case JULY:
                case AUGUST:
                case OCTOBER:
                case DECEMBER:
                    return 31;
                case FEBRUARY:
                    return 28;
                default:
                    return 30;
            }
        }
    }

    public void solve() {
        int randomMonth = new Random().nextInt(12) + 1;
        System.out.println(String.format("Tháng %d có %d ngày", randomMonth, Month.values()[randomMonth - 1].getDayOfMonth()));
    }
}
