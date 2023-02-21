package br.com.framework.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Permissao {
	
	ADMIN("Admin","Administrador"),
	USER ("USER","Usuario Padrão"),
	CADASTRO_ACESSAR("CADASTRO_ACESSAR","Acesso ao cadastro"),
	FINANCEIRO_ACESSAR("FINANCEIRO_ACESSAR","Acesso ao financeiro"),
	MENSAGEM_ACESSAR("MENSAGEM_ACESSAR","Mensagem recebida - Acessar"),
	
	BAIRRO_ACESSAR("BAIRRO_ACESSAR","Bairro - Acessar"),
	BAIRRO_NOVO("BAIRRO_NOVO","Bairro - Novo"),
	BAIRRO_EDITAR("BAIRRO_EDITAR","Bairro - Editar"),
	BAIRRO_EXCLUIR("BAIRRO_EXCLUIR","Bairro - Excluir");
	
	
	private String valor;
	private String descricao;
	
	Permissao(String nome, String descricao){
		
		this.valor=nome;
		this.descricao=descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		
		return getValor();
	}
	
	public static List<Permissao> getListPermissao(){
		
		List<Permissao> permissoes = new ArrayList<>();
		
		for(Permissao permissao:Permissao.values()) {
			
			permissoes.add(permissao);
			
		}
		
		Collections.sort(permissoes, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao obj1, Permissao obj2) {
				
				return obj1.compareTo(obj2);
			}
			
		});;
		
		return permissoes;
	
	}

}
