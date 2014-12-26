/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Michael
 */
public class Restart {
    
    public static int playAgainButtonX = Game.WIDTH/2 + 60;
    public static int playAgainButtonY = 150;
    public static int quitButtonX = Game.WIDTH/2 + 60;
    public static int quitButtonY = 300;
    public static int buttonWidth = 200;
    public static int buttonHeight = 50;
    
    
    public Rectangle playAgainButton = new Rectangle(playAgainButtonX, playAgainButtonY, buttonWidth, buttonHeight);
    public Rectangle quitButton = new Rectangle(quitButtonX, quitButtonY, buttonWidth, buttonHeight);
    
    public void render(Graphics g){
       Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("GAME OVER!", Game.WIDTH/2, 100);
        
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        
        g.setFont(fnt1);
        g.drawString("Play Again", playAgainButton.x + 30, playAgainButton.y + 35);
        g.drawString("Quit", quitButton.x + 75, quitButton.y + 35);
        g2d.draw(playAgainButton);
        g2d.draw(quitButton);
        
    }
}
