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
public class Bullet implements Entity {
    private double x;
    private double y;
    
    private Textures tex;
    
    public Bullet(double x, double y, Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
    }
    
    public void tick(){
        y -= 7;
    }
    
    public void render(Graphics g){
        g.drawImage(tex.bullet, (int) x, (int)y, null);
    }
    
    public double getY(){
        return y;
    }
    
    public double getX(){
        return x;
    }
}

