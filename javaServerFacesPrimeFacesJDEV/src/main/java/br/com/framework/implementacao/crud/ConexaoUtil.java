package br.com.framework.implementacao.crud;

public enum ConexaoUtil {
	
	JAVA_COMP_ENV_JDBC_DATA_SOURCE("java:/comp/env/jdbc/datasource");
	
	private final String opcao;
	
	ConexaoUtil(String opcao){
		
		this.opcao=opcao;
	}
	
	public String getOpcao() {
		return this.opcao;
	}
	

}
