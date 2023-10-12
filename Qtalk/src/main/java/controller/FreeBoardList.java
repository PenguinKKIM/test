package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FreeBoardService;
import service.FreeBoardServiceImpl;

/**
 * Servlet implementation class FreeBoardList
 */
@WebServlet("/freeboard")
public class FreeBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String page = request.getParameter("page");
		int curpage = 1;
		if(page!=null) {
			curpage = Integer.parseInt(page);
		}

		try {
			FreeBoardService freeBoardService = new FreeBoardServiceImpl();
			Map<String, Object> res = freeBoardService.FreeBoardListByPage(curpage);
			request.setAttribute("res", res);
			request.getRequestDispatcher("freeboard.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("freeboard.jsp").forward(request, response);
		}
	}

}
