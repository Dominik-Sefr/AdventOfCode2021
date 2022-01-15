import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_9 {
    public static void main(String[] args) {
        String fileName = "Files/H.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[][] matrix = new int[linescopy.size()+2][linescopy.get(0).length()+2];
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[row].length; col++){
                if(row == 0 || col == 0 || row == matrix.length -1 || col == matrix[row].length - 1){
                    matrix[row][col] = 9;
                }
            }
        }
        for(int row = 0; row < linescopy.size(); row++){
            for(int col = 0; col < linescopy.get(0).length(); col++){
                    matrix[row+1][col+1] = Integer.parseInt(String.valueOf(linescopy.get(row).charAt(col)));
            }
        }
        int sum=0;
        for(int row = 1; row < matrix.length-1; row++){
            for(int col = 1; col < matrix[0].length-1; col++){
                if(matrix[row][col] < 9){
                    if(matrix[row+1][col] > matrix[row][col] && matrix[row-1][col] > matrix[row][col] && matrix[row][col+1] > matrix[row][col] && matrix[row][col-1] > matrix[row][col]){
                       sum += 1 + matrix[row][col];
                    }
                }
            }
        }
        for(int i = 0; i < matrix.length; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println(sum);

    }
}