import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SecureFileShredder extends JFrame implements ActionListener {
    private JButton selectFileButton;
    private JButton selectFolderButton;

    public SecureFileShredder() {
        setTitle("Secure File Shredder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        selectFileButton = new JButton("Select File");
        selectFileButton.addActionListener(this);

        selectFolderButton = new JButton("Select Folder");
        selectFolderButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(selectFileButton);
        panel.add(selectFolderButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                shredFile(selectedFile);
            }
        } else if (e.getSource() == selectFolderButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                shredFolder(selectedFolder);
            }
        }
    }

    private void shredFile(File file) {
        if (file.exists()) {
            if (file.delete()) {
                JOptionPane.showMessageDialog(this, "File shredded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "An error occurred while shredding the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void shredFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        shredFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            if (folder.delete()) {
                JOptionPane.showMessageDialog(this, "Folder shredded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "An error occurred while shredding the folder.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Folder not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SecureFileShredder fileShredder = new SecureFileShredder();
            fileShredder.setVisible(true);
        });
    }
}
