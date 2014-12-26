/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

/**
 *
 * @author Michael
 */
public class MouseInput implements MouseListener{
    
    Game game;
    State state; 
    
    public MouseInput(Game game, State state){
        this.game = game;
        this.state = state; 
    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if(Game.getState() == state.Menu){
            if(mx >= Menu.playButtonX && mx <= Menu.playButtonX + Menu.buttonWidth){
                if(my >= Menu.playButtonY && my <= Menu.playButtonY + Menu.buttonHeight){
                    Game.setState(state.Game);
                }
            }
            if(mx >= Menu.quitButtonX && mx <= Menu.quitButtonX + Menu.buttonWidth){
                if(my >= Menu.quitButtonY && my <= Menu.quitButtonY + Menu.buttonHeight){
                    System.exit(1);
                }
            }
        }else if(Game.getState() == state.Restart){
            if(mx >= Restart.playAgainButtonX && mx <= Restart.playAgainButtonX + Restart.buttonWidth){
                if(my >= Restart.playAgainButtonY && my <= Restart.playAgainButtonY + Restart.buttonHeight){
                   
                    Game.setState(state.Game);
                }
            }
            if(mx >= Restart.quitButtonX && mx <= Restart.quitButtonX + Restart.buttonWidth){
                if(my >= Restart.quitButtonY && my <= Restart.quitButtonY + Restart.buttonHeight){
                    System.exit(1);
                }
            }
        }
        
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
}
