import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.util.List;
import java.util.*;

public class DirectoryTest {

    public String directoryName;
    private DirectoryTest parentDirectory;
    public List<DirectoryTest> childDirectories = new ArrayList<>();
    public List<Pair<String, Integer>> files = new ArrayList<>();

    public DirectoryTest(String directoryName, DirectoryTest parentDirectory){
        this.directoryName = directoryName;
        this.parentDirectory = parentDirectory;
    }

    public DirectoryTest getParentDirectory() {
        return parentDirectory;
    }

    public int getSize(){
        int size = 0;
        for(var file : files){
            size += Integer.parseInt(String.valueOf(file.value));
        }

        for (var child : childDirectories){
            var c = child.getSize();
            size += c;
        }

        return size;
    }

    public void addFile(Pair<String, Integer> file){
        files.add(file);
    }

    public void addDirectory(DirectoryTest directory){
        this.childDirectories.add(directory);
    }
}

