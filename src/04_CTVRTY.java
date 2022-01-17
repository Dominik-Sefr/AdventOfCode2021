import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_4 {
    public static void main(String[] args) {
        String fileName = "Files/04.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] callnum = linescopy.get(0).split(",");
        int[] call = new int[callnum.length];
        for(int i = 0; i < callnum.length; i++){
            call[i] = Integer.parseInt(callnum[i]);
        }
        linescopy.remove(0);
        System.out.println(Arrays.toString(call));
        String matrix = "";
        for(int i = 0; i < linescopy.size(); i++){
            if(Objects.equals(linescopy.get(i), "")) {
                matrix += "\n\n";
            }
            else {
                matrix += "\n " + linescopy.get(i);
            }
        }
        String[] matrixes = matrix.split("\n\n");
        int[][][] bingos = new int[matrixes.length-1][5][5];
        for(int i = 1; i < matrixes.length; i++){
            String[] grid = matrixes[i].split(" ");
            List<Integer> nums = new ArrayList<Integer>();
            for(int num = 0; num < grid.length; num++){
                String s = grid[num].trim();
                if(!s.equals("")){
                    nums.add(Integer.parseInt(s));
                }
            }
            for(int j = 0; j < nums.size(); j++){
                int finalrow = (int) Math.floor((double) j/ (double) bingos[i-1].length);
                int finalcol = j - (finalrow)*bingos[i-1].length;
                bingos[i-1][finalrow][finalcol] = nums.get(j);
            }
        }
        for(int i = 0; i < bingos.length; i++){
            System.out.println();
            for(int row = 0; row < bingos[i].length; row++){
                System.out.println(Arrays.toString(bingos[i][row]));
            }
        }
        int result = -1;
        for(int num = 0; num < call.length; num++){
            int drawn = call[num];
            int winner = -1;
            for(int i = 0; i < bingos.length; i++){
                for(int row = 0; row < bingos[i].length; row++){
                    for(int col = 0; col < bingos[i][row].length; col++){
                        if(drawn == bingos[i][row][col]){
                            bingos[i][row][col] = 0;
                        }
                    }
                }
            }
            for(int i = 0; i < bingos.length; i++){
                for(int row = 0; row < bingos[i].length; row++){
                    int colsum = 0;
                    int rowsum = 0;
                    for(int col = 0; col < bingos[i][row].length; col++){
                        colsum += bingos[i][col][row];
                        rowsum += bingos[i][row][col];
                    }
                    if(rowsum == 0 || colsum == 0){
                        winner = i;
                        break;
                    }
                }
                if (winner == i) {
                    break;
                }
            }
            if(winner != -1) {
                int sum = 0;
                for (int row = 0; row < bingos[winner].length; row++) {
                    for (int col = 0; col < bingos[winner][row].length; col++) {
                       sum += bingos[winner][row][col];
                    }
                }
                result = sum*drawn;
                System.out.println(winner + " " + drawn);
                break;
            }
        }
        System.out.println(result);


    }
}
class Part_2_4 {
    public static void main(String[] args) {
        String fileName = "Files/04.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] callnum = linescopy.get(0).split(",");
        int[] call = new int[callnum.length];
        for(int i = 0; i < callnum.length; i++){
            call[i] = Integer.parseInt(callnum[i]);
        }
        linescopy.remove(0);
        System.out.println(Arrays.toString(call));
        String matrix = "";
        for(int i = 0; i < linescopy.size(); i++){
            if(Objects.equals(linescopy.get(i), "")) {
                matrix += "\n\n";
            }
            else {
                matrix += "\n " + linescopy.get(i);
            }
        }
        String[] matrixes = matrix.split("\n\n");
        int[][][] bingos = new int[matrixes.length-1][5][5];
        for(int i = 1; i < matrixes.length; i++){
            String[] grid = matrixes[i].split(" ");
            List<Integer> nums = new ArrayList<Integer>();
            for(int num = 0; num < grid.length; num++){
                String s = grid[num].trim();
                if(!s.equals("")){
                    nums.add(Integer.parseInt(s));
                }
            }
            for(int j = 0; j < nums.size(); j++){
                int finalrow = (int) Math.floor((double) j/ (double) bingos[i-1].length);
                int finalcol = j - (finalrow)*bingos[i-1].length;
                bingos[i-1][finalrow][finalcol] = nums.get(j);
            }
        }
        for(int i = 0; i < bingos.length; i++){
            System.out.println();
            for(int row = 0; row < bingos[i].length; row++){
                System.out.println(Arrays.toString(bingos[i][row]));
            }
        }
        int result = -1;
        List<Integer> win = new ArrayList<Integer>();
        for(int num = 0; num < call.length; num++){
            int drawn = call[num];
            int winners = 0;
            for(int i = 0; i < bingos.length; i++){
                for(int row = 0; row < bingos[i].length; row++){
                    for(int col = 0; col < bingos[i][row].length; col++){
                        if(drawn == bingos[i][row][col]){
                            bingos[i][row][col] = -1;
                        }
                    }
                }
            }
            for(int i = 0; i < bingos.length; i++){
                boolean isin = false;
                for(int listiter = 0; listiter < win.size(); listiter++) {
                       if(i == win.get(listiter)){
                           isin = true;
                           break;
                       }
                }
                if(!isin) {
                    for (int row = 0; row < bingos[i].length; row++) {
                        int colsum = 0;
                        int rowsum = 0;
                        for (int col = 0; col < bingos[i][row].length; col++) {
                            colsum += bingos[i][col][row];
                            rowsum += bingos[i][row][col];
                        }
                        if (rowsum == -5 || colsum == -5) {
                            win.add(i);
                            break;
                        }
                    }
                }
            }
            if(win.size() == bingos.length){
                int last = win.get(win.size()-1);
                int sum = 0;
                System.out.println();
                for(int row = 0; row < bingos[last].length; row++){
                    for(int col = 0; col < bingos[last][row].length; col++){
                        if(bingos[last][row][col] == -1){
                            bingos[last][row][col] = 0;
                        }
                    }
                }
                for(int row = 0; row < bingos[last].length; row++){
                    System.out.println(Arrays.toString(bingos[last][row]));
                    for(int col = 0; col < bingos[last][row].length; col++){
                        sum += bingos[last][row][col];
                    }
                }
                result = sum * drawn;
                System.out.println(result + " " + sum + " " + drawn);
                break;
            }
        }
        System.out.println(result);
    }
}