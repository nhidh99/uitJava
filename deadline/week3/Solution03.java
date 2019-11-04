import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution03 {
    public void solve(Scanner in) {
        System.out.println("1. Nhập một bài hát");
        System.out.println("2. Nhập một danh sách bài hát");
        System.out.print("Chọn yêu cầu: ");

        int choose = Integer.parseInt(in.nextLine());

        try {
            switch (choose) {
                case 1: {
                    var song = new Song();
                    song.input(in);
                    writeSong(song);
                    break;
                }

                case 2: {
                    System.out.print("Nhập số lượng bài hát: ");
                    var numOfSongs = Integer.parseInt(in.nextLine());
                    var songs = new Song[numOfSongs];
                    for (int i = 0; i < numOfSongs; i++) {
                        System.out.println("[Bài hát thứ " + (i + 1) + "]");
                        songs[i] = new Song();
                        songs[i].input(in);
                    }
                    writeListSong(songs);
                    break;
                }

                default: {
                    break;
                }
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    class Song {
        String id;
        String name;
        String singer;

        public void input(Scanner in) {
            System.out.print("- Nhập mã bài hát: ");
            this.id = in.nextLine();
            System.out.print("- Nhập tên bài hát: ");
            this.name = in.nextLine();
            System.out.print("- Nhập ca sĩ: ");
            this.singer = in.nextLine();
        }

        @Override
        public String toString() {
            return String.format("%s_%s_%s", id, name, singer);
        }
    }

    public void writeSong(Song song) throws IOException {
        var writer = new FileWriter("baihat.txt");
        writer.write(song.toString());
        writer.close();
    }

    public void writeListSong(Song[] songs) throws IOException {
        var writer = new BufferedWriter(new FileWriter("danhsachbaihat.txt"));
        for (var song : songs) {
            writer.write(song.toString() + "\n");
        }
        writer.close();
    }
}