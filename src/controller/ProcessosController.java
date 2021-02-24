package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//M�todo construtor
public class ProcessosController
{
	public ProcessosController()
	{
		super();
	}
	
//	Retorna o sistema operacional que est� em execu��o na m�quina
	public String os()
	{

//		Keys utilizados no getProperty
//			os.name = nome do SO
//			os.arch = arquitetura do SO
//			os.version = vers�o do SO
		
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
				System.err.println(msgError);
			}
		}
	}
	
//	Ler processos tragos da aplica��o Java
	public void readProcess (String process)
	{
		try
		{
// 			O Runtime trar� os dados numa variavel do tipo processo
//			possibilitando salvarmos numa outra variavel, desta vez do tipo InputStream
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			
// 			Convers�o da variavel fluxo (sa�da de console) para string
// 			e armazenamento em um buffer especial
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
//			Para lermos e descartarmos a primeira linha do buffer
			String linha = buffer.readLine();
			
			while (linha != null)
			{
				System.out.println(linha);
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
