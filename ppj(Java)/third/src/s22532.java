import java.io.*;

public class s22532 {
    public static void main(String[] args) {
        ArrayOfPlane arrayOfPlane = new ArrayOfPlane();
        if(args.length<=0){return;}
        if(args[0].equals("save")){
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"Su-57",10,1,new Vector3(1,1,1),2,312,2,2));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"B2-Spirit",2,1,new Vector3(1,-1,1),2,360,4,4));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"B-52",3,1,new Vector3(2,-1,3),9,250,2,2));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"Tu-160",4,2,new Vector3(2,-1,3),2,340,1,1));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"Tu-22M3",3,2,new Vector3(2,-1,4),12,220,1,3));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"3M",3,3,new Vector3(2,5,9),5,211,1,5));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"M-4",1,2,new Vector3(1,1,-3),1,340,1,12));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"B-29",5,10,new Vector3(1,-1,3),5,228,1,1));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"B-36",4,5,new Vector3(2,1,8),6,209,1,3));
            arrayOfPlane.addPlane(new MilitaryPlane(arrayOfPlane.getArr(),"Avro_Vulcan",8,1,new Vector3(5,2,1),11,210,1,1));

            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"A-41",11,1,new Vector3(5,2,1),9,110,210,80));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Bugatti_Model_100",10,2,new Vector3(5,2,1),20,420,724,900));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Aero_Designs_Pulsar",5,5,new Vector3(7,12,3),2,150,306,115));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Gee_Bee_R-1_Super_Sport",5,12,new Vector3(5,3,10),3,230,800,476));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Laros-31",1,10,new Vector3(-5,2,1),1,120,400,153));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Robin_DR-400",4,5,new Vector3(2,21,12),8,191,200,270));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Zivko_Edge_540",6,8,new Vector3(3,65,4),3,130,280,340));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Extra_EA-300",2,3,new Vector3(5,2,5),11,215,408,300));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Potez_60",11,12,new Vector3(-8,2,1),4,149,150,60));
            arrayOfPlane.addPlane(new SportPlane(arrayOfPlane.getArr(),"Messerschmitt_Me.209",7,12,new Vector3(-6,12,3),27,153,750,2300));

            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Douglas_DC-3",3,11,new Vector3(-6,4,-3),7,150,32,2160));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Tu-144",13,1,new Vector3(36,4,129),28,146,80,3100));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Boeing_747",34,41,new Vector3(76,5,-17),81,210,581,14815));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Boeing_777X",23,14,new Vector3(36,3,-3),24,130,384,16170));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Airbus_A380",11,21,new Vector3(26,-4,-3),25,240,853,15400));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"de_Havilland_Comet",8,15,new Vector3(9,9,-9),26,153,101,4900));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Airbus_A300",34,51,new Vector3(8,-8,8),85,134,270,7000));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Boeing_747-8",15,1,new Vector3(-8,4,-4),84,189,581,8288));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"An-225",3,13,new Vector3(3,4,-8),10,201,88,15400));
            arrayOfPlane.addPlane(new PassengerPlane(arrayOfPlane.getArr(),"Airbus_A320",7,13,new Vector3(5,3,-3),85,138,220,5950));

            arrayOfPlane.show();
            arrayOfPlane.save("planes.txt");
        }
        else if(args[0].equals("load")){
            arrayOfPlane.load(Integer.parseInt(args[1]),"planes.txt");
            arrayOfPlane.show();
        }
    }
}

class ArrayOfPlane {
    private Plane[][] arr;

