package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import Bean.Excel;
import Dao.Goods;
import Dao.Order;
import Dao.ShopTrolley;
import Dao.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class DBUtil {
	
	private static String model="0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	private static String URL="jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	private static String username="root";
	private static String password="root";
	public static Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection=DriverManager.getConnection(URL, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public DBUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getDateString() {       //��ȡ��ǰʱ��
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getIdentifier() {    //���ɱ���
		String str="";
		for(int i=0;i<15;i++)
			str+=model.charAt((int)(Math.random()*100)%62);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static String checkLogin(User user) {               //����½
		Connection connection=getConnection();
		String sql="select * from user where name='"+user.getName()+"' and pwd='"+user.getPassword()+"'";
		ResultSet rs=null;
		String str=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next())
				str=rs.getString("status")+";"+rs.getString("id");
			else str="0;";
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static boolean checkOnline(String name) {         //�����˻��Ƿ�����
		Connection connection=getConnection();
		String sql="select * from user where name='"+name+"' and onlinestatus=1";
		ResultSet rs=null;
		boolean flag=false;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next())
				flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void changeOnlineStatus(String id) {           //��������״̬
		Connection connection=getConnection();
		String sql="update user set onlinestatus=0 where id='"+id+"'";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getUsername(String id) {    //��ȡ�û�������ͷ��url
		Connection connection=getConnection();
		String sql="select * from user where id='"+id+"'";
		ResultSet rs=null;
		String str=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next())
				str=rs.getString("name")+";"+rs.getString("imgurl");
			sql="update user set onlinestatus='1' where id='"+id+"'";           //��������״̬
			statement.executeUpdate(sql);
			connection.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static String judgeName(String name) {              //��ѯ����
		Connection connection=getConnection();
		String sql="select * from user where name='"+name+"'";
		ResultSet rs=null;
		String str=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next())
				str="1";
			else str="0";
			statement.close();
			connection.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static void regsiter(User user) {         //ע��
		Connection connection=getConnection();
		String sql="insert into user values('"+getIdentifier()+"','"+user.getName()+"','"+user.getPassword()+"','"+user.getSex()+"','"+user.getStatus()+"','"+user.getLocation()+"','"+user.getPhone()+"','"+getDateString()+"','0','6.jpg','0')";
		try {
			Statement statement=connection.createStatement();
			statement .executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void changeImg(String id) {          //��ͷ��
		Connection connection=getConnection();
		String sql="update user set imgurl='"+id+".jpg' where id='"+id+"'";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			connection.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String showGoods(String name) {        //����������Ʒ
		JSONArray jsonarr=new JSONArray();
		Connection connection=getConnection();
		String sql="select * from goods where name like '%"+name+"%' and nownum>0 and status=1";
		ResultSet rs=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Goods goods=new Goods();
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setDate(rs.getString("date"));
				goods.setImgurl(rs.getString("imgurl"));
				goods.setNownum(rs.getString("nownum"));
				goods.setPrice(rs.getString("price"));
				goods.setRemark(rs.getString("remark"));
				goods.setSeller(rs.getString("seller"));
				goods.setSellerid(rs.getString("sellerid"));
				goods.setStatus(rs.getString("status"));
				goods.setType(rs.getString("type"));
				jsonarr.add(goods);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	
	public static String showEveryGoods(String goodsid) {    //��ȡ��Ʒ����
		Connection connection=getConnection();
		String sql="select * from goods where id='"+goodsid+"'";
		ResultSet rs=null;
		Goods goods=new Goods();
		JSONArray json=new JSONArray();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next()) {
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setDate(rs.getString("date"));
				goods.setImgurl(rs.getString("imgurl"));
				goods.setNownum(rs.getString("nownum"));
				goods.setPrice(rs.getString("price"));
				goods.setRemark(rs.getString("remark"));
				goods.setSeller(rs.getString("seller"));
				goods.setSellerid(rs.getString("sellerid"));
				goods.setStatus(rs.getString("status"));
				goods.setType(rs.getString("type"));
				json.add(goods);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static String everyTrade(String userid,String goodsid,int num) {    //���ʶ�������
		double sum=0,useraccount=0,goodsprice=0;
		String sql="select price,sellerid from goods where id='"+goodsid+"'";
		Connection connection=getConnection();
		ResultSet rs=null;
		String info=null;
		String sellerid=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next()) {
				goodsprice=rs.getDouble("price");
				sellerid=rs.getString("sellerid");
			}
			sum=goodsprice*num;
			rs.close();
			sql="select account from user where id='"+userid+"'";
			rs=statement.executeQuery(sql);
			if(rs.next())
				useraccount=rs.getDouble("account");
			rs.close();
			if(useraccount>=sum) {
				sql="update user set account='"+(useraccount-sum)+"' where id='"+userid+"'";
				statement.executeUpdate(sql);
				sql="update user set account=account+'"+sum+"' where id='"+sellerid+"'";
				statement.executeUpdate(sql);
				sql="update goods set nownum=nownum-'"+num+"' where id='"+goodsid+"'";
				statement.executeUpdate(sql);
				info="��ϲ��������ɹ���";
				everyTradeOrder(userid, goodsid, num);    //��¼ÿ�ʶ�������
			}else if(useraccount<sum)
				info="�����˻����㣬���ȳ�ֵ��";
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public static String toShopTrolley(String userid,String goodsid,int num) {    //���빺�ﳵ
		double sum=0;
		boolean flag=false;
		String sql="";
		String sql1="select * from shoptrolley where buyerid='"+userid+"' and goodsid='"+goodsid+"'";
		Goods goods=new Goods();
		Connection connection=getConnection();
		ResultSet rs=null;
		String info=null;
		double price=0;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql1);
			if(rs.next()) {
				flag=true;
				price=rs.getDouble("price");
				sum=price*num;
			}
			rs.close();
			if(!flag) {
				sql="select * from goods where id='"+goodsid+"'";
				rs=statement.executeQuery(sql);
				if(rs.next()) {
					goods.setId(goodsid);
					goods.setName(rs.getString("name"));
					goods.setPrice(rs.getString("price"));
					goods.setRemark(rs.getString("remark"));
					goods.setSeller(rs.getString("seller"));
					goods.setImgurl(rs.getString("imgurl"));
				}
				rs.close();
				sum=Double.parseDouble(goods.getPrice())*num;
				sql="insert into shoptrolley values('"+goods.getId()+"','"+goods.getName()+"','"+goods.getImgurl()+"','"+goods.getPrice()+"','"+num+"','"+sum+"','"+goods.getRemark()+"','"+goods.getSeller()+"','"+userid+"')";
				statement.executeUpdate(sql);
			}else {
				sql="update shoptrolley set num=num+'"+num+"',sum=sum+'"+sum+"' where goodsid='"+goodsid+"' and buyerid='"+userid+"'";
				statement.executeUpdate(sql);
			}
			info="�ɹ����빺�ﳵ��";
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public static String showMyTrolley(String userid) {         //�鿴���ﳵ
		JSONArray json=new JSONArray();
		String sql="select * from shoptrolley where buyerid='"+userid+"'";
		Connection connection=getConnection();
		ResultSet rs=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				ShopTrolley st=new ShopTrolley();
				st.setBuyerid(userid);
				st.setGoods(rs.getString("goods"));
				st.setGoodsid(rs.getString("goodsid"));
				st.setNum(rs.getString("num"));
				st.setPrice(rs.getString("price"));
				st.setRemark(rs.getString("remark"));
				st.setSeller(rs.getString("seller"));
				st.setSum(rs.getString("sum"));
				st.setImgurl(rs.getString("imgurl"));
				json.add(st);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static String removeTrolley(String userid,String goodsid,int type) {    //�Ƴ����ﳵ
		String sql="";
		String info=null;
		if(type==0)
			sql="delete from shoptrolley where goodsid='"+goodsid+"' and buyerid='"+userid+"'";
		else if(type==1)
			sql="delete from shoptrolley where buyerid='"+userid+"'";
		Connection connection=getConnection();
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			info="�ѳɹ�ɾ��";
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public static String trade(String userid,String temp) {          //���ﳵ���ײ���
		String info="��ϲ��������ɹ���";
		String[]goodsid=temp.split(";");
		int tempnum=0,tempnum1=0;
		double summoney=0,account=0;
		String sql="";
		Connection connection=getConnection();
		ResultSet rs=null;
		try {
			Statement statement=connection.createStatement();
			sql="select sum(sum) from shoptrolley where buyerid='"+userid+"'";
			rs=statement.executeQuery(sql);
			rs.next();
			summoney=rs.getDouble(1);
			rs.close();
			sql="select account from user where id='"+userid+"'";
			rs=statement.executeQuery(sql);
			rs.next();
			account=rs.getDouble(1);
			rs.close();
			if(account<summoney)
				return "���㣬���ȳ�ֵ";
			else {
				for(int i=0;i<goodsid.length;i++) {
					sql="select num from shoptrolley where buyerid='"+userid+"' and goodsid='"+goodsid[i]+"'";
					rs=statement.executeQuery(sql);
					rs.next();
					tempnum=Integer.parseInt(rs.getString("num"));
					rs.close();
					sql="select nownum from goods where id='"+goodsid[i]+"'";
					rs=statement.executeQuery(sql);
					rs.next();
					tempnum1=Integer.parseInt(rs.getString("nownum"));
					rs.close();
					if(tempnum1<tempnum) {
						return "ĳ�̼ҿ�������㣬����ʧ�ܡ�";
					}
				}
			}
			//�û��۳���Ӧ���
			sql="update user set account='"+(account-summoney)+"' where id='"+userid+"'";
			statement.executeUpdate(sql);
			//�ۿ����
			
			for(int i=0;i<goodsid.length;i++) {   //����ɹ�����
				double sumtemp=0;     //��¼ÿ�����ݵĽ��	
				int numtemp=0;			//��¼ÿ�����ݵ���Ʒ��
				String sellerid=null;   //��¼ÿ���̻�id
				sql="select *from goods where id='"+goodsid[i]+"'";
				rs=statement.executeQuery(sql);
				rs.next();
				sellerid=rs.getString("sellerid");
				rs.close();
				sql="select * from shoptrolley where goodsid='"+goodsid[i]+"' and buyerid='"+userid+"'";
				rs=statement.executeQuery(sql);
				rs.next();
				sumtemp=rs.getDouble("sum");
				numtemp=rs.getInt("num");
				rs.close();
				sql="update user set account=account+'"+sumtemp+"' where id='"+sellerid+"'";  //�̻�������Ӧ���
				statement.executeUpdate(sql);
				sql="update goods set nownum=nownum-'"+numtemp+"' where id='"+goodsid[i]+"'";  //��Ӧ���������
				statement.executeUpdate(sql);
				everyTradeOrder(userid, goodsid[i], numtemp);     //��¼ÿ�ʶ���
			}
			sql="delete from shoptrolley where buyerid='"+userid+"'";  //ɾ����Ӧ���ﳵ��Ϣ
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public static void everyTradeOrder(String userid,String goodsid,int num) {       //��¼ÿ�ʶ�������
		Order order=new Order();
		String sql="select * from goods where id='"+goodsid+"'";
		Connection connection=getConnection();
		ResultSet rs=null;
		order.setId(getIdentifier());//���ö������
		order.setTradedate(getDateString());  //���ý���ʱ��
		
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next()) {
				order.setGoods(rs.getString("name"));
				order.setGoodsid(rs.getString("id"));
				order.setNum(String.valueOf(num));
				order.setPrice(rs.getString("price"));
				order.setSeller(rs.getString("seller"));
				order.setSellerid(rs.getString("sellerid"));
				order.setSum(String.valueOf(Double.parseDouble(order.getPrice())*num));
				order.setImgurl(rs.getString("imgurl"));
			}
			rs.close();
			sql="select * from user where id='"+order.getSellerid()+"'";
			rs=statement.executeQuery(sql);
			if(rs.next()) {
				order.setSellerlocation(rs.getString("location"));
				order.setSellerphone(rs.getString("phone"));
			}
			rs.close();
			sql="select * from user where id='"+userid+"'";
			rs=statement.executeQuery(sql);
			if(rs.next()) {
				order.setBuyer(rs.getString("name"));
				order.setBuyerid(userid);
				order.setBuyerlocation(rs.getString("location"));
				order.setBuyerphone(rs.getString("phone"));
			}
			rs.close();
			sql="insert into orders values('"+order.getId()+"','"+order.getGoods()+"','"+order.getGoodsid()+"','"+order.getImgurl()+"','"+order.getPrice()+"',"
					+ "'"+order.getNum()+"','"+order.getSum()+"','"+order.getTradedate()+"','"+order.getBuyer()+"',"
					+ "'"+order.getBuyerid()+"','"+order.getSeller()+"','"+order.getSellerid()+"','"+order.getBuyerlocation()+"','"+order.getBuyerphone()+"',"
					+ "'"+order.getSellerlocation()+"','"+order.getSellerphone()+"','0','0','0')";
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String showBuyerOrders(String userid) {        //��ȡ��ҵĶ���
		Connection connection=getConnection();
		String sql="select * from orders where buyerid='"+userid+"' and buyerstatus=0";
		ResultSet rs=null;
		JSONArray json=new JSONArray();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Order order=new Order();
				order.setBuyer(rs.getString("buyer"));
				order.setBuyerid(userid);
				order.setBuyerlocation(rs.getString("buyerlocation"));
				order.setBuyerphone(rs.getString("buyerphone"));
				order.setSellerlocation(rs.getString("sellerlocation"));
				order.setSellerphone(rs.getString("sellerphone"));
				order.setGoods(rs.getString("goods"));
				order.setGoodsid(rs.getString("id"));
				order.setNum(rs.getString("num"));
				order.setPrice(rs.getString("price"));
				order.setSeller(rs.getString("seller"));
				order.setSellerid(rs.getString("sellerid"));
				order.setSum(rs.getString("sum"));
				order.setImgurl(rs.getString("imgurl"));
				order.setTradedate(rs.getString("tradedate"));
				order.setId(rs.getString("id"));
				order.setBuyererstatus(rs.getString("buyerstatus"));
				order.setSellerstatus(rs.getString("sellerstatus"));
				order.setTradestatus(getBuyerStatus(rs.getString("tradestatus")));
				json.add(order);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static String getBuyerStatus(String e) {
		String info=null;
		switch (e) {
		case "0":info="����Ѹ���";break;
		case "1":info="��ȷ���ջ�";break;
		case "2":info="�ѷ����˻�";break;
		case "3":info="�ѷ��𻻻�";break;
		case "4":info="�̼Ҿܾ��˻�";break;
		case "5":info="�̼�ͬ���˻�";break;
		case "6":info="�̼Ҿܾ�����";break;
		case "7":info="�̼�ͬ�⻻��";break;
		default:
			break;
		}
		return info;
	}
	public static String getSellerStatus(String e) {
		String info=null;
		switch(e) {
		case "0":info="����Ѹ���";break;
		case "1":info="��ȷ���ջ�";break;
		case "2":info="�����˻�";break;
		case "3":info="���󻻻�";break;
		case "4":info="�Ѿܾ��˻�";break;
		case "5":info="��ͬ���˻�";break;
		case "6":info="�Ѿܾ�����";break;
		case "7":info="��ͬ�⻻��";break;
		default:
			break;
		}
		return info;
	}
	
	public static String changeTradeStatus(String type,String orderid) {      //�ı佻��״̬��
		String info=null;
		Connection connection=getConnection();
		String sql="update orders set tradestatus='"+type+"' where id='"+orderid+"'";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Integer.parseInt(type)>=4)
			info="��"+getSellerStatus(type);
		else info="��"+getBuyerStatus(type);
		return info;
	}
	
	public static String deleteOrders(String orderid,String type) {   //ɾ������
		Connection connection=getConnection();
		String sql="";
		if(type.equals("1"))
			sql="update orders set buyerstatus='1' where id='"+orderid+"'";
		else if(type.equals("2"))
			sql="update orders set sellerstatus='1' where id='"+orderid+"'";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "������ɾ��";
	}
	
	public static String orderInformation(String orderid) {
		JSONArray json=new JSONArray();
		String sql="select * from orders where id='"+orderid+"'";
		ResultSet rs=null;
		Connection connection=getConnection();
		Order order=new Order();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			if(rs.next()) {
				order.setBuyer(rs.getString("buyer"));
				order.setBuyerid(rs.getString("buyerid"));
				order.setBuyerlocation(rs.getString("buyerlocation"));
				order.setBuyerphone(rs.getString("buyerphone"));
				order.setSellerlocation(rs.getString("sellerlocation"));
				order.setSellerphone(rs.getString("sellerphone"));
				order.setGoods(rs.getString("goods"));
				order.setGoodsid(rs.getString("id"));
				order.setNum(rs.getString("num"));
				order.setPrice(rs.getString("price"));
				order.setSeller(rs.getString("seller"));
				order.setSellerid(rs.getString("sellerid"));
				order.setSum(rs.getString("sum"));
				order.setImgurl(rs.getString("imgurl"));
				order.setTradedate(rs.getString("tradedate"));
				order.setId(rs.getString("id"));
				order.setBuyererstatus(rs.getString("buyerstatus"));
				order.setSellerstatus(rs.getString("sellerstatus"));
				order.setTradestatus(getBuyerStatus(rs.getString("tradestatus")));
				json.add(order);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.get(0).toString();
	}
	
	public static String addGoods(Goods goods) {
		Connection connection=getConnection();
		String sql="insert into goods values('"+getIdentifier()+"','"+goods.getImgurl()+"','"+goods.getName()+"',"
				+ "'"+goods.getPrice()+"','"+goods.getNownum()+"','"+goods.getRemark()+"','"+goods.getType()+"',"
				+ "'"+getDateString()+"','"+goods.getSellerid()+"','"+goods.getSeller()+"','1')";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "�����Ʒ�ɹ�";
	}
	
	public static String getGoodsStatus(String e) {
		String info=null;
		switch(e) {
		case "1":info="���ϼ�";break;
		case "0":info="���¼�";break;
		default:break;
		}
		return info;
	}
	
	public static String getGoodsType(String e) {
		String info=null;
		switch(e) {
		case "0":info="������Ʒ";break;
		case "1":info="����Ƽ�";break;
		case "2":info="������Ʒ";break;
		case "3":info="��ױ����";break;
		case "4":info="�Ի���ʳ";break;
		default:break;
		}
		return info;
	}
	
	public static String showMyGoods(String sellerid) {
		Connection connection=getConnection();
		String sql="select * from goods where sellerid='"+sellerid+"' and status!='-1'";
		ResultSet rs=null;
		JSONArray json=new JSONArray();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Goods goods=new Goods();
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setDate(rs.getString("date"));
				goods.setImgurl(rs.getString("imgurl"));
				goods.setNownum(rs.getString("nownum"));
				goods.setPrice(rs.getString("price"));
				goods.setRemark(rs.getString("remark"));
				goods.setSeller(rs.getString("seller"));
				goods.setSellerid(rs.getString("sellerid"));
				goods.setStatus(getGoodsStatus(rs.getString("status")));
				goods.setType(getGoodsType(rs.getString("type")));
				json.add(goods);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static String showSellerOrders(String sellerid) {    //��ȡ�̻�����
		Connection connection=getConnection();
		String sql="select * from orders where sellerid='"+sellerid+"' and sellerstatus=0";
		ResultSet rs=null;
		JSONArray json=new JSONArray();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Order order=new Order();
				order.setBuyer(rs.getString("buyer"));
				order.setBuyerid(rs.getString("buyerid"));
				order.setBuyerlocation(rs.getString("buyerlocation"));
				order.setBuyerphone(rs.getString("buyerphone"));
				order.setSellerlocation(rs.getString("sellerlocation"));
				order.setSellerphone(rs.getString("sellerphone"));
				order.setGoods(rs.getString("goods"));
				order.setGoodsid(rs.getString("id"));
				order.setNum(rs.getString("num"));
				order.setPrice(rs.getString("price"));
				order.setSeller(rs.getString("seller"));
				order.setSellerid(rs.getString("sellerid"));
				order.setSum(rs.getString("sum"));
				order.setImgurl(rs.getString("imgurl"));
				order.setTradedate(rs.getString("tradedate"));
				order.setId(rs.getString("id"));
				order.setBuyererstatus(rs.getString("buyerstatus"));
				order.setSellerstatus(rs.getString("sellerstatus"));
				order.setTradestatus(getSellerStatus(rs.getString("tradestatus")));
				json.add(order);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static String serviceSeller(String sellerid) {      //�ۺ�֪ͨ
		Connection connection=getConnection();
		String sql="select * from orders where sellerid='"+sellerid+"' and (tradestatus='2' or tradestatus='3')";
		ResultSet rs=null;
		JSONArray json=new JSONArray();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Order order=new Order();
				order.setBuyer(rs.getString("buyer"));
				order.setBuyerid(rs.getString("buyerid"));
				order.setBuyerlocation(rs.getString("buyerlocation"));
				order.setBuyerphone(rs.getString("buyerphone"));
				order.setSellerlocation(rs.getString("sellerlocation"));
				order.setSellerphone(rs.getString("sellerphone"));
				order.setGoods(rs.getString("goods"));
				order.setGoodsid(rs.getString("id"));
				order.setNum(rs.getString("num"));
				order.setPrice(rs.getString("price"));
				order.setSeller(rs.getString("seller"));
				order.setSellerid(rs.getString("sellerid"));
				order.setSum(rs.getString("sum"));
				order.setImgurl(rs.getString("imgurl"));
				order.setTradedate(rs.getString("tradedate"));
				order.setId(rs.getString("id"));
				order.setBuyererstatus(rs.getString("buyerstatus"));
				order.setSellerstatus(rs.getString("sellerstatus"));
				order.setTradestatus(getSellerStatus(rs.getString("tradestatus")));
				json.add(order);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static void serviceTradeStatus467(String orderid,String type) {   //ͬ��ܾ��������ܾ��˻�
		Connection connection=getConnection();
		String sql="update orders set tradestatus='"+type+"' where id='"+orderid+"'";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void serviceTradeStatus5(String orderid) {         //ͬ���˻�
		Connection connection=getConnection();
		String sql="update orders set tradestatus=5 where id='"+orderid+"'";
		ResultSet rs=null;
		String sellerid=null,buyerid=null,goodsid=null;
		double sum=0,num=0;
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			sql="select * from orders where id='"+orderid+"'";
			rs=statement.executeQuery(sql);
			if(rs.next()) {
				buyerid=rs.getString("buyerid");
				sellerid=rs.getString("sellerid");
				goodsid=rs.getString("goodsid");
				sum=rs.getDouble("sum");
				num=rs.getDouble("num");
			}
			rs.close();
			sql="update user set account=account+'"+sum+"' where id='"+buyerid+"'";  //��ҷ������
			statement.executeUpdate(sql);
			sql="update user set account=account-'"+sum+"' where id='"+sellerid+"'";  //�̼ҿ۳����
			statement.executeUpdate(sql);
			sql="update goods set nownum=nownum+'"+num+"' where id='"+goodsid+"'";    //��Ʒ���������
			statement.executeUpdate(sql);
			statement .close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String goodsStatus(String goodsid,String type) {     //�ı���Ʒ״̬
		Connection connection=getConnection();
		String sql="update goods set status='"+type+"' where id='"+goodsid+"'";
		String info=null;
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			if(type.equals("1"))
				info="����Ʒ���ϼ�";
			else if(type.equals("0"))
				info="����Ʒ���¼�";
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public static String deleteGoods(String goodsid) {                  //ɾ����Ʒ
		Connection connection=getConnection();
		String sql="update goods set status='-1' where id='"+goodsid+"'";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "����Ʒ��ɾ��";
	}
	
	public static String showClassedGoods(String type) {     //��Ʒ����������Ʒ
		JSONArray json=new JSONArray();
		Connection connection=getConnection();
		String sql="select * from goods where type='"+type+"' and status=1 and nownum>0";
		ResultSet rs=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Goods goods=new Goods();
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setDate(rs.getString("date"));
				goods.setImgurl(rs.getString("imgurl"));
				goods.setNownum(rs.getString("nownum"));
				goods.setPrice(rs.getString("price"));
				goods.setRemark(rs.getString("remark"));
				goods.setSeller(rs.getString("seller"));
				goods.setSellerid(rs.getString("sellerid"));
				goods.setStatus(rs.getString("status"));
				goods.setType(rs.getString("type"));
				json.add(goods);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static String chargeMoney(String money,String id) {       //��ֵ
		Connection connection=getConnection();
		String info=null;
		String sql="update user set account=account+"+Double.parseDouble(money)+" where id='"+id+"'";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(sql);
			info="��ֵ�ɹ�";
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public static String myProfit(String sellerid) {         //��ȡӪҵ����
		JSONArray json=new JSONArray();
		String sql="select * from goods where sellerid='"+sellerid+"'";
		String tempid="";
		ResultSet rs=null;
		Connection connection=getConnection();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				JSONObject tempjson=new JSONObject();
				tempjson.put("goods", rs.getString("name"));
				tempjson.put("imgurl", rs.getString("imgurl"));
				tempjson.put("price", rs.getString("price"));
				tempjson.put("date", rs.getString("date"));
				tempid+=rs.getString("id")+";";
				json.add(tempjson);
			}
			rs.close();
			String id[]=tempid.split(";");
			for(int i=0;i<json.size();i++) {
				sql="select sum(sum),sum(num) from orders where goodsid='"+id[i]+"' and (tradestatus=1 or tradestatus=4 or tradestatus=6 or tradestatus=7)";
				rs=statement.executeQuery(sql);
				if(rs.next()) {
					if(rs.getString(2)!=null) {
						json.getJSONObject(i).put("num", rs.getString(2)+"��");
						json.getJSONObject(i).put("sum", rs.getString(1)+"Ԫ");
					}
					else {
						json.getJSONObject(i).put("num", 0+"��");
						json.getJSONObject(i).put("sum", 0+"Ԫ");
					}
				}else {
					json.getJSONObject(i).put("num", 0+"��");
					json.getJSONObject(i).put("sum", 0+"Ԫ");
				}
				rs.close();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static String getUserList(String type) {
		String sql="select * from user where id!='admin' order by date desc";
		if(!type.equals("0")) {
			sql="select * from user where status='"+type+"' order by date desc";
		}
		JSONArray jsonArray=new JSONArray();
		ResultSet rs=null;
		Connection connection=getConnection();
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while (rs.next()) {
				JSONObject object=new JSONObject();
				object.put("id", rs.getString("id"));
				object.put("name", rs.getString("name"));
				object.put("sex", rs.getString("sex"));
				object.put("status", rs.getString("status").equals("1")?"��ͨ�û�":"�̼�");
				object.put("location", rs.getString("location"));
				object.put("phone", rs.getString("phone"));
				object.put("date", rs.getString("date"));
				jsonArray.add(object);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toString();
	}
	
	
	public static int deleteUser(String id) {
		String sql="delete from user where id='"+id+"'";
		int a=0;
		Connection connection=getConnection();
		try {
			Statement statement=connection.createStatement();
			a=statement.executeUpdate(sql);
			connection.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	
	public static HSSFWorkbook executeExcel(Excel excel) {		//Excel����
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		HSSFRow title=sheet.createRow(0);			//��������
		HSSFCell cell=title.createCell(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellValue(excel.getTitle());
		cell.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,excel.getCoList().size()-1));
		
		HSSFRow coList=sheet.createRow(1);				//������
		for (int i=0;i<excel.getCoList().size();i++) {
			HSSFCell cell1=coList.createCell(i);
			cell1.setCellValue(excel.getCoList().get(i));
		}
		
		Connection connection=getConnection();
		ResultSet rs=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(excel.getSql());
			int count=2;
			while(rs.next()) {
				HSSFRow row=sheet.createRow(count);
				for (int i=0;i<excel.getCoList().size();i++) {
					HSSFCell cell2=row.createCell(i);
					cell2.setCellValue(rs.getString(i+1));
				}
				count++;
			}
			HSSFRow row=sheet.createRow(count);
			HSSFCell cell2=row.createCell(0);
			cell2.setCellValue("����ʱ��:"+getDateString());
			cell2.setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(count,count,0,excel.getCoList().size()-1));
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}
	
	public static String getOrdersList(String begindate,String enddate,String beginnum,String endnum) {
		JSONArray jsonArray=new JSONArray();
		String sql="select * from orders where 1=1";
		if(begindate!=null&&!begindate.isEmpty()) {
			sql+=" and tradedate>='"+begindate+"'";
		}
		if(enddate!=null&&!enddate.isEmpty()) {
			sql+=" and tradedate<='"+enddate+"'";
		}
		if(beginnum!=null&&!beginnum.isEmpty()) {
			sql+=" and sum>="+beginnum;
		}
		if(endnum!=null&&!endnum.isEmpty()) {
			sql+=" and sum<="+endnum;
		}
		sql+=" order by tradedate desc";
		System.out.println(sql);
		
		Connection connection=getConnection();
		ResultSet rs=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Order order=new Order();
				order.setBuyer(rs.getString("buyer"));
				order.setBuyerlocation(rs.getString("buyerlocation"));
				order.setBuyerphone(rs.getString("buyerphone"));
				order.setSellerlocation(rs.getString("sellerlocation"));
				order.setSellerphone(rs.getString("sellerphone"));
				order.setGoods(rs.getString("goods"));
				order.setGoodsid(rs.getString("goodsid"));
				order.setNum(rs.getString("num"));
				order.setPrice(rs.getString("price"));
				order.setSeller(rs.getString("seller"));
				order.setSellerid(rs.getString("sellerid"));
				order.setSum(rs.getString("sum"));
				order.setImgurl(rs.getString("imgurl"));
				order.setTradedate(rs.getString("tradedate"));
				order.setId(rs.getString("id"));
				order.setBuyererstatus(rs.getString("buyerstatus"));
				order.setSellerstatus(rs.getString("sellerstatus"));
				order.setTradestatus(getBuyerStatus(rs.getString("tradestatus")));
				jsonArray.add(order);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	
	public static int deleteOrder(String id) {
		String sql="delete from orders where id='"+id+"'";
		Connection connection=getConnection();
		int a=0;
		try {
			Statement statement=connection.createStatement();
			a=statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	
	public static String getGoodsList(String begindate,String enddate,String beginnum,String endnum) {
		JSONArray jsonArray=new JSONArray();
		String sql="select * from goods where 1=1";
		if(begindate!=null&&!begindate.isEmpty()) {
			sql+=" and date>='"+begindate+"'";
		}
		if(enddate!=null&&!enddate.isEmpty()) {
			sql+=" and date<='"+enddate+"'";
		}
		if(beginnum!=null&&!beginnum.isEmpty()) {
			sql+=" and price>="+beginnum;
		}
		if(endnum!=null&&!endnum.isEmpty()) {
			sql+=" and price<="+endnum;
		}
		sql+=" order by date desc";
		System.out.println(sql);
		Connection connection=getConnection();
		ResultSet rs=null;
		try {
			Statement statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				Goods goods=new Goods();
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setDate(rs.getString("date"));
				goods.setImgurl(rs.getString("imgurl"));
				goods.setNownum(rs.getString("nownum"));
				goods.setPrice(rs.getString("price"));
				goods.setRemark(rs.getString("remark"));
				goods.setSeller(rs.getString("seller"));
				goods.setSellerid(rs.getString("sellerid"));
				goods.setStatus(getGoodsStatus(rs.getString("status")));
				goods.setType(rs.getString("type"));
				goods.setStatustype(rs.getString("status").equals("1")?true:false);
				jsonArray.add(goods);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toString();
	}
	
	public static void main(String[] args) {
		getGoodsList("2020-01-11", "2020-07-12", "", "1200");
	}
	
	
}
