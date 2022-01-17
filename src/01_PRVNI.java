import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_1 {
    static void main(String[] args) {
        String fileName = "Files/01.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] lines = new int[linescopy.size()];
        int i = 0;
        for(String line : linescopy){
            lines[i] = Integer.parseInt(line);
            i++;
        }
        int counter = 0;
        for(int j = 0; j<lines.length-1; j++){
            if(lines[j] < lines[j+1]){
                counter++;
            }
        }
        System.out.println(counter);
    }
}
class Part_2_1 {
    public static void main(String[] args) {
        String fileName = "Files/01.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] lines = new int[linescopy.size()];
        int i = 0;
        for(String line : linescopy){
            lines[i] = Integer.parseInt(line);
            i++;
        }
        int prev;
        int next;
        int counter = 0;
        for(int j = 0; j < lines.length-3; j++){
            prev = lines[j] + lines[j+1] + lines[j + 2];
            next = lines[j+1] + lines[j + 2] + lines[j + 3];
            if(prev < next){
                counter++;
            }
        }
        System.out.println(counter);
    }
}