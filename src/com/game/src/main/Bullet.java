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
public class Bullet {
    private double x;
    private double y;
    
    BufferedImage image;
    
    public Bullet(double x, double y, Game game){
        this.x = x;
        this.y = y;
        
        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        
        image = ss.grabImage(2, 1, 32, 32);
    }
    
    public void tick(){
        y -= 7;
    }
    
    public void render(Graphics g){
        g.drawImage(image, (int) x, (int)y, null);
    }
    
    public double getY(){
        return y;
    }
}

