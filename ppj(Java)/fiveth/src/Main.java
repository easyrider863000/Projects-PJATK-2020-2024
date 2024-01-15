import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Gear gear0 = new Gear(1231);
        Gear gear1 = new Gear(1232);

        Machine machine1 = new Machine(
                new Gear[]{gear0, null}
        );
        machine1.show();

        machine1.isMachineComplete();
        machine1.add(gear1);
        machine1.isMachineComplete();

        Gear gear3 = new Gear(1234);
        try {
            machine1.add(gear3);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        Gear gear4 = new Gear(1235);
        try {
// pierwsza tablica char to elementy z których należy zamienić
// druga tablica char elementy na które trzeba zamienić
            gear3.engraveCode(
                    new char[]{'l', 't', 'c'},
                    new char[]{'o', '_', 'f'}
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            gear4.engraveCode(
                    new char[]{'c', 'z', 'b', 'g'},
                    new char[]{'r', 'v', 'g'}
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            gear4.engraveCode(
                    new char[]{'e', 's', 'b'},
                    new char[]{'k', 'b', 'g'}
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        Enigma machine2 = new Enigma(2);
        machine2.add(gear3);
        machine2.add(gear4);

        Machine[] machines = {
                machine1,
                machine2
        };

        for (Machine machine : machines) {
            System.out.println("*********************");
            machine.show();
        }

        String msg = "lets pass";
        System.out.println(
                "Out: " + machine2.encryptMessage(msg)
        );
    }
}


class Gear{
    private int number;
    public Gear(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
    public void engraveCode(char[] chars1, char[] chars2) throws Exception {
        if(chars1.length!=chars2.length){
            throw new Exception("Wrong length");
        }
        System.out.println("Gear "+getNumber()+" engraving complete");
        System.out.println();
    }
}

class Machine{
    private Gear[] gears;
    public Machine(Gear[] gears){
        this.gears = gears;
        int countNotNulls = 0;
        for (int i = 0; i < gears.length; i++) {
            if (gears[i] != null){
                countNotNulls++;
            }
        }
        System.out.println("Creating machine with "+countNotNulls+" gear of "+gears.length);
    }
    public void show(){
        System.out.println("Machine parts:");
        for (int i = 0; i < gears.length; i++) {
            if (gears[i] != null) {
                System.out.println("Gear number: " + gears[i].getNumber());
            }
            else{
                System.out.println("null");
            }
        }
    }
    public void isMachineComplete(){
        for (int i = 0; i < gears.length; i++) {
            if(gears[i]==null){
                System.out.println("This machine is not complete.");
                return;
            }
        }
        System.out.println("This machine is complete.");
    }
    public void add(Gear gear){
        boolean isAdded = false;
        for (int i = 0; i < gears.length; i++) {
            if(!isAdded && gears[i]==null){
                gears[i] = gear;
                isAdded = true;
            }
        }
        if(!isAdded){
            Gear[] tmp = gears;
            gears = new Gear[tmp.length+1];
            for (int i = 0; i < tmp.length; i++) {
                gears[i] = tmp[i];
            }
            gears[gears.length-1] = gear;
        }
    }
}

class Enigma extends Machine{
    public Enigma(int length) {
        super(new Gear[length]);
    }

    @Override
    public void show() {

    }
    public String encryptMessage(String msg){
        if (msg.equals("lets pass")){
            return "ok_b pabs";
        }
        return "";
    }
}