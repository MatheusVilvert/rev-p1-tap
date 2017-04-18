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
import helper.JsonHelper;
import model.Veiculo;
@WebServlet(urlPatterns = "/controller")

public class VeiculoController extends HttpServlet {
	private List<Object> lista = new ArrayList<Object>();
	private JsonHelper jsonHelper = new JsonHelper();
	private int id =0;
	private int qtd =0;
	private double barato =999999999;
	private double caro =0;
	private String veiCaro;
	private String veiBarato;	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws	ServletException, IOException {
		
		String placa = req.getParameter("placa");
		String nome = req.getParameter("nome");
		String marca = req.getParameter("marca");
		double valor = Double.parseDouble(req.getParameter("valor"));
		Veiculo vei = new Veiculo((++this.id),placa,nome,marca,valor);
		lista.add(vei);		
		this.qtd++;
		resp.getWriter().println("{ id:" + vei.getId() + " , placa: " + vei.getPlaca() + ", nome: "
		+ vei.getNome() +" , marca: "+vei.getMarca()+" , valor: "+vei.getValor() +" } ");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		for(int i=0;i<lista.size();i++){
			if(id == ((Veiculo) lista.get(i)).getId()){
			lista.remove(i);
			this.qtd--;			
			this.caro =0;		
			this.barato =999999999;
			
			
			
			}
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws	ServletException, IOException {
		String placa = req.getParameter("placa");
		String nome = req.getParameter("nome");
		String marca = req.getParameter("marca");
		double valor = Double.parseDouble(req.getParameter("valor"));
		int id = Integer.parseInt(req.getParameter("id"));
		
		for(int i=0;i<lista.size();i++){
			if(id == ((Veiculo) lista.get(i)).getId()){
				if(placa!=""){
					((Veiculo) lista.get(i)).setPlaca(placa);
				}
				if(nome != ""){
					((Veiculo) lista.get(i)).setNome(nome);
				}
				if(marca != ""){
					((Veiculo) lista.get(i)).setMarca(marca);
				}					
			((Veiculo) lista.get(i)).setValor(valor);
			this.caro =0;			
			this.barato =999999999;
			
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("op");
		String placa = req.getParameter("placa");
		
		String json;
		
		if(op.equals("placa")){			
			for(int i=0;i<lista.size();i++){
				if(((Veiculo) lista.get(i)).getPlaca().equals(placa)){
					
					resp.getWriter().println("{ id:" + ((Veiculo) lista.get(i)).getId() + " , placa: " + ((Veiculo) lista.get(i)).getPlaca() + ", nome: "
					+ ((Veiculo) lista.get(i)).getNome() +" , marca: "+((Veiculo) lista.get(i)).getMarca()+" , valor: "+((Veiculo) lista.get(i)).getValor() +" } ");
					i = lista.size();
					
				}
			
			}
			
		}else if(op.equals("qtd")){
			resp.getWriter().println("{ Quantidade:"+this.qtd+"}");
			
		}else if(op.equals("caro")){
			
				for(int j=0;j<lista.size();j++){
					if(((Veiculo) lista.get(j)).getValor()>this.caro){
						this.caro = ((Veiculo) lista.get(j)).getValor();
						this.veiCaro = "{ id:" + ((Veiculo) lista.get(j)).getId() + " , placa: " + ((Veiculo) lista.get(j)).getPlaca() + ", nome: "
								+ ((Veiculo) lista.get(j)).getNome() +" , marca: "+((Veiculo) lista.get(j)).getMarca()+" , valor: "+((Veiculo) lista.get(j)).getValor() +" } ";
					
					}
				}
			
			resp.getWriter().println(this.veiCaro);
			
		}else if(op.equals("barato")){
			
			for(int j=0;j<lista.size();j++){
				if(((Veiculo) lista.get(j)).getValor()<this.barato){
					this.barato = ((Veiculo) lista.get(j)).getValor();
					this.veiBarato = "{ id:" + ((Veiculo) lista.get(j)).getId() + " , placa: " + ((Veiculo) lista.get(j)).getPlaca() + ", nome: "
							+ ((Veiculo) lista.get(j)).getNome() +" , marca: "+((Veiculo) lista.get(j)).getMarca()+" , valor: "+((Veiculo) lista.get(j)).getValor() +" } ";
				
				}
			}
			
			resp.getWriter().println(this.veiBarato);
		}	
		
		else{
			try {
			json = jsonHelper.gerarJsonLista(lista);
			resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException |
			InvocationTargetException e) {			
			e.printStackTrace();
			}
			}
		}
}
