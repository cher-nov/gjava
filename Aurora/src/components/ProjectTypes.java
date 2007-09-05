/*
 * ProjectTypes.java
 *
 * Created on 4 de Setembro de 2007, 23:59
 */

package components;

import managers.*;
import javax.swing.*;

/**
 *
 * @author  Luís
 */
public class ProjectTypes extends JPanel {
    
    /** Creates new form ProjectTypes */
    
    public GameProjects gmpro;
    public PackageProjects pkgpro;
    
    public ProjectTypes() {
        initComponents();
        gmpro = new GameProjects();
        pkgpro = new PackageProjects();
        jScrollPane1.setViewportView(gmpro);
        jScrollPane2.setViewportView(pkgpro);
    }
    
    public JTabbedPane getTabbedPane(){
        return jTabbedPane1;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();

        jTabbedPane1.addTab(LangSupporter.activeLang.getEntry(56), jScrollPane1);
        jTabbedPane1.addTab(LangSupporter.activeLang.getEntry(57), jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
}
