
import java.awt.Color;
import java.awt.Graphics;

public class Brick3D {
	public static final int MAX_SHAPE_NUM = 7;
	public static final int CELL_WIDTH = 12;
	public static final int CELL_HEIGHT = CELL_WIDTH;
	public static final int ROTATION_0 = 0;
	public static final int ROTATION_90 = 1;
	public static final int ROTATION_180 = 2;
	public static final int ROTATION_270 = 3;
	/**
	 * 定义砖块的形状、角度、位置
	 * 
	 * */
	public static final int BRICK_INFO[][][][]={
		{//方块
			{//0
				{1,1,0,0},
				{1,1,0,0},
				{0,0,0,0},
				{0,0,0,0}
				},{//90
					{1,1,0,0},
					{1,1,0,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//180
					{1,1,0,0},
					{1,1,0,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//270
					{1,1,0,0},
					{1,1,0,0},
					{0,0,0,0},
					{0,0,0,0}
				}
			},
			{//T形
				{//0
					{1,1,1,0},
					{0,1,0,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//90
					{1,0,0,0},
					{1,1,0,0},
					{1,0,0,0},
					{0,0,0,0}				
				},{//180
					{0,1,0,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//270
					{0,1,0,0},
					{1,1,0,0},
					{0,1,0,0},
					{0,0,0,0}
				}
			},
			{//棍子形状
				{//0
					{1,1,1,1},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//90
					{1,0,0,0},
					{1,0,0,0},
					{1,0,0,0},
					{1,0,0,0}
				},{//180
					{1,1,1,1},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//270
					{1,0,0,0},
					{1,0,0,0},
					{1,0,0,0},
					{1,0,0,0}
				}
			},
			{//L形状
				{//0
					{1,0,0,0},
					{1,0,0,0},
					{1,1,0,0},
					{0,0,0,0}
				},{//90
					{0,0,1,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//180
					{1,1,0,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,0,0,0}
				},{
					{1,1,1,0},
					{1,0,0,0},
					{0,0,0,0},
					{0,0,0,0}
				}
			},
			{//反L形状
				{//0
					{0,1,0,0},
					{0,1,0,0},
					{1,1,0,0},
					{0,0,0,0}
				},{//90
					{1,1,1,0},
					{0,0,1,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//180
					{1,1,0,0},
					{1,0,0,0},
					{1,0,0,0},
					{0,0,0,0}
				},{//270
					{1,0,0,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0}
				}
			},
			{//S形状
				{//0
					{0,1,1,0},
					{1,1,0,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//90
					{1,0,0,0},
					{1,1,0,0},
					{0,1,0,0},
					{0,0,0,0}
				},{//180
					{0,1,1,0},
					{1,1,0,0},
					{0,0,0,0},
					{0,0,0,0}
				},{
					{1,0,0,0},
					{1,1,0,0},
					{0,1,0,0},
					{0,0,0,0}
				}
			},
			{//Z形状
				{//0
					{1,1,0,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0}
				},{//90
					{0,1,0,0},
					{1,1,0,0},
					{1,0,0,0},
					{0,0,0,0}
				},{//180
					{1,1,0,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0}
				},{
					{0,1,0,0},
					{1,1,0,0},
					{1,0,0,0},
					{0,0,0,0}
				}
			}
		};
	/**
	 * 初始化形状
	 * */
	private int shape ;
	/**
	 * X和Y初始化位置
	 * */
	public int X;
	public int Y;
	/**
	 * 颜色索引
	 * */
	public int color_index;
	/**
	 * 初始化角度
	 * */
	private int angle;
	/**
	 * 初始化颜色
	 * */
	public Color color;
	/**
	 * 构造函数
	 * */
	public Brick3D(int shape,int angle,Color color) {
		this.shape = shape % MAX_SHAPE_NUM;
		this.color = color;
		this.angle = angle % 4;
		X = -1;
		Y = -1;
	}
	public int getAngle() {
		return angle % 4;
	}
	public int getShape() {
		return shape;
	}
	/**
	 * 重新设定砖块的形状、角度和颜色
	 * */
	public void reset(int shape,int angle,Color color){
		this.shape = shape % MAX_SHAPE_NUM;
		this.color = color;
		this.angle = angle % 4;
	}
	/**
	 * 返回当前Brick的状态数组
	 * */
	public int[][] getShapeInfo(){
		return BRICK_INFO[shape][angle];
	}
	/**
	 * 按照指定的角度旋转
	 * */
	public void rotate(int angle){
		if((angle>=ROTATION_0)&&(angle<=ROTATION_270)){
			this.angle+=angle;
			this.angle%=4;
		}
	}
	/**
	 * 在指定的Graphics上绘制砖块
	 * */
	public void paint(Graphics g,int startX,int startY){
		g.setColor(color);
		//绘制Brick
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				//此处为具体的一个小方块
				if(BRICK_INFO[shape][angle][i][j] == 1){
					g.fill3DRect(startX+j*CELL_WIDTH,startY+i*CELL_HEIGHT , CELL_WIDTH, CELL_HEIGHT,true);
				}
			}
		}
		
	}

}
