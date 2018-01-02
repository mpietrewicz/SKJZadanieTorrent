import java.util.Arrays;
import java.util.List;

public class Command {
    private String content;
    private String operation;
    private List<String> args;

    public Command(String content) {
        this.content = content;
        List<String> sections = Arrays.asList(content.split(" "));
        this.operation = sections.get(0);
        if (sections.size() > 1) {
            this.args = sections.subList(1, sections.size());
        } else {
            this.args = null;
        }
    }

    public String getContent() {
        return content;
    }

    public String getOperation() {
        return operation;
    }

}
