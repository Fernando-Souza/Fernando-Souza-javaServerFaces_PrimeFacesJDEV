package br.com.framework.implementacao.crud;

public enum ConexaoUtil {
	
	DATA_SOURCE("java:/comp/env/jdbc/datasource");
	
	private final String opcao;
	
	ConexaoUtil(String opcao){
		
		this.opcao=opcao;
	}
	
	public String getOpcao() {
		return this.opcao;
	}
	

}