    public Plane[][] getArr() {
        return arr;
    }
    public ArrayOfPlane(Plane[][] arr) {
        this.arr = arr;
    }
    public ArrayOfPlane(int heightArr, int widthArr){
        arr = new Plane[heightArr][widthArr];
    }
    public ArrayOfPlane(){
        arr = new Plane[10][10];
    }
    public void addPlane(Plane plane){
        int i = getRowLevel(plane.getLevel());
        for (int j = 0; j < arr[i].length; j++) {
            if (arr[i][j] == null) {
                arr[i][j] = plane;
                sort(arr[i]);
                return;
            }
        }
        System.out.println("No free places for flight");
    }
    private void sort(Plane[] array){
        for (int i = 1; i < array.length; i++) {
            if(array[i]!=null) {
                Plane current = array[i];
                int j = i - 1;
                while (j >= 0 && current.getDistanceXY() < array[j].getDistanceXY()) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = current;
            }
        }
    }
    private int getRowLevel(double level){
        if(level<=0){
            return 0;
        }
        for (int i = 1; i < level; i++) {
            if(Math.pow(3,i)<= level&&Math.pow(3,(i+1))> level){
                return i;
            }
        }
        return 0;
    }
    public void save(String place){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(place)))
        {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if(arr[i][j]!=null) {
                        bw.write(arr[i][j].toString()+";\n");
                    }
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void load(int count, String place){
        try(BufferedReader br = new BufferedReader(new FileReader(place)))
        {
            String s;
            while((s=br.readLine())!=null){
                for (int i = 0; i < count; i++) {
                    if (getNumberOfPlaces() <= 0) {
                        System.out.println("Array of planes is full. You should increase array.");
                        return;
                    }
                    String[] strings = s.substring(0, s.length() - 1).split(" ");
                    Plane newPlane;
                    String[] vector = strings[2].split(",");
                    double num = Math.pow(3,arr.length)-1;
                    if(num>37650){num=37650;}
                    double positionX = 1 + Math.random() * num;
                    double positionY = 1 + Math.random() * num;
                    double level = 1 + Math.random() * num;
                    while (Plane.positionIsTaken(arr, positionX, positionY, level)||!hasArrPlace(getRowLevel(level))) {
                        positionX = 1 + Math.random() * num;
                        positionY = 1 + Math.random() * num;
                        level = 1 + Math.random() * num;
                    }
                    switch (strings[0]) {
                        case "MilitaryPlane" -> {
                            newPlane = new MilitaryPlane(arr, strings[1], positionX, positionY, new Vector3(Double.parseDouble(vector[0]), Double.parseDouble(vector[1]), Double.parseDouble(vector[2])), level, Double.parseDouble(strings[3]), Double.parseDouble(strings[4]), Double.parseDouble(strings[5]));
                            addPlane(newPlane);
                        }
                        case "PassengerPlane" -> {
                            newPlane = new PassengerPlane(arr, strings[1], positionX, positionY, new Vector3(Double.parseDouble(vector[0]), Double.parseDouble(vector[1]), Double.parseDouble(vector[2])), level, Double.parseDouble(strings[3]), Integer.parseInt(strings[4]), Double.parseDouble(strings[5]));
                            addPlane(newPlane);
                        }
                        case "SportPlane" -> {
                            newPlane = new SportPlane(arr, strings[1], positionX, positionY, new Vector3(Double.parseDouble(vector[0]), Double.parseDouble(vector[1]), Double.parseDouble(vector[2])), level, Double.parseDouble(strings[3]), Double.parseDouble(strings[4]), Double.parseDouble(strings[5]));
                            addPlane(newPlane);
                        }
                    }
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void start(String[] args){
        if(args[0].equals("save")){
            save("planes.txt");
            return;
        }
        else if(args[0].equals("load")){
            load(Integer.parseInt(args[1]),"planes.txt");
            return;
        }
        System.out.println("No such parameter.");
    }

    public void show(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != null) {
                    System.out.print("|"+arr[i][j].getName()+"| ");
                }
            }
            System.out.println();
        }
    }
    private int getNumberOfPlaces(){
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                if(arr[j][k]==null){
                    i++;
                }
            }
        }
        return i;
    }
    private boolean hasArrPlace(int row){
        for (int i = 0; i < arr[row].length; i++) {
            if(arr[row][i]==null){
                return true;
            }
        }
        return false;
    }
}

class MilitaryPlane extends Plane {
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

class PassengerPlane extends Plane{
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

class SportPlane extends Plane{
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

class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return x+","+y+","+z;
    }
    public static Vector3 fromStringToData(String string){
        String[] arr = string.split(",");
        return new Vector3(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]),Double.parseDouble(arr[2]));
    }
}

abstract class Plane {

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
