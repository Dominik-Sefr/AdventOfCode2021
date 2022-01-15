import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_6 {
    public static void main(String[] args) {
        String fileName = "Files/F.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> ryby = new ArrayList<Integer>();
        String[] input = linescopy.get(0).split(",");
        for(int i = 0; i < input.length; i++){
            ryby.add(Integer.parseInt(input[i]));
        }
        for(int day = 1; day <= 82; day++) {
            int count = 0;
            for(int i = 0; i < ryby.size(); i++){
                ryby.set(i, ryby.get(i) - 1);
                if(ryby.get(i) == -1){
                    ryby.set(i, 6);
                    count++;
                }
            }
            for(int birth = 0; birth<count; birth++){
                ryby.add(8);
            }
        }
        System.out.println(ryby.size());
    }
}
class Part_2_6{
    public static void main(String[] args) {
        String fileName = "Files/F.txt";
        List<String> linescopy = new ArrayList<String>();
            try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] input = linescopy.get(0).split(",");
        long[] ryby = new long[9];
        for(int i = 0; i < input.length; i++){
            ryby[Integer.parseInt(input[i])]++;
        }
        for(int day = 1; day <= 256; day++){
            long[] temp = new long[9];
            for(int i = 8; i >= 1; i--){
                temp[i-1] = ryby[i];
            }
            temp[6] += ryby[0];
            temp[8] += ryby[0];
            ryby = temp;
        }
        long count = 0;
        for(int i = 0; i < ryby.length; i++) {
            count += ryby[i];
        }
        System.out.println(count);
    }
}

