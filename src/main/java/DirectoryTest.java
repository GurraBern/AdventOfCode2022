import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.util.List;
import java.util.*;

public class DirectoryTest {

    private String directoryName;
    private DirectoryTest parentDirectory;
    private List<DirectoryTest> childDirectories = new ArrayList<>();
    private List<Dictionary<String, Integer>> files = new ArrayList<>();

    public DirectoryTest(String directoryName, DirectoryTest parentDirectory){
        this.directoryName = directoryName;
        this.parentDirectory = parentDirectory;
    }

    public void addFile(Dictionary<String, Integer> file){
        files.add(file);
    }

    public void addDirectory(DirectoryTest directory){
        this.childDirectories.add(directory);
    }
}

