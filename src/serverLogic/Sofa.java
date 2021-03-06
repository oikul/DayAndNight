package serverLogic;

import gameConfiguration.MachineType;

public class Sofa extends Machine {


	private double useTime;
	private double sabotageTime;
	private double sanityRestored;
	private double sanityRestoredWhenSabotaged;

	public Sofa(int x, int y, int width, int height, double useTime, double sabotageTime, double sanityRestored,
		double sanityRestoredWhenSabotaged) {
		super(x, y, width, height, MachineType.SOFA);
		this.useTime = useTime;
		this.sabotageTime = sabotageTime;
		this.sanityRestored = sanityRestored;
		this.sanityRestoredWhenSabotaged = sanityRestoredWhenSabotaged;
		this.sabotaged = false;
	}

	@Override
	public double startUsing(Player player) {
		return useTime;
	}

	@Override
	public double startSabotaging(Player player) {
		return sabotageTime;
	}

	@Override
	public void cancelAction(Player player) {

	}

	@Override
	public void finishUsing(Player player) {
		player.changeStat(Stat.SANITY, sabotaged ? sanityRestored : sanityRestoredWhenSabotaged);
		sabotaged = false;
	}

	@Override
	public void finishSabotaging(Player player) {
		sabotaged = true;
		System.err.println(this + " has been successfully sabotaged by " + player);
	}

	@Override
	public String toString() {
		return "Sofa at x=" + rectangle.x + ", y=" + rectangle.y + ". " + (sabotaged ? "Currently sabotaged" : "Not sabotaged.");
	}

}
