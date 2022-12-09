class StingyBee extends HoneyBee {
	
	private int damage;

	public StingyBee(Tile position, int damage) {
		super(position, 10, 1);
		this.damage = damage;
	}
	
	public boolean takeAction() {
		boolean checker = false;
		
		if(this.getPosition() == null) {
			return checker;
		}
		Tile temp = this.getPosition();
		
		if(this.getPosition().isOnThePath() == true || this.getPosition().isHive() == true) {
			while(checker==false && temp.isNest()==false && temp.towardTheNest()!=null) {
				if(temp.getNumOfHornets() == 0) {
					temp = temp.towardTheNest();
				}else {
					temp.getHornet().takeDamage(this.damage);
					checker = true;
				}
			}
		}
		
		return checker;
	}
	
	public boolean equals(Object obj) {
		boolean checker = false;
		if (super.equals(obj)) {
			if(((StingyBee)obj).damage == this.damage ){
				checker = true;
			}	
		}
		return checker;	
	}

}
