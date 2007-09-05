/*
 * GameProjects.java
 *
 * Created on 4 de Setembro de 2007, 23:32
 */

package components;

import managers.*;

/**
 *
 * @author  Luís
 */
public class PackageProjects extends javax.swing.JPanel {
    
    /** Creates new form GameProjects */
    
    private ProjectTypes ptypes;
    public int tnum;
    
    public PackageProjects(ProjectTypes ptypes) {
        this.ptypes = ptypes;
        initComponents();
        jButton1.setText(LangSupporter.activeLang.getEntry(59));
        jButton2.setText(LangSupporter.activeLang.getEntry(61));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/toolbar/build.png"))); // NOI18N
        jButton1.setText("Empty Package");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/toolbar/build.png"))); // NOI18N
        jButton2.setText("Organized Package");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setButton(1);
        ptypes.selTab(tnum);
        String str = jButton1.getText()
                .replaceAll("\\s", "_")
                .replaceAll("[\\.:?^(){}]","")
                .replaceAll("(\\[|\\])", "")
                + "1";
        if(str.charAt(0)=='1')
            str = "p1";
        ptypes.npro.jTextField1.setText(str);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setButton(2);
        ptypes.selTab(tnum);
        String str = jButton2.getText()
                .replaceAll("\\s", "_")
                .replaceAll("[\\.:?^(){}]","")
                .replaceAll("(\\[|\\])", "")
                + "1";
        if(str.charAt(0)=='1')
            str = "p1";
        ptypes.npro.jTextField1.setText(str);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    public void setButton(int sel){
        if(sel==1)
            jButton1.setSelected(true);
        else
            jButton1.setSelected(false);
        if(sel==2)
            jButton2.setSelected(true);
        else
            jButton2.setSelected(false);
    }
    
    public void turnOff(){
        setButton(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
    
}
