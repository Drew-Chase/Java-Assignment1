package tk.dccraft.utils.settings;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tk.dccraft.init.Main;
import tk.dccraft.utils.TextTransfer;

@SuppressWarnings("all")
public class PreferenceWindow extends Main implements ActionListener {

	private JButton reset, close, apply;
	private JLabel fgColorlbl, bgColorlbl, fontlbl;
	private JTextField fgField, bgField, fontField;
	private JPanel pane;
	private Main main;
	private JFrame f;
	private BufferedWriter bw;

	public PreferenceWindow() {
		print("Opening Preferences...");
		Color fg = getFg();
		Color bg = getBg();
		int fontSize = main.getFontSize();
		// Sets the Size
		int width = 800, height = 600;
		Dimension size = new Dimension(width, height);

		// Sets up the Content Pane
		pane = new JPanel();
		pane.setSize(size);
		pane.setBackground(bg);
		pane.setForeground(fg);
		pane.setLayout(null);

		// Sets up the Labels
		fontlbl = new JLabel("Set font size");
		fontlbl.setLocation(50, 15);
		fontlbl.setForeground(fg);
		fontlbl.setSize(100, 100);
		fontlbl.setLayout(null);
		fontlbl.setFont(getFont());
		pane.add(fontlbl);

		fgColorlbl = new JLabel("<html>Set the Text Color<br/>(Ex:0xFFFFFF = <u>White</u>, 0x000000 = <u>BLACK</u>)<br/>Click me to Lookup Color Codes</html>");
		fgColorlbl.setLocation(50, height / 2);
		fgColorlbl.setForeground(fg);
		fgColorlbl.setSize(200, 100);
		fgColorlbl.setLayout(null);
		fgColorlbl.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					open(new URI("https://www.webpagefx.com/web-design/color-picker/" + fgField.getText().substring(2)));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		pane.add(fgColorlbl);

		bgColorlbl = new JLabel("<html>Set the Background Color<br/>(Ex:0xFFFFFF = <u>White</u>, 0x000000 = <u>BLACK</u>)<br/>Click me to Lookup Color Codes</html>");
		bgColorlbl.setLocation(50, (height / 2) - 150);
		bgColorlbl.setForeground(fg);
		bgColorlbl.setSize(200, 100);
		bgColorlbl.setLayout(null);
		bgColorlbl.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					open(new URI("https://www.webpagefx.com/web-design/color-picker/" + bgField.getText().substring(2)));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		pane.add(bgColorlbl);

		// Sets up the Text Fields

		fontField = new JTextField();
		fontField.setLayout(null);
		fontField.setText(main.getFontSize() + "");
		fontField.setSize(75, 25);
		fontField.setVisible(true);
		fontField.setLocation((int) fontlbl.getLocation().getX() + fontlbl.getWidth(), (int) fontlbl.getLocation().getY() + 35);
		pane.add(fontField);

		fgField = new JTextField();
		fgField.setText("0x" + Integer.toHexString(fg.getRGB() & 0xffffff).toUpperCase());
		fgField.setSize(300, 25);
		fgField.setVisible(true);
		fgField.setLocation((int) fgColorlbl.getLocation().getX() + bgColorlbl.getWidth(), (int) fgColorlbl.getLocation().getY() + 35);
		fgField.setLayout(null);
		pane.add(fgField);

		bgField = new JTextField();
		bgField.setText("0x" + Integer.toHexString(bg.getRGB() & 0xffffff).toUpperCase());
		bgField.setSize(300, 25);
		bgField.setLocation((int) bgColorlbl.getLocation().getX() + bgColorlbl.getWidth(), (int) bgColorlbl.getLocation().getY() + 35);
		bgField.setVisible(true);
		bgField.setLayout(null);
		pane.add(bgField);

		// Sets up the Buttons
		reset = new JButton("Reset");
		reset.setVisible(true);
		reset.setSize(75, 25);
		reset.setLocation((width / 2) - 100, height - 100);
		reset.setBackground(bg);
		reset.setForeground(fg);
		reset.setLayout(null);
		reset.addActionListener(this);
		reset.setBorderPainted(false);
		pane.add(reset);

		apply = new JButton("Apply");
		apply.setVisible(true);
		apply.setSize(75, 25);
		apply.setLocation((width / 2) - 200, height - 100);
		apply.setBackground(bg);
		apply.setForeground(fg);
		apply.setLayout(null);
		apply.addActionListener(this);
		apply.setBorderPainted(false);
		pane.add(apply);

		close = new JButton("Close");
		close.setVisible(true);
		close.setSize(75, 25);
		close.setForeground(fg);
		close.setBackground(bg);
		close.setLocation(width / 2, height - 100);
		close.setLayout(null);
		close.setBorderPainted(false);
		close.addActionListener(this);
		pane.add(close);

		// Sets up the Window
		f = new JFrame();
		f.setTitle("Preferences");
		f.setSize(size);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setContentPane(pane);

	}

	private void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				print("Error: Could not open URI");
			}
		} else {
			print("Error: Environment was not supported");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(apply)) {
			String FolderName = "Settings/";
			String FileName = "Colors.ini";
			String fileContent = "bg:" + bgField.getText() + "\nfg:" + fgField.getText() + "\nft:" + fontField.getText();
			main.setBg(new Color(Integer.decode(bgField.getText())));
			main.setFg(new Color(Integer.decode(fgField.getText())));
			main.setFontSize(Integer.parseInt(fontField.getText()));
			print(fileContent);
			try {
				TextTransfer tf = new TextTransfer();
				tf.TextWriter(FileName, fileContent, FolderName, false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			f.dispose();
			new PreferenceWindow();
		} else if (e.getSource().equals(reset)) {
			String FolderName = "Settings/";
			String FileName = "Colors.ini";
			String fileContent = "bg:0x404040" + "\nfg:0xFFFFFF\nft:12";
			main.setBg(new Color(0x404040));
			main.setFg(new Color(0xFFFFFF));
			main.setFontSize(12);
			try {
				TextTransfer tf = new TextTransfer();
				tf.TextWriter(FileName, fileContent, FolderName, false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			f.dispose();
			new PreferenceWindow();
		} else if (e.getSource().equals(close)) {
			f.dispose();
		}
	}

}
