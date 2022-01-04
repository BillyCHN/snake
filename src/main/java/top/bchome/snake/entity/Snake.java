package top.bchome.snake.entity;

import java.util.Random;

/**
 * @author billy hu
 * snake entity
 */
public class Snake {

    /**
     * length
     */
    int length;

    /**
     * head
     */
    Location head;


    /**
     * tail
     */
    Location tail;


    /**
     * forward direction
     */
    int forward;

    /**
     * matrix of snake body
     */
    int[][] matrix;



    /**
     * width
     */
    int width;

    /**
     * height
     */
    int height;

    /**
     * has wall?
     */
    boolean hasWall;


    public Snake(int width,int height,boolean hasWall){
        this.width = width;
        this.height = height;
        this.hasWall = hasWall;
        length = 2;
        matrix = new int[width][height];
        head = new Location(width /2, height /2);
        matrix[width /2][height /2]=1;
        tail = new Location(width /2-1, height /2);
        matrix[width /2-1][height /2]=1;
        tail.next = head;
        generateFood();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Location getHead() {
        return head;
    }

    public void setHead(Location head) {
        this.head = head;
    }

    public Location getTail() {
        return tail;
    }

    public void setTail(Location tail) {
        this.tail = tail;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }


    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHasWall() {
        return hasWall;
    }

    public void setHasWall(boolean hasWall) {
        this.hasWall = hasWall;
    }

    /**
     * generate the food by randomly
     */
    public void generateFood() {
        boolean added = false;
        while (!added) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j]==1){
                        continue;
                    }else {
                        if (added) {
                            break;
                        }
                        Random random = new Random();
                        int randomNum = random.nextInt(matrix.length*matrix[0].length);
                        if (randomNum == matrix.length*matrix[0].length/2) {
                            matrix[i][j] = 2;
                            added = true;
                        }
                    }
                }
            }
        }

    }

    public void reset() {
        matrix = new int[width][height];
        head = new Location(width /2, height /2);
        matrix[width /2][height /2]=1;
        tail = new Location(width /2-1, height /2);
        matrix[width /2-1][height /2]=1;
        tail.next = head;
        generateFood();
    }
}
