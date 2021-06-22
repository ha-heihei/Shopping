package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import DBUtil.DBUtil;
import Dao.Goods;

/**
 * Servlet implementation class add_goods
 */
@WebServlet("/add_goods")
public class add_goods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_goods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Goods goods=new Goods();
		goods.setImgurl(request.getParameter("imgurl"));
		goods.setName(request.getParameter("name"));
		goods.setNownum(request.getParameter("nownum"));
		goods.setPrice(request.getParameter("price"));
		goods.setRemark(request.getParameter("remark"));
		goods.setSeller(request.getParameter("seller"));
		goods.setSellerid(request.getParameter("sellerid"));
		goods.setType(request.getParameter("type"));
		DBUtil.addGoods(goods);
		response.sendRedirect("mygoods.html?sellerid="+goods.getSellerid());
	}

}
