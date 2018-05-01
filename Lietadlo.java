
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lacal
 */
public class Lietadlo extends JComponent implements ActionListener, MouseListener {
    
    Timer casovac;
    Timer casovac2;
    int rychlost = 5;
    int obrazok = 0;
    private JFrame rodic;
    
    public Lietadlo(JFrame rodic) {
         setBounds(300,30,100,100);
         this.rodic = rodic;
         casovac = new Timer(100, this);
         casovac2 = new Timer(100, this);
         addMouseListener(this);
         
        
    }
    
    public void Start() {
        casovac.start();
    }
    
    public void Vykresli(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(0, 15, 50, 20);
        g.setColor(Color.gray);
        g.fillOval(25, 0, 10, 50);
        g.setColor(Color.black);
        if (casovac.isRunning()) {
            if (obrazok == 1) g.drawOval(50, 23, 10, 8);
            else g.drawOval(50, 23, 5, 4);
        }
        if (casovac2.isRunning()) {
            g.drawString("MAYDAY!!", 40, 10);
        }
        
        if (!(casovac2.isRunning()) && !(casovac.isRunning())){
            g.setColor(Color.red);
            g.fillOval(0, 15, 50, 50);
            g.setColor(Color.yellow);
            g.fillOval(5, 20, 40, 40);
        }
        
        
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Vykresli(g);
    }
    public void Posun() {
        if(getX() + getWidth() < 0) setLocation(rodic.getWidth(), getY());
        else setBounds(getX()-rychlost, getY(), getWidth(), getHeight());
        
        if (casovac2.isRunning()) {
            if (getY() + getHeight() > rodic.getHeight())  {
                casovac2.stop();
                repaint();
                
            }
            else setBounds(getX()+rychlost -2, getY()+rychlost, getWidth(), getHeight());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if (casovac.isRunning()) {
           obrazok = (obrazok + 1) % 2;
           Posun();
           repaint();
       }
       if (casovac2.isRunning()) {
           Posun();
           repaint();
       }
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        casovac.stop();
        casovac2.start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
