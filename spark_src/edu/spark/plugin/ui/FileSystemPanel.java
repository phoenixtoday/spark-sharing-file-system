package edu.spark.plugin.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;
import org.jivesoftware.spark.SparkManager;

import edu.spark.plugin.FileSystemPlugin;

public class FileSystemPanel extends JPanel {

	private static final long serialVersionUID = -7487210399307589176L;

	private JDialog fileChooserDialog = new JDialog(SparkManager
			.getMainWindow());

	private final JTextField uploadTextField = new JTextField();

	private JFileChooser fileChooser = new JFileChooser();

	public FileSystemPanel() {
		super();
		initialize();
	}

	private void initialize() {

		fileChooserDialog.setSize(450, 400);

		fileChooserDialog.setContentPane(fileChooser);

		fileChooserDialog.setLocation(SparkManager.getMainWindow().getX(),
				SparkManager.getMainWindow().getY());
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.setBackground(Color.white);

		JPanel fileContainer = new JPanel();
		fileContainer.setLayout(new GridBagLayout());
		fileContainer.setBackground(Color.white);

		FilePanel[] files = { new FilePanel("the_file_one.txt"),
				new FilePanel("the_file_two.txt"),
				new FilePanel("the_file_three.txt") };

		for (int i = 0; i < files.length; i++)
			fileContainer.add(files[i], getGridBagConstraints(i, 0.0, 0.0, 0));
		fileContainer.add(new JLabel(" "), getGridBagConstraints(files.length,
				3, 3, 1));

		this.add(fileContainer, BorderLayout.CENTER);

		JPanel uploadPanel = new JPanel();

		uploadTextField.setColumns(20);
		final JButton uploadButton = new JButton("Upload File");
		uploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == uploadButton) {
					fileChooserDialog.setVisible(true);
					int returnVal = fileChooser
							.showOpenDialog(FileSystemPanel.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						uploadTextField.setText(file.getName());
						FileTransferManager transferManager = new FileTransferManager(SparkManager.getConnection());
						OutgoingFileTransfer transfer = transferManager.createOutgoingFileTransfer("aaa");
	                    try {
							transfer.sendFile(file, "Sending");
						} catch (XMPPException e) {
							e.printStackTrace();
						}
					} else if (returnVal == JFileChooser.CANCEL_OPTION)
						fileChooserDialog.setVisible(false);
				}
			}
		});
		uploadPanel.add(uploadTextField);
		uploadPanel.add(uploadButton);
		uploadPanel.setBackground(Color.white);
		uploadPanel.setAlignmentX(LEFT_ALIGNMENT);

		this.add(uploadPanel, BorderLayout.SOUTH);

	}

	private GridBagConstraints getGridBagConstraints(int rowNum,
			double xWeight, double yWeight, int columnNum) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = columnNum;
		gridBagConstraints.gridy = rowNum;
		gridBagConstraints.weightx = xWeight;
		gridBagConstraints.weighty = yWeight;
		return gridBagConstraints;
	}

	private class FilePanel extends JPanel {
		private static final long serialVersionUID = -8932261460593637196L;

		public FilePanel(String name) {
			super();
			this.setLayout(new GridBagLayout());
			this.setBackground(Color.white);
			add(new JLabel(getIcon("images/file_share.png")));
			add(new JLabel(name));
			this.setAlignmentX(LEFT_ALIGNMENT);
		}

		private Icon getIcon(String filePath) {
			return new ImageIcon(FileSystemPlugin.class.getResource(filePath));
		}
	}

}
