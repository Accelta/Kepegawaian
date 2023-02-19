/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Axel
 */
public class model_menu {

    /**
     * @return the picture
     */
   

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public menutype getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(menutype type) {
        this.type = type;
    }
     public byte[] getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public model_menu(String icon, String name, menutype type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
        
    }
    public model_menu(){}
    private String icon;
    private String name;
    private menutype type;
    private byte[] picture;
    
    public Icon toicon(){
    return new ImageIcon(getClass().getResource("/icon/" + icon + ".png"));
    }
    public static enum menutype{
  MENU, EMPTY, TITLE
    }
}
