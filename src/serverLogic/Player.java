package serverLogic;

import java.awt.geom.Point2D;
import java.util.Optional;
import java.util.function.Function;

import globalClasses.Action;
import globalClasses.Pos;
import globalClasses.StatContainer;

public class Player {

	private double x;
	private double y;
	private double angle;
	private String id;
	private StatContainer stats;
	private Function<StatContainer, Double> speedFunction;

	private Interactable interactingWith = null;


	public Player(String id, Function<StatContainer, Double> speedFunction) {
		super();
		this.x = 0; // TODO set starting positions
		this.y = 0;
		this.id = id;
		this.stats = new StatContainer();
		this.speedFunction = speedFunction;
	}

	public void updatePosition(Pos newPosition) {
		x = newPosition.getPosition().getX();
		y = newPosition.getPosition().getY();
		angle = newPosition.getAngle();
	}
	public void changeStat(Stat stat, double value) {
		stats.increase(stat, value);
	}


	public Pos getPosition() {
		return new Pos(new Point2D.Double(x, y), (int) angle);
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public String getId() {
		return id;
	}
	public StatContainer getStats() {
		return stats;
	}
	public void setMachine(Interactable machine) {
		this.interactingWith = machine;
	}
	public Optional<Interactable> getMachine() {
		return Optional.ofNullable(interactingWith);
	}
	public double getSpeed() {
		Stat[] arguments = {Stat.COFFEE, Stat.FUN, Stat.SANITY};
		return Math.sqrt(stats.getPercentageProduct(arguments)); // Max speed is 1
	}


	private void move(double angle) {
		switch ((int) angle) {
			case 0 :
				y++;
				break;
			case 90 :
				x++;
				break;
			case 180 :
				y--;
				break;
			case 270 :
				x--;
				break;
		}
	}
	private double fixAngle(double angle) {
		while (angle < 0)
			angle += 360;
		while (angle >= 360)
			angle -= 360;
		return angle;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			return ((Player) obj).getId() == this.getId();
		} catch (ClassCastException e) {
			return false;
		}
	}

}
