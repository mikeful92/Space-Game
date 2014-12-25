/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Mike
 */
public class Controller {
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    private LinkedList<BasicEnemy> e = new LinkedList<BasicEnemy>();
    
    Random r = new Random();
    
    Bullet tempBullet;
    BasicEnemy tempBasicEnemy;
    
    Game game;
    Textures tex;
    
    public Controller(Game game, Textures tex){
        this.game = game;
        this.tex = tex;
        
        addBasicEnemy(new BasicEnemy(r.nextInt(Game.WIDTH * Game. SCALE), 0, tex));

    }
    
    public void tick(){
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            
            if(tempBullet.getY() < 0){
                removeBullet(tempBullet);
            }
            
            tempBullet.tick();
        }
        
        for(int i = 0; i < e.size(); i++){
            tempBasicEnemy = e.get(i);
                    
            
            tempBasicEnemy.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            
            tempBullet.render(g);
        }
        for(int i = 0; i < e.size(); i++){
            tempBasicEnemy = e.get(i);
            
            tempBasicEnemy.render(g);
        }
    }
    
    public void addBullet (Bullet block){
        b.add(block);
        
    }
    
    public void removeBullet (Bullet block){
        b.remove(block);
    }
    
        
    public void addBasicEnemy (BasicEnemy block){
        e.add(block);
        
    }
    
    public void removeBasicEnemy (BasicEnemy block){
        e.remove(block);
    }
}
