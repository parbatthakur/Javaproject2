package parbat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FileHandling {

	static BufferedWriter writeToFile;
	static private BufferedReader readFromFile;
	static int index = -1;
	static int readConfirm = 0;

	protected static void openToWriteToFile() {
		if (Graphics.dataFile.exists() == true) {
			Graphics.setArea(true);
			FileHandling.createFile("needFileToWrite");

		} else {
			JOptionPane.showMessageDialog(null,
					"FILE DOES NOT EXIST !\n New File is created.");
			FileHandling.createFile("needFileToWrite");
			Graphics.setArea(true);
		}
	}

	protected static void saveToFile() throws IOException {

		if (Graphics.firstNameField.getText().trim().length() > 1
				&& Graphics.firstNameField.getText().trim().length() > 1) {

			Graphics.userDataLine = Graphics.firstNameField.getText() + "|"
					+ Graphics.lastNameField.getText() + "|"
					+ (String) Graphics.professionCombo.getSelectedItem() + "|"
					+ (String) Graphics.nationalityCombo.getSelectedItem();

			try {

				FileHandling.createFile("needFileToWrite");
				writeToFile.write(Graphics.userDataLine);
				writeToFile.newLine();
				writeToFile.close();

				JOptionPane.showMessageDialog(null, "Writing complete");
				Graphics.userDataLine = "";

			} catch (IOException e) {

				JOptionPane.showMessageDialog(null, e.getMessage()
						+ "\nERROR !");
			}
			Graphics.firstNameField.setText(null);
			Graphics.lastNameField.setText(null);
		} else {

			JOptionPane.showMessageDialog(null, "Incomplete Data ! Try again");

		}
	}

	protected static void readFromFile() {

		if (Graphics.dataFile.exists() != true) {

			JOptionPane.showMessageDialog(null,
					"File error ! File doesn't exist.");

		} else {
			FileHandling.createFile("needFileToRead");
			try {
				if (FileHandling.readFromFile.read() < 2) {
					JOptionPane.showMessageDialog(null, "File is empty !");
				} else {
					String line;

					while ((line = readFromFile.readLine()) != null) {

						String[] tokens = line.split("\\|");
						String firstName = tokens[0];
						String lastName = tokens[1];
						String nationality = tokens[2];
						String profession = tokens[3];

						Graphics.firstNameField.setText(firstName);
						Graphics.lastNameField.setText(lastName);
						int indexOfProfession;
						int indexOfNationality;
						indexOfProfession = searchString(
								Graphics.professionList, nationality);
						indexOfNationality = searchString(
								Graphics.nationalityList, profession);

						Graphics.professionCombo
								.setSelectedIndex(indexOfProfession);
						Graphics.nationalityCombo
								.setSelectedIndex(indexOfNationality);
						readFromFile.close();
						Graphics.indexCounter = 3;

						break;
					}
				}
			} catch (IOException ex) {

				JOptionPane.showMessageDialog(null, ex.getMessage());

			}
		}
	}

	protected static void createFile(String getTaskMessage) {
		if (getTaskMessage.equalsIgnoreCase("needFileToWrite") == true) {
			try {
				FileHandling.writeToFile = new BufferedWriter(new FileWriter(
						Graphics.dataFileLocation, true));
			} catch (IOException ex) {
				JOptionPane
						.showMessageDialog(null, "Error :" + ex.getMessage());
			}
		} else if (getTaskMessage.equalsIgnoreCase("needFileToRead") == true) {
			try {
				FileHandling.readFromFile = new BufferedReader(
						new InputStreamReader(new FileInputStream(
								Graphics.dataFileLocation)));
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}

		}

	}

	public static int searchString(String[] arrayData, String name) {
		for (int n = 0; n < arrayData.length; n++) {
			if (arrayData[n].equals(name)) {
				System.out.println(arrayData);
				return n;
			}
		}
		return -1;

	}

	protected static void readNext() {

		if (Graphics.indexCounter < Graphics.tokens.size()) {

			Graphics.indexCounter++;
			String firstName = Graphics.tokens.get(Graphics.indexCounter);
			Graphics.indexCounter++;
			String lastName = Graphics.tokens.get(Graphics.indexCounter);
			Graphics.indexCounter++;
			String nationality = Graphics.tokens.get(Graphics.indexCounter);
			Graphics.indexCounter++;
			String profession = Graphics.tokens.get(Graphics.indexCounter);

			Graphics.firstNameField.setText(firstName);
			Graphics.lastNameField.setText(lastName);
			int indexOfProfession;
			int indexOfNationality;
			indexOfProfession = searchString(Graphics.professionList,
					nationality);
			indexOfNationality = searchString(Graphics.nationalityList,
					profession);

			Graphics.professionCombo.setSelectedIndex(indexOfProfession);
			Graphics.nationalityCombo.setSelectedIndex(indexOfNationality);
		} else {

			JOptionPane.showMessageDialog(null, "No record found !");
		}
	}

	protected static int fillArray() {

		if (readConfirm == 0) {

			if (Graphics.dataFile.exists() != true) {

				JOptionPane.showMessageDialog(null,
						"File error ! File doesn't exist.");
				return 0;

			} else {

				FileHandling.createFile("needFileToRead");
				try {
					if (FileHandling.readFromFile.read() < 2) {
						JOptionPane.showMessageDialog(null, "File is empty !");
					} else {
						String line;

						try {
							while ((line = readFromFile.readLine()) != null) {

								String token[] = line.split("\\|");
								for (int i = 0; i < 4; i++) {
									Graphics.tokens.add(getIndex(), token[i]);
									FileHandling.readConfirm = 1;

								}
							}
						} catch (IOException ex) {
							Logger.getLogger(FileHandling.class.getName()).log(
									Level.SEVERE, null, ex);
						}
					}
				} catch (IOException ex) {
					Logger.getLogger(FileHandling.class.getName()).log(
							Level.SEVERE, null, ex);
				}

				try {
					readFromFile.close();
				} catch (IOException ex) {
					Logger.getLogger(FileHandling.class.getName()).log(
							Level.SEVERE, null, ex);
				}

			}
		}
		if (Graphics.tokens.size() == 4
				|| Graphics.tokens.size() == Graphics.indexCounter + 1) {

			JOptionPane.showMessageDialog(null, "No more record");
			return 0;
		} else {
			return 1;
		}

	}

	protected static int getIndex() {

		index++;
		return index;
	}
}
