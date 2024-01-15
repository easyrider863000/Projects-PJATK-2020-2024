package mynamespace;

public class MilitaryPlane extends Plane {
    private String className = "MilitaryPlane";
    private double countGuns;
    private double countOfAmmunition;

    public String getClassName() {
        return className;
    }

    public MilitaryPlane(Plane[][] planes, String name, double positionX, double positionY, Vector3 vector, double level, double speed, double countGuns, double countOfAmmunition) {
        super(planes, name, positionX, positionY, vector, level, speed);
        setCountGuns(countGuns);
        setCountOfAmmunition(countOfAmmunition);
    }

    public double getCountGuns() {
        return countGuns;
    }

    public void setCountGuns(double countGuns) {
        if(countGuns<0){
            this.countGuns = 0;
        }
        this.countGuns = countGuns;
    }

    public double getCountOfAmmunition() {
        return countOfAmmunition;
    }

    public void setCountOfAmmunition(double countOfAmmunition) {
        if(countOfAmmunition<0){
            this.countOfAmmunition = 0;
        }
        this.countOfAmmunition = countOfAmmunition;
    }

    @Override
    public String toString() {
        return className+" "+super.toString()+" "+countGuns+" "+countOfAmmunition;
    }
}
