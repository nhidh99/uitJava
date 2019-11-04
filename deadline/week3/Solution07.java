import java.util.Comparator;
import java.util.Vector;

public class Solution07 {
    class Building {
        String name;
        int height;
        int year;

        public Building(String name, int height, int year) {
            this.name = name;
            this.height = height;
            this.year = year;
        }

        @Override
        public String toString() {
            return String.format("%s_%s_%s", name, height, year);
        }

        public int getHeight() {
            return height;
        }

        public int getYear() {
            return year;
        }
    }

    public void solve() {
        var buildings = new Vector<Building>();
        buildings.add(new Building("Building01", 300, 1998));
        buildings.add(new Building("Building02", 200, 2000));
        buildings.add(new Building("Building03", 400, 1999));
        buildings.add(new Building("Building04", 200, 1996));

        // Xuất danh sách toà nhà
        System.out.println("[Danh sách gốc]");
        buildings.stream().forEach(b -> System.out.println(b.toString()));

        // Sort toà nhà theo tiêu chí độ cao
        System.out.println("[Danh sách sort theo độ cao]");
        var copyBuildings = new Vector<Building>(buildings);
        copyBuildings.sort(Comparator.comparing(Building::getHeight));
        copyBuildings.stream().forEach(b -> System.out.println(b.toString()));

        // Sort toà nhà theo độ cao và năm xây dựng
        System.out.println("[Danh sách sort theo độ cao -> năm xây dựng]");
        copyBuildings = new Vector<Building>(buildings);
        copyBuildings.sort(Comparator.comparing(Building::getHeight).thenComparing(Building::getYear));
        copyBuildings.stream().forEach(b -> System.out.println(b.toString()));
    }
}
