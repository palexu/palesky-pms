package com.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.ProductDao;
import com.model.dao.ProjectDao;

/**
 * Servlet implementation class ShowProductServlet
 */
@WebServlet("/product")
public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stubow
		// 命名为itemList的原因是因为要实现 ./WEB-INF/part/list-group
		// 的复用，所以所有数据传过去，最好都命名为itemList --xjy
		// 需要先移除可能存在的值
		request.removeAttribute("itemList");
		request.removeAttribute("list_group_title");

		ProductDao pd = new ProductDao();
		ProjectDao pj = new ProjectDao();

		String q = "";
		if(request.getParameter("q")==null)
			q="";
		else
			q=request.getParameter("q");
		if (q.equals("all") || q.equals("")) {
			request.setAttribute("list_group_title", "产品列表");
			request.setAttribute("list_group_title2", "和我有关的项目");
			request.setAttribute("itemList", pd.findAllProduct());
			request.setAttribute("itemList2", pj.findAllProject());
			request.setAttribute("itemType", "产品");
			request.setAttribute("itemType2", "项目");
			request.setAttribute("url", "product");
			request.setAttribute("url2", "project");
		} else if (q.equals("me")) {
			request.setAttribute("list_group_title", "和我有关的产品");
			request.setAttribute("list_group_title2", "和我有关的项目");
			request.setAttribute("itemList", pd.findAllProduct());
			request.setAttribute("itemList2", pj.findAllProject());
			request.setAttribute("itemType", "产品");
			request.setAttribute("itemType2", "项目");
			request.setAttribute("url", "product");
			request.setAttribute("url2", "project");
		} else {//特定的产品
			request.setAttribute("list_group_title", "产品列表");
			request.setAttribute("list_group_title2", "和我有关的项目");
			request.setAttribute("itemList", pd.findAllProduct());
			request.setAttribute("itemList2", pj.findAllProject());
			request.setAttribute("itemType", "产品");
			request.setAttribute("itemType2", "项目");
			request.setAttribute("url", "product");
			request.setAttribute("url2", "project");
		}

		request.getRequestDispatcher("product.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}