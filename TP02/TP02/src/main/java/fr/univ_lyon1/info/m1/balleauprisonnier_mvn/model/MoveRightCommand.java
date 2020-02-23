package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;
public class MoveRightCommand implements Command{

	@Override
	public void execute(Character c) {
		c.moveRight();
	}

}
