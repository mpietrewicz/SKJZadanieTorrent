import java.util.ArrayList;
import java.util.Dictionary;

public enum Repository {
    INSTANCE;

    public ArrayList<Dictionary<String, FileInfo>> fileList = new ArrayList<>();
}
