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
public class Menu {
    
    public static int playButtonX = Game.WIDTH/2 + 120;
    public static int playButtonY = 150;

    public static int helpButtonX = Game.WIDTH/2 + 120;
    public static int helpButtonY = 250;

    public static int quitButtonX = Game.WIDTH/2 + 120;
    public static int quitButtonY = 350;
    
    public static int buttonWidth = 100;
    public static int buttonHeight = 50;

    
    public Rectangle playButton = new Rectangle(playButtonX, playButtonY, buttonWidth, buttonHeight);
    public Rectangle helpButton = new Rectangle(helpButtonX, helpButtonY, buttonWidth, buttonHeight);
    public Rectangle quitButton = new Rectangle(quitButtonX, quitButtonY, buttonWidth, buttonHeight);
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SPACE GAME", Game.WIDTH/2, 100);
        
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 19, playButton.y + 35);
        g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
        
    }
}
