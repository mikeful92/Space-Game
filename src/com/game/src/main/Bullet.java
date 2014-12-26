/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.libs.Animation;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mike
 */
public class Bullet extends GameObject implements EntityA {
    
    private Textures tex;
    private Game game;
    
    Animation anim;
    
    public Bullet(double x, double y, Textures tex, Game game){
        super(x,y);
        this.tex = tex;
        this.game = game;
        
        anim = new Animation(5, tex.bullet[0], tex.bullet[0],tex.bullet[0]);
    }
    
    public void tick(){
        y -= 7;
        
        if(Physics.Collision(this, game.eb)){
            System.out.println("COLLISION DETECTED");
        }
        anim.runAnimation();
    }
    
    public void render(Graphics g){
        //g.drawImage(tex.bullet[0], (int) x, (int)y, null);
        anim.drawAnimation(g, x, y, 0);
    }
    
    public double getY(){
        return y;
    }
    
    public double getX(){
        return x;
    }


    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 8, 13);
    }
}

