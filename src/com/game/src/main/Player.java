/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mike
 */
public class Player {
    
    private double x;
    private double y;
    
    private double velX = 0;
    private double velY = 0;
    
    private BufferedImage player;
    
    private Textures tex;
    
    public Player(double x, double y, Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
        
    }
    
    public void tick(){
        x += velX;
        y += velY;
        
        if(x <= 0)
            x = 0;
        if(x >= (Game.WIDTH * Game.SCALE) -18)
            x = (Game.WIDTH * Game.SCALE)- 18;
        if(y <= 0)
            y = 0;
        if(y >= (Game.HEIGHT* Game.SCALE)-24)
            y = (Game.HEIGHT* Game.SCALE)-24;
    }
    
    public void render(Graphics g){
        g.drawImage(tex.player, (int) x, (int) y, null);
        
    }
    
    public double getX(){
        return x;
        
    }
    
    public double getY(){
        return y;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    public void setVelX(double velX){
        this.velX = velX;
    }
    
    public void setVelY(double velY){
        this.velY = velY;
    }
}
