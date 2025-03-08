package is.interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

import java.nio.file.Path;

public class Img extends Shape {

    Path path;

    public Img(Path path) {
        this.path = path;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {

    }

    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return String.format("Image at %s", path.toString());
    }
}
