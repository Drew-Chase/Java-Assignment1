package tk.dccraft.utils.settings;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import javafx.scene.input.MouseDragEvent;
import tk.dccraft.init.Main;
import tk.dccraft.utils.BIOS;

/**
 * A Custom Preference Window. It Changes colors (background and foreground) for
 * both the Console and Main GUI Esthetics
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class PreferenceWindow extends Main {
	private Color fg = getFg();
	private Color bg = getBg();
	private Color cfg = getConsoleFg();
	private Color cbg = getConsoleBg();
	private int width = 800;
	private int height = 600;
	int fontSize = Main.getFontSize();
	private Dimension size = new Dimension(this.width, this.height);
	private BIOS io = new BIOS();
	private JButton reset;
	private JButton close;
	private JButton apply;
	private JButton next;
	private JButton prev;
	private JLabel cfgColorlbl;
	private JLabel cbgColorlbl;
	private JLabel fontlbl;
	private JLabel title;
	private JTextField cfgField;
	private JTextField cbgField;
	private JTextField fontField;
	private JLabel fgColorlbl;
	private JLabel bgColorlbl;
	private JTextField fgField;
	private JTextField bgField;
	private JPanel pane;
	private JPanel contentPane;
	private JFrame f;
	private JTabbedPane tab;
	private JCheckBox log;
	private JColorChooser chooser;
	private JDialog co;
	private int xOnFrame, yOnFrame;

	/**
	 * Initializes the Preferences Window, TabbedPane, and Control Buttons
	 */
	public PreferenceWindow() {
		print("Opening Preferences...");

		this.contentPane = new JPanel();
		this.contentPane.setSize(this.size);
		this.contentPane.setBackground(this.bg);
		this.contentPane.setForeground(this.fg);
		this.contentPane.setLayout(null);
		this.contentPane.addMouseListener(this);
		this.contentPane.addMouseMotionListener(this);

		this.tab = new JTabbedPane();
		this.tab.setSize(this.size.width, this.height - 75);
		this.tab.setForeground(this.fg);
		this.tab.setBackground(this.bg);
		this.tab.setLayout(null);
		this.contentPane.add(this.tab);

		this.f = new JFrame();
		this.f.setTitle("Preferences");
		this.f.setSize(this.size);
		this.f.setUndecorated(true);
		this.f.setVisible(true);
		this.f.setResizable(false);
		this.f.setDefaultCloseOperation(2);
		this.f.setLayout(null);
		this.f.setLocationRelativeTo(null);
		this.f.setContentPane(this.contentPane);
		this.f.addMouseListener(this);
		this.f.addMouseMotionListener(this);

		this.reset = new JButton("Reset");
		this.reset.setVisible(true);
		this.reset.setSize(75, 25);
		this.reset.setLocation(this.width / 2 - 100, this.height - 75);
		this.reset.setLayout(null);
		this.reset.addActionListener(this);
		this.reset.setBorderPainted(false);
		this.contentPane.add(this.reset);

		this.apply = new JButton("Apply");
		this.apply.setVisible(true);
		this.apply.setSize(75, 25);
		this.apply.setLocation(this.width / 2 - 200, this.height - 75);
		this.apply.setLayout(null);
		this.apply.addActionListener(this);
		this.apply.setBorderPainted(false);
		this.contentPane.add(this.apply);

		this.close = new JButton("Close");
		this.close.setVisible(true);
		this.close.setSize(75, 25);
		this.close.setLocation(this.width / 2, this.height - 75);
		this.close.setLayout(null);
		this.close.setBorderPainted(false);
		this.close.addActionListener(this);
		this.contentPane.add(this.close);

		this.next = new JButton("Next Page");
		this.next.setVisible(true);
		this.next.setSize(125, 25);
		this.next.setLocation(this.width - 125, this.height - 75);
		this.next.setLayout(null);
		this.next.setBorderPainted(false);
		this.next.addActionListener(this);
		this.contentPane.add(this.next);

		this.prev = new JButton("Previous Page");
		this.prev.setVisible(true);
		this.prev.setLocation(0, this.height - 75);
		this.prev.setSize(125, 25);
		this.prev.setLayout(null);
		this.prev.setBorderPainted(false);
		this.prev.addActionListener(this);
		this.contentPane.add(this.prev);
		mainTab();
		consoleTab();
		this.tab.setSelectedIndex(getIndex());
		initColors();
	}

	/**
	 * Initializes the TabbedPane, Control Buttons, and Preferences Window at a
	 * specified location p
	 * 
	 * @param p
	 */
	public PreferenceWindow(Point p) {
		print("Opening Preferences...");

		this.contentPane = new JPanel();
		this.contentPane.setSize(this.size);
		this.contentPane.setBackground(this.bg);
		this.contentPane.setForeground(this.fg);
		this.contentPane.setLayout(null);
		this.contentPane.addMouseListener(this);
		this.contentPane.addMouseMotionListener(this);

		this.tab = new JTabbedPane();
		this.tab.setSize(this.size.width, this.height - 75);
		this.tab.setForeground(this.fg);
		this.tab.setBackground(this.bg);
		this.tab.setLayout(null);
		this.contentPane.add(this.tab);

		this.f = new JFrame();
		this.f.setTitle("Preferences");
		this.f.setSize(this.size);
		this.f.setUndecorated(true);
		this.f.setVisible(true);
		this.f.setResizable(false);
		this.f.setDefaultCloseOperation(2);
		this.f.setLayout(null);
		this.f.setLocation(p);
		this.f.setContentPane(this.contentPane);
		this.f.addMouseListener(this);
		this.f.addMouseMotionListener(this);

		this.reset = new JButton("Reset");
		this.reset.setVisible(true);
		this.reset.setSize(75, 25);
		this.reset.setLocation(this.width / 2 - 100, this.height - 75);
		this.reset.setLayout(null);
		this.reset.addActionListener(this);
		this.reset.setBorderPainted(false);
		this.contentPane.add(this.reset);

		this.apply = new JButton("Apply");
		this.apply.setVisible(true);
		this.apply.setSize(75, 25);
		this.apply.setLocation(this.width / 2 - 200, this.height - 75);
		this.apply.setLayout(null);
		this.apply.addActionListener(this);
		this.apply.setBorderPainted(false);
		this.contentPane.add(this.apply);

		this.close = new JButton("Close");
		this.close.setVisible(true);
		this.close.setSize(75, 25);
		this.close.setLocation(this.width / 2, this.height - 75);
		this.close.setLayout(null);
		this.close.setBorderPainted(false);
		this.close.addActionListener(this);
		this.contentPane.add(this.close);

		this.next = new JButton("Next Page");
		this.next.setVisible(true);
		this.next.setSize(125, 25);
		this.next.setLocation(this.width - 125, this.height - 75);
		this.next.setLayout(null);
		this.next.setBorderPainted(false);
		this.next.addActionListener(this);
		this.contentPane.add(this.next);

		this.prev = new JButton("Previous Page");
		this.prev.setVisible(true);
		this.prev.setLocation(0, this.height - 75);
		this.prev.setSize(125, 25);
		this.prev.setLayout(null);
		this.prev.setBorderPainted(false);
		this.prev.addActionListener(this);
		this.contentPane.add(this.prev);
		mainTab();
		consoleTab();
		this.tab.setSelectedIndex(getIndex());
		initColors();
	}

	/**
	 * Initializes the Console Styling Page
	 */
	public void consoleTab() {
		this.pane = new JPanel();
		this.pane.setSize(this.size);
		this.pane.setBackground(this.cbg);
		this.pane.setForeground(this.cfg);
		this.pane.setLayout(null);
		this.tab.addTab("Console Style", this.pane);
		this.title = new JLabel("<html>Console<br/>Window<br/>Style</html>");
		this.title.setLayout(null);
		this.title.setFont(new Font(initFonts("BarcodeFont").getFontName(), Font.PLAIN, 120));
		this.title.setForeground(getTitleFg(cbg));
		this.title.setLocation((int) size.getWidth() - 250, 10);
		this.title.setSize(250, this.height - 50);
		this.pane.add(this.title);
		
		this.tab.addMouseListener(this);
		this.tab.addMouseMotionListener(this);

		chooser = new JColorChooser();

		this.fontlbl = new JLabel("Set font size");
		this.fontlbl.setLocation(50, 15);
		this.fontlbl.setForeground(this.cfg);
		this.fontlbl.setSize(100, 100);
		this.fontlbl.setLayout(null);
		this.fontlbl.setFont(getFont());
		this.pane.add(this.fontlbl);

		this.cfgColorlbl = new JLabel("<html>Set the Console Text Color<br/>Click me to Lookup Color Codes</html>");
		this.cfgColorlbl.setLocation(50, this.height / 2);
		this.cfgColorlbl.setForeground(this.cfg);
		this.cfgColorlbl.setSize(200, 100);
		this.cfgColorlbl.setLayout(null);
		this.cfgColorlbl.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				chooser.setColor(getConsoleFg());
				co = JColorChooser.createDialog(null, "Pick a Console Foreground Color", false, chooser, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Color c = chooser.getColor();
						cfgField.setText("" + Integer.toHexString(c.hashCode()).substring(2));
						Apply();
					}
				}, null);
				co.setVisible(true);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		this.pane.add(this.cfgColorlbl);

		this.cbgColorlbl = new JLabel("<html>Set the Console Background Color<br/>Click me to Lookup Color Codes</html>");
		this.cbgColorlbl.setLocation(50, this.height / 2 - 150);
		this.cbgColorlbl.setForeground(this.cfg);
		this.cbgColorlbl.setSize(200, 100);
		this.cbgColorlbl.setLayout(null);
		this.cbgColorlbl.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				chooser.setColor(getConsoleBg());
				co = JColorChooser.createDialog(null, "Pick a Console Background Color", false, chooser, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Color c = chooser.getColor();
						cbgField.setText("" + Integer.toHexString(c.hashCode()).substring(2));
						Apply();
					}
				}, null);
				co.setVisible(true);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		this.pane.add(this.cbgColorlbl);

		this.fontField = new JTextField();
		this.fontField.setLayout(null);
		this.fontField.setText(Main.getFontSize() + "");
		this.fontField.setSize(75, 25);
		this.fontField.setVisible(true);
		this.fontField.setLocation((int) this.fontlbl.getLocation().getX() + this.fontlbl.getWidth(), (int) this.fontlbl.getLocation().getY() + 35);
		this.pane.add(this.fontField);

		this.cfgField = new JTextField();
		this.cfgField.setText(Integer.toHexString(this.cfg.getRGB() & 0xFFFFFF).toUpperCase());
		this.cfgField.setSize(200, 25);
		this.cfgField.setVisible(true);
		this.cfgField.setLocation((int) this.cfgColorlbl.getLocation().getX() + this.cbgColorlbl.getWidth(), (int) this.cfgColorlbl.getLocation().getY() + 35);
		this.cfgField.setLayout(null);
		this.pane.add(this.cfgField);

		this.cbgField = new JTextField();
		this.cbgField.setText(Integer.toHexString(this.cbg.getRGB() & 0xFFFFFF).toUpperCase());
		this.cbgField.setSize(200, 25);
		this.cbgField.setLocation((int) this.cbgColorlbl.getLocation().getX() + this.cbgColorlbl.getWidth(), (int) this.cbgColorlbl.getLocation().getY() + 35);
		this.cbgField.setVisible(true);
		this.cbgField.setLayout(null);
		this.pane.add(this.cbgField);

		log = new JCheckBox("Record Debug-Log");
		log.setSelected(true);
		log.setSize((log.getText().length() * log.getFont().getSize()) + 10, 25);
		log.setLocation(cfgColorlbl.getLocation().x, cfgColorlbl.getLocation().y + (cfgField.getHeight() + log.getHeight() + 75));
		log.addActionListener(this);
		log.setForeground(cfg);
		log.setBackground(cbg);
		// pane.add(log);

		this.apply.setBackground(this.cbg);
		this.apply.setForeground(this.cfg);
		this.reset.setBackground(this.cbg);
		this.reset.setForeground(this.cfg);
		this.close.setBackground(this.cbg);
		this.close.setForeground(this.cfg);
		this.next.setForeground(this.cfg);
		this.next.setBackground(this.cbg);
		this.prev.setBackground(this.cbg);
		this.prev.setForeground(this.cfg);
		this.contentPane.setBackground(this.cbg);
		this.contentPane.setForeground(this.cfg);
	}

	/**
	 * Initializes the Main GUI Styling Page
	 */
	public void mainTab() {
		this.pane = new JPanel();
		this.pane.setSize(this.size);
		this.pane.setBackground(this.bg);
		this.pane.setForeground(this.fg);
		this.pane.setLayout(null);
		this.tab.addTab("Main Style", this.pane);
		this.tab.addMouseListener(this);
		this.tab.addMouseMotionListener(this);

		this.title = new JLabel("Main Windows Styling");
		this.title.setLayout(null);
		this.title.setForeground(getTitleFg());
		this.title.setFont(new Font(initFonts("ScorchedEarth.otf").getFontName(), Font.PLAIN, 48));
		this.title.setLocation(10, 20);
		this.title.setSize(this.width, 100);
		this.pane.add(this.title);

		this.fgColorlbl = new JLabel("<html>Set the Text Color<br/>Click me to Lookup Color Codes</html>");
		this.fgColorlbl.setLocation(50, this.height / 2);
		this.fgColorlbl.setForeground(this.fg);
		this.fgColorlbl.setSize(200, 100);
		this.fgColorlbl.setLayout(null);
		this.fgColorlbl.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				chooser.setColor(getFg());
				co = JColorChooser.createDialog(null, "Pick a Foreground Color", false, chooser, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Color c = chooser.getColor();
						fgField.setText("" + Integer.toHexString(c.hashCode()).substring(2));
						Apply();
					}
				}, null);
				co.setVisible(true);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		this.pane.add(this.fgColorlbl);

		this.bgColorlbl = new JLabel("<html>Set the Background Color<br/>Click me to Lookup Color Codes</html>");
		this.bgColorlbl.setLocation(50, this.height / 2 - 150);
		this.bgColorlbl.setForeground(this.fg);
		this.bgColorlbl.setSize(200, 100);
		this.bgColorlbl.setLayout(null);
		this.bgColorlbl.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				chooser.setColor(getBg());
				co = JColorChooser.createDialog(null, "Pick a Background Color", false, chooser, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Color c = chooser.getColor();
						bgField.setText("" + Integer.toHexString(c.hashCode()).substring(2));
						Apply();
					}
				}, null);
				co.setVisible(true);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		this.pane.add(this.bgColorlbl);

		this.fgField = new JTextField();
		this.fgField.setText(Integer.toHexString(this.fg.getRGB() & 0xFFFFFF).toUpperCase());
		this.fgField.setSize(300, 25);
		this.fgField.setVisible(true);
		this.fgField.setLocation((int) this.fgColorlbl.getLocation().getX() + this.bgColorlbl.getWidth(), (int) this.fgColorlbl.getLocation().getY() + 35);
		this.fgField.setLayout(null);
		this.pane.add(this.fgField);

		this.bgField = new JTextField();
		this.bgField.setText(Integer.toHexString(this.bg.getRGB() & 0xFFFFFF).toUpperCase());
		this.bgField.setSize(300, 25);
		this.bgField.setLocation((int) this.bgColorlbl.getLocation().getX() + this.bgColorlbl.getWidth(), (int) this.bgColorlbl.getLocation().getY() + 35);
		this.bgField.setVisible(true);
		this.bgField.setLayout(null);
		this.pane.add(this.bgField);
		initColors();
	}

	/**
	 * Opens URL in Default Web Browser
	 * 
	 * @param uri
	 */
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

	/**
	 * Changes Styling based on Page ID
	 */
	public void initColors() {
		if (tab.getSelectedIndex() == 0) {
			this.apply.setBackground(this.bg);
			this.apply.setForeground(this.fg);
			this.reset.setBackground(this.bg);
			this.reset.setForeground(this.fg);
			this.close.setBackground(this.bg);
			this.close.setForeground(this.fg);
			this.next.setForeground(this.fg);
			this.next.setBackground(this.bg);
			this.prev.setBackground(this.bg);
			this.prev.setForeground(this.fg);
			this.contentPane.setBackground(this.bg);
			this.contentPane.setForeground(this.fg);
			this.bgColorlbl.setForeground(this.fg);
			this.fgColorlbl.setForeground(this.fg);
		} else {
			this.apply.setBackground(this.cbg);
			this.apply.setForeground(this.cfg);
			this.reset.setBackground(this.cbg);
			this.reset.setForeground(this.cfg);
			this.close.setBackground(this.cbg);
			this.close.setForeground(this.cfg);
			this.next.setForeground(this.cfg);
			this.next.setBackground(this.cbg);
			this.prev.setBackground(this.cbg);
			this.prev.setForeground(this.cfg);
			this.contentPane.setBackground(this.cbg);
			this.contentPane.setForeground(this.cfg);
			this.cbgColorlbl.setForeground(this.cfg);
			this.cfgColorlbl.setForeground(this.cfg);
			this.fontlbl.setForeground(this.cfg);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.next)) {
			if (this.tab.getSelectedIndex() == 0) {
				this.tab.setSelectedIndex(1);
				setIndex(1);
				initColors();
			} else {
				this.tab.setSelectedIndex(0);
				setIndex(0);
				initColors();
			}
		} else if (e.getSource().equals(this.prev)) {
			if (this.tab.getSelectedIndex() == 1) {
				this.tab.setSelectedIndex(0);
				setIndex(0);
				initColors();
			} else {
				this.tab.setSelectedIndex(1);
				setIndex(1);
				initColors();
			}
		} else if (e.getSource().equals(this.apply)) {
			Apply();
		} else if (e.getSource().equals(this.reset)) {
			String FolderName = Main.root + "\\Settings\\";
			String FileName = "Pref.ini";
			String fileContent = "bg:0x404040\nfg:0xFFFFFF\ncbg:0x000000\ncfg:0xFFFFFF\nft:12";
			Main.setBg(new Color(4210752));
			Main.setFg(new Color(16777215));
			Main.setConsoleBg(new Color(0));
			Main.setConsoleFg(new Color(16777215));
			Main.setFontSize(12);
			try {
				this.io.TextWriter(FileName, fileContent, FolderName, false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			updateConsole();
			initColors();
			this.f.dispose();
			new PreferenceWindow(f.getLocation());
		} else if (e.getSource().equals(log)) {
			if (log.isSelected()) {
				print("Log Enabled");
			} else {
				print("Log Disabled");
			}
			setShouldLog(log.isSelected());
		} else if (e.getSource().equals(this.close)) {
			this.f.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xOnFrame = e.getX();
		yOnFrame = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = (int) e.getXOnScreen() - xOnFrame;
		int y = (int) e.getYOnScreen() - yOnFrame;

		f.setLocation(x, y);
	}

	private void Apply() {

		setIndex(this.tab.getSelectedIndex());
		String FolderName = Main.root + "\\Settings\\";
		String FileName = "Pref.ini";
		String fileContent = "bg:0x" + this.bgField.getText() + "\nfg:0x" + this.fgField.getText() + "\nft:" + this.fontField.getText() + "\ncbg:0x" + this.cbgField.getText() + "\ncfg:0x" + this.cfgField.getText() + "\nindex:" + getIndex() + "\nlog:" + log.isSelected();
		Main.setBg(new Color(Integer.decode("0x" + this.bgField.getText()).intValue()));
		Main.setFg(new Color(Integer.decode("0x" + this.fgField.getText()).intValue()));
		Main.setConsoleBg(new Color(Integer.decode("0x" + this.cbgField.getText()).intValue()));
		Main.setConsoleFg(new Color(Integer.decode("0x" + this.cfgField.getText()).intValue()));
		Main.setFontSize(Integer.parseInt(this.fontField.getText()));
		try {
			this.io.TextWriter(FileName, fileContent, FolderName, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		updateConsole();
		initColors();
		this.f.dispose();
		new PreferenceWindow(f.getLocation());

	}
}
