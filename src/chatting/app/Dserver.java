package chatting.app;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.KeyEvent ;


public class Dserver extends JFrame implements ActionListener{
    JPanel p1;
    JTextField t1;
    static JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Boolean typing;



    public Dserver() {


        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(18, 140, 126));
        p1.setBounds(0, 0, 400, 60);
        add(p1);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 15, 30, 30);
        l1.setForeground(Color.white);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(65, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(23, 4, 65, 50);
        p1.add(l2);
        JLabel l3 = new JLabel("Victim");
        l3.setBounds(85, 16, 200, 15);
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        l3.setForeground(Color.white);
        p1.add(l3);
        getContentPane().setBackground(Color.white);
        JLabel l4 = new JLabel("Active now");
        l4.setBounds(85, 31, 200, 15);
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.white);
        p1.add(l4);
        Timer t = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!typing){
                    l4.setText("Active Now");
                }

            }
        });
        t.setInitialDelay(2000);
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(275, 15, 30, 30);
        p1.add(l5);
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l6 = new JLabel(i12);
        l6.setBounds(325, 15, 35, 35);
        p1.add(l6);
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(15, 30, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel l7 = new JLabel(i15);
        l7.setBounds(375, 15, 15, 30);
        p1.add(l7);
        getContentPane().setBackground(Color.white);

        t1 = new JTextField();
        t1.setBounds(5, 590, 320, 50);
        t1.setFont(new Font("SAN_SERIF", Font.ITALIC, 16));
        t1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                l4.setText("typing...");
                t.stop();
                typing = true;

            }


            public void keyReleased(KeyEvent e) {
                typing = false;
                if(!t.isRunning()){
                    t.start();
                }
            }

        });
        add(t1);
        b1 = new JButton("Send");
        b1.setBounds(330, 590, 65, 50);
        b1.setBackground(new Color(18, 140, 126));
        b1.setForeground(new Color(255, 255, 255));
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        b1.addActionListener(this);
        add(b1);
        a1 = new JTextArea();
        a1.setBounds(5, 65, 400, 595);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        a1.setBackground(new Color(255, 255, 255));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);


        setLayout(null);
        setSize(400, 650);
        setUndecorated(true);
        setLocation(150, 50);
        setVisible(true);
    }
/*
    public void pressedEnterkey(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try{
                String out1 = t1.getText();
                a1.setText(a1.getText() + "\n\t\t" + out1);
                dout.writeUTF(out1);
                t1.setText("");
            }catch(Exception e){

            }
        }
    }

 */


    public void actionPerformed(ActionEvent ae) {

        try {
            String out1 = t1.getText();
            a1.setText(a1.getText() + "\n\t\t" + out1);
            dout.writeUTF(out1);
            t1.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public static void main(String[] args) {
        new Dserver().setVisible(true);
        String msginput = "";



        try {
            skt = new ServerSocket(6969);
            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while (true){
                msginput = din.readUTF();
            a1.setText(a1.getText() + "\n" + msginput);
        }


        } catch (Exception e) {
            System.out.println(e);

        }
    }


}

