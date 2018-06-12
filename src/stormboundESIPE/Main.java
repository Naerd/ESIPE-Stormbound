package stormboundESIPE;

import controller.Controller;
import model.Board;
import view.View;

/*TODO : Ligne de front ; attaque (fin de déplacement)*/

public class Main {

	public static void main(String[] args) {

		// Controller controller = new Controller(new Board(), new View());
		Controller controller = new Controller(new Board(), new View());
		/* TODO : Ajouter un menu */
		controller.game();

	}

}
