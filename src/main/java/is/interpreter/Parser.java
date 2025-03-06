package is.interpreter;

import is.shapes.model.CircleObject;

import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static is.interpreter.Utils.*;

public class Parser {

    Lexer lexer;
    ExpressionIF root;

    public Parser(Reader reader) {
        lexer = new Lexer(reader);
        root = constructExpression();
    }

    public ExpressionIF getExpression() {
        return root;
    }

    private ExpressionIF constructExpression() {
        ExpressionIF expression;
        Symbols currentToken;
        switch ((currentToken = lexer.nextToken())) {
            case NEW: expression = createCommand(); break;
            case DELETE: expression = deleteCommand(); break;
            case MOVE: expression = moveCommand(); break;
            case OFFSET: expression = offsetCommand(); break;
            case SCALE: expression = scaleCommand(); break;
            case LIST: expression = listCommand(); break;
            case GROUP: expression = groupCommand(); break;
            case UNGROUP: expression = ungroupCommand(); break;
            case AREA: expression = areaCommand(); break;
            case PERIMETER: expression = perimeterCommand(); break;
            default: throw new SyntaxException("This is not a recognized token " + currentToken);
        }
        return expression;
    }

    private ExpressionIF createCommand() {
        Shape typeConstructor = typeConstructor();
        Pos pos = pos();
        New createCommand = new New(typeConstructor, pos);
        return createCommand;
    }

    private ExpressionIF deleteCommand() {
        Symbols symbol = lexer.nextToken();
        if (!symbol.equals(Symbols.INTEGER)) throw new SyntaxException("You need to specify an ID to delete 'del ID'");
        return new Delete((long) Double.parseDouble(lexer.getValue()));
    }

    private ExpressionIF moveCommand() {
        if (!lexer.nextToken().equals(Symbols.INTEGER)) throw new SyntaxException("You need to specify an ID and a new position to move 'mv INT (FLOAT, FLOAT)'");
        long id = (long) Double.parseDouble(lexer.getValue());
        Pos pos = pos();
        return new Move(id, pos);
    }

    private ExpressionIF offsetCommand() {
        if (!lexer.nextToken().equals(Symbols.INTEGER)) throw new SyntaxException("You need to specify an ID and a new position to move 'mv INT (FLOAT, FLOAT)'");
        long id = (long) Double.parseDouble(lexer.getValue());
        Pos pos = pos();
        return new MoveOffset(id, pos);
    }

    private ExpressionIF scaleCommand() {
        SyntaxException exception = new SyntaxException("You need to specify an ID and a scale factor like this 'ID FLOAT'");
        long id;
        double scale;
        if (!lexer.nextToken().equals(Symbols.INTEGER)) throw exception;
        else id = (long) Double.parseDouble(lexer.getValue());
        if (!canBeFloat(lexer.nextToken())) throw exception;
        else scale = Double.parseDouble(lexer.getValue());
        return new Scale(id, scale);
    }

    private ExpressionIF listCommand() {
        List<Symbols> shapes = new ArrayList<>();
        shapes.add(Symbols.CIRCLE);
        shapes.add(Symbols.RECTANGLE);
        shapes.add(Symbols.IMG);

        Symbols argument = lexer.nextToken();
        SyntaxException exception = new SyntaxException("You need to specify something to list 'ID' | 'SHAPE' | 'all' | 'groups'");
        switch (argument) {
            case INTEGER: {
                String argumentValue = lexer.getValue();
                long id = Integer.parseInt(argumentValue);
                return new ListID(id);
            }
            case ALL: case GROUPS: {
                return new ListSymbol(argument);
            }
            default: {
                if (shapes.contains(argument)) {
                    return new ListSymbol(argument);
                } else {
                    throw exception;
                }
            }
        }

    }

    private ExpressionIF groupCommand() {
        SyntaxException exception = new SyntaxException("You need to specify a list to group 'ID, ID, ..., ID' with at least one");
        List<Long> ids = new ArrayList<>();
        if (!lexer.nextToken().equals(Symbols.INTEGER)) throw exception;
        else ids.add((long) Double.parseDouble(lexer.getValue()));
        while (true) {
            if (!(
                    lexer.nextToken().equals(Symbols.COMMA) &&
                    lexer.nextToken().equals(Symbols.INTEGER)
            )) break;
            else ids.add((long) Double.parseDouble(lexer.getValue()));
        }
        if (lexer.currentSymbol.equals(Symbols.COMMA)) throw exception;
        return new Group(ids);
    }

