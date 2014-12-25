/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Graphics;

/**
 *
 * @author Mike
 */
public class BasicEnemy {
    
    private double x;
    private double y;
    private Textures tex;
    
    public BasicEnemy(double x, double y, Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
    }
    
    public void tick(){
        y += 2;
    }
    
    public void render(Graphics g){
        g.drawImage(tex.basicEnemy, (int) x, (int) y, null);
    }
    
    public double getY(){
        return y;
    }
}
