package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DBUtil.DBUtil;
import net.sf.json.JSONObject;
/**
 * Servlet implementation class changeimg
 */
@WebServlet("/changeimg")
public class changeimg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeimg() {
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
//		String temppath="D:\\tomcat\\apache-tomcat-9.0.26\\tempfile";
		String path=request.getServletContext().getRealPath("images/user");
//		String temppath="C:\\Program Files\\apache-tomcat-9.0.31-windows-x64\\apache-tomcat-9.0.31\\tempfile";
//		String path="C:\\Program Files\\apache-tomcat-9.0.31-windows-x64\\apache-tomcat-9.0.31\\webapps\\shoppingplus\\images\\user";
		String id=request.getParameter("id");
		DiskFileItemFactory disk = new DiskFileItemFactory();
	    ServletFileUpload up = new ServletFileUpload(disk);
	    List<FileItem> list;
	    JSONObject json=new JSONObject();   //����json�ַ���
	    json.put("code", 0);
	    json.put("msg",id+".jpg");
	    File file1=new File(path);
	    for(File item:file1.listFiles()) 
	    	if(item.getName().equals(id+".jpg"))
	    		item.delete();
		try {
			 list = up.parseRequest(request);
			 FileItem file = list.get(0);
			        //��ȡ�ļ�����
			 String fileName = file.getName();
			        //��ȡ�ļ������ͣ�
			 String fileType = file.getContentType();
			        //��ȡ�ļ����ֽ��룺
			 InputStream in = file.getInputStream();
			        //�ļ���С
			 int size = file.getInputStream().available();
			        //��������ֽ���
			 OutputStream out = new FileOutputStream(path+"/"+id+".jpg");
			        //�ļ�copy
			 byte[] b = new byte[1024];      //��С1mb
			 int len = 0;
			 while((len=in.read(b))!=-1){
				 out.write(b, 0, len);
			 }
			 out.flush(); 
			 out.close();
			 in.close();
			 file.delete();
			 DBUtil.changeImg(id);
			 response.getWriter().write(json.toString());
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
	}

}
