/*
 * ActorEditor2.java
 *
 * Created on 12 September 2007, 22:43
 */
package org.gcreator.editors;

import org.gcreator.components.impl.EventCellRenderer;
import org.gcreator.components.popupmenus.EventSelect;
import org.gcreator.components.popupmenus.PopupListener;
import org.gcreator.components.popupmenus.EventListModel;
import org.gcreator.components.impl.ActionListCellRenderer;
import org.gcreator.components.impl.ActionsCellRenderer;
import org.gcreator.components.popupmenus.ActionPopupMenu;
import org.gcreator.components.popupmenus.ResourceMenu;
import org.gcreator.components.popupmenus.EventPopupMenu;
import org.gcreator.components.impl.EventSelectListener;
import org.gcreator.actions.ActionPattern;
import org.gcreator.components.popupmenus.*;
import org.gcreator.components.TabPanel;
import org.gcreator.components.impl.*;
import org.gcreator.core.gcreator;
import org.gcreator.fileclass.Project;
import org.gcreator.fileclass.res.Actor;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.gcreator.exceptions.*;
import org.gcreator.events.*;
import org.gcreator.actions.*;
import org.gcreator.managers.LangSupporter;

/**
 *
 * @author  Ali1
 */
public class ActorEditor extends TabPanel {

    public org.gcreator.fileclass.File file = null;
    public boolean changed = false;
    public static Vector<ActionCategory> actionCats;
    public EventListModel elist;
    Actor actor;
    static {
        jComboBox1 = new javax.swing.JComboBox();
        actionCats = new Vector<ActionCategory>();
    }
    
    DefaultComboBoxModel actmodel = new DefaultComboBoxModel();
    static {
        ActionCategory main = new ActionCategory(), draw2D = new ActionCategory();
        main.add(new org.gcreator.actions.mainactions.StartOfABlock());
        main.add(new org.gcreator.actions.mainactions.EndOfABlock());
        main.add(new org.gcreator.actions.mainactions.Comment());
        main.add(new org.gcreator.actions.mainactions.ExecuteCode());
        main.name = "Main";
        draw2D.add(new org.gcreator.actions.mainactions.AddImageToSprite());
        draw2D.name = "Draw 2D";
        actionCats.add(main);
        actionCats.add(draw2D);
        
    }
    ResourceMenu spriteres, extendres, maskres;

    public void spriteChanged() {
        ((Actor) file.value).sprite = (org.gcreator.fileclass.File) spriteres.getObjectWithName(spriteres.label.getText()).object;
    }

