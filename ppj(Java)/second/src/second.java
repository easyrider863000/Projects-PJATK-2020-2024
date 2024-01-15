import mynamespace.*;

public class second {
    public static void main(String[] args) {
        ArrayOfPlane arrayOfPlane = new ArrayOfPlane();
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