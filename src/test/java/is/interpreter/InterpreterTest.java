package is.interpreter;

import is.command.CommandHandler;
import is.interpreter.base.*;
import is.interpreter.operations.*;
import is.interpreter.parsing.Lexer;
import is.interpreter.parsing.Parser;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.model.GroupObject;
import is.shapes.specificcommand.*;
import is.shapes.view.GraphicObjectPanel;
import org.junit.jupiter.api.*;

import java.awt.geom.Point2D;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {

    private GraphicObjectPanel panel;
    private GraphicObjectSingleton singleton;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        panel = new GraphicObjectPanel();
        singleton = GraphicObjectSingleton.getInstance();

        java.util.List<AbstractGraphicObject> allObjects = singleton.getAll();
        for (AbstractGraphicObject obj : allObjects) {
            singleton.remove(obj.getId());
        }

        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private void restoreSystemOutput() {
        System.setOut(originalOut);
    }


    @Nested
    @DisplayName("Parser Tests")
    class ParserTests {

        @Test
        @DisplayName("Parser should create Circle creation expression")
        void testParseCircleCreation() {
            String input = "new circle (10.5) (20, 30)";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof New);

            New newExpression = (New) expression;
            assertEquals(newExpression.getType().getClass(), Circle.class);
            assertEquals(((Circle)newExpression.getType()).getRadius(), new PosFloat(10.5f));
            assertEquals(newExpression.getPosition().getX(), new PosFloat(20.0f));
            assertEquals(newExpression.getPosition().getY(), new PosFloat(30.0f));
        }

        @Test
        @DisplayName("Parser should create Rectangle creation expression")
        void testParseRectangleCreation() {
            String input = "new rectangle (15, 25) (40, 50)";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof New);
        }

        @Test
        @DisplayName("Parser should create Image creation expression")
        void testParseImageCreation() {
            String input = "new img (\"test.jpg\") (60, 70)";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof New);
        }

        @Test
        @DisplayName("Parser should create ListSymbol expression with ALL")
        void testParseListSymbolAll() {
            String input = "ls all";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof ListSymbol);
        }

        @Test
        @DisplayName("Parser should create ListSymbol expression with shape")
        void testParseListSymbolShape() {
            String input = "ls circle";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof ListSymbol);
        }

        @Test
        @DisplayName("Parser should create Group expression")
        void testParseGroup() {
            String input = "grp 1, 2,3";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof Group);
        }

        @Test
        @DisplayName("Parser should create AreaID expression")
        void testParseAreaID() {
            String input = "area 222";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof AreaID);
        }

        @Test
        @DisplayName("Parser should create AreaSymbol expression")
        void testParseAreaSymbol() {
            String input = "area circle";
            Parser parser = new Parser(new StringReader(input));

            ExpressionIF expression = parser.getExpression();
            assertNotNull(expression);
            assertTrue(expression instanceof AreaSymbol);
        }

        @Test
        @DisplayName("Parser should throw SyntaxException for invalid command")
        void testParseInvalidCommand() {
            String input = "invalid command";
            assertThrows(SyntaxException.class, () -> new Parser(new StringReader(input)));
        }

        @Test
        @DisplayName("Parser should throw SyntaxException for missing parameters")
        void testParseMissingParameters() {
            String input = "new circle";
            assertThrows(SyntaxException.class, () -> new Parser(new StringReader(input)));
        }

        @Test
        @DisplayName("Parser should throw SyntaxException for negative values in PosFloat")
        void testParseNegativeValues() {
            String input = "new circle (-10) (20, 30)";
            assertThrows(SyntaxException.class, () -> new Parser(new StringReader(input)));
        }
    }

    @Nested
    @DisplayName("Lexer Tests")
    class LexerTests {

        @Test
        @DisplayName("Lexer should recognize commands")
        void testLexerRecognizesCommands() {
            String input = "new del mv mvoff scale ls all groups grp ungrp area perimeter circle rectangle img";
            Lexer lexer = new Lexer(new StringReader(input));

            assertEquals(Symbols.NEW, lexer.nextToken());
            assertEquals(Symbols.DELETE, lexer.nextToken());
            assertEquals(Symbols.MOVE, lexer.nextToken());
            assertEquals(Symbols.OFFSET, lexer.nextToken());
            assertEquals(Symbols.SCALE, lexer.nextToken());
            assertEquals(Symbols.LIST, lexer.nextToken());
            assertEquals(Symbols.ALL, lexer.nextToken());
            assertEquals(Symbols.GROUPS, lexer.nextToken());
            assertEquals(Symbols.GROUP, lexer.nextToken());
            assertEquals(Symbols.UNGROUP, lexer.nextToken());
            assertEquals(Symbols.AREA, lexer.nextToken());
            assertEquals(Symbols.PERIMETER, lexer.nextToken());
            assertEquals(Symbols.CIRCLE, lexer.nextToken());
            assertEquals(Symbols.RECTANGLE, lexer.nextToken());
            assertEquals(Symbols.IMG, lexer.nextToken());
            assertEquals(Symbols.EOF, lexer.nextToken());
        }

        @Test
        @DisplayName("Lexer should recognize symbols")
        void testLexerRecognizesSymbols() {
            String input = "( ) ,";
            Lexer lexer = new Lexer(new StringReader(input));

            assertEquals(Symbols.OPEN_PARENTHESIS, lexer.nextToken());
            assertEquals(Symbols.CLOSE_PARENTHESIS, lexer.nextToken());
            assertEquals(Symbols.COMMA, lexer.nextToken());
            assertEquals(Symbols.EOF, lexer.nextToken());
        }

        @Test
        @DisplayName("Lexer should recognize numbers")
        void testLexerRecognizesNumbers() {
            String input = "123 45.67";
            Lexer lexer = new Lexer(new StringReader(input));

            assertEquals(Symbols.INTEGER, lexer.nextToken());
            assertEquals("123.0", lexer.getValue());

            assertEquals(Symbols.FLOAT, lexer.nextToken());
            assertEquals("45.67", lexer.getValue());

            assertEquals(Symbols.EOF, lexer.nextToken());
        }

        @Test
        @DisplayName("Lexer should recognize quotes")
        void testLexerRecognizesQuotes() {
            String input = "\"test.jpg\"";
            Lexer lexer = new Lexer(new StringReader(input));

            assertEquals(Symbols.QUOTE, lexer.nextToken());
            assertEquals("test.jpg", lexer.getValue());

            assertEquals(Symbols.EOF, lexer.nextToken());
        }
    }

    @Nested
    @DisplayName("Command Tests")
    class CommandTests {
        @Test
        @DisplayName("Test NewCommand creates and removes objects correctly")
        void testNewCommand() {
            Circle circle = new Circle(new PosFloat(50.0f));
            Pos position = new Pos(new PosFloat(100.0f), new PosFloat(200.0f));

            NewCommand newCmd = new NewCommand(panel, circle, position);

            assertTrue(newCmd.doIt());

            assertEquals(1, singleton.getAll().size());
            AbstractGraphicObject createdObject = singleton.getAll().get(0);
            assertEquals("Circle", createdObject.getType());
            assertEquals(100.0, createdObject.getPosition().getX(), 0.001);
            assertEquals(200.0, createdObject.getPosition().getY(), 0.001);

            assertTrue(newCmd.undoIt());
            assertEquals(0, singleton.getAll().size());
        }

        @Test
        @DisplayName("Test MoveCommand changes object position correctly")
        void testMoveCommand() {
            Circle circle = new Circle(new PosFloat(50.0f));
            Pos initialPos = new Pos(new PosFloat(100.0f), new PosFloat(200.0f));
            NewCommand newCmd = new NewCommand(panel, circle, initialPos);
            newCmd.doIt();

            AbstractGraphicObject createdObject = singleton.getAll().get(0);
            long objectId = createdObject.getId();

            Point2D newPosition = new Point2D.Double(300.0, 400.0);
            MoveCommand moveCmd = new MoveCommand(objectId, newPosition);

            assertTrue(moveCmd.doIt());

            assertEquals(300.0, createdObject.getPosition().getX(), 0.001);
            assertEquals(400.0, createdObject.getPosition().getY(), 0.001);

            assertTrue(moveCmd.undoIt());
            assertEquals(100.0, createdObject.getPosition().getX(), 0.001);
            assertEquals(200.0, createdObject.getPosition().getY(), 0.001);
        }

        @Test
        @DisplayName("Test RemoveCommand deletes and restores objects")
        void testRemoveCommand() {
            Circle circle = new Circle(new PosFloat(50.0f));
            Pos position = new Pos(new PosFloat(100.0f), new PosFloat(200.0f));
            NewCommand newCmd1 = new NewCommand(panel, circle, position);
            newCmd1.doIt();

            Rectangle rectangle = new Rectangle(new Pos(new PosFloat(60.0f), new PosFloat(40.0f)));
            Pos position2 = new Pos(new PosFloat(300.0f), new PosFloat(400.0f));
            NewCommand newCmd2 = new NewCommand(panel, rectangle, position2);
            newCmd2.doIt();

            assertEquals(2, singleton.getAll().size());

            long objectId = singleton.getAll().get(0).getId();
            RemoveCommand removeCmd = new RemoveCommand(panel, objectId);

            assertTrue(removeCmd.doIt());
            assertEquals(1, singleton.getAll().size());

            assertTrue(removeCmd.undoIt());
            assertEquals(2, singleton.getAll().size());
        }
    }
}