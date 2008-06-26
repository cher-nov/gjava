/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gcreator.components;

import java.awt.*;
import javax.swing.*;
import org.gcreator.editors.*;
import org.gcreator.fileclass.res.GImage;

/**
 *
 * @author luis
 */
public class TilesetPreviewer extends JComponent{
    private TilesetEditor editor;
    
    public TilesetPreviewer(TilesetEditor editor){
        this.editor = editor;
    }
    
    private ImageIcon getSourceImage(){
        if(editor==null)
            return null;
        if(editor.value.image==null)
            return null;
        org.gcreator.fileclass.GFile imgFile = editor.value.image;
        if(imgFile==null)
            return null;
        return ((org.gcreator.fileclass.res.GImage)(imgFile.value)).image;
    }
    
    public int getPreferredWidth(){
        /*int w = 0;
        try{
            w = getSourceImage().getIconWidth();
        }
        catch(NullPointerException e){
            return 0;
        }
        w -= editor.value.startx;
        int hsepcount = (w % editor.value.tilew == 0 ? w/editor.value.tilew : (w/editor.value.tilew)+1);
        return w+(hsepcount*2);
        */
        try {
            return ((GImage)editor.value.image.value).image.getIconWidth();
        } catch (NullPointerException exc) {
            return 0;
        } catch (ClassCastException exc) {
            System.out.println("??? "+exc);
            return 0;
        }
    }
    
    public int getPreferredHeight(){
        /*
        int h = 0;
        try{
            h = getSourceImage().getIconHeight();
        }
        catch(NullPointerException e){
            return 0;
        }
        h -= editor.value.starty;
        int vsepcount = (h % editor.value.tileh == 0 ? h/editor.value.tileh : (h/editor.value.tileh)+1);
        return h+(vsepcount*2);
        */
        try {
            return ((GImage)editor.value.image.value).image.getIconHeight();
        } catch (NullPointerException exc) {
            return 0;
        } catch (ClassCastException exc) {
            System.out.println("??? "+exc);
            return 0;
        }
    }
    
    public int getWidth(){
        return getPreferredWidth();
    }
    
    
    public int getHeight(){
        return getPreferredHeight();
    }
    
    public Dimension getPreferredSize(){
        return new Dimension(getPreferredWidth(), getPreferredHeight());
    }
    
    public void paint(Graphics g){
        super.paint(g);
        ImageIcon img = getSourceImage();
        g.clearRect(0, 0, getPreferredWidth(), getPreferredHeight());
        if(img==null)
            return;
        int imgw = img.getIconWidth();
        int imgh = img.getIconHeight();
        int w = editor.value.tilew;
        int h = editor.value.tileh;
        int bw =  editor.value.bwidth;
        int bh =  editor.value.bheight;
        //int di = 0;
        //int dj = 0;
        //for(int i = editor.value.startx; i < imgw; i+=editor.value.tilew+editor.value.bwidth){
        //    dj = 0;
        //    for(int j = editor.value.starty; j < imgh; j+=editor.value.tileh+editor.value.bheight){
        //        g.drawImage(img.getImage(), di, dj, di+editor.value.tilew, dj+editor.value.tileh, i, j, i+editor.value.tilew, j+editor.value.tileh,img.getImageObserver());
        //        dj += 2;
        //        dj += editor.value.tileh;
        //    }
        //    di += 2;
        //    di += editor.value.tilew;
        //}
        g.drawImage(img.getImage(),0,0,((org.gcreator.fileclass.res.GImage)(editor.value.image.value)).transparentColor,img.getImageObserver());
        //g.setColor(Color.WHITE);
        //g.setXORMode(Color.BLACK);
        g.setColor(Color.BLUE);
        
        for (int i = editor.value.startx; i < imgw-1; i += w+bw)
            for (int j = editor.value.starty; j < imgh-1; j += h+bh) {
                g.drawRect(i, j, w, h);
            }
    }
}
