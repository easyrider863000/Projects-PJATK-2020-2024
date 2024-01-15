package first;

public class Main {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(j);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    System.err.print(j);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}