/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.awt.Graphics;

/**
 *
 * @author marcelino
 */
public class Nodo {
    
    private int x, y;
    public static final int d=60;

    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
    }
 
   public void pintar (Graphics g){
       g.drawOval(this.x - d/2, this.y - d/2, d,d);
   }
}
