/**
 *
 * Copyright (C) 2008 Luís Reis <luiscubal@gmail.com>
 *
 * This file is part of G-Creator.
 * G-Creator is free software and comes with ABSOLUTELY NO WARRANTY.
 * See LICENSE for more details.
 */

package org.gcreator.components;

import java.awt.*;
import javax.swing.*;

import org.gcreator.editors.*;

/**
 * Previews a sprite subimage
 * @author Luís Reis
 */
public class SubimagePreview extends JLabel {
    private SpriteEditor editor;
    
    /**
     * Creates a new sprite subimage previewer
     * @param editor The sprite editor
     */
    public SubimagePreview(SpriteEditor editor){
        this.editor = editor;
    }
    
    public ImageIcon getCurrentImage(){
        return editor.getCurrentImage();
    }
    
    @Override
    public Dimension getPreferredSize(){
        ImageIcon img = getCurrentImage();
        if(img == null)
            return new Dimension(0, 0);
        return new Dimension(img.getIconWidth(), img.getIconHeight());
    }
    
    @Override
    public void paint(Graphics g){
        ImageIcon img = getCurrentImage();
        drawBackground(g);
        if (img != null) {
            g.drawImage(img.getImage(), 0, 0, img.getImageObserver());
            
            /*
             * Draw the sprite origin
            */
            g.setColor(Color.WHITE);
            g.setXORMode(Color.BLACK);//Invert everything
            org.gcreator.fileclass.res.Sprite s = editor.sprite;
            byte width = 32;
            //horizontal
            if (s.originX-width < s.width && s.originY < s.height)
                g.drawLine(s.originX-width,s.originY,Math.min(s.originX+width,s.width),s.originY);
            //Vertical
            if (s.originY-width < s.height && s.originX < s.width)
                g.drawLine(s.originX,s.originY-width,s.originX,Math.min(s.originY+width,s.height));
            /*
             * Draw the bounding box
             * I can't just use drawRect becasue the BBox 
             * shouldn't be drawn outside the image.
            */
            //g.drawRect(s.BBRight,s.BBTop,s.BBleft-s.BBRight,s.BBBottom-s.BBTop);
            //left
            g.drawLine(s.BBleft,s.BBTop+((s.BBTop < s.BBBottom) ? +1 : -1),s.BBleft,s.BBBottom-((s.BBTop < s.BBBottom) ? +1 : -1));
            //right
            g.drawLine(s.BBRight,s.BBTop+((s.BBTop < s.BBBottom) ? +1 : -1),s.BBRight,s.BBBottom-((s.BBTop < s.BBBottom) ? +1 : -1));
            //top
            g.drawLine(s.BBleft,s.BBTop,s.BBRight,s.BBTop);
            //bottom
            g.drawLine(s.BBleft,s.BBBottom,s.BBRight,s.BBBottom);
        }
    }
    
    public void drawBackground(Graphics g) {
        Dimension d = getPreferredSize();
        boolean white = false;
        boolean row = true;
        g.setColor(Color.WHITE);
        for(int i = 0; i < d.width/10; i++) {
            row = !row;
            white = row;
            g.setColor(((white) ? Color.WHITE : Color.DARK_GRAY));
            for(int j = 0; j < d.height/10; j++){
                g.fillRect(i*10, j*10, 10, 10);
                if (white = !white)
                    g.setColor(Color.WHITE);
                else
                    g.setColor(Color.DARK_GRAY);
            }
        }
    }
}
