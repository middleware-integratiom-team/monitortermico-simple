package distribution;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;
import aplication.exceptions.InsufficientMedicoesException;
import aplication.exceptions.ServerNotFoundException;

public class ClientProxy implements IMonitor, Serializable {

	private static final long serialVersionUID = 1L;
	protected String host;
	protected int port;
	protected int objectId;

	public ClientProxy() {
		super();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public ClientProxy(String h, int p){
		this.host = h;
		this.port = p;
	}
	@Override
	public Medicao getMedicao(TipoGrandeza tipo) throws ServerNotFoundException, Throwable {
		// preparando as variaveis
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {
		}
		;
		String methodName;
		Requestor requestor = new Requestor();

		// Preenche variaveis temporarias
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(tipo);

		// preenche os parametros da chamada
		inv.setClientProxy(new ClientProxy());
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		ter = requestor.invoke(inv);
		Medicao result = (Medicao) ter.getResult();		

		return result;
	}

	@SuppressWarnings("unused")
	@Override
	public void setmedicao(Medicao m) throws UnknownHostException, IOException, InsufficientMedicoesException, Throwable {
		// preparando as variaveis
				Invocation inv = new Invocation();
				Termination ter = new Termination();
				ArrayList<Object> parameters = new ArrayList<Object>();
				class Local {
				}
				;
				String methodName;
				Requestor requestor = new Requestor();

				// information received from Client
				methodName = Local.class.getEnclosingMethod().getName();
				parameters.add(m);

				// information sent to Requestor
				inv.getClientProxy().setHost(this.getHost());
				inv.getClientProxy().setPort(this.port);
				inv.setOperationName(methodName);
				inv.setParameters(parameters);

				ter = requestor.invoke(inv);		
	}

	@SuppressWarnings("unused")
	@Override
	public Medicao getMedicaoAnterior() throws UnknownHostException, IOException, InsufficientMedicoesException, Throwable {
		// preparando as variaveis
				Invocation inv = new Invocation();
				Termination ter = new Termination();
				ArrayList<Object> parameters = new ArrayList<Object>();
				class Local {
				}
				;
				String methodName;
				Requestor requestor = new Requestor();

				// Preenche variaveis temporarias
				methodName = Local.class.getEnclosingMethod().getName();

				// preenche os parametros da chamada
				inv.getClientProxy().setHost(this.getHost());
				inv.getClientProxy().setPort(this.port);
				inv.setOperationName(methodName);

				ter = requestor.invoke(inv);
			
				Medicao result = (Medicao) ter.getResult();

				return result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Medicao> getCincoUltimasMedicoes() throws UnknownHostException, IOException, Throwable {
		// preparando as variaveis
				Invocation inv = new Invocation();
				Termination ter = new Termination();
				ArrayList<Object> parameters = new ArrayList<Object>();
				class Local {
				}
				;
				String methodName;
				Requestor requestor = new Requestor();

				// Preenche variaveis temporarias
				methodName = Local.class.getEnclosingMethod().getName();

				// preenche os parametros da chamada
				inv.setClientProxy(new ClientProxy());
				inv.getClientProxy().setHost(this.getHost());
				inv.getClientProxy().setPort(this.port);
				inv.setOperationName(methodName);
				inv.setParameters(parameters);

				ter = requestor.invoke(inv);

				// TODO Auto-generated method stub
				return ((List<Medicao>) ter.getResult());
	}
}
