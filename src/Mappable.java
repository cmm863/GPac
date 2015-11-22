import java.awt.Point;
public class Mappable {
	// Static variables
	private static int idIndex = 0;
	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT, 
		HOLD
	};
	// Class variables
	private Point coordinate;
	private int id;
	private char debugCharacter;
	private int type;
	
	// Constructors
	public Mappable(int x, int y, int t) {
		this.coordinate = new Point(x, y);
		this.id = Mappable.idIndex;
		this.type = t;
		this.setDebugCharacter(this.type);
		Mappable.idIndex++;
	}
	
	public Mappable(Point coor, char t) {
		this.coordinate = new Point(coor);
		this.id = Mappable.idIndex;
		this.type = t;
		this.setDebugCharacter(this.type);
		Mappable.idIndex++;
	}
	
	// Getters
	public int getID() {
		return id;
	}
	
	public Point getCoordinate() {
		return coordinate;
	}
	
	public int getX() {
		return coordinate.x;
	}
	
	public int getY() {
		return coordinate.y;
	}
	
	public char getDebugCharacter() {
		return debugCharacter;
	}
	
	public int getType() {
		return type;
	}
	
	// Setter
	public void setX(int x) {
		this.coordinate.x = x;
	}
	
	public void setY(int y) {
		this.coordinate.y = y;
	}
	
	private void setDebugCharacter(int t) {
		switch(t) {
		case MappableType.TILE:
			this.debugCharacter = MappableType.TILE_C;
			break;
		case MappableType.WALL:
			this.debugCharacter = MappableType.WALL_C;
			break;
		case MappableType.MS_PAC_MAN:
			this.debugCharacter = MappableType.MS_PAC_MAN_C;
			break;
		case MappableType.GHOST_1:
			this.debugCharacter = MappableType.GHOST_1_C;
			break;
		case MappableType.GHOST_2:
			this.debugCharacter = MappableType.GHOST_2_C;
			break;
		case MappableType.GHOST_3:
			this.debugCharacter = MappableType.GHOST_3_C;
			break;
		case MappableType.PILL:
			this.debugCharacter = MappableType.PILL_C;
			break;
		default:
			this.debugCharacter = MappableType.UNDEFINED_C;
			break;
		}
	}
	
	// Public Methods
	public void printCoordinate() {
		System.out.println(coordinate.x + ", " + coordinate.y);
	}
	
}
