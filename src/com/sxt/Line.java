package com.sxt;

import java.awt.*;

public class Line {
    int x = 380; //coordinate of start point
    int y = 180;
    int endx = 600; // coordinate of end point
    int endy = 600;

    double length = 100;
    double k = 0;

    double miniLength = 100;
    double maxLength = 750;

    int direction = 1;

    //state = 0 , swing , state = 1; capture; state = 2, back
    int state;

    Image hook = Toolkit.getDefaultToolkit().getImage("images/hook.png");

    GameWin frame;

    Line(GameWin frame){
        this.frame = frame;
    }

    void logic(){
        for(Object object:this.frame.objectList){
            if (endx > object.x && endx < object.x + object.width
                    && endy > object.y && endy < object.y + object.height){
                state = 3;
                object.flag = true;
            }
        }

    }

    void showlines(Graphics graphics){
        endx = (int) (x + length*Math.cos(k*Math.PI));
        endy = (int) (y + length*Math.sin(k*Math.PI));
        graphics.setColor(Color.red);
        graphics.drawLine(x-1,y,endx-1,endy);
        graphics.drawLine(x,y,endx,endy);
        graphics.drawLine(x+1,y,endx+1,endy);
        graphics.drawImage(hook,endx-36,endy-2,null);
    }

    void reStartGame(){
        k = 0;
        length = 100;

    }

    void paintSelf(Graphics graphics) throws InterruptedException {
        logic();
        switch (state){
            case 0:
                if (k < 0.1) {
                    direction = 1;
                }else if ( k > 0.9){
                    direction = -1;
                }
                k = k + 0.005*direction;
                showlines(graphics);
                break;
            case 1:
                if (length < maxLength) {
                    length = length + 10;
                    showlines(graphics);
                }else {state = 2;}
                break;

            case 2:
                if (length > miniLength) {
                    length = length - 5;
                    showlines(graphics);
                }else {state = 0;}
                break;
            case 3:
                int m = -1;
                if (length > miniLength) {
                    length = length - 5;
                    showlines(graphics);
                    for(Object object:this.frame.objectList){
                        if(object.flag){
                            m = object.mass;
                            object.x = endx-object.getWidth()/2;
                            object.y = endy;
                        if(length <= miniLength){
                            object.x = -150;
                            object.y = -150;
                            object.flag = false;
                            Background.usingWater = false;
                            Background.integral += object.integral;
                            state = 0;
                        }

                        if(Background.usingWater){
                            if(object.type.equals(Object.Type.Gold)){
                                m = 1;
                            }else if(object.type.equals(Object.Type.Rock)){
                                object.x = -150;
                                object.y = -150;
                                object.flag = false;
                                Background.usingWater = false;
                                state = 2;
                            }
                        }

                        }

                    }
                    Thread.sleep(m);
                }else {
                    showlines(graphics);
                    for(Object object:this.frame.objectList){
                        if(object.flag){
                            m = object.mass;
                            object.x = endx-object.getWidth()/2;
                            object.y = endy;
                            if(length <= miniLength){
                                object.x = -150;
                                object.y = -150;
                                object.flag = false;
                                state = 0;
                            }


                        }

                    }
                }

                break;
            default:


        }

    }

}
