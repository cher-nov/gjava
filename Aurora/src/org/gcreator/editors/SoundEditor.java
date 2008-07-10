/*
 * Copyright (C) 2007-2008 Luís Reis <luiscubal@gmail.com>
 * Copyright (C) 2007-2008 TGMG <thegamemakerguru@hotmail.com>
 * Copyright (c) 2008 BobSerge or Bobistaken <serge_1994@hotmail.com>
 * 
 * This file is part of G-Creator.
 * G-Creator is free software and comes with ABSOLUTELY NO WARRANTY.
 * See LICENSE for more details.
 */
package org.gcreator.editors;

import com.jmex.audio.AudioSystem;
import com.jmex.audio.AudioTrack;
import java.applet.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gcreator.components.*;
import org.gcreator.core.*;
import org.gcreator.fileclass.Project;
import org.gcreator.managers.*;
import org.gcreator.components.impl.*;
//import org.newdawn.slick.Sound;
import org.gcreator.fileclass.res.Sound;

/**
 *
 * @author  TGMG
 */
public class SoundEditor extends TabPanel {
    static int number = 0;
    public AudioSystem audio;
    private AudioTrack targetSound;
    private File soundFile;
    private AudioClip newAudioClip;
    /** Creates new form SoundEditor
     * @param file
     * @param project 
     */
    public SoundEditor(org.gcreator.fileclass.GFile file,Project project) {
        if(!(file.value instanceof Sound))
            file.value = new Sound();
        this.project = project;
        this.file = file;
        initComponents();
        jTextField1.setText(file.name);
        try {
            audio = AudioSystem.getSystem();
        } catch (UnsatisfiedLinkError exc) {
            System.err.println("Cannot get AudioSystem: "+exc);
        }
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        jFileChooser1.setCurrentDirectory(new java.io.File("C:\\Program Files\\NetBeans 6.0"));
        jFileChooser1.setDialogTitle("Choose a sound");

        jLabel1.setText(LangSupporter.activeLang.getEntry(166));

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jButton1.setText(LangSupporter.activeLang.getEntry(170));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText(LangSupporter.activeLang.getEntry(167));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText(LangSupporter.activeLang.getEntry(169));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setText(LangSupporter.activeLang.getEntry(168));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Stream");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("<html>If it is a large file (over 1mb) then make sure \"Stream\" is on, otherwise it will load the whole file into memory when playing. Only take \"Stream\" off if you are having trouble playing very small files.</html>");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jButton3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton5))
                    .add(layout.createSequentialGroup()
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jCheckBox1))
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 407, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton3)
                    .add(jButton2)
                    .add(jButton5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jCheckBox1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    org.newdawn.slick.Music fx;// = new Sound("res/boom.wav");
//fx.play();
    public SoundPlayer p = null;
    public AudioClip clip = null;
    
    //open the file
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // try {
            
            jFileChooser1.showDialog(this, null);
            
            this.soundFile = jFileChooser1.getSelectedFile();
            System.out.println("Name:"+soundFile.getName());
            System.out.println("path:"+soundFile.getPath());
            /*if(f.getName().endsWith(".wav"))
                ((Sound)file.value).extension = ".wav";
            else if (f.getName().endsWith(".ogg"))
                ((Sound)file.value).extension = ".ogg";
            else
                return;
            InputStream is = new FileInputStream(f);

            // Get the size of the file
            long length = f.length();

            if (length > Integer.MAX_VALUE) {
                // File is too large
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            
                while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                    offset += numRead;
                }
            
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                System.out.println("Could not completely read file " + f.getName());
            }

            // Close the input stream and return bytes
            is.close();
            ((Sound)file.value).sound = bytes;
           
            
            clip = new AppletAudioClip(bytes);
            p = new SoundPlayer((byte[]) file.value);
            p = new SoundPlayer(f.getAbsolutePath());
        } catch (Exception ex) {
            System.out.println("Exception in load sound");
            Logger.getLogger(SoundEditor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    //     AudioClip newAudioClip;
       try {
            if (soundFile == null) {
                return;
            }
            newAudioClip = Applet.newAudioClip(soundFile.toURI().toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(SoundEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
         newAudioClip.play();
         
        /* try {
            audio.cleanup();
//      Delete old file
        File ff = new File("./Sound/");
        if(!ff.exists())
            ff.mkdir();
        ff = new File("./Sound/play"+number+".ogg");
        if (ff.exists())
           ff.delete();
        ff = new File("./Sound/play"+number+".wav");
        if (ff.exists())
           ff.delete();
          number++;  
          
            FileOutputStream f = new FileOutputStream("./Sound/play"+number+((Sound)file.value).extension); //+ ((Sound) file.value).extension);

//GEN-LAST:event_jButton3ActionPerformed
       
            /* try {
            audio.cleanup();
            //      Delete old file
            File ff = new File("./Sound/");
            if(!ff.exists())
            ff.mkdir();
            ff = new File("./Sound/play"+number+".ogg");
            if (ff.exists())
            ff.delete();
            ff = new File("./Sound/play"+number+".wav");
            if (ff.exists())
            ff.delete();
            number++;
            FileOutputStream f = new FileOutputStream("./Sound/play"+number+((Sound)file.value).extension); //+ ((Sound) file.value).extension);
            byte[] b = ((Sound)file.value).sound;
            f = new FileOutputStream("./Sound/play.wav");// + ((Sound) file.value).extension);
            f.write(b);
            f.close();
            ff= new File("Sound/play"+number+((Sound)file.value).extension );
            if (this.jCheckBox1.isSelected())
            targetSound = audio.createAudioTrack(ff.toURI().toURL() , true);
            else
            targetSound = audio.createAudioTrack(ff.toURI().toURL() , false);
            targetSound.setVolume(1.0f);
            targetSound.play();
            fx = new org.newdawn.slick.Music("/Sound/play"+number+((Sound)file.value).extension,true);// + ((Sound) file.value).extension);
            fx.play();
            } catch (Exception ex) {
            System.out.println(""+ex);
            Logger.getLogger(SoundEditor.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
                byte[] b = ((Sound)file.value).sound;
                f = new FileOutputStream("./Sound/play.wav");// + ((Sound) file.value).extension);
                f.write(b);
                f.close();
                
                ff= new File("Sound/play"+number+((Sound)file.value).extension );
               if (this.jCheckBox1.isSelected())
             targetSound = audio.createAudioTrack(ff.toURI().toURL() , true); 
                else
                   targetSound = audio.createAudioTrack(ff.toURI().toURL() , false);  
             
             targetSound.setVolume(1.0f);
             targetSound.play();
                 fx = new org.newdawn.slick.Music("/Sound/play"+number+((Sound)file.value).extension,true);// + ((Sound) file.value).extension);
            fx.play();
            
            } catch (Exception ex) {
                System.out.println(""+ex);
                Logger.getLogger(SoundEditor.class.getName()).log(Level.SEVERE, null, ex);
            } */
        
                
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//stop sound
        if (targetSound != null) {
            targetSound.setLooping(false);
            targetSound.stop();
        }
       fx.stop(); 
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//loop sound
        fx.loop();
        if (targetSound != null) {
            targetSound.setLooping(true);
            targetSound.play();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        file.name = jTextField1.getText();
        gcreator.panel.workspace.updateUI();
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton5;
    public javax.swing.JCheckBox jCheckBox1;
    public javax.swing.JFileChooser jFileChooser1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
