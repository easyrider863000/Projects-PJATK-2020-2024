package mynamespace;

import java.io.*;

public class ArrayOfPlane {
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