    private ExpressionIF ungroupCommand() {
        if (!lexer.nextToken().equals(Symbols.INTEGER)) throw new SyntaxException("You need to specify an ID to ungroup 'ID'");
        long id = (long) Double.parseDouble(lexer.getValue());
        return new Ungroup(id);
    }

    private ExpressionIF areaCommand() {
        List<Symbols> shapes = new ArrayList<>();
        shapes.add(Symbols.CIRCLE);
        shapes.add(Symbols.RECTANGLE);
        shapes.add(Symbols.IMG);

        Symbols argument = lexer.nextToken();
        SyntaxException exception = new SyntaxException("You need to specify something to list 'ID' | 'SHAPE' | 'all'");
        switch (argument) {
            case INTEGER: {
                String argumentValue = lexer.getValue();
                long id = (long) Double.parseDouble(argumentValue);;
                return new AreaID(id);
            }
            case ALL: {
                return new AreaSymbol(argument);
            }
            default: {
                if (shapes.contains(argument)) {
                    return new AreaSymbol(argument);
                } else {
                    throw exception;
                }
            }
        }
    }

    private ExpressionIF perimeterCommand() {
        List<Symbols> shapes = new ArrayList<>();
        shapes.add(Symbols.CIRCLE);
        shapes.add(Symbols.RECTANGLE);
        shapes.add(Symbols.IMG);

        Symbols argument = lexer.nextToken();
        SyntaxException exception = new SyntaxException("You need to specify something to list 'ID' | 'SHAPE' | 'all'");
        switch (argument) {
            case INTEGER: {
                String argumentValue = lexer.getValue();
                long id = (long) Double.parseDouble(argumentValue);;
                return new PerimeterID(id);
            }
            case ALL: {
                return new PerimeterSymbol(argument);
            }
            default: {
                if (shapes.contains(argument)) {
                    return new PerimeterSymbol(argument);
                } else {
                    throw exception;
                }
            }
        }
    }

    private Shape typeConstructor() {
        Symbols symbol = lexer.nextToken();
        Shape shape;
        switch (symbol) {
            case CIRCLE -> shape = circle();
            case RECTANGLE -> shape = rectangle();
            case IMG -> shape = img();
            default -> throw new SyntaxException("You must provide a valid object");
        }
        return shape;
    }

    private Shape circle() {
        SyntaxException exception = new SyntaxException("You must provide a float to instantiate a circle like this (FLOAT)");
        String value;
        if (!lexer.nextToken().equals(Symbols.OPEN_PARENTHESIS)) throw exception;
        if (!canBeFloat(lexer.nextToken())) throw exception;
        else value = lexer.getValue();
        if (!lexer.nextToken().equals(Symbols.CLOSE_PARENTHESIS)) throw exception;
        float number = Float.parseFloat(value);
        return new Circle(new PosFloat(number));
    }

    private Shape rectangle() {
        Pos pos = pos();
        return new Rectangle(pos);
    }

    private Shape img() {
        String filepath = null;
        SyntaxException exception = new SyntaxException("You must provide image path between parentheses (PATH)");

        if (!lexer.nextToken().equals(Symbols.OPEN_PARENTHESIS)) throw exception;
        if (!lexer.nextToken().equals(Symbols.QUOTE)) throw exception;
            else filepath = lexer.getValue();
        if (!lexer.nextToken().equals(Symbols.CLOSE_PARENTHESIS)) throw exception;
        Path path = Paths.get(filepath);
        return new Img(path);
    }

    private Pos pos() {
        List<Float> numbers = new ArrayList<>();
        SyntaxException exception = new SyntaxException("You must provide a pos like this (FLOAT, FLOAT)");
        if (!lexer.nextToken().equals(Symbols.OPEN_PARENTHESIS)) throw exception;
        if (!canBeFloat(lexer.nextToken())) throw exception;
        else numbers.add(Float.parseFloat(lexer.getValue()));
        if (!lexer.nextToken().equals(Symbols.COMMA)) throw exception;
        if (!canBeFloat(lexer.nextToken())) throw exception;
        else numbers.add(Float.parseFloat(lexer.getValue()));
        if (!lexer.nextToken().equals(Symbols.CLOSE_PARENTHESIS)) throw exception;

        return new Pos(new PosFloat(numbers.get(0)), new PosFloat(numbers.get(1)));
    }

}
