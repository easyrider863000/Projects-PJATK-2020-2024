package second;

public class Main {
    public static void main(String[] args) {
        /*long start = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println("Time: "+(System.currentTimeMillis()-start)+" miliseconds.");
        //Time: 2291.0 miliseconds.*/

        Thread thread1 = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i <= 1000000; i+=2) {
                System.out.print(i);
            }
            System.out.println();
            System.out.println("Time: "+(System.currentTimeMillis()-start)+" miliseconds.");
        });
        Thread thread2 = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 1; i <= 1000000; i+=2) {
                System.out.print(i);
            }
            System.out.println();
            System.out.println("Time: "+(System.currentTimeMillis()-start)+" miliseconds.");
        });
        thread1.start();
        thread2.start();
        //2942
        //3000
    }
}
