package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import Bean.Excel;
import DBUtil.DBUtil;

/**
 * Servlet implementation class adminController
 */
@WebServlet("/adminController")
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String m=request.getParameter("m");
		if(m==null)
			return;
		switch(m) {
		case "getuserlist":
			getUserList(request, response);
			break;
		case "deleteuser":
			deleteUser(request, response);
			break;
		case "xlsdownload":
			xlsDownLoad(request, response);
			break;
		case "getorderlist":
			getOrderList(request, response);
			break;
		case "deleteorder":
			deleteOrder(request, response);
			break;
		case "getgoodslist":
			getGoodsList(request, response);
			break;
		case "changegoodsstatus":
			changeGoodsStatus(request, response);
			break;
		default:break;
		}
		
	}
	
	private void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		response.getWriter().write(DBUtil.getUserList(type));
		
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		response.getWriter().write(DBUtil.deleteUser(id));
		
	}
	

	private void xlsDownLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type=Integer.parseInt(request.getParameter("type"));
		Excel excel=new Excel();
		if (type==0) {				//用户表
			excel.setFilename("用户表.xls");
			excel.setTitle("用户一览表");
			List<String> list=new ArrayList<String>();
			list.add("ID");
			list.add("昵称");
			list.add("性别");
			list.add("身份");
			list.add("地区");
			list.add("联系方式");
			list.add("注册时间");
			excel.setCoList(list);
			excel.setSql("select id,name,sex,case status "
					+ "when '1' then '普通用户' "
					+ "when '2' then '商家' "
					+ "else '管理员' end"
					+ ",location,phone,date from user where id!='admin' order by date desc");
		}else if(type==1){     		//商品表
			
			String begindate=request.getParameter("bdate");
			String enddate=request.getParameter("edate");
			String beginnum=request.getParameter("bnum");
			String endnum=request.getParameter("endnum");
			String sql="select id,name,price,nownum,date,case status "
					+ "when '0' then '已下架' "
					+ "when '1' then '已上架' end"
					+ ",seller from goods where 1=1";
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
			excel.setSql(sql);
			excel.setFilename("商品表.xls");
			excel.setTitle("商品一览表");
			
			List<String> list=new ArrayList<String>();
			list.add("ID");
			list.add("商品名称");
			list.add("单价");
			list.add("当前库存");
			list.add("发布时间");
			list.add("商品状态");
			list.add("商家昵称");
			excel.setCoList(list);
			
		}else if(type==2) {			//订单表
			String begindate=request.getParameter("bdate");
			String enddate=request.getParameter("edate");
			String beginnum=request.getParameter("bnum");
			String endnum=request.getParameter("endnum");
			String sql="select id,goods,price,num,sum,tradedate,case tradestatus "
					+ "when '0' then '买家已付款' "
					+ "when '1' then '已确认收货' "
					+ "when '2' then '已发起退货' "
					+ "when '3' then '已发起换货' "
					+ "when '4' then '商家拒绝退货' "
					+ "when '5' then '商家同意退货' "
					+ "when '6' then '商家拒绝换货' "
					+ "when '7' then '商家同意换货' "
					+ "else '' end "
					+ "from orders where 1=1";
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
			excel.setSql(sql);
			excel.setFilename("订单表.xls");
			excel.setTitle("订单一览表");
			List<String> list=new ArrayList<String>();
			list.add("ID");
			list.add("商品名称");
			list.add("商品单价");
			list.add("购买数量");
			list.add("小计");
			list.add("日期");
			list.add("订单状态");
			excel.setCoList(list);
			
		}
		HSSFWorkbook wb=DBUtil.executeExcel(excel);
		OutputStream output=response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition","attachment; filename="+new String(excel.getFilename().getBytes("GB2312"),"ISO-8859-1"));
		response.setContentType("application/octet-stream");        
		wb.write(output);
		output.close();
		wb.close();
		
	}
	
	private void getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String begindate=request.getParameter("begindate");
		String enddate=request.getParameter("enddate");
		String beginnum=request.getParameter("beginnum");
		String endnum=request.getParameter("endnum");
		response.getWriter().write(DBUtil.getOrdersList(begindate, enddate, beginnum, endnum));
		
	}
	
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		response.getWriter().write(DBUtil.deleteOrder(id));
		
	}

	private void getGoodsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String begindate=request.getParameter("begindate");
		String enddate=request.getParameter("enddate");
		String beginnum=request.getParameter("beginnum");
		String endnum=request.getParameter("endnum");
		response.getWriter().write(DBUtil.getGoodsList(begindate, enddate, beginnum, endnum));
		
	}
	
	private void changeGoodsStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		response.getWriter().write(DBUtil.goodsStatus(id, "0"));
		
	}
	

}
