import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_8 {
    public static void main(String[] args) {
        String fileName = "Files/08.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        for(int i = 0; i < linescopy.size(); i++){
            String[] lin  = linescopy.get(i).split("\\|");
            String[] line = lin[1].split(" ");
            for(int prvek = 0; prvek < line.length; prvek++){
                if(line[prvek].length() == 2 || line[prvek].length() == 3 || line[prvek].length() == 4 || line[prvek].length() == 7){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
class Part_2_8{
    public static void main(String[] args) {
        String fileName = "Files/08.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int result = 0;
        for(int i = 0; i < linescopy.size(); i++){
            String[] line = linescopy.get(i).split("\\|");
            String[] pattern = line[0].split(" ");
            String[] outputpattern = line[1].split(" ");
            char[] segments = new char[7];
            int[] count = new int[7];
            String[] numbers = new String[10];
            for(int num = 0; num < pattern.length; num++){
                char[] chars = pattern[num].toCharArray();
                Arrays.sort(chars);
                pattern[num] = new String(chars);
                if(pattern[num].length() == 2){
                    numbers[1] = pattern[num];
                }
                if(pattern[num].length() == 3){
                    numbers[7] = pattern[num];
                }
                if(pattern[num].length() == 4){
                    numbers[4] = pattern[num];
                }
                if(pattern[num].length() == 7){
                    numbers[8] = pattern[num];
                }
                for(int character = 0; character < pattern[num].length(); character++){
                    if(pattern[num].charAt(character) == 'a'){
                        count[0]++;
                    }if(pattern[num].charAt(character) == 'b'){
                        count[1]++;
                    }if(pattern[num].charAt(character) == 'c'){
                        count[2]++;
                    }if(pattern[num].charAt(character) == 'd'){
                        count[3]++;
                    }if(pattern[num].charAt(character) == 'e'){
                        count[4]++;
                    }if(pattern[num].charAt(character) == 'f'){
                        count[5]++;
                    }
                    if(pattern[num].charAt(character) == 'g'){
                        count[6]++;
                    }
                }
            }
            int maxValueIndex = 0;
            for (int ind = 0; ind < count.length; ind++) {
                if (count[ind] > count[maxValueIndex]) {
                    maxValueIndex = ind;
                }
            }
            if(maxValueIndex == 0){
                segments[2] = 'a';
            }
            if(maxValueIndex == 1){
                segments[2] = 'b';
            }
            if(maxValueIndex == 2){
                segments[2] = 'c';
            }
            if(maxValueIndex == 3){
                segments[2] = 'd';
            }
            if(maxValueIndex == 4){
                segments[2] = 'e';
            }
            if(maxValueIndex == 5){
                segments[2] = 'f';
            }
            if(maxValueIndex == 6){
                segments[2] = 'g';
            }
            segments[0] = substract(numbers[7], numbers[1]).charAt(0);
            for(int num = 0; num < pattern.length; num++){
                if(pattern[num].length() == 5) {
                    if (Objects.equals(pattern[num], substract(pattern[num], String.valueOf(segments[2])))) {
                        numbers[2] = pattern[num];
                    }
                }
            }

            segments[1] = substract(numbers[1], String.valueOf(segments[2])).charAt(0);
            for(int num = 0; num < pattern.length; num++){
                if(pattern[num].length() == 6){
                    if(Objects.equals(pattern[num], substract(pattern[num], String.valueOf(segments[1])))){
                        numbers[6] = pattern[num];
                    }
                }
                if(pattern[num].length() == 5){
                    if(!pattern[num].equals(numbers[2])) {
                        if (String.valueOf(segments[2]).equals(substract(pattern[num], numbers[2]))) {
                            numbers[3] = pattern[num];
                        } else {
                            numbers[5] = pattern[num];
                        }
                    }
                }
            }
            for(int num = 0; num < pattern.length; num++) {
                if (pattern[num].length() == 6 && !Objects.equals(pattern[num], numbers[6])) {
                    if(Objects.equals(String.valueOf(segments[1]), substract(pattern[num], numbers[5]))){
                        numbers[9] = pattern[num];
                    }
                }
            }
            for(int num = 0; num < pattern.length; num++) {
                if (pattern[num].length() == 6 && !Objects.equals(pattern[num], numbers[6]) && !Objects.equals(pattern[num], numbers[9])) {
                    numbers[0] = pattern[num];
                }
            }
            for(int num = 0; num < outputpattern.length; num++ ){
                char[] chars = outputpattern[num].toCharArray();
                Arrays.sort(chars);
                outputpattern[num] = new String(chars);
            }
            String lineoutput = "";
            for(int num = 0; num < outputpattern.length; num++){
                for(int number = 0; number < numbers.length; number++){
                    if(Objects.equals(outputpattern[num], numbers[number])){
                        lineoutput += number;
                        break;
                    }
                }
            }
            result += Integer.parseInt(lineoutput);
        }
        System.out.println(result);
    }
    static String substract(String string1, String string2){
        char[] shorter = new char[string2.length()];
        for (int i = 0; i < string2.length(); i++) {
            shorter[i] = string2.charAt(i);
        }
        for(int i = 0; i < shorter.length; i++){
            string1 = string1.replace(String.valueOf(shorter[i]), "");
        }
        return string1;
    }
}
