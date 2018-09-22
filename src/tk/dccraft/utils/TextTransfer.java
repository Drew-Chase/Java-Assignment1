package tk.dccraft.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tk.dccraft.Assignment_2.bank.SavingsAccount;
import tk.dccraft.Assignment_2.bank.SavingsAccountTester;
import tk.dccraft.init.Main;

@SuppressWarnings("all")
public class TextTransfer {
	private static String FileName, fileContent;
	private static File objFile;
	private static BufferedWriter bw;
	private static BufferedReader reader;

	private static String text;

	public String bg, fg, ft, name, balance, total, tax;

	// private SavingsAccountTester sat = new SavingsAccountTester();

	public int count = 0, normalCount = 0, size;

	List<String> balanceList = new ArrayList<String>(), nameList = new ArrayList<String>();
	List<Double> taxList = new ArrayList<Double>();

	private Main main = new Main();

	public void TextReader(String FileName, String FileLocation, String type) throws IOException {
		main.print("Accessing Text Reader Method...");
		main.print("Attempting To Read Designated File...");

		FileReader file = new FileReader(FileLocation + FileName);
		main.print("Reading File: " + FileName + " in " + FileLocation);
		BufferedReader reader = new BufferedReader(file);

		BufferedInputStream inStream = new BufferedInputStream(System.in);

		main.print("Initializing Buffered Reader... \nInitializing Buffered Input Stream...");

		if (type.equalsIgnoreCase("style")) {
			try {
				String text = "";

				String line = reader.readLine();
				while (line != null) {
					if (line.startsWith("bg:")) {
						bg = line.substring(3);
						main.print("Background Color is: " + bg);
					}
					if (line.startsWith("fg:")) {
						fg = line.substring(3);
						main.print("Foreground Color is: " + fg);
					}
					if (line.startsWith("ft:")) {
						ft = line.substring(3);
						main.print("Font size is: " + ft);
					}
					text += line;
					line = reader.readLine();
				}
			} catch (Exception e) {
				main.print("Had A Problem with the while loop in the TextReader Method(Section 1:Style)\n Couldn't proccess line reader");
				e.printStackTrace();
			}
		} else if (type.equalsIgnoreCase("bank")) {
			try {
				String text = "";
				String line = reader.readLine();
				while (line != null) {
					if (line.startsWith("Name" + count + ":")) {
						name = line.substring(6);
						nameList.add(name);
						main.print("Name" + count + ":" + name);
					}
					if (line.startsWith("Balance" + count + ":")) {
						balance = line.substring(9);
						balanceList.add(balance);
						main.print("Balance" + count + ":" + balance);
					}

					text += line;
					line = reader.readLine();
					if ((normalCount % 2) != 0)
						count++;
					normalCount++;
				}

				for (int i = 0; i < nameList.size(); i++)
					SavingsAccountTester.accounts.add(new SavingsAccount(Double.parseDouble(balanceList.get(i)), nameList.get(i)));

			} catch (Exception e) {
				main.print("Had A Problem with the while loop in the TextReader Method(Section 2:Banking)\n Couldn't proccess line reader");
				e.printStackTrace();
			}
		} else {
			try {
				TextTransfer.text = "";
				String text = TextTransfer.text;

				String line = reader.readLine();
				while (line != null) {
					main.print("Line:" + line + " Text" + text);
					text += line;
					line = reader.readLine();

				}
			} catch (Exception e) {
				main.print("Had A Problem with the while loop in the TextReader Method\n Couldn't proccess line reader");
				e.printStackTrace();
			}
		}

	}

	public void TextWriter(String FileName, String fileContent, String FolderName, boolean append) throws IOException {
		main.print("Accessing Settings Saving Method...");
		File f = new File(FolderName);
		try {
			if (f.mkdir()) {
				main.print("Directory Created in " + f.getAbsolutePath());
			} else {// Exists
				main.print("Directory is not created -- Maybe the File already exists");
			}
		} catch (Exception e) {
			main.print(e.getMessage());
		}
		try {
			bw = new BufferedWriter(new FileWriter(FolderName + FileName, append));
			bw.write(fileContent);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			main.print("Had an issue with Writting the file " + FileName + " in TextWriter Meathod.  ERROR: " + e.getMessage());
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
	}
}