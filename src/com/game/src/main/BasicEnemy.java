/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Mike
 */
public class BasicEnemy implements Entity{
    
    private double x;
    private double y;
    private double velY;
    Random r = new Random();
    
    private Textures tex;
    
    public BasicEnemy(double x, double y, Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
        velY = r.nextInt(3) + 1;
    }
    
    public void tick(){
        y += velY;
        
        if(y >= Game.HEIGHT * Game.SCALE){
            y = -10;
            setX(r.nextInt((Game.WIDTH * Game.SCALE)));
        }
    }
    
    public void render(Graphics g){
        g.drawImage(tex.basicEnemy, (int) x, (int) y, null);
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
}
