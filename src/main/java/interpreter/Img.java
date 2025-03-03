package interpreter;

import java.nio.file.Path;

public class Img extends Shape {

    Path path;

    public Img(Path path) {
        this.path = path;
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Image at %s", path.toString());
    }
}
