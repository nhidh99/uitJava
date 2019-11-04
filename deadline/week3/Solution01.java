import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Solution01 {
    public void solve(Scanner in) {
        var studentList = new StudentList();
        System.out.print("Nhập số lượng sinh viên: ");
        var numberOfStudents = Integer.parseInt(in.nextLine());
        studentList.input(in, numberOfStudents);

        System.out.println("--- DANH SÁCH SINH VIÊN SINH VÀO THÁNG 1 ---");
        var studentsBornInJanuary = studentList.findStudentsByBirthMonth(1);
        studentsBornInJanuary.stream().forEach(student -> student.output());
        System.out.println();

        System.out.println("--- DANH SÁCH SINH VIÊN TÊN 'ANH'");
        var studentsWithNameAnh = studentList.findStudentsByName("Anh");
        studentsWithNameAnh.stream().forEach(student -> {
            System.out.println("[Sinh viên thứ " + (studentsWithNameAnh.indexOf(student) + 1) + "]");
            System.out.println("- Họ tên: " + student.getName());
            System.out.println("- Điểm toán: " + student.getSpecificMark("Math"));
            System.out.println("- Điểm trung bình: " + student.getSpecificMark("Average"));
            System.out.println();
        });

        System.out.println("--- SINH VIÊN CÓ TÊN VỚI NHIỀU TỪ NHẤT ---");
        var studentWithMaxWordsInName = studentList.getStudentMaxWordsInName();
        studentWithMaxWordsInName.output();

        in.close();
    }

    class Student {
        private String id;
        private String name;
        private String birth;
        private String marks;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getLastNameWord() {
            var tokens = new StringTokenizer(name);
            var output = "";
            while (tokens.hasMoreTokens()) {
                output = tokens.nextToken();
            }
            return output;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public double getSpecificMark(String subject) {
            var tokens = new StringTokenizer(marks, "-");
            var mapMarks = new HashMap<String, Double>();
            mapMarks.put("Math", Double.parseDouble(tokens.nextToken()));
            mapMarks.put("Physic", Double.parseDouble(tokens.nextToken()));
            mapMarks.put("Chemistry", Double.parseDouble(tokens.nextToken()));
            mapMarks.put("Average", mapMarks.values().stream().mapToDouble(Double::doubleValue).sum() / 3);
            return mapMarks.containsKey(subject) ? mapMarks.get(subject) : -1;
        }

        public LocalDate getBirthDate() {
            var tokens = new StringTokenizer(birth, "/");
            var day = Integer.parseInt(tokens.nextToken());
            var month = Integer.parseInt(tokens.nextToken());
            var year = Integer.parseInt(tokens.nextToken());
            var output = LocalDate.of(year, month, day);
            return output;
        }

        public int getNumberOfWordsInName() {
            var tokens = new StringTokenizer(name);
            return tokens.countTokens();
        }

        void input(Scanner in) {
            System.out.print("Nhập mssv: ");
            this.id = in.nextLine();
            System.out.print("Nhập họ tên: ");
            this.name = in.nextLine();
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            this.birth = in.nextLine();
            System.out.print("Nhập điểm toán-lí-hoá: ");
            this.marks = in.nextLine();
        }

        void output() {
            System.out.println(String.format("MSSV: %s\n" +
                            "Họ tên: %s\n" +
                            "Ngày sinh: %s\n" +
                            "Điểm toán: %f\n" +
                            "Điểm lý: %f\n" +
                            "Điểm hoá: %f\n",
                    id, name, birth,
                    getSpecificMark("Math"),
                    getSpecificMark("Physic"),
                    getSpecificMark("Chemistry")));
        }
    }

    class StudentList {
        List<Student> list;

        void input(Scanner in, int numberOfStudents) {
            list = new ArrayList<Student>();
            for (int i = 0; i < numberOfStudents; i++) {
                System.out.println("[Thông tin sinh viên thứ " + (i + 1) + "]");
                var student = new Student();
                student.input(in);
                list.add(student);
            }
            System.out.println();
        }

        void output() {
            list.stream().forEach(student -> student.output());
        }

        List<Student> findStudentsByName(String name) {
            if (list.isEmpty()) return null;
            return list.stream()
                    .filter(student -> student.getLastNameWord().equals(name))
                    .collect(Collectors.toList());
        }

        List<Student> findStudentsByBirthMonth(int month) {
            if (list.isEmpty()) return null;
            return list.stream()
                    .filter(student -> student.getBirthDate().getMonthValue() == month)
                    .collect(Collectors.toList());
        }

        Student getStudentMaxWordsInName() {
            if (list.isEmpty()) return null;
            Student output = list.get(0);
            for (var student : list) {
                if (output.getNumberOfWordsInName() < student.getNumberOfWordsInName()) {
                    output = student;
                }
            }
            return output;
        }
    }
}