package controller;

import java.io.IOException;

public class ProcessosController
{
	public ProcessosController()
	{
		super();
	}
	
//	Retorne o sistema operacional que est� em execu��o na m�quina
	public String os()
	{
/*
 *		Keys utilizados no getProperty
 *			os.name = nome do SO
 *			os.arch = arquitetura do SO
 *			os.version = vers�o do SO
 */
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		
		return os + " - v. " + version + " - arch. " + arch;
	}
	
//	Para aplica��o Java chamar um novo processo
	public void callProcess(String process)
	{
		try
		{
			Runtime.getRuntime().exec(process);
		}
		catch (Exception e)
		{
			String msgError = e.getMessage();
//			System.err.println(msgError);
			
			if (msgError.contains("740"))
			{
//				Para "elevar" o acesso no windows:
//				cmd /c caminho_do_processo -> traz a tela de permiss�o
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				
				try
				{
					Runtime.getRuntime().exec(buffer.toString());
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
			else
			{
				e.printStackTrace();
			}
		}
	}
}
