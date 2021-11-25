package com.sxt;

import java.awt.*;

public class Rock extends Object{
    public Rock() {
        this.x = (int) (Math.random()*700);
        this.y = (int) (Math.random()*350+301);
        this.width = 71;
        this.height = 71;
        this.mass = 50;
        this.integral = 1;
        this.type = Type.Rock;
        this.flag = false;
        this.image = Toolkit.getDefaultToolkit().getImage("images/rock1.png");
    }
}
