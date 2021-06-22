package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBUtil.DBUtil;

/**
 * Servlet implementation class customer_service
 */
@WebServlet("/customer_service")
public class customer_service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customer_service() {
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
		String orderid=request.getParameter("orderid");
		String type=request.getParameter("type");
		String info=null;
		switch(type) {
		case "1":DBUtil.serviceTradeStatus5(orderid);info="您已同意退货";break;
		case "2":DBUtil.serviceTradeStatus467(orderid,"7");info="您已同意换货";break;
		case "3":DBUtil.serviceTradeStatus467(orderid,"4");info="您已拒绝退货";break;
		case "4":DBUtil.serviceTradeStatus467(orderid,"6");info="您已拒绝换货";break;
		default:break;
		}
		response.getWriter().write(info);
	}

}
