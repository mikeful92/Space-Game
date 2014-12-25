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
public interface Entity {
    
    public void tick();
    public void render(Graphics g);
    
    public double getX();
    public double getY();
    
}
