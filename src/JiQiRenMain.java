import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.Connection;

import java.sql.ResultSet;




import java.awt.Color;
import java.awt.Graphics;


public class JiQiRenMain extends JFrame //implements ActionListener
{
    public JPanel chatJP,logoJP,otherJP,functionJP,inputJP,buttonJP,leftJP,rightJP,centerJP,downJP,toolJP,buttonLJP,buttonRJP;
    public JTextArea jt;
    public JTextPane chatarea,inputarea,questionPane;
    public JLabel JL;
    public JScrollPane scrollPane,scrollPane2;
    public JButton practiceJB,sendJB,quxiaoJB;
    public String input,output,out;
    private GridBagConstraints c=new GridBagConstraints();
    private GridBagLayout grid=new GridBagLayout();
    private JSplitPane splitPane,hJsplitpane,splitPane2,splitPane3,splitPane4;


    public String s1="com.mysql.jdbc.Driver";
    public String s2="jdbc:mysql://192.168.24.71:3306/user?";
    Connection con=null;
    java.sql.Statement stm=null;
    ResultSet rs;
    SimpleDateFormat sdf=new 	SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public 	JiQiRenMain(){
        super();
        setTitle("蓝豹聊天");
        setBounds(200,100,1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);//窗口大小不可改变
        init();
    }
    void init(){
        logoJP=new JPanel();
        logoJP.setBackground(Color.red);
        centerJP=new JPanel();
        downJP=new JPanel();
        toolJP=new JPanel();
        JButton jb=new JButton("萨芬");
        toolJP.add(jb);

        chatJP=new JPanel(){
            private static final long serialVersionUID = -8220994963464909915L;
            {
                this.setOpaque(false); // 设置透明
                this.setLayout(new BorderLayout());
            }
            protected void paintComponent(Graphics g) {
                ImageIcon  picture =new ImageIcon("110.jpg");
                g.drawImage(picture.getImage(),0,0,getWidth(),getHeight(),picture.getImageObserver());
                super.paintComponent(g);
            }
        };
        chatarea=new JTextPane();
        chatarea.setEditable(false);//设置文本域不可编辑
        chatarea.setOpaque(false);//设置透明，气泡才可见
        scrollPane=new JScrollPane(chatarea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        chatJP.setLayout(new GridLayout(1,1));
        chatJP.add(scrollPane);

        otherJP=new JPanel();

        buttonJP=new JPanel();buttonLJP=new JPanel();buttonRJP=new JPanel();
        buttonJP.setLayout(grid);
        practiceJB=new JButton("训练");
        quxiaoJB=new JButton("取消");
        sendJB=new JButton("发送");
        c.weightx=15;
        c.weighty=10;
        c.fill=GridBagConstraints.BOTH;
        buttonJP.add(buttonLJP,c);
        c.weightx=1;
        c.weighty=10;
        buttonJP.add(buttonRJP,c);

        buttonLJP.setLayout(new BorderLayout());
        buttonRJP.setLayout(new BorderLayout());
        buttonLJP.add(practiceJB,BorderLayout.WEST);
        buttonLJP.add(quxiaoJB,BorderLayout.EAST);
        buttonRJP.add(sendJB,BorderLayout.EAST);

        functionJP=new JPanel();
        inputJP=new JPanel();
        inputarea=new JTextPane();
        inputarea.setOpaque(false);//设置透明，气泡才可见
        scrollPane2=new JScrollPane(inputarea);
        scrollPane2.setOpaque(false);
        scrollPane2.getViewport().setOpaque(false);
        inputJP.setLayout(new GridLayout(1,1));
        inputJP.add(scrollPane2);

        leftJP=new JPanel();
        leftJP.setBackground(Color.blue);
        showchat();
        rightJP=new JPanel();
        rightJP.setBackground(Color.yellow);

        splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,false);
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                splitPane.setDividerLocation(0.2);
            }
        });
        splitPane.setDividerSize(4);
        splitPane.setEnabled(false);//禁止移动分割线
        splitPane.setLeftComponent(logoJP);

        hJsplitpane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false);
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                hJsplitpane.setDividerLocation(0.75);
            }
        });
        getContentPane().add(hJsplitpane,BorderLayout.CENTER);
        splitPane.setRightComponent(hJsplitpane);
        hJsplitpane.setLeftComponent(leftJP);
        hJsplitpane.setRightComponent(rightJP);

        hJsplitpane.setEnabled(false);//禁止移动分割线
        hJsplitpane.setDividerSize(4);
        add(splitPane);
    }
    void showchat(){
        leftJP.setLayout(new BorderLayout());
        splitPane2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,false);
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                splitPane2.setDividerLocation(0.65);
            }
        });
        splitPane2.setDividerSize(4);
        splitPane2.setEnabled(false);//禁止移动分割线
        splitPane2.setLeftComponent(chatJP);
        splitPane2.setRightComponent(centerJP);
        leftJP.add(splitPane2);



        centerJP.setLayout(new BorderLayout());
        splitPane3=new JSplitPane(JSplitPane.VERTICAL_SPLIT,false);
        splitPane3.setDividerLocation(15);
        splitPane3.setDividerSize(4);
        splitPane3.setEnabled(false);//禁止移动分割线
        splitPane3.setLeftComponent(toolJP);
        splitPane3.setRightComponent(downJP);
        //splitPane3.setRightComponent(inputJP);
        centerJP.add(splitPane3);

        downJP.setLayout(grid);
        c.gridwidth=0;
        c.weightx=1;
        c.weighty=10;
        c.fill=GridBagConstraints.BOTH;downJP.add(inputJP,c);

        c.gridwidth=0;
        c.weightx=1;
        c.weighty=1;
        downJP.add(buttonJP,c);

		
		/*downJP.setLayout(new BorderLayout()); 
		splitPane4=new JSplitPane(JSplitPane.VERTICAL_SPLIT,false);
		this.addComponentListener(new ComponentAdapter(){
		     public void componentResized(ComponentEvent e){
				 splitPane4.setDividerLocation(0.8);
			}
		});
		splitPane4.setDividerSize(4);
		splitPane4.setEnabled(false);//禁止移动分割线
		splitPane4.setLeftComponent(inputJP);
		splitPane4.setRightComponent(buttonJP);
		downJP.add(splitPane4);*/

    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        JiQiRenMain op=new JiQiRenMain();
        op.setVisible(true);
    }
}