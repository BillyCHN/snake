package top.bchome.snake.operation;

import top.bchome.snake.entity.Snake;

/**
 * @author billy hu
 * operation interface
 */
public interface SnakeOperation {
    /**
     * turn left
     */
    int LEFT = 0;
    /**
     * turn right
     */
    int RIGHT = 1;
    /**
     * head direction east
     */
    int EAST = 0;
    /**
     * head direction south
     */
    int SOUTH = 1;
    /**
     * head direction west
     */
    int WEST = 2;
    /**
     * head direction north
     */
    int NORTH = 3;


    /**
     * turn left
     * @param snake
     */
    void turnLeft(Snake snake);

    /**
     * turn right
     * @param snake
     */
    void turnRight(Snake snake);

    /**
     * 前进
     * @param snake
     */
    void goAhead(Snake snake);

}
