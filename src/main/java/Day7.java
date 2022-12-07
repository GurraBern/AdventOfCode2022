import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay7.txt");
        Scanner myReader = new Scanner(myObj);

        Stack<String> dir = new Stack<>();
        while (myReader.hasNext()){
            var data = myReader.nextLine();
            //var t = data.split();
        }

        var b = 5;
        /*
        - / (dir)
          - a (dir)
            - e (dir)
              - i (file, size=584)
            - f (file, size=29116)
            - g (file, size=2557)
            - h.lst (file, size=62596)
          - b.txt (file, size=14848514)
          - c.dat (file, size=8504156)
          - d (dir)
            - j (file, size=4060174)
            - d.log (file, size=8033020)
            - d.ext (file, size=5626152)
            - k (file, size=7214296)
        */

    }

    private static void exeCommand(String input){
        if(input.equals("cd x")){

        } else if(input.equals("cd ..")){

        } else if(input.equals("cd /")){

        } else if(input.equals("ls")){

        }
    }

}