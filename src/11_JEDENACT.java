import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Octopus{
    int energy;
    List<Octopus> nearby;
    public Octopus(int _en){
        energy = _en;
        nearby = new ArrayList<>();
    }

    public void increment(){
        if (energy == 10){
            return;
        }
            energy++;
        if(energy == 10){
            for(Octopus oc : nearby){
                oc.increment();
            }
        }
    }
}

class Part_1_11{
    public static void main(String[] args) {
        String fileName = "Files/J.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Octopus[][] ocs = new Octopus[10][10];
        for(int row = 0; row < ocs.length; row++){
            for(int col = 0; col < ocs.length; col++){
                ocs[row][col] = new Octopus(Integer.parseInt(String.valueOf(linescopy.get(row).charAt(col))));
            }
        }
        List<Octopus> octopuses = new ArrayList<>();

        for(int row = 0; row < ocs.length; row++){
            for(int col = 0; col < ocs.length; col++){
                Octopus oc = ocs[row][col];

                if(row != 0){
                    oc.nearby.add(ocs[row-1][col]);
                }
                if(col != 0){
                    oc.nearby.add(ocs[row][col-1]);
                }
                if(row != ocs.length - 1){
                    oc.nearby.add(ocs[row+1][col]);
                }
                if(col != ocs.length - 1){
                    oc.nearby.add(ocs[row][col+1]);
                }
                if(row != 0 && col != 0){
                    oc.nearby.add(ocs[row-1][col-1]);
                }
                if(row != ocs.length - 1 && col != ocs.length - 1){
                    oc.nearby.add(ocs[row+1][col+1]);
                }
                if(row != 0 && col != ocs.length - 1){
                    oc.nearby.add(ocs[row-1][col+1]);
                }
                if(row != ocs.length - 1 && col != 0){
                    oc.nearby.add(ocs[row+1][col-1]);
                }
                octopuses.add(oc);
            }
        }
        int flashes = 0;
        for(int step = 1; step <= 100; step++){
            for(Octopus oc : octopuses){
                oc.increment();
            }
            for(Octopus oc : octopuses){
                if(oc.energy == 10){
                    oc.energy = 0;
                    flashes++;
                }
            }
        }
        System.out.println(flashes);
    }
}
class Part_2_11{
    public static void main(String[] args) {
        String fileName = "Files/J.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Octopus[][] ocs = new Octopus[10][10];
        for(int row = 0; row < ocs.length; row++){
            for(int col = 0; col < ocs.length; col++){
                ocs[row][col] = new Octopus(Integer.parseInt(String.valueOf(linescopy.get(row).charAt(col))));
            }
        }
        List<Octopus> octopuses = new ArrayList<>();

        for(int row = 0; row < ocs.length; row++){
            for(int col = 0; col < ocs.length; col++){
                Octopus oc = ocs[row][col];

                if(row != 0){
                    oc.nearby.add(ocs[row-1][col]);
                }
                if(col != 0){
                    oc.nearby.add(ocs[row][col-1]);
                }
                if(row != ocs.length - 1){
                    oc.nearby.add(ocs[row+1][col]);
                }
                if(col != ocs.length - 1){
                    oc.nearby.add(ocs[row][col+1]);
                }
                if(row != 0 && col != 0){
                    oc.nearby.add(ocs[row-1][col-1]);
                }
                if(row != ocs.length - 1 && col != ocs.length - 1){
                    oc.nearby.add(ocs[row+1][col+1]);
                }
                if(row != 0 && col != ocs.length - 1){
                    oc.nearby.add(ocs[row-1][col+1]);
                }
                if(row != ocs.length - 1 && col != 0){
                    oc.nearby.add(ocs[row+1][col-1]);
                }
                octopuses.add(oc);
            }
        }
        int result = 0;
        for(int step = 1; step <= 1001; step++){
            int flashes = 0;
            for(Octopus oc : octopuses){
                oc.increment();
            }
            for(Octopus oc : octopuses){
                if(oc.energy == 10){
                    oc.energy = 0;
                    flashes++;
                }
            }
            if(flashes == 100){
                result = step;
                break;
            }
        }
        System.out.println(result);
    }
}