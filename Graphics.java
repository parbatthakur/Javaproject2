package parbat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class Graphics extends JFrame implements ActionListener {
	
	private JPanel panelForButton, panelForNameInput, panelForSelection, infoPanel;
    protected static JTextField firstNameField, lastNameField;
    private JButton openToRead, writeThis, openToWrite, readNext;
    private JLabel firstName, lastName, nationalityLabel, professionLabel, infoLabel;
    protected static JComboBox<?> nationalityCombo, professionCombo;
    protected static String[] nationalityList = {"Nepal", "American", "Australian", "Austrian",
        "Bangladeshi", "Brazilian", "British", "Bulgarian", "Canadian", "Chinese",
        "Estonian", "Ethiopian", "Filipino", "Finnish", "French", "German", "Ghanaian",
        "Indian", "Iranian", "Iraqi", "Irish", "Italian", "Japanese", "Kenyan",
        "Lithuanian", "Malaysian", "Afghan", "Netherlander", "New Zealander",
        "Nigerien", "Pakistani", "Romanian", "Russian", "Somali", "South African",
        "South Korean", "Spanish", "Swedish", "Vietnamese"};
    protected static String[] professionList = {"Student", "Professor", "Graphic Designer", "Waiter", "Cook",
        "Bus Driver", "Seller", "Secretary", "Programmer", "Web Developer",
        "Nurse", "Cleaner", "Psychologist"};
    protected static String userDataLine;
    protected static RandomAccessFile out;
    protected static final String dataFileLocation = "studentDataFile.txt";
    protected static final File dataFile = new File(dataFileLocation);
    protected static int indexCounter = 3;
    protected static ArrayList<String> tokens = new ArrayList<String>();
    
    public Graphics(){
    	
    }
    
    protected void createGUI() {

        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.black);


        panelForButton = new JPanel();
        panelForButton.setBackground(Color.white);
        panelForButton.setPreferredSize(new Dimension(600, 40));

        panelForNameInput = new JPanel();
        panelForNameInput.setBackground(Color.white);
        panelForNameInput.setPreferredSize(new Dimension(600, 80));
        panelForNameInput.setLayout(new GridLayout(3, 2));

        infoPanel = new JPanel();
        infoPanel.setBackground(Color.white);
        infoPanel.setPreferredSize(new Dimension(600, 40));
        infoPanel.setLayout(new FlowLayout());

        panelForSelection = new JPanel();
        panelForSelection.setBackground(Color.white);
        panelForSelection.setPreferredSize(new Dimension(600, 80));
        panelForSelection.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 3));


        openToWrite = new JButton("Open to Write");
        openToWrite.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        firstNameField.setText("");
                        lastNameField.setText("");
                        nationalityCombo.setSelectedIndex(0);
                        professionCombo.setSelectedIndex(0);
                        FileHandling.openToWriteToFile();
                    }
                });


        writeThis = new JButton("Save");
        writeThis.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {



                        	FileHandling.saveToFile();
                            setArea(false);
                            Graphics.nationalityCombo.setSelectedIndex(0);
                            Graphics.professionCombo.setSelectedIndex(0);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                });

        openToRead = new JButton("Open to Read");
        openToRead.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setArea(false);
                        FileHandling.readFromFile();
                    }
                });




        readNext = new JButton("Read Next");
        readNext.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setArea(false);

                        if (FileHandling.fillArray() == 1) {

                        	FileHandling.readNext();
                        }
                    }
                });


        firstName = new JLabel("First Name", JLabel.CENTER);
        lastName = new JLabel("Last Name", JLabel.CENTER);

        firstNameField = new JTextField(JTextField.CENTER);
        firstNameField.setEnabled(false);

        lastNameField = new JTextField(JTextField.CENTER);
        lastNameField.setEnabled(false);

        nationalityLabel = new JLabel("Nationality", JLabel.CENTER);
        professionLabel = new JLabel("Profession", JLabel.CENTER);

        infoLabel = new JLabel("National Survey", JLabel.CENTER);

        nationalityCombo = new JComboBox<Object>(nationalityList);
        nationalityCombo.setEnabled(false);
        professionCombo = new JComboBox<Object>(professionList);
        professionCombo.setEnabled(false);
        nationalityCombo.setPreferredSize(new Dimension(200, 20));
        professionCombo.setPreferredSize(new Dimension(200, 20));




        window.add(infoPanel);
        window.add(panelForButton);
        window.add(panelForNameInput);
        window.add(panelForSelection);

        infoPanel.add(infoLabel);

        panelForButton.add(openToWrite);
        panelForButton.add(writeThis);
        panelForButton.add(openToRead);
        panelForButton.add(readNext);

        panelForNameInput.add(firstName);
        panelForNameInput.add(firstNameField);

        panelForNameInput.add(lastName);
        panelForNameInput.add(lastNameField);

        panelForSelection.add(professionLabel);
        panelForSelection.add(professionCombo);

        panelForSelection.add(nationalityLabel);
        panelForSelection.add(nationalityCombo);



    }
    
    public void actionPerformed(ActionEvent ae) {
    }

    protected static void setArea(boolean setValue) {

        Graphics.firstNameField.setEnabled(setValue);
        Graphics.lastNameField.setEnabled(setValue);
        Graphics.nationalityCombo.setEnabled(setValue);
        Graphics.professionCombo.setEnabled(setValue);

    }

}
