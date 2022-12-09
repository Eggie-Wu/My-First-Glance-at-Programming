class TankyBee extends HoneyBee {
	
	private int damage;
	private int armor;

	public TankyBee(Tile position, int damage, int armor) {
		super(position, 30, 3);
		this.damage = damage;
		this.armor = armor;
	}
	


	public boolean takeAction() {
		boolean checker = false;	
		
		if(this.getPosition() == null) {
			return false;
		}

		if(this.getPosition().getNumOfHornets() > 0) {
			this.getPosition().getHornet().takeDamage(this.damage);
			checker = true;
		}
			
		return checker;
	}
	
	public void takeDamage(int damage) {
		int temp = damage*100/(100+this.armor);
		super.takeDamage(temp);
	}
	
	public boolean equals(Object obj) {
		boolean checker = false;
		if (super.equals(obj)) {
			if(((TankyBee)obj).damage == this.damage && ((TankyBee)obj).armor == this.armor){
				checker = true;
			}	
		}
		return checker;	
	}
}
