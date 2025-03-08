package is;

import is.command.CommandHandler;
import is.command.HistoryCommandHandler;
import is.interpreter.ExpressionIF;
import is.interpreter.parsing.Parser;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestGraphics {

	public static void main(String[] args) {

		JFrame f = new JFrame();

		JToolBar toolbar = new JToolBar();
		JButton undoButt = new JButton("Undo");
		JButton redoButt = new JButton("Redo");

		final HistoryCommandHandler handler = new HistoryCommandHandler();

		undoButt.addActionListener(evt -> handler.undo());

		redoButt.addActionListener(evt -> handler.redo());
		toolbar.add(undoButt);
		toolbar.add(redoButt);

		GraphicObjectLogger logger = new GraphicObjectLogger();

		GraphicObjectPanel gpanel = GraphicObjectSingleton.getInstance().getPanel();

		gpanel.setPreferredSize(new Dimension(400, 400));
		GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());

		f.add(toolbar, BorderLayout.NORTH);
		f.add(gpanel, BorderLayout.CENTER);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

		List<String> initialCommands = new ArrayList<>();
		initialCommands.add("new circle (5.0) (100, 100)");
		initialCommands.add("new rectangle (25.0, 100) (200, 100)");
		initialCommands.add("new circle (14) (300, 200)");
		initialCommands.add("new img (\"goku.jpg\") (100, 300)");
		for (String command : initialCommands) {
			launch(command, gpanel, handler);
		}

		Scanner scanner = new Scanner(System.in);
		String input = null;
		while (true) {
			System.out.print("$ ");
			input = scanner.nextLine();
			if (input.equals("exit")) { break; }
			launch(input, gpanel, handler);
		}

		System.out.println("Exiting program...");
		f.dispose();
	}

	private static void launch(String input, GraphicObjectPanel gpanel, CommandHandler commandHandler) {
		try {
			Parser parser = new Parser(new StringReader(input));
			ExpressionIF expression = parser.getExpression();
			if (expression != null) expression.interpret(null, commandHandler);
		} catch (Exception e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}
}