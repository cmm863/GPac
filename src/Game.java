import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
public class Game {
	// Private Variables
	private HashMap<Point, Mappable> gameMap;
	private HashMap<Integer, Unit> gameUnits;
	private HashMap<Point, Mappable> gamePills;
	public static int height, width;
	public int time;
	private double pillDensity;
	private int pillCount;
	private int pillsEaten;
	public double score;
	private Random rand;
	private Unit msPacMan;
	public boolean gameEnd;
	private int turnNumber;
	private int seed;
	
	// Constructor
	public Game(List<String> lines) {
		String[] lineAttributes;
		// Parse config file
		for(String l : lines) {
			lineAttributes = l.split("\\s+");
			switch(lineAttributes[0]) {
			case "height":
				Game.height = Integer.parseInt(lineAttributes[1]);
				break;
			case "width":
				Game.width = Integer.parseInt(lineAttributes[1]);
				break;
			case "pill_density":
				this.pillDensity = Double.parseDouble(lineAttributes[1]);
				break;
			case "seed":
				break;
			default:
				break;
			}
		}
		
		// Initialize dimensions
		this.time = 2*Game.height*Game.width;
		this.gameEnd = false;
		this.turnNumber = 0;
		this.rand = new Random();
		// Create gameMap
		gameMap = new HashMap<Point, Mappable>();
		for(int i = 0; i < Game.height; i++) {
			for(int j = 0; j < Game.width; j++) {
				Mappable t = new Mappable(j, i, MappableType.TILE);
				gameMap.put(t.getCoordinate(), t);
			}
		}
		
		// Create gamePills
		gamePills = this.generatePills(this.pillDensity);
		pillCount = gamePills.size();
		pillsEaten = 0;
		// Create gameUnits
		gameUnits = new HashMap<Integer, Unit>();
		this.msPacMan = new Unit(0, Game.height - 1, MappableType.MS_PAC_MAN);
		gameUnits.put(this.msPacMan.getID(), this.msPacMan);
		Unit ghost1 = new Unit(Game.width - 1, 0, MappableType.GHOST_1);
		Unit ghost2 = new Unit(Game.width - 1, 0, MappableType.GHOST_2);
		Unit ghost3 = new Unit(Game.width - 1, 0, MappableType.GHOST_3);
		gameUnits.put(ghost1.getID(), ghost1);
		gameUnits.put(ghost2.getID(), ghost2);
		gameUnits.put(ghost3.getID(), ghost3);
		
	}
	
	public void takeTurn() {
		for(Unit u : gameUnits.values()) {
			if(u.getType() == MappableType.MS_PAC_MAN) {
				while(!(u.move(rand.nextInt(5))));
			} else {
				while(!(u.move(rand.nextInt(4))));
			}
		}
		if(gamePills.get(this.msPacMan.getCoordinate()) != null) {
			gamePills.remove(this.msPacMan.getCoordinate());
			this.pillsEaten++;
			if(this.pillsEaten == this.pillCount) {
				this.gameEnd = true;
			}
			this.score = ((double) this.pillsEaten)/this.pillCount;
		}
		for(Unit u : gameUnits.values()) {
			if(u.getType() != MappableType.MS_PAC_MAN) {
				if(u.getCoordinate().equals(msPacMan.getCoordinate())) {
					this.gameEnd = true;
				}
			}
		}
		//this.outputState();
		if(turnNumber == 0) {
			for(Mappable p : gamePills.values()) {
				//System.out.println(p.getDebugCharacter() + " " + p.getX() + " " + p.getY());
			}
		}
		this.turnNumber++;
		if(turnNumber == this.time) {
			this.gameEnd = true;
		}
	}
	
	public void debugString() {
		for(Unit u : gameUnits.values()) {
			System.out.println(u.getDebugCharacter() + " " + u.getX() + " " + u.getY());
		}
	}
	
	public void printMap() {
		for(int i = 0; i < Game.width; i++) {
			System.out.print("--");
		}
		System.out.println();
		char[][] mapArray = new char[Game.width][Game.height];
		for(Mappable m : gameMap.values()) {
			boolean unitOccupied = false;
			for(Unit u : gameUnits.values()) {
				if(u.getCoordinate().equals(m.getCoordinate())) {
					mapArray[u.getX()][u.getY()] = u.getDebugCharacter();
					unitOccupied = true;
					break;
				}
			}
			if(!unitOccupied) {
				boolean pillOccupied = false;
				for(Mappable p : gamePills.values()) {
					if(p.getCoordinate().equals(m.getCoordinate())) {
						mapArray[p.getX()][p.getY()] = p.getDebugCharacter();
						pillOccupied = true;
						break;
					}
				}
				if(!pillOccupied) {
					mapArray[m.getX()][m.getY()] = m.getDebugCharacter();
				}
			}
		}
		for(int j = Game.height - 1; j >= 0; j--) {
			for(int i = 0; i < Game.width; i++) {
				System.out.print(mapArray[i][j] + " ");
			}
			System.out.println();
		}
		for(int i = 0; i < Game.width; i++) {
			System.out.print("--");
		}
		System.out.println();
	}
	
	public HashMap<Point, Mappable> generatePills(double pd) {
		HashMap<Point, Mappable> pillSet = new HashMap<Point, Mappable>();
		while(pillSet.isEmpty()) {
			for(int j = Game.height - 1; j >= 0; j--) {
				for(int i = 0; i < Game.width; i++) {
					if(i != 0 && j != Game.height - 1) {
						if(this.rand.nextInt(101) <= pd) {
							Mappable new_pill = new Mappable(i, j, MappableType.PILL);
							pillSet.put(new_pill.getCoordinate(), new_pill);
						}
					}
				}
			}
		}
		return pillSet;
	}
	
	public void outputState() {
		for(Unit u : gameUnits.values()) {
			System.out.println(u.getDebugCharacter() + " " + u.getX() + " " + u.getY());
		}
		System.out.println("t " + ((int)this.time - (int)this.turnNumber) + " " + (int)(this.score * 100));
	}
}
