/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SnapshotForm.java
 *
 * Created on 14.10.2009, 11:46:52
 */

package vizzy.forms.panels;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.logging.Level;
import javax.swing.text.BadLocationException;
import org.apache.log4j.Logger;
import vizzy.controller.VizzyController;
import vizzy.model.SearchResult;
import vizzy.model.SettingsModel;
import vizzy.tasks.WordSearcher;

/**
 *
 * @author sergeil
 */
public class SnapshotForm extends javax.swing.JFrame {
    private static final Logger log = Logger.getLogger(SnapshotForm.class);

    private VizzyController controller;
    private SettingsModel settings;
    private WordSearcher wordSearcher;

    /** Creates new form OptionsForm */
    public SnapshotForm(VizzyController controller, SettingsModel settings) {
        this.controller = controller;
        this.settings = settings;
        this.wordSearcher = new WordSearcher(settings);
        initComponents();
        
        wordSearcher.setTextArea(jTextArea);
        
        setTitle("Log snapshot (" + Calendar.getInstance().getTime() + ")");

        try {
            this.setIconImage(settings.getAppIcon());
        } catch (Exception e) {
//            log.warn("SnapshotForm() ", e);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jTextFieldSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Log snapshot");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                onWindowClose(evt);
            }
        });

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .add(jTextFieldSearch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jTextFieldSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onWindowClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowClose
        controller.snapshotFormsClose(this);
        dispose();
    }//GEN-LAST:event_onWindowClose

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_F3) {
            if (jTextFieldSearch.getText() != null && !jTextFieldSearch.getText().equals("")) {
                SearchResult search = wordSearcher.search(jTextFieldSearch.getText(), jTextArea.getText(), wordSearcher.getLastSearchPos() + 1);
                try {
                    jTextArea.scrollRectToVisible(jTextArea.modelToView(search.offset));
                } catch (BadLocationException ex) {
                    java.util.logging.Logger.getLogger(SnapshotForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (jTextFieldSearch.getText().equals("")) {
                wordSearcher.clearSearch();
            }
        }
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    public void setWordWrap(boolean b) {
        jTextArea.setLineWrap(b);
    }

    public void init(String text) {
        jTextArea.setText(text);
        jTextArea.setFont(settings.getTraceFont());
        jTextArea.setLineWrap(settings.isWordWrap());
        jTextArea.setBackground(settings.getBgColor());
        jTextArea.setForeground(settings.getFontColor());
        setVisible(true);
    }

    @Override
    public void dispose() {
        controller = null;
        settings = null;
        wordSearcher = null;
        super.dispose();
    }


}