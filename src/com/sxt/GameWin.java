package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {

    static State state;
    enum State{PREPARE,PLAY,SHOP,FAIL,WIN}
    List<Object> objectList = new ArrayList<>();
    Line line = new Line(this);


    {
        boolean isAbleToPlace = true;
        for(int i =0;i<11;i++){
            double random = Math.random();
            Gold gold;
            if(random<0.3){
                gold = new GoldMini();
            }else if(random < 0.7){
                gold = new Gold();
            }else {
                gold = new GoldPlus();
            }

            for(Object object : objectList){
                if(gold.getRectangle().intersects(object.getRectangle())){
                    isAbleToPlace = false;
                }
            }
            if(isAbleToPlace){
                objectList.add(gold);
            }else {
                isAbleToPlace = true;
                i--;
            }

        }
        for(int i =0;i<5;i++){
            Rock rock = new Rock();
            for(Object object:objectList){
                if(rock.getRectangle().intersects(object.getRectangle())){
                    isAbleToPlace = false;
                }
            }
            if(isAbleToPlace){
                objectList.add(rock);
            }else {
                isAbleToPlace = true;
                i--;
            }
        }
    }

    Image offScreenImage;
    Background background = new Background();
    void launch() throws InterruptedException {
        if(state == null){
            state = State.PREPARE;
        }
        this.setVisible(true);
        this.setSize(768,1000);
        this.setLocationRelativeTo(null); //居中
        this.setTitle("黄金矿工 GoldMiner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (state){
                    case PREPARE -> {
                        if(e.getButton() == 3){
                            state = State.PLAY;
                            background.startTime = System.currentTimeMillis();
                        }
                    }
                    case PLAY -> {
                        if(e.getButton() == 1 && line.state == 0) {// left mouse button
                            line.state = 1;
                        }
                        if (e.getButton() == 3 && line.state == 3 && Background.numOfWater > 0){// right mouse button
                            Background.numOfWater--;
                            Background.usingWater = true;
                        }
                    }
                    case SHOP -> {
                        if(e.getButton() == 1){
                            background.buyWater = true;
                        }
                        if(e.getButton() == 3){
                            state = State.PLAY;
                            background.startTime = System.currentTimeMillis();
                        }
                    }
                    case FAIL, WIN -> {
                        if(e.getButton() == 1){
                            state = State.PREPARE;
                            background.reStartGame();
                            line.reStartGame();
                        }
                    }
                }

            }
        });

        while (true){
            repaint();
            nextLevel();
            Thread.sleep(10);
        }
    }
    public void nextLevel() throws InterruptedException {
        if(background.getTime() && state.equals(State.PLAY)){
            if(Background.integral >= background.goal){

                if(Background.level == 3){
                    state = State.WIN;
                }else {
                    state = State.SHOP;
                    Background.level++;
                    //state = State.PREPARE;
                    //background.startTime = System.currentTimeMillis();
                }

            }else {
                state = State.FAIL;
            }
            dispose();
            GameWin gameWin = new GameWin();

            gameWin.launch();
        }

    }

    @Override
    public void paint(Graphics g) {

        offScreenImage = this.createImage(768,1000);
        Graphics gImage = offScreenImage.getGraphics();

        background.paintSelf(gImage);


        if(state.equals(State.PLAY)){
            for(Object object:objectList){
                object.paintSelf(gImage);
            }

            try {
                line.paintSelf(gImage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) throws InterruptedException {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
