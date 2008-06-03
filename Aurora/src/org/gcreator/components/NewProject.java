/*
 * NewProject.java
 *
 * Created on 4 de Setembro de 2007, 23:55
 * Created on  Setember 4, 2007, 11:55 PM
 */


/* I had to made this a normal class because NetBeans 
 * designer didn't allow me to do what I wanted to.
 */
package org.gcreator.components;

import java.awt.event.*;
import java.util.*;
//import javax.swing.*;

import javax.swing.*;
import org.gcreator.core.*;
import org.gcreator.fileclass.*;
import org.gcreator.macro.*;
import org.gcreator.managers.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

/**
 *
 * @author  Luís
 */
public class NewProject extends TabPanel {

    /** Creates new form NewProject */
    public Vector ProjectButtons = new Vector(); //

    public NewProject() {
        initComponents();
        //jLabel1.setText(LangSupporter.activeLang.getEntry(54));
        //jLabel2.setText(LangSupporter.activeLang.getEntry(60));
        //jLabel3.setVisible(false);
    }

    public void uncheckAll(){
        java.lang.Object[] btns = ProjectButtons.toArray();
        java.lang.Object btn;
        for(int i = 0; i < btns.length; i++){
            btn = btns[i];
            if(btn!=null && btn instanceof AbstractButton)
                ((JButton) btn).setSelected(false);
        }
    }
    
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton1 = new JButton();
        jTextField1 = new JTextField();
        ptypes = new ProjectTypes(this);
        jLabel3 = new JLabel();

        jLabel1.setText(LangSupporter.activeLang.getEntry(54));

        jLabel2.setText(LangSupporter.activeLang.getEntry(60));

        jLabel3.setVisible(false);

        jButton1.setText(LangSupporter.activeLang.getEntry(94));
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                createProject();
            }
        });
        jTextField1.setText("");

        jLabel3.setText("jLabel3");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.LEADING)
                .add(
                    GroupLayout.TRAILING,
                    layout.createSequentialGroup()
                        .addContainerGap()
                        .add(
                            layout.createParallelGroup(GroupLayout.TRAILING)
                            .add(
                                ptypes,
                                //GroupLayout.LEADING,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                                
                            .add(
                                jLabel1
                                //GroupLayout.LEADING
                                )
                            .add(
                                GroupLayout.LEADING,
                                layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(LayoutStyle.RELATED)
                                    .add(
                                        jTextField1,
                                        GroupLayout.DEFAULT_SIZE,
                                        263,
                                        Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.RELATED)
                                    .add(jButton1)
                            )
                            .add(jLabel3)
                       )
                       .addContainerGap()
                 )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.LEADING)
                    .add(
                        layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel1)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(
                                ptypes,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE
                            )
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(jLabel3)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(
                            layout.createParallelGroup(GroupLayout.BASELINE)
                                .add(jLabel2)
                                .add(jButton1)
                                .add(
                                    jTextField1,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                            )
                        .addContainerGap()
                    )
        );
    } // </editor-fold>
    
    public int getProjectType(){
        return ptypes.getProjectType();
    }
    
    public void createProject(){
            org.gcreator.core.utilities.addStringMessage("create new project");
            Project project = null;
            int type = getProjectType();
            if (type == ProjectTypes.EMPTY_GAME){
                project = GameProject.balance(ProjectTypes.EMPTY_GAME);
                project.name = jTextField1.getText();
                project.location = "";
                GPanel.setMainProject(project);
                Macro.macroAction(
                        "project = org.gcreator.fileclass.GameProject.balance("
                        + "org.gcreator.components.ProjectTypes.EMPTY_GAME);\n"
                        + "project.name = \"" + project.name + "\";\n"
                        + "project.location = \"\";\n"
                        + "org.gcreator.core.GPanel.setMainProject(project);");
            }
            else if (type == ProjectTypes.RPG_GAME){
                project = GameProject.balance(type);
                project.name = jTextField1.getText();
                project.location = "";
                GPanel.setMainProject(project);
                Macro.macroAction(
                        "project = org.gcreator.fileclass.GameProject.balance("
                        + type + ");\n"
                        + "project.name = \"" + project.name + "\";\n"
                        + "project.location = \"\";\n"
                        + "org.gcreator.core.GPanel.setMainProject(project);");
            }
            else if (type == ProjectTypes.PLATFORM_GAME){
                project = GameProject.balance(type);
                project.name = jTextField1.getText();
                project.location = "";
                GPanel.setMainProject(project);
                Macro.macroAction(
                        "project = org.gcreator.fileclass.GameProject.balance("
                        + type + ");\n"
                        + "project.name = \"" + project.name + "\";\n"
                        + "project.location = \"\";\n"
                        + "org.gcreator.core.GPanel.setMainProject(project);");
            }
            else if (type == ProjectTypes.AIR_BATTLE){
                project = GameProject.balance(type);
                project.name = jTextField1.getText();
                project.location = "";
                GPanel.setMainProject(project);
                Macro.macroAction(
                        "project = org.gcreator.fileclass.GameProject.balance("
                        + type + ");\n"
                        + "project.name = \"" + project.name + "\";\n"
                        + "project.location = \"\";\n"
                        + "org.gcreator.core.GPanel.setMainProject(project);");
            }
            else if (type == 4){
                project = ModuleProject.balance();
                project.name = jTextField1.getText();
                project.location = "";
                GPanel.setMainProject(project);
                Macro.macroAction(
                        "project = org.gcreator.fileclass.ModuleProject.balance();\n"
                        + "project.name = \"" + project.name + "\";\n"
                        + "project.location = \"\";\n"
                        + "org.gcreator.core.GPanel.setMainProject(project);");
            }
            /*else if(type == 1){
                project = new PackageProject(jTextField1.getText(), "");
                Aurwindow.setMainProject(project);
            }
            else if(type == 2){
                project = new PackageProject(jTextField1.getText(), "");
                Aurwindow.setMainProject(project);
            }
            else if(type == 3){
                project = new ExtensionProject(jTextField1.getText(), "");
                StaticGroup src;
                Group grp;
                project.add(grp = src = new StaticGroup(project, "Source"));
                grp.editable = false;
                src.add(grp = new JavaGroup(project, "Java"));
                grp.editable = false;
                src.add(grp = new CppGroup(project, "C++"));
                grp.editable = false;
                src.add(grp = new EGMLGroup(project, "EGML"));
                grp.editable = false;
                StaticGroup ref;
                project.add(grp = ref = new StaticGroup(project, "References"));
                grp.editable = false;
                ref.add(grp = new JavaRefGroup(project, "Java"));
                grp.editable = false;
                ref.add(grp = new CppRefGroup(project, "C++"));
                grp.editable = false;
                Aurwindow.setMainProject(project);
            }*/
            if(type!=-1){
                if(project!=null){
                    Macro.macroAction(
                            "org.gcreator.managers.ProjectTree.importFolderToTree(project, "
                            + "org.gcreator.core.gcreator.panel);\n");
                    ProjectTree.importFolderToTree(project, gcreator.panel.top);
                }
                Macro.macroAction("try{ org.gcreator.core.gcreator.panel.workspace.updateUI(); }\n");
                Macro.macroAction("catch(err){}\n");
                try{
                gcreator.panel.workspace.updateUI();
                }
                catch(Exception e){}
            }
    }
    
    // Variables declaration
    public JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    public ProjectTypes ptypes;
    public JTextField jTextField1;
    // End of variables declaration
}
