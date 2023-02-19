/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import kepegawaian.Selecteventmenu;
import model.model_menu;
/**
 *
 * @author Axel
 */
public class listmenu<E extends Object> extends JList<E>{

    private final DefaultListModel model;
    private int selectedindex = -1;
    private int overindex= -1;
      private Selecteventmenu event;
     
     public void addevent(Selecteventmenu event){
     this.event=event;
     };
    
    
  public listmenu(){
      model=new DefaultListModel();
      setModel(model);
      addMouseListener(new  MouseAdapter(){
          @Override
          public void mousePressed(MouseEvent e) {
              if (SwingUtilities.isLeftMouseButton(e)) {
                  int index=locationToIndex(e.getPoint());
                  Object o =model.getElementAt(index);
                  if (o instanceof  model_menu) {
                      model_menu menu = (model_menu)o;
                      if (menu.getType()==model_menu.menutype.MENU) {
                          selectedindex=index;
                        if(event!= null){
                        event.selected(index);
                        }
                      }
                  }else{
                      selectedindex=index;
                  }
                  repaint();
              }
          }

          @Override
          public void mouseExited(MouseEvent e) {
            overindex = -1;
            repaint();
          }
      
      });
      addMouseMotionListener(new MouseMotionAdapter(){
          @Override
          public void mouseMoved(MouseEvent e) {
             int index =locationToIndex(e.getPoint());
              if (index!=overindex) {
                  Object o=model.getElementAt(index);
                  if (o instanceof model_menu) {
                      model_menu menu = (model_menu)o;
                      if (menu.getType()==model_menu.menutype.MENU) {
                          overindex = index;
                      }else{
                      overindex =-1;
                      }

                      repaint();
                  }
              }
          }
      
      });
  }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int index, boolean selected, boolean focus) {
         model_menu data;
                if (o instanceof model_menu) {
                    data=(model_menu)o;
                }else{
                data=new model_menu("",o+"",model_menu.menutype.EMPTY);
                
                }
                menuitem item= new menuitem(data);
                item.setSelected(selectedindex==index);
            item.setover(overindex == index);
                return item;
            }
        
        };
    }
   public void additem(model_menu data){
  model.addElement(data);
    }
  
    
}
