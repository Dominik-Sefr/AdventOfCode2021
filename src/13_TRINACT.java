import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.DoubleToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_13 {
    public static void main(String[] args) {
        String fileName = "Files/13.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> axis = new ArrayList<String>();
        List<String> instructions = new ArrayList<String>();
        boolean isEmpty = false;
        for(int i = 0; i < linescopy.size(); i++){
            if(!isEmpty) {
                if (Objects.equals(linescopy.get(i), "")) {
                    isEmpty = true;
                }
                if(!isEmpty){
                    axis.add(linescopy.get(i));
                }
            }
            else {
                instructions.add(linescopy.get(i));
            }
        }
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        for(int i = 0; i < axis.size(); i++) {
            String[] line = axis.get(i).split(",");
            x.add(Integer.parseInt(line[0]));
            y.add(Integer.parseInt(line[1]));
        }
        List<Integer> sortedListx = x.stream().sorted().collect(Collectors.toList());
        List<Integer> sortedListy = y.stream().sorted().collect(Collectors.toList());
        String[][] grid = new String[sortedListy.get(sortedListy.size()-1)+1][sortedListx.get(sortedListx.size()-1)+1];
        for(int row = 0; row < grid.length; row++){
            Arrays.fill(grid[row], ".");
        }
        for(int i = 0; i < axis.size(); i++){
            String[] line = axis.get(i).split(",");
            grid[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = "#";
        }
        for(int instruct = 0; instruct < 1; instruct++){
            String[] split = instructions.get(instruct).split(" ");
            String[] ins = split[2].split("=");
            int instructint = Integer.parseInt(ins[1]);
            String ax = ins[0];
            grid = fold(grid, instructint, ax);
        }
        int visible = 0;
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(Objects.equals(grid[row][col], "#")){
                    visible++;
                }
            }
        }
        System.out.println(visible);
    }

    static String[][] fold(String[][] grid, int line, String ax){
        String[][] part1 = new String[1][1];
        String[][] part2 = new String[1][1];
        if(Objects.equals(ax, "y")) {
            part1 = new String[line][grid[0].length];
            part2 = new String[line][grid[0].length];
            for (int row = 0; row < line; row++) {
                for(int col = 0; col < grid[row].length; col++) {
                    part1[line-row-1][col] = grid[row][col];
                }
            }
            for(int row = line + 1; row < grid.length; row++){
                for(int col = 0; col < grid[row].length; col++){
                    part2[row-(line+1)][col] = grid[row][col];
                }
            }
        }
        if(Objects.equals(ax, "x")){
            part1 = new String[grid.length][line];
            part2 = new String[grid.length][line];
            for (int col = 0; col < line; col++) {
                for(int row = 0; row < grid.length; row++) {
                    part1[row][line-col-1] = grid[row][col];
                }
            }
            for(int col = line + 1; col < grid[0].length; col++){
                for(int row = 0; row < grid.length; row++){
                    part2[row][col-(line+1)] = grid[row][col];
                }
            }
        }
        String[][] finalgrid = new String[part1.length][part1[0].length];
        for(int row = 0; row < finalgrid.length; row++){
            Arrays.fill(finalgrid[row], ".");
        }
        for(int row = 0; row < part1.length; row++){
            for(int col = 0; col < part1[row].length; col++){
                if(Objects.equals(part1[row][col], "#") || Objects.equals(part2[row][col], "#")){
                    finalgrid[row][col] = "#";
                }
            }
        }
        return finalgrid;
    }
}
class Part_2_13 {
    public static void main(String[] args) {
        String fileName = "Files/13.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> axis = new ArrayList<String>();
        List<String> instructions = new ArrayList<String>();
        boolean isEmpty = false;
        for(int i = 0; i < linescopy.size(); i++){
            if(!isEmpty) {
                if (Objects.equals(linescopy.get(i), "")) {
                    isEmpty = true;
                }
                if(!isEmpty){
                    axis.add(linescopy.get(i));
                }
            }
            else {
                instructions.add(linescopy.get(i));
            }
        }
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        for(int i = 0; i < axis.size(); i++) {
            String[] line = axis.get(i).split(",");
            x.add(Integer.parseInt(line[0]));
            y.add(Integer.parseInt(line[1]));
        }
        List<Integer> sortedListx = x.stream().sorted().collect(Collectors.toList());
        List<Integer> sortedListy = y.stream().sorted().collect(Collectors.toList());
        String[][] grid = new String[sortedListy.get(sortedListy.size()-1)+1][sortedListx.get(sortedListx.size()-1)+1];
        for(int row = 0; row < grid.length; row++){
            Arrays.fill(grid[row], " ");
        }
        for(int i = 0; i < axis.size(); i++){
            String[] line = axis.get(i).split(",");
            grid[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = "#";
        }
        for(int instruct = 0; instruct < instructions.size(); instruct++){
            String[] split = instructions.get(instruct).split(" ");
            String[] ins = split[2].split("=");
            int instructint = Integer.parseInt(ins[1]);
            String ax = ins[0];
            grid = fold(grid, instructint, ax);
        }
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                System.out.print( grid[grid.length-row-1][grid[row].length-col-1] + " ");
            }
            System.out.println();
        }
        int visible = 0;
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(Objects.equals(grid[row][col], "#")){
                    visible++;
                }
            }
        }
    }

    static String[][] fold(String[][] grid, int line, String ax){
        String[][] part1 = new String[1][1];
        String[][] part2 = new String[1][1];
        if(Objects.equals(ax, "y")) {
            part1 = new String[line][grid[0].length];
            part2 = new String[line][grid[0].length];
            for (int row = 0; row < line; row++) {
                for(int col = 0; col < grid[row].length; col++) {
                    part1[line-row-1][col] = grid[row][col];
                }
            }
            for(int row = line + 1; row < grid.length; row++){
                for(int col = 0; col < grid[row].length; col++){
                    part2[row-(line+1)][col] = grid[row][col];
                }
            }
        }
        if(Objects.equals(ax, "x")){
            part1 = new String[grid.length][line];
            part2 = new String[grid.length][line];
            for (int col = 0; col < line; col++) {
                for(int row = 0; row < grid.length; row++) {
                    part1[row][line-col-1] = grid[row][col];
                }
            }
            for(int col = line + 1; col < grid[0].length; col++){
                for(int row = 0; row < grid.length; row++){
                    part2[row][col-(line+1)] = grid[row][col];
                }
            }
        }
        String[][] finalgrid = new String[part1.length][part1[0].length];
        for(int row = 0; row < finalgrid.length; row++){
            Arrays.fill(finalgrid[row], " ");
        }
        for(int row = 0; row < part1.length; row++){
            for(int col = 0; col < part1[row].length; col++){
                if(Objects.equals(part1[row][col], "#") || Objects.equals(part2[row][col], "#")){
                    finalgrid[row][col] = "#";
                }
            }
        }
        return finalgrid;
    }
}
