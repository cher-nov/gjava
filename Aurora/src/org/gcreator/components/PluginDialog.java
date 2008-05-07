/*
 * PluginDialog.java
 *
 * Created on 27 de Janeiro de 2008, 20:53
 */

package org.gcreator.components;

import java.awt.*;
import java.io.*;
import java.util.zip.*;
import javax.swing.*;
import org.gcreator.components.impl.*;
import org.gcreator.plugins.*;

/**
 *
 * @author  Luís Reis
 */
public class PluginDialog extends JDialog {
    
    /** Creates new form PluginDialog */
    public IconList list;
    public PluginDialog(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        list = new IconList();
        list.setColumnWidth(100);
        list.setColumnHeight(100);
        list.setColumns(5);
        jScrollPane1.setViewportView(list);
        for(Plugin plugin : PluginList.stdlist.plugins){
            System.out.println("Plugin: " + plugin.name);
            list.addElement(plugin.name, plugin.image, plugin);
        }
        pack();
        setMinimumSize(getSize());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Plugin Manager");
        setResizable(false);
        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Install new plugin");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Uninstall plugin");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static JFileChooser fc = new JFileChooser();
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setDialogTitle("Choose the file");
        fc.setApproveButtonText("OK");
        fc.setFileFilter(new CustomFileFilter(".jar", "Plugin archives (*.jar)"));
        fc.showOpenDialog(this);
        File f = fc.getSelectedFile();
        if(f==null)
            return;
        if(!f.exists())
            return;
        addPlugin(f);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Object o = list.getSelectedExtraContent();
        if(o==null)
            return;
        if(o instanceof Plugin){
            uninstall((Plugin) o);
            list.removeElement(list.getSelectedIndex());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void uninstall(Plugin plugin){
        System.out.println("Uninstall " + plugin.name);
        String s = "[G-Creator Plugin List]\n";
        for(Plugin plug : PluginList.stdlist.plugins){
            if(plug==plugin)
                continue;
            s += "[~Plugin~]\n";
            for(String author : plug.authors)
                s += "Author=" + author + "\n";
            s += "Core=" + plug.value.getClass().getName() + "\n";
            s += "License=" + plug.licenseLocation + "\n";
            s += "Name=" + plug.name + "\n";
            //s += "Version=" + plug.version + "\n";
            s += "Image=" + plug.img_loc + "\n";
            for(String file : plug.files)
                s += "File=" + file + "\n";
        }
        File f = new File("./settings/pluglist");
        FileOutputStream stream = null;
        try{
            if(f.exists()){
                f.delete();
            }
            stream = new FileOutputStream(f);
            BufferedOutputStream bstream = new BufferedOutputStream(stream);
            bstream.write(s.getBytes());
            bstream.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void addPlugin(File f){
        FileInputStream istream = null;
        ZipInputStream in = null;
        File f2 = new File("./settings/pluglist");
        FileOutputStream f2stream = null;
        String files = "";
        try{
            if(!f2.exists()){
                f2.createNewFile();
                f2stream = new FileOutputStream(f2);
                BufferedOutputStream s = new BufferedOutputStream(f2stream);
                s.write("[G-Creator Plugin List]\n".getBytes());
                s.close();
                f2stream = null;
            }
        }
        catch(Exception e){
            
        }
        try{
            f2stream = new FileOutputStream(f2, true);
            istream = new FileInputStream(f);
            in = new ZipInputStream(istream);
        }
        catch(Exception e){
            return;
        }
        ZipEntry entry;
        try{
            while((entry = in.getNextEntry())!=null){
                if(entry.getName().equals("PLUGIN")){
                    int len;
                    while ((len = in.read()) != -1) {
                        f2stream.write(len);
                    }
                    f2stream.write('\n');
                }
                else{
                    if(entry.isDirectory()){
                        (new File("./plugins/"+entry.getName())).mkdirs();
                    }
                    else{
                        File f3 = new File("./plugins/"+entry.getName());
                        FileOutputStream ost = new FileOutputStream(f3);
                        int len;
                        while ((len = in.read()) != -1) {
                            ost.write(len);
                        }
                        ost.close();
                        files += "File=" + entry.getName() + "\n";
                    }
                }
            }
            f2stream.write(files.getBytes());
            f2stream.close();
        }
        catch(Exception e){
            
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
