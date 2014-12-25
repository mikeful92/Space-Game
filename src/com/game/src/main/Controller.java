/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Mike
 */
public class Controller {
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    
    Bullet tempBullet;
    
    Game game;
    
    public Controller(Game game){
        this.game = game;
        
    }
    
    public void tick(){
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            
            if(tempBullet.getY() < 0){
                removeBullet(tempBullet);
            }
            
            tempBullet.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            
            tempBullet.render(g);
        }        
    }
    
    public void addBullet (Bullet block){
        b.add(block);
        
    }
    
    public void removeBullet (Bullet block){
        b.remove(block);
    }
}
