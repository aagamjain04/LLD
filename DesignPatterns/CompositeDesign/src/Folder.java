import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystem{

    String name;
    List<FileSystem> children = new ArrayList<>();

    public Folder(String name){
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Folder :" + name);
        for(FileSystem fs : children){
            fs.showDetails();
        }
    }

    void add(FileSystem file){
        children.add(file);
    }

    void remove(FileSystem file){
        children.remove(file);
    }


}
