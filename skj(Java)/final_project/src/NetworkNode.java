public class NetworkNode {
    public static void main(String[] args) {
        //Важен порядок указания параметров
        if (args.length<=6){
            System.out.println("Required more arguments");
            return;
        }
        if (!args[0].equals("-ident")){
            System.out.println("\"-ident\" parameter is not provided");
        }
        if (!args[2].equals("-tcpport")){
            System.out.println("\"-tcpport\" parameter is not provided");
        }
        if (!args[4].equals("-gateway")){
            System.out.println("\"-gateway\" parameter is not provided");
        }
        int ident = Integer.parseInt(args[1]);
        int tcpport = Integer.parseInt(args[3]);
        int gateway = Integer.parseInt(args[5]);



    }
}
