import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AboutUs extends JFrame implements ActionListener {

    private JPanel mainFrame;
    private JPanel aboutUsPanel;
    private JPanel aboutUsTitleContainer;
    private JLabel aboutUsLabel;
    private JLabel mainTitle;
    private JLabel signatureLabel;
    private JScrollPane mainScrollPane;
    private JLabel spacer7;
    private JButton homeButton1;
    private JLabel img1;
    private JLabel img2;
    private JLabel img3;
    private JLabel img4;

    AboutUs(){

        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000,700);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setTitle("About Us");
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(18);

        //home buttons
        ImageIcon homeIcon = new ImageIcon("src/Icons/homeButtonIcon1.png");
        Image scaledImage = homeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(scaledImage);
        homeButton1.setIcon(homeIcon);
        homeButton1.addActionListener(this);

        ImageIcon icon = new ImageIcon(HomePage.class.getResource("Icons/windowLogo.png"));
        this.setIconImage(icon.getImage());

        File aboutUsTxTFile = new File("src/main/java/Other/AboutUs.txt");
        openFile(aboutUsTxTFile);

        homeButton1.addActionListener(this);

        //For CEO
        ImageIcon CEO = new ImageIcon("src/main/java/Images/about1.jpg");
        Image image1 = CEO.getImage();
        Image newimg1 = image1.getScaledInstance(350, 275,  java.awt.Image.SCALE_SMOOTH);
        CEO = new ImageIcon(newimg1);
        img1.setIcon(CEO);

        //For lobby
        ImageIcon lobby = new ImageIcon("src/main/java/Images/about3.jpg");
        Image image2 = lobby.getImage();
        Image newimg2 = image2.getScaledInstance(350, 275,  java.awt.Image.SCALE_SMOOTH);
        lobby = new ImageIcon(newimg2);
        img2.setIcon(lobby);

        //For Ghosts
        ImageIcon ghost = new ImageIcon("src/main/java/Images/about2.jpg");
        Image image3 = ghost.getImage();
        Image newimg3 = image3.getScaledInstance(350, 275,  java.awt.Image.SCALE_SMOOTH);
        ghost = new ImageIcon(newimg3);
        img3.setIcon(ghost);

        //For money
        ImageIcon money = new ImageIcon("src/main/java/Images/about4.png");
        Image image4 = money.getImage();
        Image newimg4 = image4.getScaledInstance(350, 275,  java.awt.Image.SCALE_SMOOTH);
        money = new ImageIcon(newimg4);
        img4.setIcon(money);

        this.setVisible(true);

    }

    private void openFile(File file) {
        // No need for JFileChooser, as the file is already specified
        displayFileContents(file);
    }

    private void displayFileContents(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                // Replace spaces with &nbsp; for indents and use <br> for newlines
                String formattedLine = line.replaceAll(" ", "&nbsp;").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;") + "<br>";
                content.append(formattedLine);
            }

            aboutUsLabel.setText("<html>" + content.toString() + "</html>");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeButton1){
            this.dispose();
        }
    }

}
