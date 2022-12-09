abstract class HoneyBee extends Insect {
	
	private int costinfood;

	public HoneyBee(Tile position, int hp, int costinfood) {
		super(position, hp);
		this.costinfood = costinfood;
	}
	
	public int getCost() {
		return this.costinfood;
	}
	
	public boolean equals(Object obj) {
		boolean checker = false;
		if (this == obj) {
            checker = true;
		}
		else if(obj != null && obj.getClass() == this.getClass()) {
			if(((HoneyBee)obj).getPosition() == this.getPosition()&&
					((HoneyBee)obj).getHealth() == this.getHealth() && ((HoneyBee)obj).costinfood == this.costinfood) {
				checker = true;
			}
		}
		return checker;	
	}
	
	
}