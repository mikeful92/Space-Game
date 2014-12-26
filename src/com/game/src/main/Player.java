/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.libs.Animation;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mike
 */
public class Player extends GameObject implements EntityA{
    
   
    private double velX = 0;
    private double velY = 0;
    private Textures tex;
    private Game game;
    
    Animation anim;
    
    public Player(double x, double y, Textures tex){
        super(x,y);
        this.tex = tex;
        
        anim = new Animation(5, tex.player[0], tex.player[1], tex.player[2], tex.player[1]);
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
        
        anim.runAnimation();
               
    }
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }    

    
    public void render(Graphics g){
        //g.drawImage(tex.player[0], (int) x, (int) y, null);
        anim.drawAnimation(g, x, y, 0);
        
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
