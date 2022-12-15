import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.util.*;

public class Day7 {

    private static DirectoryTest current;
    private static List<DirectoryTest> directories = new ArrayList<>();

    private static DirectoryTest root = new DirectoryTest("/", null);

    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay7.txt");
        Scanner myReader = new Scanner(myObj);


        directories.add(root);
        current = root;

        Stack<String> cmds = new Stack<>();
        while (myReader.hasNext()){
            var data = myReader.nextLine();
            exeCommand(data);
        }
        Collections.reverse(cmds);
        var t = findDirectories(100000);

        var totalSize = 0;
        for (var s : t){
            totalSize += s.getSize();
        }

        var b = 5;
    }

    private static void exeCommand(String input){
        var splitCmd = input.split(" ");
        if(splitCmd[0].equals("dir")){
            DirectoryTest newDir = new DirectoryTest(splitCmd[1], current);
            directories.add(newDir);
            current.addDirectory(newDir);
            //current = newDir;
        } else if(splitCmd[1].equals("cd")){
            current = findDirectory(splitCmd[2]);
        } else if(splitCmd[0].matches(".*\\d.*")){
            var fileSize = splitCmd[0];
            var fileName = splitCmd[1];

            current.addFile(new Pair(fileName, fileSize));
        }
    }

    private static List<DirectoryTest> findDirectories(int size){
        List<String> testNames = new ArrayList<>();
        int sizeD = 0;

        List<DirectoryTest> ddd = new ArrayList<>();

        for (var d : directories){
            var vv = d.directoryName;
            var t = d.getSize();

            if(t <= size){
                ddd.add(d);
            }

            var b=0;
        }

        var t = 0;
        return ddd;
    }
    // 95437 should be
    //756536 too low
    //228803543 too high


    //hmmmm
    //1496359
    private static DirectoryTest findDirectory(String directory){
        for(var dir : directories){

            if (dir.directoryName.equals(directory)){
                return dir;
            }
        }
        return null;
    }

    private static Integer getSizeOfDirectory(List<Pair<String,Integer>> files){
        int size = 0;
        for(var file: files){

            size += Integer.parseInt(String.valueOf(file.value));
        }
        return size;
    }
}