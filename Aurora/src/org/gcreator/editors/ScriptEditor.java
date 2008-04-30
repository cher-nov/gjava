/*
 * ScriptEditor.java
 *
 * Created on 06 January 2008, 03:45
 */

package org.gcreator.editors;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.SwingUtilities;
import org.gcreator.components.scanning.GScriptScanner;
import org.gcreator.components.scanning.Scanner;
import org.gcreator.components.SyntaxHighlighter;
import org.jdesktop.layout.GroupLayout;
import org.gcreator.components.TabPanel;
import org.gcreator.components.impl.*;
import org.gcreator.fileclass.Project;
import org.gcreator.fileclass.res.Classes;

import publicdomain.*;

/**
 *
 * @author  Ali
 */
public class ScriptEditor extends TabPanel {
    
    public boolean changed = true;
    SyntaxHighlighter g;
    
    public boolean Save() {
        file.value = g.getText();
        if (file.value == null) {
            file.value = "";
        }
        return true;
    }
    
    /** Creates new form ScriptEditor */
    public ScriptEditor(org.gcreator.fileclass.GFile file,Project project) {
        initComponents();
        
        if(file.value==null)
            file.value = new Classes("// Press CTRL + SPACE for code completion \n show_message(\"test\")\n");
        Scanner scanner = new GScriptScanner();
        g = new SyntaxHighlighter(100, 100, scanner, project);
        g.setText(((Classes)file.value).toString());
        
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        //jPanel1.setLayout(jPanel1Layout);
        /*jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(g, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, g, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
        );*/
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, jScrollPane1);
        jScrollPane1.setViewportView(g);
        
        g.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                changed = true;
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                g.requestFocusInWindow();
            }
        });
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(244);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setLastDividerLocation(354);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setToolTipText("GCL function list");
        jList1.setMaximumSize(new java.awt.Dimension(33, 100));
        jList1.setMinimumSize(new java.awt.Dimension(33, 10));
        jList1.setPreferredSize(new java.awt.Dimension(33, 50));
        jList1.setValueIsAdjusting(true);
        jList1.setVisibleRowCount(3);
        jScrollPane1.setViewportView(jList1);

        jSplitPane1.setRightComponent(jScrollPane1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 398, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 243, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables

//    private void setLayout(GroupLayout layout) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    
}
