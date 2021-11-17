/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author marcelino
 */
public class Lienzo extends JPanel implements MouseListener, MouseMotionListener {

    private Vector<Nodo> vectorNodos;
    private Vector<Enlace>vectorEnlace;
    private Point p1,p2;
    private Nodo nodoMover;
    private int iNodo;
    
    public Lienzo (){
        this.vectorNodos = new Vector<>();
        this.vectorEnlace = new Vector<>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
       
    }
    
    @Override
    public void paint (Graphics g){
        super.paint(g);
        for(Nodo nodos : vectorNodos){
            nodos.pintar(g);
        }
        for(Enlace enlace : vectorEnlace){
            enlace.pintar(g);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
      if(e.getButton()==MouseEvent.BUTTON1){
          String nombre = JOptionPane.showInputDialog("Ingrese nombre Nodo: ");
          this.vectorNodos.add(new Nodo(e.getX(), e.getY(), nombre));
          repaint();
      }
      if(e.getButton()==MouseEvent.BUTTON3){
          for(Nodo nodo : vectorNodos){
          if(new Rectangle(nodo.getX() - Nodo.d/2, nodo.getY() - Nodo.d/2, Nodo.d, Nodo.d).contains(e.getPoint())){
              if(p1 == null)
                  p1= new Point(nodo.getX(), nodo.getY());
              else{
                  p2 = new Point(nodo.getX(), nodo.getY());
                  String nombre=JOptionPane.showInputDialog("Ingrse un nombre nodo; ");
                  this.vectorEnlace.add(new Enlace(p1.x, p2.y, p2.x, p2.y, nombre ));
                  repaint();
                  p1= null;
                  p2= null;
              }
          }
        }
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       int iN=0;
       for(Nodo nodo : vectorNodos){
       if(new Rectangle(nodo.getX() - Nodo.d/2, nodo.getY()- Nodo.d/2, Nodo.d, Nodo.d).contains(e.getPoint())){
       nodoMover= nodo;
       iNodo=iN;
       break;
           
            }
       iN++;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       nodoMover = null;
       iNodo = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(nodoMover != null){
            this.vectorNodos.set(iNodo, new Nodo(e.getX(), e.getY(), nodoMover.getNombre()));
            int iE=0;
            for(Enlace enlace: vectorEnlace){
                if(new Rectangle(enlace.getX1() - Nodo.d/2,enlace.getY1()- Nodo.d/2, Nodo.d, Nodo.d).contains(e.getPoint())){
                    this.vectorEnlace.set(iE, new Enlace(e.getX(), e.getY(), enlace.getX2(), enlace.getY2(), enlace.getNombre()));
                }
                else if(new Rectangle(enlace.getX2() - Nodo.d/2,enlace.getY2()- Nodo.d/2, Nodo.d, Nodo.d).contains(e.getPoint())){
                    this.vectorEnlace.set(iE, new Enlace(enlace.getX1(), enlace.getY1(),e.getX(), e.getY(), enlace.getNombre()));
                }
                iE++;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       
    }
    
}
