package Game;

public class AllShapes {
    public static boolean[][] getRandShape(){
        int r = (int)(Math.random()*28);
        return switch (r) {
            case 0 -> (new boolean[][]{{true, false, false},
                    {true, false, false},
                    {true, false, false}});
            case 1 -> (new boolean[][]{{false, false, true},
                    {false, false, true},
                    {false, false, true}});
            case 2 -> (new boolean[][]{{true, true, true},
                    {false, false, false},
                    {false, false, false}});
            case 3 -> (new boolean[][]{{false, false, false},
                    {false, false, false},
                    {true, true, true}});
            case 4, 6 -> (new boolean[][]{{false, false, false},
                    {false, true, true},
                    {false, true, true}});
            case 5, 7 -> (new boolean[][]{{false, false, false},
                    {true, true, false},
                    {true, true, false}});
            case 8 -> (new boolean[][]{{false, true, false},
                    {true, true, true},
                    {false, false, false}});
            case 9 -> (new boolean[][]{{false, true, false},
                    {false, true, true},
                    {false, true, false}});
            case 10 -> (new boolean[][]{{false, false, false},
                    {true, true, true},
                    {false, true, false}});
            case 11 -> (new boolean[][]{{false, true, false},
                    {true, true, false},
                    {false, true, false}});
            case 12 -> (new boolean[][]{{true, false, false},
                    {true, true, true},
                    {false, false, false}});
            case 13 -> (new boolean[][]{{false, true, true},
                    {false, true, false},
                    {false, true, false}});
            case 14 -> (new boolean[][]{{false, false, false},
                    {true, true, true},
                    {false, false, true}});
            case 15 -> (new boolean[][]{{false, true, false},
                    {false, true, false},
                    {true, true, false}});
            case 16 -> (new boolean[][]{{false, false, true},
                    {true, true, true},
                    {false, false, false}});
            case 17 -> (new boolean[][]{{false, true, false},
                    {false, true, false},
                    {false, true, true}});
            case 18 -> (new boolean[][]{{false, false, false},
                    {true, true, true},
                    {true, false, false}});
            case 19 -> (new boolean[][]{{true, true, false},
                    {false, true, false},
                    {false, true, false}});
            case 20 -> (new boolean[][]{{false, true, true},
                    {true, true, false},
                    {false, false, false}});
            case 21 -> (new boolean[][]{{false, true, false},
                    {false, true, true},
                    {false, false, true}});
            case 22 -> (new boolean[][]{{false, false, false},
                    {false, true, true},
                    {true, true, false}});
            case 23 -> (new boolean[][]{{true, false, false},
                    {true, true, false},
                    {false, true, false}});
            case 24 -> (new boolean[][]{{true, true, false},
                    {false, true, true},
                    {false, false, false}});
            case 25 -> (new boolean[][]{{false, false, true},
                    {false, true, true},
                    {false, true, false}});
            case 26 -> (new boolean[][]{{false, false, false},
                    {true, true, false},
                    {false, true, true}});
            default -> (new boolean[][]{{false, true, false},
                    {true, true, false},
                    {true, false, false}});
        };
    }
}
