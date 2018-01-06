import java.util.ArrayList;

public enum UserRegister {
    INSTANCE;

    ArrayList<String> usetList = new ArrayList<>();

    public void addUser(String id) {
        usetList.add(id);
    }

    public ArrayList<String> getUsers() {
        return usetList;
    }
}
