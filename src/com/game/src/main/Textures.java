/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.image.BufferedImage;

/**
 *
 * @author Mike
 */
public class Textures {
    
    public BufferedImage[] player = new BufferedImage[1];
    public BufferedImage[] bullet = new BufferedImage[1];
    public BufferedImage[] basicEnemy = new BufferedImage[1];
    
    private SpriteSheet ss;
    
    public Textures(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());
        
        getTextures();
    }
    
    private void getTextures(){
        player[0] = ss.grabImage(1, 1, 32, 32);
        bullet[0] = ss.grabImage(2, 1, 8, 13);
        basicEnemy[0] = ss.grabImage(3, 1, 22, 24);
    }
}
