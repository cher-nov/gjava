/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gcreator.components.impl;

import org.gcreator.units.TimelineStep;
import java.awt.*;
import javax.swing.*;
import org.gcreator.fileclass.res.*;

/**
 *
 * @author luis
 */
public class TimelineStepsCellRenderer extends JLabel implements ListCellRenderer{
    public TimelineStepsCellRenderer(){
        setOpaque(true);
    }
    
    public Component getListCellRendererComponent
            (JList list, Object value, int index, boolean selected, boolean focus){
        if(value==null){
            setText("");
        }
        else if(value instanceof TimelineStep){
            TimelineStep step = (TimelineStep) value;
            setText("Step " + step.stepnum);
        }
        else{
            setText("Invalid resource");
        }
        if(selected){
           try{
                    setForeground(list.getSelectionForeground());
                    setBackground(list.getSelectionBackground());
                }
                catch(Exception e){
                    setForeground(Color.WHITE);
                    setBackground(Color.BLACK);
                }
        }
        else{
            setForeground(Color.BLACK);
            setBackground(Color.WHITE);
        }
        return this;
    }
}
