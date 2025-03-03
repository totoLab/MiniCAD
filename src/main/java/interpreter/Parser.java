package interpreter;

import java.awt.*;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    Lexer lexer;
    ExpressionIF root;

    public Parser(Reader reader) {
        lexer = new Lexer(reader);
        root = constructExpression();
    }

    private ExpressionIF constructExpression() {
        ExpressionIF expression;
        Symbols currentToken;
        switch ((currentToken = lexer.nextToken())) {
            case NEW: expression = createCommand(); break;
            case DELETE: expression = deleteCommand(); break;
            case MOVE: expression = moveCommand(); break;
            case OFFSET: expression = offsetCommand(); break;
            default: throw new SyntaxException("This is not a recognized token " + currentToken);
        }
        return expression;
    }

    private ExpressionIF createCommand() {
        ExpressionIF typeConstructor = typeConstructor();
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

    private ExpressionIF typeConstructor() {
        Symbols symbol = lexer.nextToken();
        ExpressionIF expression;
        switch (symbol) {
            case CIRCLE -> expression = circle();
            case RECTANGLE -> expression = rectangle();
            case IMG -> expression = img();
            default -> throw new SyntaxException("You must provide a valid object");
        }
        return expression;
    }

    boolean canBeFloat(Symbols symbol) {
        return symbol.equals(Symbols.FLOAT) || symbol.equals(Symbols.INTEGER);
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
