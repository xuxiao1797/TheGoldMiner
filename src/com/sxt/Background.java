package com.sxt;

import java.awt.*;

public class Background {
    static int integral = 0;
    static int numOfWater = 3;
    static boolean usingWater = false;
    static int level = 1;
    int goal = level*5;
    int price = level*2;
    boolean buyWater = false;

    long startTime;
    long endTime;
    Image bg = Toolkit.getDefaultToolkit().getImage("images/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("images/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("images/peo.png");
    Image water = Toolkit.getDefaultToolkit().getImage("images/water.png");


    void paintSelf(Graphics graphics){
        graphics.drawImage(bg1,0,0,null);
        graphics.drawImage(bg,0,200,null);
        switch (GameWin.state){
            case PREPARE -> {
                drawWord(graphics,30,"点击鼠标右键以开始游戏",200,400,Color.BLACK);
                drawWord(graphics,30,"click right mouse button to start",130,430,Color.BLACK);
            }
            case PLAY -> {
                graphics.drawImage(peo,310,50,null);
                graphics.drawImage(water,450,40,null);


                drawWord(graphics,30,"积分 integral: " + integral,30,150,Color.BLACK);
                drawWord(graphics,30,"*" + numOfWater,510,70,Color.BLACK);

                drawWord(graphics,20,"第" + level + "关" + " level" + level,30,60,Color.BLACK);
                drawWord(graphics,30,"目标分数 goal: " + goal,30,110,Color.BLACK);
                endTime = System.currentTimeMillis();
                long time = 20-(endTime - startTime)/1000;
                drawWord(graphics,30,"时间 time: " + (time>0?time:0),520,150,Color.BLACK);
            }
            case SHOP -> {
                graphics.drawImage(water,300,400,null);

                drawWord(graphics,30,"当前积分: " + integral,300,300,Color.BLACK);
                drawWord(graphics,30,"当前药水数量: " + numOfWater,300,350,Color.BLACK);
                drawWord(graphics,30,"药水价格" + price,300,500,Color.BLACK);
                drawWord(graphics,30,"是否购买",300,550,Color.BLACK);
                drawWord(graphics,30,"鼠标左键购买，右键继续",300,600,Color.BLACK);
                if(buyWater){
                    integral = integral - price;
                    numOfWater++;
                    buyWater = false;
                    //GameWin.state = GameWin.State.PLAY;
                    //startTime = System.currentTimeMillis();
                }
            }
            case FAIL -> {
                drawWord(graphics,30,"胜败乃兵家常事",270,400,Color.BLACK);
                drawWord(graphics,30,"大侠请继续努力",270,430,Color.BLACK);
            }
            case WIN -> {
                drawWord(graphics,50,"好厉害！你竟然通关了！",130,400,Color.RED);
                drawWord(graphics,50,"分数: " + integral,280,480,Color.RED);

            }
        }


    }

    void reStartGame(){
        integral = 0;
        numOfWater = 3;
        usingWater = false;
        level = 1;
        goal = level*5;
    }
    //true: Countdown completed
    public boolean getTime(){
        long time = (endTime - startTime)/1000;
        if(time > 20) return true;
        else return false;
    }


    public static void drawWord(Graphics graphics,int size,String string,int x, int y,Color color){
        graphics.setColor(color);
        graphics.setFont(new Font("仿宋",Font.BOLD,size));
        graphics.drawString(string,x,y);
    }



}
