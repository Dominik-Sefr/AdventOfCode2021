import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

class Part_1_5 {
    public static void main(String[] args) {
        String fileName = "Files/E.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //242,601 -> 242,18
        int[] x1 = new int[linescopy.size()];
        int[] y1 = new int[linescopy.size()];
        int[] x2 = new int[linescopy.size()];
        int[] y2 = new int[linescopy.size()];
        for(int row = 0; row < linescopy.size(); row++){
            String temp = linescopy.get(row);
            String[] splitted = temp.split(" ");
            String[] splittedones = splitted[0].split(",");
            String[] splittedtwos = splitted[2].split(",");
            x1[row] = Integer.parseInt(splittedones[0]);
            y1[row] = Integer.parseInt(splittedones[1]);
            x2[row] = Integer.parseInt(splittedtwos[0]);
            y2[row] = Integer.parseInt(splittedtwos[1]);
        }
        int maxx1 = Arrays.stream(x1)
                .max()
                .getAsInt();
        int maxy1 = Arrays.stream(y1)
                .max()
                .getAsInt();
        int maxx2 = Arrays.stream(x2)
                .max()
                .getAsInt();
        int maxy2 = Arrays.stream(y2)
                .max()
                .getAsInt();
        int maxx, maxy;
        maxx = Math.max(maxx1, maxx2);
        maxy = Math.max(maxy1, maxy2);
        int[][] pole = new int[maxy+1][maxx+1];
        for(int i = 0; i < x1.length; i++){
            if(x1[i] == x2[i] || y1[i] == y2[i]){
                int length = Math.abs(x1[i] - x2[i]);
                int high = Math.abs(y1[i] - y2[i]);
                int leftx = Math.min(x1[i], x2[i]);
                int highery = Math.min(y1[i], y2[i]);
                for(int j = leftx; j <= leftx+length; j++){
                    for(int k = highery; k <= highery + high; k++){
                        pole[k][j]++;
                    }
                }
            }
        }
        int count = 0;
        for(int row = 0; row < pole.length; row++){
            for(int col = 0; col < pole[0].length; col++){
                if(pole[row][col] > 1){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
class Part_2_5 {
    public static void main(String[] args) {
        String fileName = "Files/E.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //242,601 -> 242,18
        int[] x1 = new int[linescopy.size()];
        int[] y1 = new int[linescopy.size()];
        int[] x2 = new int[linescopy.size()];
        int[] y2 = new int[linescopy.size()];
        for (int row = 0; row < linescopy.size(); row++) {
            String temp = linescopy.get(row);
            String[] splitted = temp.split(" ");
            String[] splittedones = splitted[0].split(",");
            String[] splittedtwos = splitted[2].split(",");
            x1[row] = Integer.parseInt(splittedones[0]);
            y1[row] = Integer.parseInt(splittedones[1]);
            x2[row] = Integer.parseInt(splittedtwos[0]);
            y2[row] = Integer.parseInt(splittedtwos[1]);
        }
        int maxx1 = stream(x1)
                .max()
                .getAsInt();
        int maxy1 = stream(y1)
                .max()
                .getAsInt();
        int maxx2 = stream(x2)
                .max()
                .getAsInt();
        int maxy2 = stream(y2)
                .max()
                .getAsInt();
        int maxx = Math.max(maxx1, maxx2);
        int maxy = Math.max(maxy1, maxy2);
        int[][] pole = new int[maxy+1][maxx+1];
        for (int i = 0; i < x1.length; i++) {
            int length = Math.abs(x1[i] - x2[i]);
            if(x1[i] == x2[i] || y1[i] == y2[i]){
                int high = Math.abs(y1[i] - y2[i]);
                int leftx = Math.min(x1[i], x2[i]);
                int highery = Math.min(y1[i], y2[i]);
                for (int j = leftx; j <= leftx + length; j++) {
                    for (int k = highery; k <= highery + high; k++) {
                        pole[k][j]++;
                    }
                }
            }
            else
            {
                int x;
                int y;
                boolean right = x1[i] < x2[i];
                boolean down = y1[i] < y2[i];
                for(int k = 0; k <= length; k++){
                    if(right){
                        x = x1[i] + k;
                    }
                    else
                    {
                        x = x1[i] - k;
                    }
                    if(down){
                        y = y1[i] + k;
                    }
                    else{
                        y = y1[i] -k;
                    }
                    pole[y][x]++;
                }
            }
        }
        int count = 0;
        for (int row = 0;row < pole.length; row++){
            for (int col = 0; col < pole[0].length; col++) {
                if (pole[row][col] > 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}