package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//Método construtor
public class ProcessosController
{
	public ProcessosController()
	{
		super();
	}
	
//	Retorna o sistema operacional que está em execução na máquina
	public String os()
	{

//		Keys utilizados no getProperty
//			os.name = nome do SO
//			os.arch = arquitetura do SO
//			os.version = versão do SO
		
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		
		return os + " - v. " + version + " - arch. " + arch;
	}
	
//	Para aplicação Java chamar um novo processo
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
//				cmd /c caminho_do_processo -> traz a tela de permissão
				
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
	
//	Ler processos tragos da aplicação Java
	public void readProcess (String process)
	{
		try
		{
// 			O Runtime trará os dados numa variavel do tipo processo
//			possibilitando salvarmos numa outra variavel, desta vez do tipo InputStream
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			
// 			Conversão da variavel fluxo (saída de console) para string
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
	
//	Para encerrar processos
	public void killProcess(String param)
	{
		String cmdPid = "TASKKILL /PID";
		String cmdNome = "TASKKILL /IM";
		StringBuffer buffer = new StringBuffer();
		int pid = 0;
		
//		Caso seja número, a variavel pid será preenchida, caso contrario ocorrá uma Exception (NumberFormatException)
		
		try
		{
			pid = Integer.parseInt(param);
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
//			TASKKILL /PID 9999
		}
		catch(NumberFormatException e)
		{
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(param);
//			TASKKILL /PID XXXXXX
		}
		
		callProcess(buffer.toString());
		
	}
}
