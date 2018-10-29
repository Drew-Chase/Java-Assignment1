package tk.dccraft.init;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Allows easier creation of custom windows
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class CustomWindow extends Main {

	// private Dimension size;
	private JFrame f;
	private int xOnFrame, yOnFrame;
	private JTextField t;

	public String initStringValue = "";

	/**
	 * Creates a Window
	 * 
	 * @param size
	 * @param title
	 * @return Window Object
	 */
	public JFrame createWindow(Dimension size, String title) {
		// setSize(size);
		f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setUndecorated(true);
		f.setSize(size);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setBackground(getBg());
		f.setForeground(getFg());
		f.setContentPane(createContentPane());
		f.addMouseListener(this);
		f.addMouseMotionListener(this);

		return f;
	}

	/**
	 * Creates a Window next to another Component
	 * 
	 * @param size
	 * @param title
	 * @param neighbor
	 * @return
	 */
	public JFrame createWindow(Dimension size, String title, Component neighbor) {
		// setSize(size);
		f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocationRelativeTo(neighbor);
		f.setUndecorated(true);
		f.setSize(size);
		f.setVisible(true);
		f.setBackground(getBg());
		f.setForeground(getFg());
		f.setContentPane(createContentPane());
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		return f;
	}

	/**
	 * Creates a Window at a location on screen
	 * 
	 * @param size
	 * @param title
	 * @param locOnScreen
	 * @return
	 */
	public JFrame createWindow(Dimension size, String title, Point locOnScreen) {
		f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocation(locOnScreen);
		f.setUndecorated(true);
		f.setVisible(true);
		f.setSize(size);
		f.setBackground(getBg());
		f.setForeground(getFg());
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		return f;
	}

	/**
	 * Gets the Windows Dimenions
	 * 
	 * @return
	 */
	public Dimension getSize() {
		return f.getSize();
	}

	/**
	 * Sets up the Content Pane
	 * 
	 * @return
	 */
	public JPanel createContentPane() {
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(getBg());
		pane.setForeground(getFg());
		pane.setSize(getSize());
		pane.addMouseListener(this);
		pane.addMouseMotionListener(this);
		return pane;
	}

	/**
	 * Sets up the Content Pane with Title Label
	 * 
	 * @param title
	 * @param fontName
	 * @return
	 */
	public JPanel createContentPane(String title, String fontName) {
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(getBg());
		pane.setForeground(getFg());
		pane.setSize(getSize());
		pane.addMouseListener(this);
		pane.addMouseMotionListener(this);

		JLabel l = createLabel(title, new Point(25, 25));
		l.setForeground(getTitleFg());
		l.setFont(new Font(initFonts(fontName).getFontName(), Font.PLAIN, 28));
		l.setSize(title.length() * l.getFont().getSize() + 15, l.getFont().getSize() + 15);
		pane.add(l);

		return pane;
	}

	/**
	 * Button Prefab with size
	 * 
	 * @param name
	 * @param loc
	 * @param size
	 * @return
	 */
	public JButton createButton(String name, Point loc, Dimension size) {
		JButton b = new JButton(name);
		b.setFont(new Font("Arial", Font.PLAIN, 12));
		b.setVisible(true);
		b.setSize(size);
		b.setLayout(null);
		b.setBorderPainted(false);
		b.setBackground(getBg());
		b.setForeground(getFg());
		b.setLocation(loc);
		return b;
	}

	/**
	 * Button Prefab with intuitive size
	 * 
	 * @param name
	 * @param loc
	 * @return
	 */
	public JButton createButton(String name, Point loc) {
		JButton b = new JButton(name);
		b.setFont(new Font("Arial", Font.PLAIN, 12));
		b.setSize(b.getText().length() * b.getFont().getSize(), b.getFont().getSize() + 2);
		b.setLayout(null);
		b.setBorderPainted(false);
		b.setBackground(getBg());
		b.setForeground(getFg());
		b.setLocation(loc);
		b.setVisible(true);
		return b;
	}

	/**
	 * Creates the Husk of a button
	 * 
	 * @param name
	 * @return
	 */
	public JButton createButton(String name) {
		JButton b = new JButton(name);
		b.setFont(new Font("Arial", Font.PLAIN, 12));
		b.setSize(b.getText().length() * b.getFont().getSize(), b.getFont().getSize() + 2);
		b.setLayout(null);
		b.setBorderPainted(false);
		b.setBackground(getBg());
		b.setForeground(getFg());
		b.setVisible(true);
		return b;
	}

	/**
	 * Label Prefab
	 * 
	 * @param name
	 * @param loc
	 * @param size
	 * @return
	 */
	public JLabel createLabel(String name, Point loc, Dimension size) {
		JLabel l = new JLabel(name);
		l.setSize(size);
		l.setLayout(null);
		l.setBackground(getBg());
		l.setForeground(getFg());
		l.setLocation(loc);
		l.setVisible(true);
		return l;
	}

	/**
	 * Label Prefab with Dynamic Sizing
	 * 
	 * @param name
	 * @param loc
	 * @return
	 */
	public JLabel createLabel(String name, Point loc) {
		JLabel l = new JLabel(name);
		l.setVisible(true);
		l.setSize(l.getText().length() * l.getFont().getSize(), l.getFont().getSize() + 2);
		l.setLayout(null);
		l.setBackground(getBg());
		l.setForeground(getFg());
		l.setLocation(loc);
		return l;
	}

	/**
	 * Creates a TextField
	 * 
	 * @param name
	 * @param loc
	 * @param size
	 * @return
	 */
	public JTextField createTextBox(String name, Point loc, Dimension size) {
		t = new JTextField(name);
		initStringValue = name;
		t.setSize(size);
		t.setLocation(loc);
		t.setVisible(true);
		t.addFocusListener(this);
		return t;
	}

	/**
	 * Sets up the Basics of a Custom Window -- Grabs the Mouse Position when
	 * frame is grabbed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		xOnFrame = e.getX();
		yOnFrame = e.getY();
	}

	/**
	 * Sets up the Basics of a Custom Window -- Moves the Window based on Mouse
	 * Dragging from anywhere on the window
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = (int) e.getXOnScreen() - xOnFrame;
		int y = (int) e.getYOnScreen() - yOnFrame;

		f.setLocation(x, y);
	}

	/**
	 * Basic GUI Etiquette -- Clearing Text only when it should
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (t.getText().equals(initStringValue)) {
			t.setText("");
		}
	}

	/**
	 * Basic GUI Etiquette -- Reloading Standard Text When it should
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (t.getText().equals("")) {
			t.setText(initStringValue);
		}
	}

}
