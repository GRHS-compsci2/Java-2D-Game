package com.github.grhscompsci2.java2DGame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Actor {
  private int dx;
  private int dy;
  private int x;
  private int y;

  private BufferedImage sprite;
  private String fileName;
  private int speed;

  /**
   * Constructor that will set the image to the fileName, and set the position to
   * (0,0)
   * 
   * @param fileName the relative location of the file ("images/filename.png")
   */
  public Actor() {
    this("images/actor.png", 0, 0);
  }

  /**
   * Constructor that will set the image to the fileName and the position to the
   * provided coordinates.
   * 
   * @param fileName the relative location of the file ("images/filename.png")
   * @param x        the x coordinate of the sprite
   * @param y        the y coordinate of the sprite
   */
  public Actor(String fileName, int x, int y) {
    this(fileName, x, y, 2);
    loadImage();
  }

  /**
   * Constructor that will set the image to the fileName and the position to the
   * provided coordinates, and set the speed of the actor.
   * 
   * @param fileName the relative location of the file ("images/filename.png")
   * @param x        the x coordinate of the sprite
   * @param y        the y coordinate of the sprite
   * @param speed    the speed of the sprite, in scaled pixels
   */
  public Actor(String fileName, int x, int y, int speed) {
    this.fileName = fileName;
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.dx = 0;
    this.dy = 0;
    loadImage();
  }

  /**
   * Using the fileName attribute, load the image into the sprite attribute
   */
  private void loadImage() {
    // Load the image
    try {
      this.sprite = ImageIO.read(Actor.class.getResource(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Update the position based on the change in x and y attributes
   */
  public void act() {
    x += dx;
    y += dy;
  }

  /**
   * Getter for the x attribute
   * 
   * @return the x attribute
   */
  public int getX() {
    return x;
  }

  /**
   * Getter for the y attribute
   * 
   * @return the y attribute
   */
  public int getY() {
    return y;
  }

  /**
   * Getter for the width of the sprite
   * 
   * @return the width attribute.
   */
  public int getWidth() {
    return sprite.getWidth();
  }

  /**
   * Getter for the height of the sprite
   * 
   * @return the height attribute
   */
  public int getHeight() {
    return sprite.getHeight();
  }

  /**
   * Getter for the sprite
   * 
   * @return the sprite attribute
   */
  public BufferedImage getImage() {
    return sprite;
  }

  public String getFileName(){
    return fileName;
  }

  /**
   * Update the image for the actor sprite. Useful for animations.
   * 
   * @param fileName the relative location of the file ("images/filename.png")
   */
  public void setImage(String fileName) {
    this.fileName = fileName;
    loadImage();
  }

  /**
   * Getter for dx
   * @return the dx attribute
   */
  public int getDX() {
    return dx;
  }

  /**
   * Setter for dx
   * @param dx the new value for dx
   */
  public void setDX(int dx) {
    this.dx = dx;
  }

  /**
   * Getter for dy
   * @return the attribute dy
   */
  public int getDY() {
    return dy;
  }

  /**
   * Setter for dy
   * @param dy the new value for dy
   */
  public void setDY(int dy) {
    this.dy = dy;
  }

  /**
   * Getter for speed
   * @return the attribute speed
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Setter for speed
   * @param speed the new value for speed
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Draws the sprite at the x, y location. Adapts to scale of the JFrame.
   * 
   * @param g Graphics2D object to draw the image
   * @param i Which JPanel is it drawn in?
   */
  public void draw(Graphics2D g, ImageObserver i) {
    int offsetX=x-sprite.getWidth()/2;
    int offsetY=y-sprite.getHeight()/2;
    g.drawImage(sprite, Utility.scale(offsetX), Utility.scale(offsetY), Utility.scale(sprite.getWidth()),
        Utility.scale(sprite.getHeight()), i);
  }

  /**
   * This method is called when a key is pressed. The dx and dy attributes are
   * modified based on what keys are pressed
   * 
   * @param e the KeyEvent passed in from the KeyAdapter
   */
  public void keyPressed(KeyEvent e) {

    // Get which key was pressed.
    int key = e.getKeyCode();
    // if it was left, subtract speed from dx.
    if (key == KeyEvent.VK_LEFT) {
      dx = -1 * speed;
    }

    // if it was right, add speed to dx
    if (key == KeyEvent.VK_RIGHT) {
      dx = speed;
    }

    // if it was up, subtract speed from dy.
    if (key == KeyEvent.VK_UP) {
      dy = -1 * speed;
    }

    // if it was down, add speed to dy
    if (key == KeyEvent.VK_DOWN) {
      dy = speed;
    }
  }

  /**
   * This method is called when a key is released
   * 
   * @param e the KeyEvent generated by the KeyAdapter
   */
  public void keyReleased(KeyEvent e) {

    // which key was released?
    int key = e.getKeyCode();

    // if the key was left or right, reset the dx to zero
    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
      dx = 0;
    }

    // if the key was up or down, reset the dy to zero
    if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
      dy = 0;
    }
  }

  /**
   * Returns the bounds of the sprite. Used for collision dectection.
   * 
   * @return a rectangle in the position and size of the sprite
   */
  public Rectangle getBounds() {
    return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
  }
}
