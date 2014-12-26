/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Mike
 */
public class BasicEnemy extends GameObject implements EntityB{
    
    private double velY;
    Random r = new Random();
    
    private Textures tex;
    
    private Animation anim;
    
    public BasicEnemy(double x, double y, Textures tex){
        super(x,y);
        this.tex = tex;
        velY = 1;
        
        anim = new Animation(5, tex.basicEnemy[0], tex.basicEnemy[0], tex.basicEnemy[0]);
    }
    
    public void tick(){
        y += velY;
        
        if(y >= Game.HEIGHT * Game.SCALE){
            y = -10;
            x = (r.nextInt((Game.WIDTH * Game.SCALE)));
        }
        anim.runAnimation();
    }
    
    public void render(Graphics g){
        //g.drawImage(tex.basicEnemy[0], (int) x, (int) y, null);
        anim.drawAnimation(g, x, y, 0);
    }
    
    public double getY(){
        return y;
    }
    
    public double getX(){
        return x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public void setX(double x){
        this.x = x;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 22, 24);
    }
}
