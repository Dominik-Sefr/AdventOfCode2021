import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Cave{
    String name;
    List<Cave> nearby;

    public Cave(String _name){
        name = _name;
        nearby = new ArrayList<>();
    }

    void search(String cesta){
        String[] line = cesta.split(",");
        List<String> caves = new ArrayList<String>();
        for(int side = 0; side < line.length; side++){
            if(caves.contains(line[side]) && Character.isLowerCase(line[side].charAt(0))){
                return;
            }
            else{
                caves.add(line[side]);
            }
        }
        if(name.equals("end")){
            String cestaout = cesta + "end";
            Part_1_12.cesty.add(cestaout);
            return;
        }
        for(int cave = 0; cave < nearby.size(); cave++)
        {
            nearby.get(cave).search(cesta + name + ",");
        }
    }
}
class Part_1_12 {
    static List<String> cesty = new ArrayList<>();
    public static void main(String[] args) {
        String fileName = "Files/M.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Cave> caves = new ArrayList<Cave>();

        for(int line = 0; line < linescopy.size(); line++){
            String[] linesplit = linescopy.get(line).split("-");
            for(int side = 0; side < linesplit.length; side++){
                boolean nové = true;
                for(int cav = 0; cav < caves.size(); cav++){
                    if(caves.get(cav).name.equals(linesplit[side])) {
                        nové = false;
                        break;
                    }
                }
                if(nové){
                    caves.add(new Cave(linesplit[side]));
                }
            }
        }

        for(int line = 0; line < linescopy.size(); line++){
            Cave current = null;
            Cave near = null;
            String[] linesplit = linescopy.get(line).split("-");
            for(int cav = 0; cav < caves.size(); cav++){
                if(linesplit[0].equals(caves.get(cav).name)){
                    current = caves.get(cav);
                }
                if(linesplit[1].equals(caves.get(cav).name)){
                    near = caves.get(cav);
                }
            }
            current.nearby.add(near);
            if(!current.name.equals("start") && !near.name.equals("end")){
                near.nearby.add(current);
            }
        }

        for(int cav = 0; cav < caves.size(); cav++){
            if(caves.get(cav).name.equals("start")){
                for(int near = 0; near < caves.get(cav).nearby.size(); near++){
                    caves.get(cav).nearby.get(near).search("start,");
                }
                break;
            }
        }
        System.out.println(cesty.size());
    }
}

class CaveTwice{
    String name;
    List<CaveTwice> nearby;

    public CaveTwice(String _name){
        name = _name;
        nearby = new ArrayList<>();
    }

    void search(String cesta){
        boolean twice = false;
        String[] line = cesta.split(",");
        List<String> caves = new ArrayList<String>();
        for(int side = 0; side < line.length; side++){
            if(caves.contains(line[side]) && Character.isLowerCase(line[side].charAt(0))){
                if(!twice && !line[side].equals("start")){
                    twice = true;
                }
                else{
                    return;
                }
            }
            else{
                caves.add(line[side]);
            }
        }

        if(name.equals("end")){
            String cestaout = cesta + "end";
            Part_2_12.cesty.add(cestaout);
            return;
        }
        for(int cave = 0; cave < nearby.size(); cave++)
        {
            nearby.get(cave).search(cesta + name + ",");
        }
    }
}
class Part_2_12 {
    static List<String> cesty = new ArrayList<>();
    public static void main(String[] args) {
        String fileName = "Files/M.txt";
        List<String> linescopy = new ArrayList<String>();
        try (Stream<String> result = Files.lines(Paths.get(fileName))) {
            linescopy = result.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<CaveTwice> caves = new ArrayList<CaveTwice>();

        for(int line = 0; line < linescopy.size(); line++){
            String[] linesplit = linescopy.get(line).split("-");
            for(int side = 0; side < linesplit.length; side++){
                boolean nové = true;
                for(int cav = 0; cav < caves.size(); cav++){
                    if(caves.get(cav).name.equals(linesplit[side])) {
                        nové = false;
                        break;
                    }
                }
                if(nové){
                    caves.add(new CaveTwice(linesplit[side]));
                }
            }
        }

        for(int line = 0; line < linescopy.size(); line++){
            CaveTwice current = null;
            CaveTwice near = null;
            String[] linesplit = linescopy.get(line).split("-");
            for(int cav = 0; cav < caves.size(); cav++){
                if(linesplit[0].equals(caves.get(cav).name)){
                    current = caves.get(cav);
                }
                if(linesplit[1].equals(caves.get(cav).name)){
                    near = caves.get(cav);
                }
            }
            current.nearby.add(near);
            if(!current.name.equals("start") && !near.name.equals("end")){
                near.nearby.add(current);
            }
        }

        for(int cav = 0; cav < caves.size(); cav++){
            if(caves.get(cav).name.equals("start")){
                for(int near = 0; near < caves.get(cav).nearby.size(); near++){
                    caves.get(cav).nearby.get(near).search("start,");
                }
                break;
            }
        }
        System.out.println(cesty.size());
    }
}
