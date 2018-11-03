package tk.dccraft.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import tk.dccraft.Assignment_2.bank.SavingsAccount;
import tk.dccraft.Assignment_2.bank.SavingsAccountTester;
import tk.dccraft.init.Main;

/**
 * Basic Input/Output System for this program
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class BIOS extends Main {
	private static String FileName, fileContent;
	private static File objFile;
	private static BufferedWriter bw;
	private static BufferedReader reader;

	private static String text;

	public String bg, fg, ft, cfg, cbg, name, balance, total, tax, index, log, author, version;

	public int count = 0, normalCount = 0, size;

	List<String> balanceList = new ArrayList<String>(), nameList = new ArrayList<String>();
	List<Double> taxList = new ArrayList<Double>();
	public boolean exists = false;

	/**
	 * Reads the file and organizes by type("style" or more specifically by
	 * program use, like "bank" for the SavingsAccountTester.class)
	 * 
	 * @param FileName
	 * @param FileLocation
	 * @param type
	 * @throws IOException
	 */
	public void TextReader(String FileName, String FileLocation, String type) {
		print("Accessing Text Reader Method...");
		print("Attempting To Read Designated File...");
		FileReader file;
		try {
			file = new FileReader(FileLocation + FileName);
			print("Reading File: " + FileName + " in " + FileLocation);
			reader = new BufferedReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			print("Thats so weird cant seem to locate the file");
			loadDefaultFiles();
		}

		BufferedInputStream inStream = new BufferedInputStream(System.in);

		print("Initializing Buffered Reader... \nInitializing Buffered Input Stream...");

		if (type.equalsIgnoreCase("style")) {
			try {
				String text = "";

				String line = reader.readLine();
				while (line != null) {
					if (line.startsWith("bg:")) {
						bg = line.substring(3);
						print("Background Color is: " + bg);
					}
					if (line.startsWith("fg:")) {
						fg = line.substring(3);
						print("Foreground Color is: " + fg);
					}
					if (line.startsWith("ft:")) {
						ft = line.substring(3);
						print("Font size is: " + ft);
					}
					if (line.startsWith("cfg:")) {
						cfg = line.substring(4);
						print("Console Foreground Color is " + cfg);
					}
					if (line.startsWith("cbg:")) {
						cbg = line.substring(4);
						print("Console Background Color is " + cbg);
					}
					if (line.startsWith("index:")) {
						index = line.substring(6);
						print("Page Index is: " + index);
					}
					if (line.startsWith("log:")) {
						try {
							log = line.substring(4);
							print(line);
						} catch (Exception e) {
							print("Could not parse log boolean string in " + FileLocation + "/" + FileName + " line " + line + " line should say \"log:true\" or \"log:false\"");
						}
						// if(line.contains("true"))
						// else if(line.contains("false"))
						// log = false;
						// else{
						// print("Invalid token for the log preference, should
						// be \"log:true | log:false\" not " + line + "\n
						// setting log to true");
						// log = true;
						// }
					}

					text += line;
					line = reader.readLine();
				}
			} catch (Exception e) {
				print("Had A Problem with the while loop in the TextReader Method(Section 1:Style)\nCouldn't proccess line reader");
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
						print("Name" + count + ":" + name);
					}
					if (line.startsWith("Balance" + count + ":")) {
						balance = line.substring(9);
						balanceList.add(balance);
						print("Balance" + count + ":" + balance);
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
				print("Had A Problem with the while loop in the TextReader Method(Section 2:Banking)\n Couldn't proccess line reader");
				e.printStackTrace();
			}
		} else if (type.equalsIgnoreCase("metadata")) {
			try {
				String text = "";
				String line = reader.readLine();
				while (line != null) {
					if (line.startsWith("author:")) {

					}
				}
			} catch (Exception e) {

			}
		} else {
			try {
				BIOS.text = "";
				String text = BIOS.text;

				String line = reader.readLine();
				while (line != null) {
					print("Line:" + line + " Text" + text);
					text += line;
					line = reader.readLine();

				}
			} catch (Exception e) {
				print("Had A Problem with the while loop in the TextReader Method\n Couldn't proccess line reader");
				e.printStackTrace();
			}
		}
		try {
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Writes to a file, either by overwriting or appending to the end of the
	 * file. with the append boolean
	 * 
	 * @param FileName
	 * @param fileContent
	 * @param FolderName
	 * @param append
	 * @throws IOException
	 */
	public void TextWriter(String FileName, String fileContent, String FolderName, boolean append) throws IOException {
		print("Accessing Settings Saving Method...");
		File f = new File(FolderName);
		try {
			if (f.mkdirs()) {
				print("Directory Created in " + f.getAbsolutePath());
			} else {// Exists
				print("Directory is not created -- Maybe the File already exists");
			}
		} catch (Exception e) {
			print(e.getMessage());
		}
		try {
			bw = new BufferedWriter(new FileWriter(FolderName + FileName, append));
			bw.write(fileContent);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			print("Had an issue with Writting the file " + FileName + " in TextWriter Meathod.  ERROR: " + e.getMessage());
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
	}
}