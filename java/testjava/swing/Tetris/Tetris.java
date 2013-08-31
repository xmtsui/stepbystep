
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.*;

/**
 * 俄罗斯方块，主要的实现方法在Board里面，这个Board表示整个游戏
 * 面板，通过createNewBrick 和 updateInfo方法我们能实现游戏的动作
 * 主要性能，而checkRow方法实现了游戏的附加性能
 * 
 * */
public class Tetris extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1280111737160885262L;
	private JMenuBar jMenuBar1;
	private JToggleButton jbtnEndGame;
	private JToolBar jToolBar1;
	private JButton jbtnNewGame;
	private JMenu jmenuGame;
	private JMenuItem miNewGame;
	private JMenuItem miEndGame;
	private Board board;
	
	public Tetris(){
		this.setTitle("俄罗斯方块");
		this.setSize(new Dimension(380,550));
		this.setLocation(200,100);
		init();
	}
    /**
     * 初始化窗口（采用国际化开发，针对不同的语言环境，显示不同的语言）
     * */
	public void init(){
		jToolBar1 = new JToolBar();
		jbtnNewGame = new JButton();
		jbtnEndGame = new JToggleButton();
		jMenuBar1 = new JMenuBar();
		jmenuGame = new JMenu();
		miNewGame = new JMenuItem();
		miEndGame = new JMenuItem();
		
		this.setFont(new Font("宋体",0,12));
		this.setName("applet1");
		this.setSize(380,544);
		this.setResizable(false);
		this.getAccessibleContext().setAccessibleName(null);
		
		jmenuGame.setFont(this.getFont());
		jmenuGame.setText(ResourceBundle.getBundle("TetrisApplet").getString("Game"));
		
		miNewGame.setFont(this.getFont());
		miNewGame.setText(ResourceBundle.getBundle("TetrisApplet").getString("New_Game"));
		miNewGame.getAccessibleContext().setAccessibleName(ResourceBundle.getBundle("TetrisApplet").getString("New_Game"));
		miNewGame.getAccessibleContext().setAccessibleDescription(ResourceBundle.getBundle("TetrisApplet").getString("New_Game"));
		miNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				miNewGameActionPerformed(evt);
			}			
		});
		jmenuGame.add(miNewGame);
		
		miEndGame.setFont(this.getFont());
		miEndGame.setText(ResourceBundle.getBundle("TetrisApplet").getString("End_Game"));
		miEndGame.getAccessibleContext().setAccessibleName(ResourceBundle.getBundle("TetrisApplet").getString("End_Game"));
		miEndGame.getAccessibleContext().setAccessibleDescription(ResourceBundle.getBundle("TetrisApplet").getString("End_Game"));
		miEndGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				miEndGameActionPerformed(evt);
			}
		});
		
		jmenuGame.add(miEndGame);
		
		jToolBar1.setPreferredSize(new Dimension(10,24));
		jbtnNewGame.setFont(this.getFont());
		jbtnNewGame.setText(ResourceBundle.getBundle("TetrisApplet").getString("New_Game"));
		jbtnNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				miNewGameActionPerformed(evt);
			}

		});
		
		jToolBar1.add(jbtnNewGame);
		
		jbtnEndGame.setFont(this.getFont());
		jbtnEndGame.setText(ResourceBundle.getBundle("TetrisApplet").getString("End_Game"));
		jbtnEndGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
                miEndGameActionPerformed(evt);	
			}
		});
		
		jToolBar1.add(jbtnEndGame);
		this.getContentPane().add(jToolBar1,BorderLayout.NORTH);
		
		jMenuBar1.add(jmenuGame);
		this.setJMenuBar(jMenuBar1);
		
		board = new Board();
		board.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(board);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
/*	public void initComponents(){
		
	}*/
	/**
	 * 开始游戏
	 */
	public void miNewGameActionPerformed(ActionEvent evt){
		board.newGame();
		
	}
	/**
	 * 结束游戏
	 */
	public void miEndGameActionPerformed(ActionEvent evt){
		board.endGame();
		
	}
	
	public static void main(String[] args){
		new Tetris().setVisible(true);
	}

}
