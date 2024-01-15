package mynamespace;

public class PassengerPlane extends Plane{
    private String className = "PassengerPlane";
    private int maxNumberOfPassengers;
    private double maxFlightRange;

    public String getClassName() {
        return className;
    }

    public PassengerPlane(Plane[][] planes, String name, double positionX, double positionY, Vector3 vector, double level, double speed, int maxNumberOfPassengers, double maxFlightRange) {
        super(planes, name, positionX, positionY, vector, level, speed);
        setMaxNumberOfPassengers(maxNumberOfPassengers);
        setMaxFlightRange(maxFlightRange);
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
        if(maxNumberOfPassengers<0){
            System.out.println("Invalid number of passengers.");
            this.maxNumberOfPassengers = 0;
        }
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    public double getMaxFlightRange() {
        return maxFlightRange;
    }

    public void setMaxFlightRange(double maxFlightRange) {
        if(maxFlightRange<0){
            System.out.println("Invalid flight range.");
            this.maxFlightRange = 0;
        }
        this.maxFlightRange = maxFlightRange;
    }

    @Override
    public String toString() {
        return className + " " + super.toString() + " " +maxNumberOfPassengers+" "+maxFlightRange;
    }
}
