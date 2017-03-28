package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.ValueOutputStream;

import helper.JsonHelper;
import model.Resultado;
import model.operacao;
@WebServlet(urlPatterns = "/controller")

public class OperacaoController  extends HttpServlet{

	private List<Object> lista = new ArrayList<Object>();
	private List<Object> listares = new ArrayList<Object>();
	private JsonHelper jsonHelper =  new JsonHelper();
	@Override	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String n1 = req.getParameter("n1");
		String n2 = req.getParameter("n2");		
		String op = req.getParameter("op");
		double res =0;
		
		operacao opera = new operacao(Double.parseDouble(n1),Double.parseDouble(n2),op);
		
		lista.add(opera);
		
		
		if(op.equals("som")){
		res = Double.parseDouble(n1) + Double.parseDouble(n2);
		}else if(op.equals("sub")){
			res = Double.parseDouble(n1) - Double.parseDouble(n2);
		}else if(op.equals("mult")){
			res = Double.parseDouble(n1) * Double.parseDouble(n2);
		}else if(op.equals("div")){
			res = Double.parseDouble(n1) / Double.parseDouble(n2);
		}
		
		Resultado resu = new Resultado(res);
		listares.add(resu);
		
		try {
			resp.getWriter().println(jsonHelper.gerarJson(opera));
	
		
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String json;
		try {
			json = jsonHelper.gerarJsonLista(listares);
			resp.getWriter().print(json);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		
	}
}
