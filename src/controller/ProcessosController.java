package controller;

public class ProcessosController
{
	public ProcessosController()
	{
		super();
	}
	
	// Retorne o sistema operacional que est� em execu��o na m�quina
	public String os()
	{
		/*
		 * Keys utilizados no getProperty
		 * 	os.name = nome do SO
		 * 	os.arch = arquitetura do SO
		 * 	os.version = vers�o do SO
		 */
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		
		return os + " - v. " + version + " - arch. " + arch;
	}
}
