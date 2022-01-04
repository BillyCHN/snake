package com.minglead.snake.operation;

import com.minglead.snake.entity.Location;
import com.minglead.snake.entity.Snake;


/**
 * @author billy hu
 * operation implements
 */
public class SnakeOperationImpl implements SnakeOperation {
    @Override
    synchronized public void turnLeft(Snake snake) {
        snake.setForward(snake.getForward()+5);
        snake.setForward(snake.getForward()%4);
    }

    @Override
    synchronized public void turnRight(Snake snake) {
        snake.setForward(snake.getForward()+7);
        snake.setForward(snake.getForward()%4);
    }

    @Override
    synchronized public void  goAhead(Snake snake) {
        snake.setLength(snake.getLength()+1);

        Location newHead = new Location();
        Location oldHead = snake.getHead();
        switch (snake.getForward()){
            case SnakeOperation.EAST:
                newHead.setX(oldHead.getX()+1);
                newHead.setY(oldHead.getY());
                break;
            case SnakeOperation.SOUTH:
                newHead.setY(oldHead.getY()+1);
                newHead.setX(oldHead.getX());
                break;
            case SnakeOperation.WEST:
                newHead.setX(oldHead.getX()-1);
                newHead.setY(oldHead.getY());
                break;
            case SnakeOperation.NORTH:
                newHead.setY(oldHead.getY()-1);
                newHead.setX(oldHead.getX());
                break;
            default:
                break;
        }
        boolean b = testError(snake, newHead);
        if (b) {
            throw new IllegalArgumentException("game over");
        }
        oldHead.setNext(newHead);
        snake.setHead(newHead);
        if (snake.getMatrix()[newHead.getX()][newHead.getY()] == 1) {
            //吃到身体
            throw new IllegalArgumentException("game over");
        }else if (snake.getMatrix()[newHead.getX()][newHead.getY()] == 0) {
            //没吃到食物
            snake.getMatrix()[newHead.getX()][newHead.getY()] = 1;
            Location oldTail = snake.getTail();
            Location newTail = oldTail.getNext();
            snake.getMatrix()[oldTail.getX()][oldTail.getY()] = 0;
            snake.setTail(newTail);
        }else {
            //吃到食物
            snake.getMatrix()[newHead.getX()][newHead.getY()] = 1;
            snake.generateFood();
        }

    }




    private boolean testError(Snake snake, Location newHead) {
        int[][] matrix = snake.getMatrix();
        return newHead.getX() >= snake.getWidth()
                ||newHead.getX() < 0
                ||newHead.getY()>=snake.getHeight()
                ||newHead.getY() < 0
                ||matrix[newHead.getX()][newHead.getY()] == 1
        ;
    }
}
