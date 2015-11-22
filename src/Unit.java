
public class Unit extends Mappable{
	public Unit(int x, int y, int t) {
		super(x, y, t);
	}
	
	public boolean move(int direction) {
		int x, y;
		Direction d = Direction.values()[direction];
		switch(d) {
		case UP:
			y = this.getY() + 1;
			if(y >= Game.height) {
				return false;
			} else {
				this.setY(y);
				return true;
			}
		case DOWN:
			y = this.getY() - 1;
			if(y < 0) {
				return false;
			} else {
				this.setY(y);
				return true;
			}
		case LEFT:
			x = this.getX() - 1;
			if(x < 0) {
				return false;
			} else {
				this.setX(x);
				return true;
			}
		case RIGHT:
			x = this.getX() + 1;
			if(x >= Game.width) {
				return false;
			} else {
				this.setX(x);
				return true;
			}
		case HOLD:
		default:
			return true;
			
		}
	}
}
