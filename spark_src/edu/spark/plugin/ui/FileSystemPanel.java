package edu.spark.plugin.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FileSystemPanel extends JPanel {

	/**
	 * 
	 */
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
		this.add(new JButton("first file"), getGridBagConstraints(0, 1.0));
		this.add(new JButton("second file"), getGridBagConstraints(1, 1.0));
		this.add(new JButton("third file"), getGridBagConstraints(2, 1.0));
		
		JPanel uploadPanel = new JPanel();
		uploadPanel.add(new JButton("upload part"));
		this.add(uploadPanel, getGridBagConstraints(3, 1.0));
		
		
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

}
