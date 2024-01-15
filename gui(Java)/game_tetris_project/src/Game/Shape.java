package Game;

import java.io.Serializable;

public class Shape implements Serializable {
    private boolean [][] array;
    private int indexX;
    private int indexY;
    private final Item kind;
    public Shape(Item kindShape, int indexX, int indexY){
        array = AllShapes.getRandShape();
        kind = kindShape;
        this.indexX = indexX;
        this.indexY = indexY;
    }

    public void leftTransformShape(Item[][] items) {
        clearShapeFromField(items);
        int N = array.length;
        boolean[][] tmp = getCopyShape();
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - i - 1; j++) {
                boolean temp = tmp[i][j];
                tmp[i][j] = tmp[j][N - 1 - i];
                tmp[j][N - 1 - i] = tmp[N - 1 - i][N - 1 - j];
                tmp[N - 1 - i][N - 1 - j] = tmp[N - 1 - j][i];
                tmp[N - 1 - j][i] = temp;
            }
        }
        if(!isShapeSwam(tmp,indexX,indexY,items)){
            array = tmp;
        }
        update(items);
    }
    public boolean getItemsWithShape(Item[][] items){
        if(isShapeSwam(array,indexX,indexY,items)){
            return false;
        }
        update(items);
        return true;
    }

    public boolean moveDown(Item[][] items){
        clearShapeFromField(items);
        boolean[][] tmp = getCopyShape();
        if(!isShapeSwam(tmp,indexX,indexY+1,items)){
            indexY++;
            update(items);
            return true;
        }
        update(items);
        return false;
    }
    public void moveRight(Item[][] items){
        clearShapeFromField(items);
        boolean[][] tmp = getCopyShape();
        if(!isShapeSwam(tmp,indexX+1,indexY,items)){
            indexX++;
        }
        update(items);
    }
    public void moveLeft(Item[][] items){
        clearShapeFromField(items);
        boolean[][] tmp = getCopyShape();
        if(!isShapeSwam(tmp,indexX-1,indexY,items)){
            indexX--;
        }
        update(items);
    }

    private void update(Item[][] items){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j]){
                    items[i+indexX][j+indexY] = new Item(kind.getColor(), kind.getWidth(), kind.getHeight(),kind.isDefault());
                }
            }
        }
    }
    private void clearShapeFromField(Item[][] items){
        int width = items[0][0].getWidth();
        int height = items[0][0].getHeight();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j]){
                    items[i+indexX][j+indexY] = new Item(Item.getDefaultItem().getColor(),width,height,true);
                }
            }
        }
    }
    private boolean[][] getCopyShape(){
        boolean[][] copy = new boolean[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, copy[i], 0, array.length);
        }
        return copy;
    }
    private boolean isShapeSwam(boolean[][] tmpShape, int tmpX, int tmpY, Item[][] items){
        for (int i = 0; i < tmpShape.length; i++) {
            for (int j = 0; j < tmpShape[i].length; j++) {
                if(tmpShape[i][j]){
                    if(tmpX+i>=items.length){return true;}
                    if(tmpY+j>=items[0].length){return true;}
                    if(tmpX+i<0){return true;}
                    if(!items[tmpX+i][tmpY+j].isDefault()){return true;}
                }
            }
        }
        return false;
    }
}