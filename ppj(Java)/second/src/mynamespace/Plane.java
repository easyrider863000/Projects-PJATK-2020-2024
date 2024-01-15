package mynamespace;

public abstract class Plane {

    private double positionX;
    private double positionY;
    private Vector3 vector;
    private double level;
    private double speed;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.indexOf(' ')>=0||name.indexOf(';')>=0){
            System.out.println("Invalid name for plane. Do not use \" \" or\";\".");
            name = "noname";
        }
        this.name = name;
    }

    public Plane(Plane[][] planes, String name, double positionX, double positionY, Vector3 vector, double level, double speed) {
        setPosition(planes,positionX,positionY);
        setName(name);
        setVector(vector);
        setLevel(planes,level);
        setSpeed(speed);
    }

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }

    public void setPosition(Plane[][] planes, double positionX, double positionY) {
        if(positionIsTaken(planes,positionX,positionY,this.level)) {
            System.out.println("This position is taken.");
        }
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Vector3 getVector() {
        return vector;
    }

    public void setVector(Vector3 vector) {
        this.vector = vector;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(Plane[][] planes ,double level) {
        if(positionIsTaken(planes,this.positionX,this.positionY,level)) {
            System.out.println("This position is taken.");
        }
        if(level<0 || level>37650){
            System.out.println("This is invalid height");
            this.level=0;
        }
        this.level = level;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if(speed<0 || speed>2650){
            System.out.println("This is invalid speed");
            this.speed = 0;
        }
        this.speed = speed;
    }

    public double getDistanceXY(){
        return Math.sqrt(Math.pow(positionX,2)+Math.pow(positionY,2));
    }

    public static boolean positionIsTaken(Plane[][] planes,double positionX, double positionY, double level){
        for (Plane[] plane : planes) {
            for (Plane value : plane) {
                if (value!=null&&value.positionX == positionX && value.positionY == positionY && value.level == level) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name+" "+vector.toString()+" "+speed;
    }
}
