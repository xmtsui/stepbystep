import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class Calendar {

	private JTable table;
	private JSpinner yearSpin;
	private JComboBox monthCom;
	private JFrame frame;
	private String year;
	private String month;
	public static final int ROWS = 6;
	public static final int COLS = 7;	
	private Object[][] dates ;
	private static final String[] WEEKDAYS= {"日","一","二","三","四","五","六"};
	private int tmpdate;
	private Point p = new Point();
	//当前日期
	java.util.Calendar cal = java.util.Calendar.getInstance();

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Calendar window = new Calendar();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application
	 */
	public Calendar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		
		frame = new JFrame("大旻日历");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 328, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加主面板 添加标题
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "日期(D)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.BLUE));
		panel.setLayout(null);
		panel.setBounds(35, 37, 261, 192);
		frame.getContentPane().add(panel);
		//初始化日期
		tmpdate = cal.get(java.util.Calendar.DATE);
        //添加月份下拉框
		monthCom = new JComboBox();
		monthCom.setBounds(40, 25, 80, 20);
		monthCom.addItem("一月");
		monthCom.addItem("二月");
		monthCom.addItem("三月");
		monthCom.addItem("四月");
		monthCom.addItem("五月");
		monthCom.addItem("六月");
		monthCom.addItem("七月");
		monthCom.addItem("八月");
		monthCom.addItem("九月");
		monthCom.addItem("十月");
		monthCom.addItem("十一月");
		monthCom.addItem("十二月");
		monthCom.setSelectedIndex(cal.get(java.util.Calendar.MONTH));
		monthCom.addItemListener(new MonthComListener());
		panel.add(monthCom);
        //添加年份微调器
		yearSpin = new JSpinner();
		yearSpin.setBounds(150, 25, 80, 20);
		yearSpin.setModel(new SpinnerNumberModel(cal.get(java.util.Calendar.YEAR),1900,3000,1));
		yearSpin.addChangeListener(new YearSpinListener());
		panel.add(yearSpin);
		 //添加日历主体
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 64, 190, 120);
		panel.add(scrollPane);

		table = new JTable(ROWS,COLS);
		table.setShowGrid(false);
		//table.setEnabled(false);
		table.setSelectionBackground(new Color(129,167,222));
		/*table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);*/
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel tm = new DefaultTableModel(WEEKDAYS,6);
		table.setModel(tm);
		table.setForeground(Color.BLACK);
		table.setDragEnabled(false);
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				p.x = table.getSelectedRow();
				p.y = table.getSelectedColumn();
				tmpdate = Integer.parseInt((String)table.getValueAt(p.x, p.y));

			}

		});


		table.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				table.requestFocusInWindow();
				table.changeSelection(p.x, p.y, true, false);
			}
		});

		scrollPane.setViewportView(table);
        //添加主题标签
		final JLabel label = new JLabel();
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("隶书", Font.BOLD, 22));
		label.setText("大旻日历");
		label.setBounds(120, 10, 92, 34);
		frame.getContentPane().add(label);
		//初始化日历
		month = String.valueOf(monthCom.getSelectedIndex()+1);
		year = String.valueOf(yearSpin.getValue());
		getDates(year,month);
	}
	
	//monthCom 的监听内部类
	class MonthComListener implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
	           //table.remove
				month = String.valueOf(monthCom.getSelectedIndex()+1);
				getDates(year,month);
			}
		}
		
	}
	
	//yearSpin 的监听内部类
	class YearSpinListener implements ChangeListener{

		public void stateChanged(ChangeEvent e) {

			year =  String.valueOf(yearSpin.getValue());
			getDates(year,month);
			
		}
		
	}
	
	
	public void getDates(String year,String month){
		//取消选中所有已选定的行和列
		table.clearSelection();
		dates = CalendarParse.parse(year, month);
		Object[] dds;
		for(int i=0;i<dates.length;i++){
			dds = dates[i];
			for(int j=0;j<dds.length;j++){
				table.setValueAt(dates[i][j], i, j);
				if(dates[i][j]!=null&&!((String)dates[i][j]).trim().equals("")&&Integer.parseInt((String) dates[i][j]) == tmpdate){
	    			 //选择单元格
					table.changeSelection(i, j,true,false);
					p.x = i;
					p.y = j;
				}
			}
		}
	}
}
