package stormboundESIPE;

import controller.Controller;
import model.Board;
import view.View;

public class Main {

	public static void main(String[] args) {

		Controller controller = new Controller(new Board(), new View());

		controller.game();

	}

}
