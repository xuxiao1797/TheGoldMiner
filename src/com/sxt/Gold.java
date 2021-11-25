package com.sxt;

import java.awt.*;

public class Gold extends Object{

    public Gold() {
        this.x = (int) (Math.random()*700);
        this.y = (int) (Math.random()*350+301);
        this.width = 52;
        this.height = 52;
        this.mass = 30;
        this.integral = 4;
        this.flag = false;
        this.type = Type.Gold;
        this.image = Toolkit.getDefaultToolkit().getImage("images/gold1.gif");
    }
}

class GoldMini extends Gold{
    public GoldMini() {
        this.width = 36;
        this.height = 36;
        this.mass = 15;
        this.integral = 2;
        this.image = Toolkit.getDefaultToolkit().getImage("images/gold0.gif");
    }
}

class GoldPlus extends Gold{
    public GoldPlus() {
        this.x = (int) (Math.random()*650);
        this.width = 105;
        this.height = 105;
        this.mass = 60;
        this.integral = 8;
        this.image = Toolkit.getDefaultToolkit().getImage("images/gold2.gif");
    }
}
