/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
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
    
    private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    
    private boolean is_Shooting = false;
    
    private int enemy_count = 5;
    private int enemy_killed = 0;
   
    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu;
    private Restart restart;
    
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    
    public static int Health = 100;
    
    private static State state = State.Menu;
    
    
    private boolean[] keyDown = new boolean[4];
    
    public void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("src/res/sprite_sheet.png");
            background = loader.loadImage("src/res/background.png");
        }catch(IOException e){
            e.printStackTrace();
        }
        tex = new Textures(this);
        c = new Controller(tex, this);
        p = new Player((WIDTH * SCALE)/2, (HEIGHT * SCALE)- 100, tex, this,c);
        menu = new Menu();
        restart = new Restart();
        
        ea = c.getEntityA();
        eb = c.getEntityB();
        
        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput(this, getState()));
        
        c.createBasicEnemy(enemy_count);
        
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(getState() == State.Game){
            if(key == KeyEvent.VK_D){
                p.setVelX(6);
                keyDown[0] = true; 
            }else if(key == KeyEvent.VK_A){
                p.setVelX(-6);
                keyDown[1] = true; 
            }else if(key == KeyEvent.VK_S){
                p.setVelY(6);
                keyDown[2] = true; 
            }else if(key == KeyEvent.VK_W){
                p.setVelY(-6);
                keyDown[3] = true; 
            }else if(key == KeyEvent.VK_SPACE && !is_Shooting){
                is_Shooting = true;
                c.addEntity(new Bullet(p.getX() + 12, p.getY() + 10, tex, this, c));
            }
        }
        
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_D){
            keyDown[0] = false; 
        }else if(key == KeyEvent.VK_A){
            keyDown[1] = false; 
        }else if(key == KeyEvent.VK_S){
            keyDown[2] = false; 
        }else if(key == KeyEvent.VK_W){
            keyDown[3] = false; 
        }else if(key == KeyEvent.VK_SPACE){
            is_Shooting = false;
        }
        
        //vertical movement
        if(!keyDown[0] && !keyDown[1]) p.setVelX(0);
        //horizontal movement
        if(!keyDown[2] && !keyDown[3]) p.setVelY(0);
    }
    
    //Initiates game, sets frame (Dimension and properties) and starts game
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
        if(getState() == State.Game){
            p.tick();
            c.tick();
                    
            if(enemy_killed >= enemy_count){
                enemy_count += 2;
                enemy_killed = 0;
                c.createBasicEnemy(enemy_count);
            }

            if( Health <= 0){
                setState(State.Restart);
            }
        }else if(getState() == State.Restart){
            Health = 100;
            setEnemy_killed(0);
            setEnemy_count(5);
            p.setX((WIDTH * SCALE)/2);
            p.setY((HEIGHT * SCALE)- 100);
                        
        }
        
    }
    private void render(){
        
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        //Render Start
        //g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background, 0, 0, null);
        
        if(getState() == State.Game){
            c.render(g);
            p.render(g);
            
            g.setColor(Color.gray);
            g.fillRect(5,5,100,20);
            g.setColor(Color.green);
            g.fillRect(5,5,Health,20);
            g.setColor(Color.white);
            g.drawRect(5,5,100,20);
        }else if(getState() == State.Menu){
            menu.render(g);
        }else if(getState() == State.Restart){
            restart.render(g);
        }
                        
        // Render End
        g.dispose();
        bs.show();
    }
    
    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    /**
     * @return the enemy_count
     */
    public int getEnemy_count() {
        return enemy_count;
    }

    /**
     * @param enemy_count the enemy_count to set
     */
    public void setEnemy_count(int enemy_count) {
        this.enemy_count = enemy_count;
    }

    /**
     * @return the enemy_killed
     */
    public int getEnemy_killed() {
        return enemy_killed;
    }

    /**
     * @param enemy_killed the enemy_killed to set
     */
    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }
    
     /**
     * @return the state
     */
    public static State getState() {
        return state;
    }
    /**
     * @param aState the state to set
     */
    public static void setState(State aState) {
        state = aState;
    }
    
    public static void reinit(){
        Health = 100;
       
    }
}