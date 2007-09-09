/*
 * RoomEditor.java
 *
 * Created on 06 September 2007, 02:23
 */

package editors;

import com.l2fprod.common.demo.OutlookBarMain;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;
import components.TabPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;

/**
 *
 * @author  Ali1
 */
public class SceneEditor extends TabPanel {
    
    /** Creates new form RoomEditor */
    public SceneEditor() {
        initComponents();
       // jPanel1.add(makeOutlookPanel(SwingConstants.CENTER));
        this.updateUI();
        jPanel1.setVisible(true);
        jPanel1.updateUI();
    }
    
    JPanel makeOutlookPanel(int alignment) {
        
    JOutlookBar outlook = new JOutlookBar();
    outlook.setTabPlacement(JTabbedPane.LEFT);
    outlook.setVisible(true);
    addTab(outlook, "Actors");
    addTab(outlook, "Tiles");

    // show it is possible to add any component to the bar
    JTree tree = new JTree();
    outlook.addTab("A JTree", outlook.makeScrollPane(tree));

    outlook.addTab("Disabled", new JButton());
    outlook.setEnabledAt(3, false);
    outlook.setAllTabsAlignment(alignment);
    
    JPanel panel = new JPanel();//new PercentLayout(PercentLayout.HORIZONTAL, 3)
    panel.add(outlook); //, "100"
    return panel;
  }

  void addTab(JOutlookBar tabs, String title) {
      
    JPanel panel = new JPanel();
    panel.setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
    panel.setOpaque(false);

    String[] buttons = new String[] {"Inbox", "icons/outlook-inbox.gif",
      "Outbox", "icons/outlook-outbox.gif", "Drafts", "icons/outlook-inbox.gif",
      "Templates", "icons/outlook-inbox.gif", "Deleted Items",
      "icons/outlook-trash.gif",};

    for (int i = 0, c = buttons.length; i < c; i += 2) {
      JButton button = new JButton(buttons[i]);
      try {
        button.setUI((ButtonUI)Class.forName(
          (String)UIManager.get("OutlookButtonUI")).newInstance());
      } catch (Exception e) {
        e.printStackTrace();
      }
      button.setIcon(new ImageIcon(OutlookBarMain.class
        .getResource(buttons[i + 1])));
      panel.add(button);
    }

    JScrollPane scroll = tabs.makeScrollPane(panel);
    tabs.addTab("", scroll);

    // this to test the UI gets notified of changes
    int index = tabs.indexOfComponent(scroll);
    tabs.setTitleAt(index, title);
    tabs.setToolTipTextAt(index, title + " Tooltip");
  }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
JPanel jp = makeOutlookPanel(SwingConstants.CENTER);
        jToolBar1.setRollover(true);

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                
				.addComponent(jp)
                )
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                
				.addComponent(jp)
                )
        );

        jSplitPane1.setLeftComponent(jp);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );
    }
    
    
   
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
   
    
}
