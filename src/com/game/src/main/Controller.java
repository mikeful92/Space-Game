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
    private LinkedList<Entity> e = new LinkedList<Entity>();
    
    Entity ent;

    
//    Game game;
    Textures tex;
    Random r = new Random();
    
    public Controller(Textures tex){
//        this.game = game;
        this.tex = tex;
//        addEntity(new BasicEnemy(r.nextInt(Game.WIDTH * Game. SCALE), 0, tex));
    }
    
    public void createBasicEnemy(int enemy_count){
        for(int i = 0; i < enemy_count; i++){
            addEntity(new BasicEnemy(r.nextInt(Game.WIDTH * Game. SCALE), 0, tex));
        }
    }
    
    public void tick(){
        for(int i = 0; i < e.size(); i++){
            ent = e.get(i);
            
            ent.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i = 0; i < e.size(); i++){
            ent = e.get(i);
            
            ent.render(g);
        }
    }
    
    public void addEntity(Entity block){
        e.add(block);
    }
    
    public void removeEntity(Entity block){
        e.remove(block);
    }
}
