package com.sxt;

import java.awt.*;
import java.lang.reflect.Type;

public class Object {
    int x;
    int y;

    int width;
    int height;

    Image image;

    int mass;

    boolean flag = false; //false: cannot move ; true: able to move

    int integral;

     Type type;
    public enum Type {Rock,Gold}


    void paintSelf(Graphics graphics){
        graphics.drawImage(image,x,y,null);

    }

    public int getWidth() {
        return width;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }
}
