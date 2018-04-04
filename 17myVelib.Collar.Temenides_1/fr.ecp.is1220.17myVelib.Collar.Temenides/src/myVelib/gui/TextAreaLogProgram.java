package myVelib.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Date;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JToolBar;
 
public class TextAreaLogProgram extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The text area which is used for displaying logging information.
     */
    private JTextArea textArea;
     
    private JButton buttonStart = new JButton("Start");
    private JButton buttonClear = new JButton("Clear");
     
    private PrintStream standardOut;
    private final JToolBar toolBar = new JToolBar();
     
    public TextAreaLogProgram() {
        super("Demo printing to JTextArea");
         
        textArea = new JTextArea(50, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
         
        // keeps reference of standard output stream
        standardOut = System.out;
         
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
 
        // creates the GUI
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{0.0, 0.0};
        getContentPane().setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
         
        constraints.gridx = 1;
        
        GridBagConstraints gbc_toolBar = new GridBagConstraints();
        gbc_toolBar.insets = new Insets(0, 0, 5, 0);
        gbc_toolBar.gridx = 0;
        gbc_toolBar.gridy = 0;
        getContentPane().add(toolBar, gbc_toolBar);
        getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{buttonStart, buttonClear, textArea}));
        toolBar.add(buttonStart);
        
        // adds event handler for button Start
        buttonStart.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               printLog();
           }
        });
        toolBar.add(buttonClear);
        
        // adds event handler for button Clear
        buttonClear.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               // clears the text area
               try {
                   textArea.getDocument().remove(0,
                           textArea.getDocument().getLength());
                   standardOut.println("Text area cleared");
               } catch (BadLocationException ex) {
                   ex.printStackTrace();
               }
           }
        });
         
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, constraints);
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 320);
        setLocationRelativeTo(null);    // centers on screen
    }
     
    /**
     * Prints log statements for testing in a thread
     */
    private void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Time now is " + (new Date()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
     
    /**
     * Runs the program
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextAreaLogProgram().setVisible(true);
            }
        });
    }
}