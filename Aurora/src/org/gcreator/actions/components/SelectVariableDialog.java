/*
 * SelectVariableDialog.java
 *
 * Created on August 31, 2008, 6:38 PM
 */
package org.gcreator.actions.components;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultListModel;
import org.gcreator.components.ActorFieldPane.Field;
import org.gcreator.fileclass.res.Actor;
import org.gcreator.fileclass.res.Resource;

/**
 *
 * @author  Serge Humphrey
 */
public final class SelectVariableDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 2;
    protected VariableNameEditor editor;
    protected Resource resource;
    private VariableNameTextField field;
    private Vector<Thing> list;

    /** Creates new form SelectVariableDialog
     * @param fnames The function fnames for the list.
     * @param owner The Dialog's owner
     * @param title The title for the dialog.
     * @param e The VariableNameEditor
     * @param f The VariableNameTextField.
     */
    SelectVariableDialog(Frame owner, String title,
            VariableNameEditor e, VariableNameTextField field, Resource r) {
        super(owner, true);

        this.editor = e;
        this.field = field;
        this.resource = r;
        initComponents();
        this.setMinimumSize(new Dimension(getSize()));
        //TODO: Retrieve These vars from something else
        list = new Vector<SelectVariableDialog.Thing>();
        String[] builtinVars = new String[]{"x", "y", "sprite", "TODO: more vars"};
        for (String s : builtinVars) {
            list.add(new Thing(s, "red", true));
        }
        
        if (r != null && r instanceof Actor) {
            Actor a = (Actor) r;
            for (Field f : a.fields) {
                list.add(new Thing(f.name, "blue", false));
            }
        }
        
        Object a[] = list.toArray();
        Arrays.sort(a);
        list.clear();

        for (Object o : a) {
            list.add((Thing) o);
        }

        jList1.setModel(new DefaultListModel() {

            private static final long serialVersionUID = 1;

            @Override
            public int getSize() {
                return list.size();
            }

            @Override
            public Object getElementAt(int index) {
                return list.get(index);
            }
        });
        setLocationRelativeTo(null);
        setTitle(title);
    }

    private static class Thing implements Comparable<Thing> {

        private String text;
        private String name;
        private boolean builtin;
        
        public Thing(String name, String color, boolean builtin) {
            this.name = name;
            this.text = "<html><span style=\"color: " + color + "\">" + name + "</span</html>";
            this.builtin = builtin;
        }

        @Override
        public String toString() {
            return text;
        }

        @Override
        public int compareTo(Thing o) {
            return name.compareToIgnoreCase(o.name);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select A Function");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Name:");

        jTextField1.setText(editor.getAsText());
        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        int i = 0;
        String text = jTextField1.getText();
        if (text == "" || text.matches("\\W+")) {
            return;
        }
        while (i < list.size() && list.get(i).name.compareToIgnoreCase(text) < 0) {
            i++;
        }
        jList1.setSelectedIndex(i);
        try {
            Point p = jList1.getUI().indexToLocation(jList1, i);
            p.y -= jList1.getUI().indexToLocation(jList1, 1).y;
            jScrollPane1.getViewport().setViewPosition(p);
        } catch (NullPointerException ex) {//Sometimes happens
            jList1.updateUI();
        }
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ((VariableName) editor.getValue()).name = jTextField1.getText();
        field.setText(jTextField1.getText());
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if (evt.getClickCount() >= 2) {
            jTextField1.setText(((Thing) jList1.getSelectedValue()).name);
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        Thing t = (Thing)jList1.getSelectedValue();
    }//GEN-LAST:event_jList1ValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
