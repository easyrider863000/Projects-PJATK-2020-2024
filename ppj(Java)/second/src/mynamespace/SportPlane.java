package mynamespace;

public class SportPlane extends Plane{
    private String className = "SportPlane";
    private double enginePower;
    private double maxSpeed;

    public double getEnginePower() {
        return enginePower;
    }

    public String getClassName() {
        return className;
    }

    public void setEnginePower(double enginePower) {
        if(enginePower<0){
            this.enginePower = 0;
        }
        this.enginePower = enginePower;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        if(maxSpeed<0){
            this.maxSpeed = 0;
        }
        this.maxSpeed = maxSpeed;
    }

    public SportPlane(Plane[][] planes, String name, double positionX, double positionY, Vector3 vector, double level, double speed, double maxSpeed, double enginePower) {
        super(planes, name, positionX, positionY, vector, level, speed);
        setMaxSpeed(maxSpeed);
        setEnginePower(enginePower);
    }

    @Override
    public String toString() {
        return className + " " + super.toString() + " " +enginePower+" "+maxSpeed;
    }
}
