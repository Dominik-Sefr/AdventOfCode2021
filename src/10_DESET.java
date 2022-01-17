import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_10 {
    public static void main(String[] args) {
        String fileName = "Files/I.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Character> corruptedchars = new ArrayList<Character>();
        for(int row = 0; row < linescopy.size(); row++){
            List<Character> expected = new ArrayList<Character>();
            for(int character = 0; character < linescopy.get(row).length(); character++){
                if(linescopy.get(row).charAt(character) == '(' || linescopy.get(row).charAt(character) == '[' || linescopy.get(row).charAt(character) == '{' || linescopy.get(row).charAt(character) == '<'){
                    expected.add(linescopy.get(row).charAt(character));
                }
                else{
                    if(expected.get(expected.size() - 1) == '('){
                        if(linescopy.get(row).charAt(character) == ')'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            corruptedchars.add(linescopy.get(row).charAt(character));
                            break;
                        }
                    }
                    else if(expected.get(expected.size() - 1) == '['){
                        if(linescopy.get(row).charAt(character) == ']'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            corruptedchars.add(linescopy.get(row).charAt(character));
                            break;
                        }
                    }
                    else if(expected.get(expected.size() - 1) == '{'){
                        if(linescopy.get(row).charAt(character) == '}'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            corruptedchars.add(linescopy.get(row).charAt(character));
                            break;
                        }
                    }
                    else if(expected.get(expected.size() - 1) == '<'){
                        if(linescopy.get(row).charAt(character) == '>'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            corruptedchars.add(linescopy.get(row).charAt(character));
                            break;
                        }
                    }
                }
            }
        }
        int result = 0;
        for(int i = 0; i < corruptedchars.size(); i++){
            if(corruptedchars.get(i) == ')'){
                result += 3;
            }
            else if(corruptedchars.get(i) == ']'){
                result += 57;
            }
            else if(corruptedchars.get(i) == '}'){
                result += 1197;
            }else if(corruptedchars.get(i) == '>'){
                result += 25137;
            }
        }
        System.out.println(result);
    }
}
class Part_2_10 {
    public static void main(String[] args) {
        String fileName = "Files/I.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Long> missing = new ArrayList<Long>();
        List<Integer> remove = new ArrayList<Integer>();
        for(int row = 0; row < linescopy.size(); row++){
            List<Character> expected = new ArrayList<Character>();
            for(int character = 0; character < linescopy.get(row).length(); character++){
                if(linescopy.get(row).charAt(character) == '(' || linescopy.get(row).charAt(character) == '[' || linescopy.get(row).charAt(character) == '{' || linescopy.get(row).charAt(character) == '<'){
                    expected.add(linescopy.get(row).charAt(character));
                }
                else{
                    if(expected.get(expected.size() - 1) == '('){
                        if(linescopy.get(row).charAt(character) == ')'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            remove.add(row);
                            break;
                        }
                    }
                    else if(expected.get(expected.size() - 1) == '['){
                        if(linescopy.get(row).charAt(character) == ']'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            remove.add(row);
                            break;
                        }
                    }
                    else if(expected.get(expected.size() - 1) == '{'){
                        if(linescopy.get(row).charAt(character) == '}'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            remove.add(row);
                            break;
                        }
                    }
                    else if(expected.get(expected.size() - 1) == '<'){
                        if(linescopy.get(row).charAt(character) == '>'){
                            expected.remove(expected.size() - 1);
                        }
                        else{
                            remove.add(row);
                            break;
                        }
                    }
                }
            }
        }
        List<String> lines = new ArrayList<String>();
        for(int i = 0; i < linescopy.size(); i++){
            boolean add = true;
            for(int j = 0; j < remove.size(); j++) {
                if(i == remove.get(j)){
                    add = false;
                }
            }
            if(add){
                lines.add(linescopy.get(i));
            }
        }
        for(int row = 0; row < lines.size(); row++){
            List<Character> missingchars = new ArrayList<Character>();
            System.out.println(missingchars.size());
            System.out.println(lines.get(row));
            for(int character = 0; character < lines.get(row).length(); character++){
                if(lines.get(row).charAt(character) == '(' || lines.get(row).charAt(character) == '[' || lines.get(row).charAt(character) == '{' || lines.get(row).charAt(character) == '<'){
                    missingchars.add(lines.get(row).charAt(character));
                }
                else{
                    if(missingchars.get(missingchars.size() - 1) == '('){
                        if(lines.get(row).charAt(character) == ')'){
                            missingchars.remove(missingchars.size() - 1);
                        }
                    }
                    else if(missingchars.get(missingchars.size() - 1) == '['){
                        if(lines.get(row).charAt(character) == ']'){
                            missingchars.remove(missingchars.size() - 1);
                        }
                    }
                    else if(missingchars.get(missingchars.size() - 1) == '{'){
                        if(lines.get(row).charAt(character) == '}'){
                            missingchars.remove(missingchars.size() - 1);
                        }
                    }
                    else if(missingchars.get(missingchars.size() - 1) == '<'){
                        if(lines.get(row).charAt(character) == '>'){
                            missingchars.remove(missingchars.size() - 1);
                        }
                    }
                }
            }
            System.out.println(missingchars);
            Collections.reverse(missingchars);
            long result = 0;
            for(int i = 0; i < missingchars.size(); i++){
                result *= 5;
                if(missingchars.get(i) == '('){
                    result += 1;
                }
                else if(missingchars.get(i) == '['){
                    result += 2;
                }
                else if(missingchars.get(i) == '{'){
                    result += 3;
                }
                else if(missingchars.get(i) == '<'){
                    result += 4;
                }
            }
            missing.add(result);
        }
        List<Long> sorted = missing.stream().sorted().collect(Collectors.toList());
        Long vysledek = sorted.get(sorted.size()/2);
        System.out.println(sorted);
        System.out.println(vysledek);
    }
}