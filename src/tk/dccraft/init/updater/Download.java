
package tk.dccraft.init.updater;

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


@SuppressWarnings("all")
public class Download extends JFrame {
	private Thread worker;
	private final String root = "Assignment-Selector/";
	private JTextArea outText;
	private JButton cancle;
	private JButton launch;
	private JScrollPane sp;
	private JPanel pan1;
	private JPanel pan2;

	public Download() {
		initComponents();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Download().setVisible(true);
			}
		});
		
		this.outText.setText("Contacting Download Server...");
		download();
	}

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
				Download.this.launch();
			}
		});
		this.pan2.add(this.launch);

		this.cancle = new JButton("Cancel Update");
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
		setTitle("Updating to the Latest Assignment Release");
	}

	private void download() {
		this.worker = new Thread(new Runnable() {
			public void run() {
				try {
					Download.this.downloadFile("https://dl.dropboxusercontent.com/u/101936268/Vid-Eo_Both.zip");
					Download.this.unzip();
					Download.this.copyFiles(new File("Vid-Eo/"), new File("").getAbsolutePath());
					Download.this.cleanup();
					Download.this.launch.setEnabled(true);
					Download.this.outText.setText(Download.this.outText.getText() + "\nUpdate Finished!");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error occured while preforming update!");
				}
			}
		});
		this.worker.start();
	}

	private void launch() {
		String[] run = { "java", "-jar", "Assignment-Selector.jar" };
		try {
			Runtime.getRuntime().exec(run);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	}

	private void cleanup() {
//		try {
//			TextTransfer.TextWriter("/currentVersion.txt/", Updater.getLatestVersion(), "/");
//		} catch (IOException e) {
//			System.out.println("Had Troubles in The Clean UP Method [Catch 1].  ERROR: " + e.getMessage());
//		} catch (Exception e) {
//			System.out.println("Had Troubles in The Clean UP Method [Catch 2].  ERROR: " + e.getMessage());
//		}
		this.outText.setText(this.outText.getText() + "\nPreforming clean up...");
		File f = new File("update.zip");
		f.delete();
		if (f.exists()) {
			f.delete();
		}
		remove(new File("Assignment-Selector/"));
		new File("Assignment-Selector/").delete();
	}

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

	private void unzip() throws IOException {
		int BUFFER = 2048;
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;

		ZipFile zipfile = new ZipFile("update.zip");
		Enumeration e = zipfile.entries();
		new File("Vid-Eo/").mkdir();
		while (e.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) e.nextElement();
			this.outText.setText(this.outText.getText() + "\nExtracting: " + entry);
			if (entry.isDirectory()) {
				new File("Vid-Eo/" + entry.getName()).mkdir();
			} else {
				new File("Vid-Eo/" + entry.getName()).createNewFile();
				is = new BufferedInputStream(zipfile.getInputStream(entry));

				byte[] data = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream("Vid-Eo/" + entry.getName());
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

	private void downloadFile(String link) throws MalformedURLException, IOException {
		URL url = new URL(link);
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		long max = conn.getContentLength();
		this.outText.setText(
				this.outText.getText() + "\n" + "Downloding file...\nUpdate Size(compressed): " + max + " Bytes");
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

	private String getDownloadLinkFromHost() throws MalformedURLException, IOException {
		String path = "https://dl.dropboxusercontent.com/u/101936268/Download.html";
		URL url = new URL(path);

		InputStream html = null;

		html = url.openStream();

		int c = 0;
		StringBuilder buffer = new StringBuilder("");
		while (c != -1) {
			c = html.read();
			buffer.append((char) c);
		}
		return buffer.substring(buffer.indexOf("[url]") + 5, buffer.indexOf("[/url]"));
	}
}