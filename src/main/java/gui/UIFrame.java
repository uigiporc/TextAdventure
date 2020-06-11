/*
 * Created by JFormDesigner on Fri Jun 05 21:00:41 CEST 2020
 */

package gui;

import javax.swing.event.*;
import javax.swing.plaf.*;

import engine.GameProgress;
import engine.Parser;
import engine.ResourceHandler;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

/**
 * @author unknown
 */
public class UIFrame extends JFrame{
    private UIFrame() {
        initComponents();
    }

    private void sendCommandActionPerformed(ActionEvent e) {
        Parser.parseCommand(commandTextField.getText());
        commandTextField.setText("");
    }

    private void UIFrameWindowClosing(WindowEvent e) {
        int selectedOption = JOptionPane.showConfirmDialog(null,
                ResourceBundle.getBundle("bundles.UIbundle").getString("exitMessage"),
                "",
                JOptionPane.OK_CANCEL_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        //
    }

    private void radioButtonMenuItem2ItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
            Locale.setDefault(Locale.ITALIAN);
            ResourceHandler.loadResources();
            this.initComponentText();
            if (GameProgress.getCurrentRoom() != null) {
                this.logPane.setText("");
                printString(GameProgress.getCurrentRoom().roomInformations());
            }
        }
    }

    private void radioButtonMenuItem1ItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
            Locale.setDefault(Locale.ENGLISH);
            ResourceHandler.loadResources();
            this.initComponentText();

            if (GameProgress.getCurrentRoom() != null) {
                this.logPane.setText("");
                printString(GameProgress.getCurrentRoom().roomInformations());
            }

        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Luigi

        JFrame = new JFrame();
        menuBar = new JMenuBar();
        menu1 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu2 = new JMenu();
        menu6 = new JMenu();
        radioButtonMenuItem1 = new JRadioButtonMenuItem();
        radioButtonMenuItem2 = new JRadioButtonMenuItem();
        menu3 = new JMenu();
        menuItem5 = new JMenuItem();
        sendButton = new JButton();
        commandTextField = new JTextField();
        scrollPane1 = new JScrollPane();
        logPane = new JEditorPane();

        //======== JFrame ========
        {
            JFrame.setVisible(true);
            JFrame.setResizable(false);
            JFrame.setTitle("Into the unknown");
            JFrame.setMinimumSize(new Dimension(600, 400));
            JFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            JFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    UIFrameWindowClosing(e);
                }
            });
            var JFrameContentPane = JFrame.getContentPane();

            //======== menuBar ========
            {

                //======== menu1 ========
                {


                    //---- menuItem3 ----

                    menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
                    menu1.add(menuItem3);

                    //---- menuItem4 ----

                    menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
                    menu1.add(menuItem4);
                }
                menuBar.add(menu1);

                //======== menu2 ========
                {


                    //======== menu6 ========
                    {
                        //---- radioButtonMenuItem1 ----
                        radioButtonMenuItem1.addItemListener(e -> radioButtonMenuItem1ItemStateChanged(e));
                        menu6.add(radioButtonMenuItem1);

                        //---- radioButtonMenuItem2 ----
                        radioButtonMenuItem2.addItemListener(e -> radioButtonMenuItem2ItemStateChanged(e));
                        menu6.add(radioButtonMenuItem2);
                    }
                    menu2.add(menu6);
                }
                menuBar.add(menu2);

                //======== menu3 ========
                {
                    //---- menuItem5 ----
                    menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
                    menu3.add(menuItem5);
                }
                menuBar.add(menu3);
            }
            JFrame.setJMenuBar(menuBar);

            //---- sendButton ----
            sendButton.addActionListener(e -> sendCommandActionPerformed(e));

            //---- commandTextField ----
            commandTextField.addActionListener(e -> sendCommandActionPerformed(e));

            //======== scrollPane1 ========
            {
                scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //---- logPane ----
                logPane.setEditable(false);
                logPane.setFont(new Font("Bodoni MT", Font.PLAIN, 18));
                scrollPane1.setViewportView(logPane);
            }

            GroupLayout JFrameContentPaneLayout = new GroupLayout(JFrameContentPane);
            JFrameContentPane.setLayout(JFrameContentPaneLayout);
            JFrameContentPaneLayout.setHorizontalGroup(
                JFrameContentPaneLayout.createParallelGroup()
                    .addGroup(JFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFrameContentPaneLayout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                            .addGroup(JFrameContentPaneLayout.createSequentialGroup()
                                .addComponent(commandTextField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendButton)
                                .addGap(12, 12, 12)))
                        .addContainerGap())
            );
            JFrameContentPaneLayout.setVerticalGroup(
                JFrameContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, JFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JFrameContentPaneLayout.createParallelGroup()
                            .addComponent(commandTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton))
                        .addGap(20, 20, 20))
            );
            JFrame.setSize(600, 395);
            JFrame.setLocationRelativeTo(JFrame.getOwner());
        }

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButtonMenuItem1);
        buttonGroup1.add(radioButtonMenuItem2);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        if (Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            radioButtonMenuItem1.setSelected(true);
        } else {
            radioButtonMenuItem2.setSelected(true);
        }
        //initComponentText();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Luigi
    private JFrame JFrame;
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenu menu2;
    private JMenu menu6;
    private JRadioButtonMenuItem radioButtonMenuItem1;
    private JRadioButtonMenuItem radioButtonMenuItem2;
    private JMenu menu3;
    private JMenuItem menuItem5;
    private JButton sendButton;
    private JTextField commandTextField;
    private JScrollPane scrollPane1;
    private JEditorPane logPane;
    private static UIFrame UI = new UIFrame();
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public synchronized void printString(String outString) {
        Document document = this.getDocument();
        for (int i = 0; i < outString.length(); i++) {
            try {
                logPane.getDocument().insertString(logPane.getDocument().getLength(),
                        String.valueOf(outString.charAt(i)), null);
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BadLocationException e) {

            }
        }
    }

    public Document getDocument() {
        return logPane.getDocument();
    }

    private void initComponentText() {

        ResourceBundle bundle = ResourceBundle.getBundle("bundles.UIbundle");
        // ======== menuBar ========

        // ======== menu1 ========
        menu1.setText("File");

        // ======== menuItem4 ========
        menuItem4.setText(bundle.getString("FINALTEST.load"));

        // ======== menu2 =========
        menu2.setText(bundle.getString("FINALTEST.settings"));
        menu2.setActionCommand(bundle.getString("FINALTEST.settings"));

        // ======== menu6 ========
        menu6.setText(bundle.getString("FINALTEST.language"));

        //---- radioButtonMenuItem1 ----
        radioButtonMenuItem1.setText(bundle.getString("FINALTEST.english"));

        //---- radioButtonMenuItem2 ----
        radioButtonMenuItem2.setText(bundle.getString("FINALTEST.italian"));

        //======== menu3 ========
        menu3.setText(bundle.getString("FINALTEST.help"));

        //---- menuItem5 ----
        menuItem5.setText(bundle.getString("FINALTEST.tutorial"));

        //---- sendButton ----
        sendButton.setText(bundle.getString("FINALTEST.send"));
        sendButton.setToolTipText(bundle.getString("FINALTEST.buttonToolTip"));

        //---- commandTextField ----
        commandTextField.setToolTipText(bundle.getString("FINALTEST.textfieldToolTip"));
    }

    public static UIFrame getUIFrame(){
        return UI;
    }

    public void resetText() {
        logPane.setText("");
    }
}
