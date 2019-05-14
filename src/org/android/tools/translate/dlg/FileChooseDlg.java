package org.android.tools.translate.dlg;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class FileChooseDlg extends JFrame {
	JButton openBtn = null;
	JTextField mResPathField = null;
	JButton startButton = null;
	JTextField cfgField;
	DetectAction actionListener; 
	
	public static void showDlg(DetectAction detectAction) {
		
		FileChooseDlg choose = new FileChooseDlg();
		choose.actionListener = detectAction;
		choose.setVisible(true);
	}

	public FileChooseDlg() throws HeadlessException {
		setTitle("format check");
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(3, 1));
		
		JPanel resPanel = new JPanel(new GridLayout(2,2));		
		this.openBtn = new JButton("");
		openBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jFileChooser.showDialog(new JLabel(), "Select");
				File file = jFileChooser.getSelectedFile(); 
				mResPathField.setText(file.getAbsolutePath());
				
			}
		}); 
		
		mResPathField = new JTextField("E:\\LionMobi\\lionmobi\\2019\\ca\\lioncalculator6\\app\\src\\main\\res"); 
		resPanel.add(openBtn);
		resPanel.add(mResPathField);

		
		JPanel startPanel = new JPanel();
		startButton = new JButton("");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.detector(mResPathField.getText(), cfgField.getText());
				
			}
		});
		startPanel.add(startButton);
		
		
		JPanel cfgPanel = new JPanel(new GridLayout(2,2));
		cfgField = new JTextField("E:\\Eclipse\\workspace\\DetectForamtError\\src\\formatdetector\\cfg\\language.json");
		JButton cfgChooseBtn = new JButton(")");
		cfgChooseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jFileChooser.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public boolean accept(File f) {
						if (f.getName().toLowerCase().endsWith("json") || f.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				jFileChooser.showDialog(new JLabel(), "Select");
				File file = jFileChooser.getSelectedFile(); 
				cfgField.setText(file.getAbsolutePath());
				
			}
		});
		cfgPanel.add(cfgChooseBtn);
		cfgPanel.add(cfgField);
		
		

		container.add(resPanel);
		container.add(cfgPanel);
		container.add(startPanel);
		
		this.setBounds(400,200,500,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	} 
	
	public interface DetectAction{
		void detector(String resPath, String cfgPath);
	}
}
