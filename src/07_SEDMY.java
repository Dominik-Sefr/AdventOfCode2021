import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_7 {
    public static void main(String[] args) {
        String fileName = "Files/07.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] pos = linescopy.get(0).split(",");
        int[] positions = new int[pos.length];
        for(int i = 0; i < positions.length; i++){
            positions[i] = Integer.parseInt(pos[i]);
        }
        Arrays.sort(positions);
        int median;
        if(positions.length % 2 == 1){
            median = positions[((positions.length+1)/2) - 1];
        }
        else{
            median = (positions[(positions.length/2 - 1)] + positions[(positions.length/2)])/2;
        }
        int fuel = 0;
        for(int i = 0; i < positions.length; i++){
            fuel += Math.abs(positions[i] - median);
        }
        System.out.println(fuel);
    }
}
class Part_2_7{
    public static void main(String[] args) {
        String fileName = "Files/07.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] pos = linescopy.get(0).split(",");
        int[] positions = new int[pos.length];
        for(int i = 0; i < positions.length; i++){
            positions[i] = Integer.parseInt(pos[i]);
        }
        Arrays.sort(positions);
        int avg = (int) Math.floor((double) Arrays.stream(positions).sum() / (double) positions.length);
        int fuel = 0;
        for(int i = 0; i < positions.length; i++){
            for(int j = 1; j <= (Math.abs(positions[i] - avg)); j++){
                fuel += j;
            }
        }
        System.out.println(fuel);

    }
}
