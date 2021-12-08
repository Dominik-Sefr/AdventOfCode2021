import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_2 {
    public static void main(String[] args) {
        String fileName = "Files/B.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = new String[linescopy.size()];
        int i = 0;
        for(String line : linescopy){
            lines[i] = line;
            i++;
        }

        int pos = 0;
        int depth = 0;
        for(String line : lines){
            if(line.contains("forward")){
                String[] parts = line.split(" ");
                pos += Integer.parseInt(parts[1]);
            }
            else if(line.contains("down")){
                String[] parts = line.split(" ");
                depth += Integer.parseInt(parts[1]);
            }
            else if(line.contains("up")){
                String[] parts = line.split(" ");
                depth -= Integer.parseInt(parts[1]);
            }
        }
        System.out.println(pos*depth);
    }
}
class Part_2_2 {
    public static void main(String[] args) {
        String fileName = "Files/B.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = new String[linescopy.size()];
        int i = 0;
        for(String line : linescopy){
            lines[i] = line;
            i++;
        }

        int aim = 0;
        int depth = 0;
        int pos = 0;
        String[] parts;
        for(String line : lines){
            if(line.contains("forward")){
                parts = line.split(" ");
                pos += Integer.parseInt(parts[1]);
                depth += aim * Integer.parseInt(parts[1]);
            }
            else if(line.contains("down")){
                parts = line.split(" ");
                aim += Integer.parseInt(parts[1]);
            }
            else if(line.contains("up")){
                parts = line.split(" ");
                aim -= Integer.parseInt(parts[1]);
            }
        }
        System.out.println(pos*depth);
    }
}
