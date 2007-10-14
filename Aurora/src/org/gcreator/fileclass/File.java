/*
 * File.java
 *
 * Created on 4/Set/2007, 20:12:37
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gcreator.fileclass;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.zip.*;
import javax.swing.ImageIcon;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStreamImpl;
import org.gcreator.fileclass.res.*;

/**
 *
 * @author Luís
 */
public class File extends Object {

    public Folder root;
    public String type; //If file is "a.txt", leave only "txt" here
    public java.lang.Object value;
    public ImageIcon treeimage;

    public File(Folder root, String name, String type, Object value) {
        super(name);
        this.root = root;
        this.type = type;
        this.value = value;
        root.add(this);
    }

    private File(String name, String type) {
        super(name);
        this.type = type;
    }

    @Override
    public String getObjectType() {
        return "File";
    }

    private class MyOutputStream extends ImageOutputStreamImpl {

        public ZipOutputStream out;

        public MyOutputStream(ZipOutputStream out) {
            this.out = out;
        }

        @Override
        public void write(byte[] barray, int a, int b) throws IOException {
            for (int i = a; i < b; i++) {
            }
        }
        
        @Override
        public void write(byte[] barray) throws IOException {
            for (int i = 0; i < barray.length; i++) {
                out.write(barray[i]);
            }
        }
        
        public void write(int a) throws IOException {
            out.write((byte) a);
        }
        
        public int read(byte[] barray, int a, int b) {
            return 0;
        }

        public int read(int a) {
            return 0;
        }

        public int read() {
            return 0;
        }
    }

    public void writeToBuffer(ZipOutputStream out) throws IOException {
        if (value instanceof String) {
            out.write(value.toString().getBytes());
        } else if (value instanceof ImageIcon) {
            Image img = ((ImageIcon) value).getImage();
            System.out.println(img.toString());
            MyOutputStream stream = new MyOutputStream(out);
            int iw = img.getWidth(null);
            int ih = img.getHeight(null);
            BufferedImage ii = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
            Graphics imageGraphics = ii.createGraphics();
            imageGraphics.drawImage(img, 0, 0, null);
            imageGraphics.dispose();
            ImageIO.write(ii, type, stream);
        } else if (value instanceof org.gcreator.fileclass.res.Resource) {
            out.write(((org.gcreator.fileclass.res.Resource) value).writeXml().getBytes());
        }
    }

    public static ImageIcon getScaledIcon(ImageIcon ii) {
        ImageIcon iii = new ImageIcon();
        if (ii != null) {
            Image i = ii.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
            iii.setImage(i);
            return iii;
        }
        return null;
    }

    @Override
    public Object clone() {
        File o = new File(name, type);
        if (value instanceof Resource) {
            o.value = ((Resource) value).clone();
        } else if (value instanceof ImageIcon) {
            o.value = value;
        } else {
            o.value = value;
        }
        return o;
    }
}