    /** Creates new form ActorEditor2 */
    public ActorEditor(org.gcreator.fileclass.File file, Project project) throws WrongResourceException {
        this.project = project;
        if (file.value == null) {
            this.actor = new Actor(file.name);
            actor.readXml(file.xml);
            file.value = actor;
            actor.events = new Vector<org.gcreator.events.Event>();
        } else if (file.value instanceof Actor) {
            this.actor = (Actor) file.value;
        } else {
            throw new WrongResourceException();
        }
        this.file = file;
        elist = new EventListModel(file);
        initComponents();
        jList1.addMouseListener(new PopupListener(jList1, new EventPopupMenu(this)));
        jList2.addMouseListener(new PopupListener(jList2, new ActionPopupMenu(this)));
        jComboBox2.setModel(new DefaultComboBoxModel(actionCats));
        jComboBox2.setRenderer(new ActionListCellRenderer());
        try {
            jTextField1.setText(file.name);
        } catch (NullPointerException e) {
        }

        jList1.setCellRenderer(new EventCellRenderer());
        jComboBox1.setModel(new DefaultComboBoxModel(actionCats.get(0).patterns));
        jComboBox1.setRenderer(new ActionListCellRenderer());
        jList2.setCellRenderer(new ActionsCellRenderer());
        jList2.setModel(actmodel);

        //setup resource menu's
        jPanel13.setLayout(new FlowLayout());
        jPanel13.add(spriteres = new ResourceMenu("sprite", "<no sprite>", true, project));
        if (actor.sprite != null) {
            spriteres.label.setText(actor.sprite.name);
        }
        spriteres.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        spriteChanged();
                    }
                });
        jPanel11.setLayout(new FlowLayout());
        jPanel11.add(extendres = new ResourceMenu("actor", "<no actor>", true, project));
        jPanel12.setLayout(new FlowLayout());
        jPanel12.add(maskres = new ResourceMenu("sprite", "<no mask>", true, project));

        //load variables
        Load();

        jList1.addMouseListener(new PopupListener(jList1, new EventPopupMenu(this)));
        jList2.addMouseListener(new PopupListener(jList2, new ActionPopupMenu(this)));
    }

    public boolean wasModified() {
        return changed;
    }

    public boolean Load() {
        jCheckBox1.setSelected(actor.visible);
        jCheckBox2.setSelected(actor.solid);
        jCheckBox3.setSelected(actor.persistant);
        return true;
    }

    public boolean Save() {

        actor.visible = jCheckBox1.isSelected();
        actor.solid = jCheckBox2.isSelected();
        actor.persistant = jCheckBox3.isSelected();
        file.value = actor;

        return true;
    }

    public static void setupActions() {
        //JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        //addAction(p,"test");
        //addAction(p,"test2");
        //addActionCat(p,"Move");
        //p = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        //addAction(p,"OtherCat");
        //addAction(p,"2");
        //addActionCat(p,"Main");
        //addActionCat(p,"Control");
        //addActionCat(p,"Draw");

        jComboBox1.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        JComboBox cb = (JComboBox) e.getSource();
                        String name = (String) cb.getSelectedItem();

                        for (Enumeration en = actionCats.elements(); en.hasMoreElements();) {
                            ActionCategory a = ((ActionCategory) en.nextElement());
                            if (a.name.equals(name)) {
                            //jScrollPane3.setViewportView(a.j); 
                            }

                        }


                    }
                });
    }

    /*public static void addAction(JPanel p, String name) {
        JLabel b = new JLabel(name);

        b.setHorizontalAlignment(JLabel.LEFT);
        b.setVerticalAlignment(JLabel.TOP);
        b.setPreferredSize(new Dimension(32, 32));
        p.add(b);
    }*/

    /*public static void addActionCat(JPanel p, String name)
    {
        jComboBox1.addItem(name);
        actionCats.add(new actionCat(name,p));
    }*/
    /*static class actionCat {

        String name;
        JPanel j;

        actionCat(String name, JPanel j) {
            this.name = name;
            this.j = j;
        }
    }*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();

        jSplitPane1.setDividerLocation(140);
        jSplitPane1.setDividerSize(3);

        jSplitPane2.setDividerLocation(260);
        jSplitPane2.setDividerSize(3);

        jSplitPane3.setDividerSize(3);

        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.setDragEnabled(true);
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jSplitPane3.setRightComponent(jScrollPane2);

        jList1.setModel(elist);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton5.setText("Add event");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton5MouseReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jButton5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton5))
        );

        jSplitPane3.setLeftComponent(jPanel6);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(jPanel4);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jComboBox1, 0, 0, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1))
            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
            .add(jComboBox2, 0, 131, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel5);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel1);

        jLabel1.setText(LangSupporter.activeLang.getEntry(136));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(LangSupporter.activeLang.getEntry(137)));

        jButton2.setText(LangSupporter.activeLang.getEntry(138));

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 113, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 23, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
            .add(jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2))
        );

        jCheckBox1.setText(LangSupporter.activeLang.getEntry(139));
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText(LangSupporter.activeLang.getEntry(140));
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText(LangSupporter.activeLang.getEntry(141));
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox3MouseClicked(evt);
            }
        });
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jLabel2.setText(LangSupporter.activeLang.getEntry(142));

        jTextField3.setText("0");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel3.setText("Extends:");

        jLabel4.setText("Mask:");

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 72, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 15, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 87, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 15, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jCheckBox2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .add(jCheckBox1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                            .add(jCheckBox3)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBox1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBox2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBox3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(jPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void event(int type) {

        if (type == EventSelectListener.CREATE) {
            elist.add(new CreateEvent());
        } else if (type == EventSelectListener.DESTROY) {
            elist.add(new DestroyEvent());
        } else if (type == EventSelectListener.DRAW) {
            elist.add(new DrawEvent());
        } else if (type == EventSelectListener.BEGINSTEP) {
            elist.add(new BeginStepEvent());
        } else if (type == EventSelectListener.STEP) {
            elist.add(new StepEvent());
        } else if (type == EventSelectListener.ENDSTEP) {
            elist.add(new EndStepEvent());
        } else if (type >= EventSelectListener.MOUSELEFTCLICKED && type <= EventSelectListener.MOUSEOUT) {
            elist.add(new org.gcreator.events.MouseEvent(type));
        }
        jScrollPane1.updateUI();
        jList1.updateUI();
        updateActionList();
    }

    public void updateActionList() {
        actmodel.removeAllElements();
        if (jList1.getSelectedValue() == null) {
            return;
        }
        Vector<org.gcreator.actions.Action> actions = ((org.gcreator.events.Event) jList1.getSelectedValue()).actions;
        for (int i = 0; i < actions.size(); i++) {
            actmodel.addElement(actions.get(i));
        }
    }

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        EventSelectListener listener = new EventSelectListener() {

                    public void eventSelected(int type) {
                        event(type);
                    }
                };
        EventSelect selector = new EventSelect(gcreator.window, true, evt.getX(), evt.getY(), listener); //java 6 uses OnScreen()
        changed = true;
    }//GEN-LAST:event_jButton5MouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        file.name = jTextField1.getText();
        org.gcreator.core.Aurwindow.workspace.updateUI();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        file.name = jTextField1.getText();
        org.gcreator.core.Aurwindow.workspace.updateUI();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
    // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        changed = true;
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        changed = true;
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jCheckBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseClicked
        changed = true;
    }//GEN-LAST:event_jCheckBox3MouseClicked

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        changed = true;
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseReleased
        jButton5MouseClicked(evt);
    }//GEN-LAST:event_jButton5MouseReleased

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        updateActionList();
        jScrollPane3.setViewportView(null);
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jList1.getSelectedValue() == null) {
            return;
        }
        if (jComboBox1.getSelectedItem() == null) {
            return;
        }
        org.gcreator.actions.Action a = new org.gcreator.actions.Action(this, (org.gcreator.actions.ActionPattern) jComboBox1.getSelectedItem());
        ((org.gcreator.events.Event) jList1.getSelectedValue()).actions.add(a);
        updateActionList();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        if (jList2.getSelectedValue() == null) {
            return;
        }
        jScrollPane3.setViewportView(((org.gcreator.actions.Action) jList2.getSelectedValue()).getPanel());
    }//GEN-LAST:event_jList2ValueChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        actor.visible = jCheckBox1.isSelected();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        actor.solid = jCheckBox2.isSelected();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        actor.persistant = jCheckBox3.isSelected();
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        jComboBox1.setModel(new DefaultComboBoxModel(((ActionCategory) jComboBox2.getSelectedItem()).patterns));
        jComboBox1.updateUI();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    public static javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JList jList1;
    public javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    /*class Value
    {
    //value is the event name
        public String value,img;
        public ImageIcon image;
        public DefaultListModel actions = new DefaultListModel();
        public Value(String value, String img,DefaultListModel actions)
        {
            this.img = img;
            this.image = new ImageIcon(img);
            this.value = value;
            
        }
        
    }*/
    /*class SimpleCellRenderer extends JLabel implements ListCellRenderer
    {
        public SimpleCellRenderer()
        {
            setOpaque(true);
        }
        
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            Value val = (Value)value;
            setText(val.value);
            setIcon(val.image);
            
            setBackground(isSelected ? Color.black : (index & 1) == 0 ? Color.white : Color.LIGHT_GRAY);
            setForeground(isSelected ? Color.white : Color.black);
            return this;
        }
        
        
    }*/
}
    