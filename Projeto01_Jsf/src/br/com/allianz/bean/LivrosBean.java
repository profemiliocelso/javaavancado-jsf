package br.com.allianz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.allianz.dao.LivrosDao;
import br.com.allianz.models.Livro;

@ManagedBean(name= "beanLivro")
@RequestScoped
public class LivrosBean {
	
	private Livro livro;
	public LivrosBean() {
		if(livro == null) {
			livro = new Livro();
		}
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	//metodo de a��o (action) que, quando executado, retorna o 
	//redirecionamento conforme o resultado da execu��o
	public String incluirLivro() {
		FacesMessage msg = new FacesMessage();

		try {
			new LivrosDao().incluirLivro(livro);
			msg.setSummary("OK");
			msg.setDetail("Livro " + livro.getTitulo() + " inclu�do");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

			return "sucesso";
		} catch (Exception e) {
			//e.printStackTrace();
			msg.setSummary("ERRO:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

			return "erro";
		}
	}
	
	//propriedade usada para apresentar a lista dos livros cadastrados
	public List<Livro> getListaLivros() throws Exception {
		return new LivrosDao().listarLivros();
	}
}
