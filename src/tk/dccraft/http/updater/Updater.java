
package tk.dccraft.http.updater;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Creates an Updater that connects to a remote .zip file
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Updater extends JFrame {
	private Thread worker;
	private final String root = "Assignment/", url = "http://dccraft.tk/assignment/Assignment.zip", jarName = "Assignment-Selector.jar";
	private JTextArea outText;
	private JButton cancle;
	private JButton launch;
	private JScrollPane sp;
	private JPanel pan1;
	private JPanel pan2;

	public Updater() {
		initComponents();
		this.outText.setText("Contacting Download Server...");
		download();
	}

	/**
	 * Initializes the UI components
	 */
	private void initComponents() {
		setDefaultCloseOperation(3);

		this.pan1 = new JPanel();
		this.pan1.setLayout(new BorderLayout());

		this.pan2 = new JPanel();
		this.pan2.setLayout(new FlowLayout());

		this.outText = new JTextArea();
		this.sp = new JScrollPane();
		this.sp.setViewportView(this.outText);
		this.launch = new JButton("Continue");
		this.launch.setEnabled(false);
		this.launch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Updater.this.launch();
			}
		});
		this.pan2.add(this.launch);

		this.cancle = new JButton("Cancel Install/Update");
		this.cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.pan2.add(this.cancle);
		this.pan1.add(this.sp, "Center");
		this.pan1.add(this.pan2, "South");

		add(this.pan1);
		pack();
		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Updating/Installing Assignment");
	}

	/**
	 * Sets up the Order of Operations, so to speak
	 */
	private void download() {
		this.worker = new Thread(new Runnable() {
			public void run() {
				try {
					Updater.this.downloadFile(url);
					Updater.this.unzip();
					Updater.this.copyFiles(new File("Assignment/"), new File("").getAbsolutePath());
					Updater.this.cleanup();
					Updater.this.launch.setEnabled(true);
					Updater.this.outText.setText(Updater.this.outText.getText() + "\nUpdate Finished!");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error occured while preforming update!");
				}
			}
		});
		this.worker.start();
	}

	/**
	 * Launches the newly downloaded/updated and unzipped file
	 */
	private void launch() {
		String[] run = { "java", "-jar", jarName };
		try {
			Runtime.getRuntime().exec(run);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * Delets the temp zip file
	 */
	private void cleanup() {
		this.outText.setText(this.outText.getText() + "\nPreforming clean up...");
		File f = new File("update.zip");
		f.delete();
		if (f.exists()) {
			f.delete();
		}
		remove(new File("Assignment/"));
		new File("Assignment/").delete();
	}

	/**
	 * Removes folder that the files were copied from
	 * 
	 * @param f
	 */
	private void remove(File f) {
		File[] files = f.listFiles();
		File[] arrayOfFile1;
		int j = (arrayOfFile1 = files).length;
		for (int i = 0; i < j; i++) {
			File ff = arrayOfFile1[i];
			if (ff.isDirectory()) {
				remove(ff);
				ff.delete();
			} else {
				ff.delete();
			}
		}
	}

	/**
	 * Copies all file out of a folder
	 * 
	 * @param f
	 * @param dir
	 * @throws IOException
	 */
	private void copyFiles(File f, String dir) throws IOException {
		File[] files = f.listFiles();
		File[] arrayOfFile1;
		int j = (arrayOfFile1 = files).length;
		for (int i = 0; i < j; i++) {
			File ff = arrayOfFile1[i];
			if (ff.isDirectory()) {
				new File(dir + "/" + ff.getName()).mkdir();
				copyFiles(ff, dir + "/" + ff.getName());
			} else {
				copy(ff.getAbsolutePath(), dir + "/" + ff.getName());
			}
		}
	}

	/**
	 * If there is only a single file
	 * 
	 * @param srFile
	 * @param dtFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void copy(String srFile, String dtFile) throws FileNotFoundException, IOException {
		File f1 = new File(srFile);
		File f2 = new File(dtFile);

		InputStream in = new FileInputStream(f1);

		OutputStream out = new FileOutputStream(f2);

		byte[] buf = new byte['?'];
		int len;
		while ((len = in.read(buf)) > 0) {
			// int len;
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	/**
	 * Unzips the downloaded zip file
	 * 
	 * @throws IOException
	 */
	private void unzip() throws IOException {
		int BUFFER = 2048;
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;

		ZipFile zipfile = new ZipFile("update.zip");
		Enumeration e = zipfile.entries();
		new File("Assignment/").mkdir();
		while (e.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) e.nextElement();
			this.outText.setText(this.outText.getText() + "\nExtracting: " + entry);
			if (entry.isDirectory()) {
				new File("Assignment/" + entry.getName()).mkdir();
			} else {
				new File("Assignment/" + entry.getName()).createNewFile();
				is = new BufferedInputStream(zipfile.getInputStream(entry));

				byte[] data = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream("Assignment/" + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				int count;
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					// int count;
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		}
		zipfile.close();
	}

	/**
	 * Downloads file from the link parameter
	 * 
	 * @param link
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void downloadFile(String link) throws MalformedURLException, IOException {
		URL url = new URL(link);
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		long max = conn.getContentLength();
		this.outText.setText(this.outText.getText() + "\n" + "Downloding file...\nUpdate Size(compressed): " + max + " Bytes");
		BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(new File("update.zip")));
		byte[] buffer = new byte[32768];
		int bytesRead = 0;
		int in = 0;
		while ((bytesRead = is.read(buffer)) != -1) {
			in += bytesRead;
			fOut.write(buffer, 0, bytesRead);
		}
		fOut.flush();
		fOut.close();
		is.close();
		this.outText.setText(this.outText.getText() + "\nDownload Complete!");
	}

	/**
	 * Just in case I want to make it an installer or use it as an updater
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Updater().setVisible(true);
			}
		});
	}
}