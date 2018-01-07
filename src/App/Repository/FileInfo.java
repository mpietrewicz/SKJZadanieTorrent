package App.Repository;

public class FileInfo {
    String name;
    String checksum;

    public FileInfo(String name) {
        this.name = name;
    }

    public FileInfo(String name, String checksum) {
        this.name = name;
        this.checksum = checksum;
    }

    public String getName() {
        return name;
    }

    public String getChecksum() {
        return checksum;
    }
}
