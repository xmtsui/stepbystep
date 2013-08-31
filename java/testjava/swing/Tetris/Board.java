

import java.awt.*;

import javax.swing.JPanel;
import java.awt.event.*;
import java.util.ResourceBundle;
/**
 * 俄罗斯方块的面板类，用于绘制和移动砖块
 * */
public class Board extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 335817980665524384L;
    /**
     * 最大行数
     * */
	private static final int MAX_ROWS = 36;
    /**
     * 最大列数
     * */
	private static final int MAX_COLS = 18;
    /**
     * 最大颜色索引
     * */
	private static final int MAX_COLOR_NUM = 7;
    /**
     *空单元格
     * */
	private static final int BLANK_CELL = -1;
	
	private static final int BORDER = 20;
	private int delay = 1500;
    /**
     * 砖块颜色数组
     * */
	private Color[] colors;
	private int score = 0;
    /**
     * 显示下一个砖块的起始X坐标
     * */
	private int msgX;
    /**
     * 游戏是否终止标志
     * */
	private boolean gameTerminated = true;
    /**
     * 砖块是否到底标志，到底则新建一个砖块
     * */
	private boolean reachBottom;
    /**
     * 用于描述面板中的所有的砖块对象
     * */
	private int[][] board_info;
    /**
     * 当前砖块和下一个砖块 
     * */
	private Brick3D brick,next_brick;
	
    /**
     * 构造函数，创建一个Board 实例
     * */
	public Board(){
		//创建颜色组
		colors = new Color[MAX_COLOR_NUM];
		colors[0] = new Color(128,255,128);
		colors[1] = new Color(128,0,0);
		colors[2] = new Color(0,128,0);
		colors[3] = new Color(255,0,128);
		colors[4] = new Color(128,128,0);
		colors[5] = new Color(128,0,128);
		colors[6] = new Color(0,128,128);
		//设置背景颜色
		this.setBackground(Color.black);
        //启用由传递给此组件的指定事件掩码参数所定义的事件。	使当前程序能够使用下列事件
		this.enableEvents(AWTEvent.MOUSE_EVENT_MASK|AWTEvent.MOUSE_WHEEL_EVENT_MASK|AWTEvent.KEY_EVENT_MASK);
		//计算消息区的起始X坐标
		msgX = 2*BORDER + (MAX_COLS+2)*Brick3D.CELL_WIDTH ;
	    //创建当前砖块和预览砖块对象
		brick = new Brick3D(0,0,Color.BLUE);
		next_brick = new Brick3D(0,0,Color.BLUE);
	}
	/**
	 * 开始执行游戏
	 * */
	public void newGame(){
		//请求此 Component 获取输入焦点，并请求此 Component 的顶层祖先成为获得焦点的 Window。
		grabFocus();
		//将面板全部清空
		reset();
		score = 0;
		gameTerminated = false;
		reachBottom = false;
		new Thread(this).start();
		repaint();
	}
	/**
	 * 结束游戏
	 * */
	public void endGame(){
		this.gameTerminated = true;
		long st = System.currentTimeMillis();
		while(System.currentTimeMillis() - st < delay){}
		
		brick.X = -1;
		next_brick.X = -1;
		repaint();
	}
	/**
	 * 线程运行的函数
	 * */
	public void run(){
		while(!gameTerminated){
			if((brick.X==-1)||(reachBottom)){
				createNewBrick();
				reachBottom = false;
				updateBoardInfo(true);
				updateBoardInfo(false);
				
			}else{
				drop();
				if(reachBottom){
					continue;
				}
			}
			
			try{
				Thread.sleep(delay);
			}catch(InterruptedException e){
				System.out.println(e.toString());
			}
		}
	}
	/**
	 * 绘制背景，边界和方块
	 * */
	public void paint(Graphics g){
		//调用父类的paint方法 画出边框
		super.paint(g);
		drawBorder(g);
		if(brick.X==-1){
			return;
		}
		drawBricks(g);
		drawNextBrick(g);
		drawScore(g);
		if(gameTerminated){
			g.setFont(new Font("宋体",0,36));
			g.setColor(Color.RED);
			g.drawString(ResourceBundle.getBundle("org/n2536/TetrisApplet").getString("Game_Over"), 4*BORDER, this.getHeight()/2);
		}
	}
	/**
	 * 初始化board_info数组，将所有的元素设为BLANK_CELL
	 * */
	public void reset(){
		board_info = new int[MAX_ROWS][MAX_COLS];
		for(int i=0;i<MAX_ROWS;i++){
			for(int j=0;j<MAX_COLS;j++){
				board_info[i][j] = BLANK_CELL;
			}
		}
	}
	/**
	 * 绘制边框
	 * */
	public void drawBorder(Graphics g){
		g.setColor(Color.GRAY);
		int bottomY = BORDER + (MAX_ROWS)*Brick3D.CELL_HEIGHT;
		int rightX = BORDER + (MAX_COLS)*Brick3D.CELL_WIDTH;
		
		int y = BORDER;
		while(y<bottomY){
			g.fill3DRect(BORDER,y , Brick3D.CELL_WIDTH, Brick3D.CELL_HEIGHT, true);
			g.fill3DRect(rightX,y, Brick3D.CELL_WIDTH, Brick3D.CELL_WIDTH, true);
			y += Brick3D.CELL_HEIGHT;
		}
		
		int x = BORDER;
		while(x<=rightX){
			g.fill3DRect(x, y, Brick3D.CELL_WIDTH, Brick3D.CELL_HEIGHT, true);
			x += Brick3D.CELL_WIDTH;
		}
	}
	/*核心方法*/
	public void drawBricks(Graphics g){
		for(int i=0;i<MAX_ROWS;i++){
			for(int j=0;j<MAX_COLS;j++){
				int c = board_info[i][j];
				if(c!=BLANK_CELL){
					if(c>=colors.length){
						c = colors.length-1;
					}
					g.setColor(colors[c]);
					g.fill3DRect(BORDER+(j+1)*Brick3D.CELL_WIDTH,BORDER+i*Brick3D.CELL_HEIGHT,Brick3D.CELL_WIDTH, Brick3D.CELL_HEIGHT, true);
				}
			}
		}
	}
	/*核心方法*/
	public void drawNextBrick(Graphics g){
		if(next_brick==null){
			return;
		}
		g.setColor(next_brick.color);
		g.drawString(ResourceBundle.getBundle("TetrisApplet").getString("Next_Block"),msgX,5*BORDER);
		next_brick.paint(g, msgX, 6*BORDER);
	}
	
	public void drawScore(Graphics g){
		g.setColor(Color.RED);
		g.drawString(ResourceBundle.getBundle("TetrisApplet").getString("Score")+": "+score, msgX, 1*BORDER);
	}
	/**
	 * 创建下一个新的砖块形状、角度和颜色
	 * 角度、形状和颜色随机产生
	 * */
	/*核心方法*/
	public void createNewBrick(){
		brick.reset(next_brick.getShape(), next_brick.getAngle(), next_brick.color);
		brick.X = (int)MAX_COLS/2;
		brick.Y = 0;
		brick.color_index = next_brick.color_index;
		/*随机获取形状、角度和颜色*/
		int color_index = (int)Math.round(Math.random()*(MAX_COLOR_NUM-1));
		int shape = (int)Math.round(Math.random()*(Brick3D.MAX_SHAPE_NUM-1));
		int angle = (int)Math.round(Math.random()*3);
		next_brick.reset(shape, angle, colors[color_index]);
		next_brick.color_index = color_index;
	}
	/**
	 * 根据当前的brick_info数组，更新board_info数组
	 * */
	/*核心方法*/
	public void updateBoardInfo(boolean clear){
		int[][] si = brick.getShapeInfo();
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(si[i][j]!=0){
					board_info[brick.Y+i][brick.X+j] = (clear?BLANK_CELL:brick.color_index);
				}
			}
		}
		if(!clear){
			repaint();
		}
	}
