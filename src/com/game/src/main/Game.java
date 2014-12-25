/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Mike
 */
public class Game extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH /12*9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Space Game";
    
    private boolean running = false;
    private Thread thread;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private boolean is_Shooting;
    
    private Player p;
    private Controller c;
    
    private boolean[] keyDown = new boolean[4];
    
    public void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("src/res/sprite_sheet.png");

        }catch(IOException e){
            e.printStackTrace();
        }
        
        addKeyListener(new KeyInput(this));
        
        p = new Player(200, 200, this);
        c = new Controller(this);
        
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT){
            p.setVelX(5);
            keyDown[0] = true; 
        }else if(key == KeyEvent.VK_LEFT){
            p.setVelX(-5);
            keyDown[1] = true; 
        }else if(key == KeyEvent.VK_DOWN){
            p.setVelY(5);
            keyDown[2] = true; 
        }else if(key == KeyEvent.VK_UP){
            p.setVelY(-5);
            keyDown[3] = true; 
        }else if(key == KeyEvent.VK_SPACE && !is_Shooting){
            is_Shooting = true;
            c.addBullet(new Bullet(p.getX(), p.getY(), this));
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT){
            keyDown[0] = false; 
        }else if(key == KeyEvent.VK_LEFT){
            keyDown[1] = false; 
        }else if(key == KeyEvent.VK_DOWN){
            keyDown[2] = false; 
        }else if(key == KeyEvent.VK_UP){
            keyDown[3] = false; 
        }else if(key == KeyEvent.VK_SPACE){
            is_Shooting = false;
        }
        
        //vertical movement
        if(!keyDown[0] && !keyDown[1]) p.setVelX(0);
        //horizontal movement
        if(!keyDown[2] && !keyDown[3]) p.setVelY(0);
    }
    
    public static void main(String args[]){
        Game game = new Game();
        
        game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        
        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.start();
    }
    
    private synchronized void start(){
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private synchronized void stop(){
        if(!running)
            return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.exit(1);
        
    }
    
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, FPS " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
     }

    private void tick() {
        p.tick();
        c.tick();
    }
    private void render(){
        
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        //Render Start
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        c.render(g);
        p.render(g);
        
        
        // Render End
        g.dispose();
        bs.show();
    }
    
    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
}