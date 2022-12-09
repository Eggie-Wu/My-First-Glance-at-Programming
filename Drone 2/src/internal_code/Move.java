package internal_code;

import java.util.Objects;

class Move extends Movement{
    private Direction direction;
    private double distance;
    private Speed speed;
    private boolean isRecord;

    /**
     * Constructor
     * @param direction,distance,speed,isRecord of the Movement
     */
    Move(Direction direction,double distance, Speed speed, boolean isRecord){
        this.direction=direction;
        if(distance<=0){
            this.distance=0;
        }else{
            this.distance=distance;
        }
        this.speed=speed;
        this.isRecord=isRecord;
    }

    Direction getDirection(){
        return this.direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return Double.compare(move.distance, distance) == 0 && direction == move.direction && speed == move.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, distance, speed);
    }

    //print the movement
    void execute() {
        if(this.isRecord){
            System.out.println("Move "+this.direction.toString()+ " for "+this.distance
                    +" meters at "+this.speed.toString()+" speed. (Recording)");
        }else{
            System.out.println("Move "+this.direction.toString()+ " for "+ this.distance
                    +" meters at "+this.speed.toString()+" speed. (Not Recording)");
        }
    }
}
