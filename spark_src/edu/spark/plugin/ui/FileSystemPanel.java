package edu.spark.plugin.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.spark.SparkManager;

import edu.spark.plugin.FileSystemPlugin;

public class FileSystemPanel extends JPanel {

	private static final long serialVersionUID = -7487210399307589176L;
	
	public FileSystemPanel()
	{
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.setBackground(Color.white);
		this.setAlignmentX(LEFT_ALIGNMENT);
		
		FilePanel[] files = {new FilePanel("file_one.txt"), new FilePanel("file_two.txt"), new FilePanel("file_three.txt")};
		
		for (int i = 0; i < files.length; i++) 
			this.add(files[i], getGridBagConstraints(i, 0.0));
		
		JPanel uploadPanel = new JPanel();
		JButton uploadButton = new JButton("Upload File");
		uploadButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				JDialog dialog = new JDialog(SparkManager.getMainWindow());
				dialog.setSize(450, 400);
				
				dialog.setContentPane(getJContentPane());

				dialog.setLocation(SparkManager.getMainWindow().getX(),
						SparkManager.getMainWindow().getY());
				dialog.setVisible(true);
			}

			private Container getJContentPane() {
				JPanel panel = new JPanel();
				panel.add(new JFileChooser());
				return panel;
			}
			
		});
		uploadPanel.add(uploadButton);
		uploadPanel.setBackground(Color.white);
		uploadPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		this.add(uploadPanel, getGridBagConstraints(files.length, 1.0));
		
		
	}
	
	private GridBagConstraints getGridBagConstraints(int rowNum, double yWeight)
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = rowNum;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = yWeight;
		return gridBagConstraints;
	}
	
	private class FilePanel extends JPanel
	{
		private static final long serialVersionUID = -8932261460593637196L;
		
		public FilePanel(String name)
		{
			super();
			this.setLayout(new GridBagLayout());
			this.setBackground(Color.white);
			add(new JLabel(getIcon("images/things.png")));
			add(new JLabel(name));
			this.setAlignmentX(LEFT_ALIGNMENT);
		}
		
		private Icon getIcon(String filePath)
		{
			return new ImageIcon(FileSystemPlugin.class.getResource(filePath));
		}
	}

}
