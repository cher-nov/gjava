/*
 * CommentPanel.java
 *
 * Created on 26 de Setembro de 2007, 16:59
 */

package org.gcreator.actions.mainactions;

import org.gcreator.components.impl.*;
import org.gcreator.components.*;

import publicdomain.*;

/**
 *
 * @author  Luís
 */
public class EGMLPanel extends javax.swing.JPanel {
    
    /** Creates new form CommentPanel */
    SyntaxHighlighter text;
    public EGMLPanel() {
        initComponents();
        Scanner scanner = new GCLScanner();
        text = new SyntaxHighlighter(100, 100, scanner);
        text.setText("//some EGML code");
        this.add(text);
        /*org.jdesktop.layout.GroupLayout jPanelLayout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(text, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, text, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );*/
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 133, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 132, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
