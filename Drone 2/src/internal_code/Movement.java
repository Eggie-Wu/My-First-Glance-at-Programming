package internal_code;

abstract class Movement {
    /**
     * Execute a drone movement
     */
    abstract public boolean equals(Object o);
    abstract public int hashCode();
    abstract Direction getDirection();

    abstract void execute();
}
