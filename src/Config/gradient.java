/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
/**
 *
 * @author Axel
 */
@SuppressWarnings("unchecked")
public class gradient extends JPanel{
   @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
GradientPaint g= new GradientPaint(0, 0, Color.decode("#0052D4"), 0, getHeight(), Color.decode("#9CECFB"));
g2.setPaint(g);
g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
super.paintChildren(grphcs);
       //To change body of generated methods, choose Tools | Templates.
    }
}

