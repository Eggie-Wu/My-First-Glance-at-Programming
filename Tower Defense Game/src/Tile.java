class Tile {
	
	private int food;
	private boolean bee_hive_checker;
	private boolean hornet_nest_checker;
	private boolean is_path_checker;
	private Tile nexttile_hive;
	private Tile nexttile_nest;
	private HoneyBee bee;
	private SwarmOfHornets all_hornets;
	
	public Tile() {
		this.food = 0;
		this.bee_hive_checker = false;
		this.hornet_nest_checker = false;
		this.is_path_checker = false;
		this.nexttile_hive = null;
		this.nexttile_nest = null;
		this.bee = null;
		this.all_hornets = new SwarmOfHornets();
	}
	
	public Tile(int food, boolean bee_hive_checker, boolean hornet_nest_checker,
				boolean is_path_checker, Tile nexttile_bh, Tile nexttile_hn, HoneyBee bee, SwarmOfHornets all_hornets) {
		this.food = food;
		this.bee_hive_checker = bee_hive_checker;
		this.hornet_nest_checker = hornet_nest_checker;
		this.is_path_checker = is_path_checker;
		this.nexttile_hive = nexttile_bh;
		this.nexttile_nest = nexttile_hn;
		this.bee = bee;
		this.all_hornets = all_hornets;
	}
	
	public boolean isHive() {
		return this.bee_hive_checker;
	}
	
	public boolean isNest() {
		return this.hornet_nest_checker;
	}
	
	public void buildHive() {
		if(!this.bee_hive_checker) {
			this.bee_hive_checker = true;
		}
	}
	
	public void buildNest() {
		if(!this.hornet_nest_checker) {
			this.hornet_nest_checker = true;
		}
	}
	
	public boolean isOnThePath() {
		return this.is_path_checker;
	}
	
	public Tile towardTheHive() {
		if(!this.is_path_checker) {
			return null;
		}
		return this.nexttile_hive;
	}
	
	public Tile towardTheNest() {
		if(!this.is_path_checker) {
			return null;
		}
		return this.nexttile_nest;
	}
	
	public void createPath(Tile nexttile_hive, Tile nexttile_nest) {
		this.nexttile_hive = nexttile_hive;
		this.nexttile_nest = nexttile_nest;
		this.is_path_checker = true;
	}
	
	public int collectFood() {
		int collected = this.food;
		this.food = 0;
		return collected;
	}
	
	public void storeFood(int storage) {
		this.food += storage;
	}
	
	public HoneyBee getBee() {
		return this.bee;
	}
	
	public Hornet getHornet() {
		return this.all_hornets.getFirstHornet();
	}
	
	public int getNumOfHornets() {
		return this.all_hornets.sizeOfSwarm();
	}
	
	public boolean addInsect(Insect insect) {
		
		if(insect instanceof HoneyBee && this.bee == null && this.hornet_nest_checker == false) {
			insect.setPosition(this);
			this.bee = (HoneyBee) insect;
			return true;
			
			
		}else if((insect instanceof Hornet)&&(this.hornet_nest_checker == true || this.bee_hive_checker == true
				|| this.is_path_checker ==true)) {
			insect.setPosition(this);
			this.all_hornets.addHornet((Hornet) insect);
			return true;
		}
		return false;
	}
	
	public boolean removeInsect(Insect insect) {
		if(insect instanceof HoneyBee && this.bee==insect) {
			insect.setPosition(null);
			this.bee = null;
			return true;
			
		}else if(insect instanceof Hornet) {
			
			if(this.all_hornets.removeHornet((Hornet) insect)) {
				insect.setPosition(null);
				return true;
			}
		}
		
		return false;
	}
}
