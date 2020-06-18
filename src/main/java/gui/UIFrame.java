package gui;

import engine.GameProgress;
import engine.Parser;
import engine.ResourceHandler;
import engine.SaveStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author unknown
 */
public class UIFrame extends JFrame{
    private UIFrame() {
        initComponents();
    }

    private void sendCommandActionPerformed() {
        Parser.parseCommand(commandTextField.getText());
        commandTextField.setText("");
    }

    private void UIFrameWindowClosing() {
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
                UIHandler.printInFrame(GameProgress.getCurrentRoom().roomInformations());
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
                UIHandler.printInFrame(GameProgress.getCurrentRoom().roomInformations());
            }
        }
    }

    private void openTutorialActionPerformed() {
        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + File.separator + "src"
                    + File.separator +"main" + File.separator
                    + "resources" + File.separator + "tutorial_it.html"));
        } catch (IOException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ResourceBundle.getBundle("bundles/UIbundle").getString("errorMessage"), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveActionPerformed() {
        JFileChooser saveFileChooser = new JFileChooser(System.getProperty("user.dir"));
        saveFileChooser.setMultiSelectionEnabled(false);
        saveFileChooser.setSelectedFile(new File("progress.save"));
        saveFileChooser.setFileFilter(new FileNameExtensionFilter("Save", "save"));
        int choice = saveFileChooser.showSaveDialog(this);
        if(choice == JFileChooser.APPROVE_OPTION) {
            SaveStream.saveProgress(saveFileChooser.getSelectedFile());
        }
    }

    private void initComponents() {

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
                    UIFrameWindowClosing();
                }
            });
            var JFrameContentPane = JFrame.getContentPane();

            //======== menuBar ========
            {

                //======== menu1 ========
                {


                    //---- menuItem3 ----

                    menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
                    menuItem3.addActionListener(e -> saveActionPerformed());
                    menu1.add(menuItem3);

                    //---- menuItem4 ----

                    menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
                    menuItem4.addActionListener(e -> loadActionPerformed());
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
                    menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
                    menuItem5.addActionListener(e -> openTutorialActionPerformed());
                    menu3.add(menuItem5);
                }
                menuBar.add(menu3);
            }
            JFrame.setJMenuBar(menuBar);

            //---- sendButton ----
            sendButton.addActionListener(e -> sendCommandActionPerformed());

            //---- commandTextField ----
            commandTextField.addActionListener(e -> sendCommandActionPerformed());

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
            JFrame.setIconImage(new ImageIcon(System.getProperty("user.dir") + File.separator + "src"
                    + File.separator +"main" + File.separator
                    + "resources" + File.separator + "logo.png").getImage());
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

    private void loadActionPerformed() {
        JFileChooser loadFileChooser = new JFileChooser(System.getProperty("user.dir"));
        loadFileChooser.setMultiSelectionEnabled(false);
        loadFileChooser.setFileFilter(new FileNameExtensionFilter("Save", "save"));
        int choice = loadFileChooser.showOpenDialog(this);
        if(choice == JFileChooser.APPROVE_OPTION) {
            SaveStream.loadProgress(loadFileChooser.getSelectedFile());
            UIHandler.printInFrame(GameProgress.getCurrentRoom().roomInformations() + "\n");

            //The player could be loading after a game over.
            sendButton.setEnabled(true);
            commandTextField.setEnabled(true);
            menuItem3.setEnabled(true);
        }
    }

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
    private static final UIFrame UI = new UIFrame();

    public synchronized void printString(String outString) {
        Document document = this.getDocument();
        try {
            for (int i = 0; i < outString.length(); i++) {

                document.insertString(document.getLength(),
                        String.valueOf(outString.charAt(i)), null);
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            //When it's interrupted, we want to stop printing. So, we just return.
            return;
        } catch (BadLocationException ignored) {

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
        menuItem3.setText(bundle.getString("UIFrame.save"));
        menuItem4.setText(bundle.getString("UIFrame.load"));

        // ======== menu2 =========
        menu2.setText(bundle.getString("UIFrame.settings"));
        menu2.setActionCommand(bundle.getString("UIFrame.settings"));

        // ======== menu6 ========
        menu6.setText(bundle.getString("UIFrame.language"));

        //---- radioButtonMenuItem1 ----
        radioButtonMenuItem1.setText(bundle.getString("UIFrame.english"));

        //---- radioButtonMenuItem2 ----
        radioButtonMenuItem2.setText(bundle.getString("UIFrame.italian"));

        //======== menu3 ========
        menu3.setText(bundle.getString("UIFrame.help"));

        //---- menuItem5 ----
        menuItem5.setText(bundle.getString("UIFrame.tutorial"));

        //---- sendButton ----
        sendButton.setText(bundle.getString("UIFrame.send"));
        sendButton.setToolTipText(bundle.getString("UIFrame.buttonToolTip"));

        //---- commandTextField ----
        commandTextField.setToolTipText(bundle.getString("UIFrame.textfieldToolTip"));
    }

    public static UIFrame getUIFrame(){
        return UI;
    }

    public void resetText() {
        logPane.setText("");
    }

    void disableInput() {
        this.commandTextField.setEnabled(false);
        this.sendButton.setEnabled(false);
    }

    void disableSave() {
        menuItem3.setEnabled(false);
    }
}
