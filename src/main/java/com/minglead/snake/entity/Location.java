package com.minglead.snake.entity;

/**
 * @author billy hu
 */
public class Location{
    int x;
    int y;
    Location next;

    public Location(int x, int y, Location next) {
        this.x = x;
        this.y = y;
        this.next = next;
    }

    public Location() {
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Location getNext() {
        return next;
    }
    public void setNext(Location next) {
        this.next = next;
    }
}