/**
 * 	判断是否可以移动砖块
 *  当砖块超过边界时，就返回false，拒绝移动
	*/
	/*核心方法*/
	public boolean canMove(int newX,int newY){
		if((newX<0)||(newY>=MAX_ROWS)){
			return false;
		}
		//判断不能和其他砖块重叠，超过底部和右边边界
		int[][] si = brick.getShapeInfo();
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(si[i][j]!=0){
					if(((newX+j)>=(MAX_COLS-1))||((newY+i)>=(MAX_ROWS))||(board_info[newY+i][newX+j]!=BLANK_CELL)){			
						return false;
					}
				}
			}
		}
	//	
		return true;
	}
	/**
	 * 移动砖块，1表示向右移动，-1向左移动
	 * */
	public void move(int dx){
		if(brick.X!=-1){
			updateBoardInfo(true);
			if(canMove(brick.X+dx,brick.Y)){
				brick.X+=dx;
			}
			updateBoardInfo(false);
		}
	}
	/**
	 * 砖块的自由下落函数
	 * */
	public void drop(){
		if(brick.X==-1){
			return;
		}
		//清空原内容
		updateBoardInfo(true);
		if(canMove(brick.X,brick.Y+1)){
			brick.Y+=1;
		}else{
			if(brick.Y==0){
				gameTerminated = true;
			}else{
				reachBottom = true;
			}
		}
	/*	System.out.println("__________________");*/
		updateBoardInfo(false);
		//当到达底部时，检查是否可以消行
		if(reachBottom){
			checkRow();
			
			
		}
	}
	/**
	 * 检查是否可以消行，并计算分数
	 * */
	/*核心方法*/
	public void checkRow(){
		boolean full_row,full_color;
		//连续的行数
		int full_lines = 0;
		final int base_score = 100;
		int score = 0;
		for(int i=MAX_ROWS-1;i>=0;i--){
			full_row = true;
			full_color = true;
			for(int j=MAX_COLS-2;j>=0;j--){
				//检查是否本行颜色相同
		    	if((j>0)&&(board_info[i][j]!=board_info[i][j-1])){
				    full_color = false;
		    	}
		    	//检查是否有空格子
		    	if(((board_info[i][j]==BLANK_CELL))){
				    full_color = false;
				    full_row = false;
				    break;
		    	}
			}
			
		
			//本行已填满
			if(full_row){
				full_lines++;
				if(full_color){
					score += 4*base_score;
				}else{
					score += base_score;
				}
				//将上面的行向下移动
				for(int row=i;row>0;row--){
					board_info[row] = board_info[row-1];
				}
				//清除最上面的一行
				int[] top_line = new int[MAX_COLS];
				for(int j=MAX_COLS-1;j>=0;j--){
					top_line[j] = BLANK_CELL;
				}
				board_info[0] = top_line;
				i++;
			}else{
				//不是连续行
				if(full_lines>0){
					score *= full_lines;
				}
				full_lines = 0;
				if(score>0){
					this.score += score;
					score = 0;
					delay = 1500 - (int)(this.score/10);
				}
			}
		}
	}
	/**
	 * 旋转砖块，并更新board_info数组
	 * */
	public void rotate(){
		if(brick.X!=-1){
			updateBoardInfo(true);
			brick.rotate(Brick3D.ROTATION_90);
			if(!canMove(brick.X,brick.Y)){
				brick.rotate(Brick3D.ROTATION_270);
			}
			updateBoardInfo(false);
		}
	}
	
	public void processKeyEvent(KeyEvent e){
		if(e.getID()==KeyEvent.KEY_PRESSED){
			switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				move(-1);
				break;
			case KeyEvent.VK_RIGHT:
				move(1);
				break;
			case KeyEvent.VK_DOWN:
				drop();
				break;
			case KeyEvent.VK_UP:
				if(brick!=null){
					rotate();
				}
				break;	
			case KeyEvent.VK_F3:
				newGame();
				break;
			case KeyEvent.VK_F4:
				endGame();
				break;	
			
			}
		}
	}
	
	public void processMouseEvent(MouseEvent e){
		super.processMouseEvent(e);
		if(e.getID()==MouseEvent.MOUSE_CLICKED){
			grabFocus();
			if(brick==null){
				return;
			}
			if(e.getButton()==MouseEvent.BUTTON1){
				move(-1);
			}else if(e.getButton()==MouseEvent.BUTTON2){
				rotate();
			}else if(e.getButton()==MouseEvent.BUTTON3){
				move(1);
			}
		}
	}
	
	public void processMouseWheelEvent(MouseWheelEvent e){
		super.processMouseWheelEvent(e);
		if((brick!=null)&&(e.getWheelRotation()>0)){
			drop();
		}
	}
	
}
