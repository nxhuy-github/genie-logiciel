package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

public class AutoCommand implements Command{

	@Override
	public void execute(Character c) {
		c.autoMoveTurnShoot();
	}

}
