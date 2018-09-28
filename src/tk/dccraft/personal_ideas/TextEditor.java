package tk.dccraft.personal_ideas;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tk.dccraft.init.Main;

public class TextEditor extends Main {

	private JTextArea area;
	private JFrame f;
	private JPanel pane;
	private Dimension size = new Dimension(800,600);

	public TextEditor() {
		initFrame();
	}

	public TextEditor(String file) {
		initFrame();
		f.setTitle(file);
	}

	public void initFrame() {
		pane = new JPanel();
		pane.setSize(size);
		pane.setLayout(null);
		pane.setBackground(getBg());
		pane.setForeground(getFg());

		f = new JFrame();
		f.setVisible(true);
		f.setSize(size);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setContentPane(pane);
	}

}
