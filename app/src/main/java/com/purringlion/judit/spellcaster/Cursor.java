package com.purringlion.judit.spellcaster;

/**
 * Created by Judit on 2017.11.11..
 */
public class Cursor {
    private float x;
    private float y;
    private float z;
    private int radius;

    public Cursor(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void moveDelta(float x, float y, float z)
    {
        this.x+=x;
        this.y+=y;
        this.z+=z;
    }

    public void moveDelta(float x, float y)
    {
        this.x+=x;
        this.y+=y;
    }

//    public float getHorizontalPos()
//    {
//        return this.getX();
//    }



    @Override
    public String toString() {
        return "{" +
                "x=" + Math.round(x) +
                ", y=" + Math.round(y) +
                ", z=" + Math.round(z) +
                '}';
    }
}