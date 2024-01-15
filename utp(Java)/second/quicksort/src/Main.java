import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        int tab[] = new int[]{1,3,5,7,9};
        int findNum = 7;
        int result = 0+((findNum-tab[0])*(tab.length-1-0))/(tab[tab.length-1]-tab[0]);
        System.out.println("0+(("+findNum+"-"+tab[0]+")/("+tab[tab.length-1]+"-"+tab[0]+"))*("+(tab.length-1)+"-0) = "+result);
        if(findNum==tab[result]){
            System.out.println("TAK");
        }
        else{
            System.out.println("NIE");
        }
    }
}
