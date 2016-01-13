package aplication;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import aplication.exceptions.InsufficientMedicoesException;
import aplication.exceptions.ServerNotFoundException;


public interface IMonitor {
	
	public Medicao getMedicao(TipoGrandeza tipo) throws ServerNotFoundException, Throwable;
	public void setmedicao(Medicao m) throws ServerNotFoundException, UnknownHostException, IOException, InsufficientMedicoesException, Throwable;
	public Medicao getMedicaoAnterior() throws ServerNotFoundException, UnknownHostException, IOException, InsufficientMedicoesException, Throwable;
	public List<Medicao> getCincoUltimasMedicoes() throws InsufficientMedicoesException, ServerNotFoundException, UnknownHostException, IOException, Throwable;

}
