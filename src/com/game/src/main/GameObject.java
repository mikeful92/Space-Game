/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Rectangle;

/**
 *
 * @author Mike
 */
public class GameObject {
    
    public double x;
    public double y;
    
    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public Rectangle getBounds(int width, int height){
        return new Rectangle((int) x, (int) y, width, height);
    }
}
