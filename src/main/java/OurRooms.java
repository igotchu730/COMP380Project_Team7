import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OurRooms extends JFrame implements ActionListener {

    private JPanel mainFrame;
    private JScrollPane mainScrollPane;
    private JPanel ourRoomsTitleContainer;
    private JPanel mainPanel;
    private JLabel singleRoomTitle;
    private JLabel doubleRoomTitle;
    private JLabel familyRoomTitle;
    private JLabel luxuryRoomTitle;
    private JLabel slogan;
    private JLabel mainTitle;
    private JButton homeButton1;
    private JButton homeButton2;
    private JLabel spacer1;
    private JLabel spacer2;
    private JLabel spacer3;
    private JLabel spacer4;
    private JLabel spacer5;
    private JLabel spacer6;
    private JLabel spacer7;
    private JLabel descipt1;
    private JLabel descript2;
    private JLabel descript3;
    private JLabel descript4;
    private JLabel descript1;
    private JPanel d1container;
    private JPanel d2container;
    private JPanel d3container;
    private JPanel d4container;

    OurRooms(){
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1000,1000);
        //this.setVisible(true);
        //this.setLocationRelativeTo(null);

        mainScrollPane.getVerticalScrollBar().setUnitIncrement(18);
        homeButton1.addActionListener(this);
        homeButton2.addActionListener(this);

        //For single room
        ImageIcon singleRoom = new ImageIcon("src/main/java/Images/SingleRoom.png");
        Image image1 = singleRoom.getImage();
        Image newimg1 = image1.getScaledInstance(720, 405,  java.awt.Image.SCALE_SMOOTH);
        singleRoom = new ImageIcon(newimg1);
        singleRoomTitle.setIcon(singleRoom);

        d1container.setPreferredSize(new Dimension(400,250));
        descript1.setFont(new Font("Calibri",Font.PLAIN,14));
        descript1.setText(
                "<html>" +
                        "<br/><u>Description</u><br/><br/>" +
                        "Find desolation and an escape in the comfort of this hotel room. <br/>" +
                        "Many happy customers have dubbed this room 'The Prison Suite'. " +
                        "And yes, it does come with a room key. <br/><br/>" +

                        "<u>Room Information</u><br/><br/>" +
                        "- Sleeps 2<br/>" +
                        "- 1 Queen Bed<br/>" +
                        "- Poor air conditioning<br/>" +
                        "- Iron/ironing board<br/>" +
                        "- Room is haunted<br/>" +
                        "- 1 chair<br/>" +
                        "- Non-smoking room<br/>" +
                        "- Single toilet and sink<br/>" +
                        "</html>"
        );


        //For double room
        ImageIcon doubleRoom = new ImageIcon("src/main/java/Images/DoubleRoom.png");
        Image image2 = doubleRoom.getImage();
        Image newimg2 = image2.getScaledInstance(720, 405,  java.awt.Image.SCALE_SMOOTH);
        doubleRoom = new ImageIcon(newimg2);
        doubleRoomTitle.setIcon(doubleRoom);

        d2container.setPreferredSize(new Dimension(400,250));
        descript2.setFont(new Font("Calibri",Font.PLAIN,14));
        descript2.setText(
                "<html>" +
                        "<br/><u>Description</u><br/><br/>" +
                        "For those who appreciate open air design, this room is for you. " +
                        "Guests love the constant light breeze that<br/> this room offers.<br/><br/> " +

                        "<u>Room Information</u><br/><br/>" +
                        "- Sleeps 4<br/>" +
                        "- 2 Queen Beds<br/>" +
                        "- Air conditioning<br/>" +
                        "- Iron/ironing board<br/>" +
                        "- Hairdryer<br/>" +
                        "- Bathrobes<br/>" +
                        "- 32-inch HDTV<br/>" +
                        "- Coffee/tea maker<br/>" +
                        "- Desk, ergonomic chair, and electrical outlet<br/>" +
                        "- Non-smoking room<br/>" +
                        "- 1 toilet and sink<br/>" +
                        "- Hole in the wall and broken windows<br/>" +
                        "</html>"
        );


        //For family room
        ImageIcon familyRoom = new ImageIcon("src/main/java/Images/FamilyRoom.png");
        Image image3 = familyRoom.getImage();
        Image newimg3 = image3.getScaledInstance(720, 405,  java.awt.Image.SCALE_SMOOTH);
        familyRoom = new ImageIcon(newimg3);
        familyRoomTitle.setIcon(familyRoom);

        d3container.setPreferredSize(new Dimension(400,250));
        descript3.setFont(new Font("Calibri",Font.PLAIN,14));
        descript3.setText(
                "<html>" +
                        "<br/><u>Description</u><br/><br/>" +
                        "Is your family vacation looking expensive? Looking to save money on your stay? We have bad news. What<br/>" +
                        " you’ll find here is an average room, for the average family, at above average cost. <br/><br/>" +

                        "<u>Room Information</u><br/><br/>" +
                        "- Sleeps 6<br/>" +
                        "- 2 Queen Beds, 1 pullout sofa<br/>" +
                        "- 2 Bathrooms <br/>" +
                        "- Living Area<br/>" +
                        "- Kitchenette<br/>" +
                        "- Dining Area<br/>" +
                        "- Coffee/tea maker<br/>" +
                        "- Desk, ergonomic chair, and electrical outlet<br/>" +
                        "- Mini refrigerator<br/>" +
                        "- Air conditioning<br/>" +
                        "- Iron/ironing board<br/>" +
                        "- 42-inch HDTV<br/>" +
                        "- Hairdryer<br/>" +
                        "- Non-smoking room<br/>" +
                        "</html>"
        );


        //For luxury room
        ImageIcon luxuryRoom = new ImageIcon("src/main/java/Images/LuxuryRoom.png");
        Image image4 = luxuryRoom.getImage();
        Image newimg4 = image4.getScaledInstance(720, 405,  java.awt.Image.SCALE_SMOOTH);
        luxuryRoom = new ImageIcon(newimg4);
        luxuryRoomTitle.setIcon(luxuryRoom);

        d4container.setPreferredSize(new Dimension(400,250));
        descript4.setFont(new Font("Calibri",Font.PLAIN,14));
        descript4.setText(
                "<html>" +
                        "<br/><u>Description</u><br/><br/>" +
                        "Purses feeling a little heavy? Wallets too thick? Don't worry, we'll take those off your hands. For a ridiculous <br/>" +
                        "price, this scam of a room will make you feel like an elite. <br/><br/>" +

                        "<u>Room Information</u><br/><br/>" +
                        "- Sleeps 2<br/>" +
                        "- 1 Cali King Bed<br/>" +
                        "- 2 Bathrooms <br/>" +
                        "- Living Area<br/>" +
                        "- Kitchenette<br/>" +
                        "- Dining Area<br/>" +
                        "- Balcony<br/>" +
                        "- Jacuzzi <br/>" +
                        "- Wet Bar<br/>" +
                        "- Coffee/tea maker<br/>" +
                        "- Desk, ergonomic chair, and electrical outlet<br/>" +
                        "- Refrigerator<br/>" +
                        "- Air conditioning<br/>" +
                        "- Iron/ironing board<br/>" +
                        "- 98-inch HDTV<br/>" +
                        "- Hairdryer<br/>" +
                        "- Smoking room<br/>" +
                        "</html>"
        );

        //home buttons
        ImageIcon homeIcon = new ImageIcon("src/Icons/homeButtonIcon1.png");
        Image scaledImage = homeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(scaledImage);
        homeButton1.setIcon(homeIcon);
        homeButton2.setIcon(homeIcon);

        this.setVisible(true);
    }

        //home button


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeButton1){
            dispose();
        }
        if(e.getSource() == homeButton2){
            dispose();
        }

    }

}
