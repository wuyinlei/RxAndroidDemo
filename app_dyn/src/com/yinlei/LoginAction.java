package com.yinlei;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.JSONSerializer;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;

@WebServlet("/login")
public class LoginAction extends HttpServlet {

	private static final long SerialVersionUIDAdder = 1L;
	
	public LoginAction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理客户的post请求
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username.equals("admin")&&password.equals("123456")) {
			ResultBean entity = new ResultBean();
			entity.setResultCode(1);
			entity.setResultMsg("success");
			HashMap<String, ResultBean> map = new HashMap<>();
			map.put("result", entity);
			String json_value = net.sf.json.JSONSerializer.toJSON(map).toString();
			pw.println(json_value);
		}
		pw.flush();
		pw.close();
	}
}
