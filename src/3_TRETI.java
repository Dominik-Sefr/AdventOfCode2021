import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Part_1_3 {
    public static void main(String[] args){
        String fileName = "Files/C.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[][] cols = new char[linescopy.size()][linescopy.get(0).length()];
        String gamma = "";
        String epsilon = "";
        int onecount;
        int zerocount;
        for(int j = 0; j < linescopy.get(0).length(); j++){
            for(int l = 0; l<linescopy.size(); l++)
            cols[l][j] = linescopy.get(l).charAt(j);
        }
        for(int j = 0; j<cols[0].length; j++){
            onecount = 0;
            zerocount = 0;
            for(int i = 0; i < cols.length; i++){
                if(cols[i][j] == '1'){
                    onecount++;
                }
                else{
                    zerocount++;
                }
            }
            if(onecount > zerocount){
                gamma += '1';
                epsilon += '0';
            }
            else{
                epsilon += '1';
                gamma += '0';
            }
        }
        int gammarate = Integer.parseInt(gamma,2);
        int epsilonrate = Integer.parseInt(epsilon,2);
        System.out.println(gammarate * epsilonrate);
    }
}
class Part_2_3{
    public static void main(String[] args) {
        String fileName = "Files/C.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> oxyout = co(linescopy,0, "oxy");
        for(String line : oxyout) {
            System.out.println(line);
        };
        List<String> coout = co(linescopy,0, "co");
        for(String line : coout) {
            System.out.println(line);
        };

    }
    static List<String> co(List<String> pole1, int col, String radix){
        char yes;
        char not;
        if(radix == "oxy"){
            yes = '1';
            not = '0';
        }
        else{
            yes = '0';
            not = '1';
        }
        List<String> pole = new ArrayList<String>(pole1);
        if(pole.size() > 1) {
            int onecount = 0;
            int zerocount = 0;
            for (String s : pole) {
                if (s.charAt(col) == '1') {
                    onecount++;
                } else {
                    zerocount++;
                }
            }
            for (int row = 0; row < pole.size(); row++) {
                if (zerocount <= onecount) {
                    if (pole.get(row).charAt(col) == not) {
                        pole.set(row, null);
                    }
                }
                else {
                    if (pole.get(row).charAt(col) == yes) {
                        pole.set(row, null);
                    }
                }
            }
            while (pole.remove(null)) {
            }
            return co(pole, col+1, radix);
        }
        else {
            return pole;
        }
    }
}

