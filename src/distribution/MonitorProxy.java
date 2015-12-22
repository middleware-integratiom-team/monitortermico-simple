package distribution;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import utilsconf.UtilsConf;
import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;
import aplication.exceptions.InsufficientMedicoesException;

public class MonitorProxy extends ClientProxy implements IMonitor {

	private static final long serialVersionUID = 1L;

	// TODO objID
	public MonitorProxy() throws UnknownHostException {
		this.host = InetAddress.getLocalHost().getHostName();
		this.port = UtilsConf.nextPortAvailable();
	}

	public MonitorProxy(String h, int p) {
		this.host = h;
		this.port = p;
	}

	@Override
	public Medicao getMedicao(TipoGrandeza tipo) throws Throwable {
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
		parameters.add(tipo);

		// information sent to Requestor
		inv.setClientProxy(this);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		// invoke Requestor
		ter = requestor.invoke(inv);

		// @ Result sent back to Client
		return (Medicao) ter.getResult();
	}

	@Override
	public void setmedicao(Medicao m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medicao getMedicaoAnterior() {
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

		// information sent to Requestor
		inv.setClientProxy(this);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		// invoke Requestor
		try {
			ter = requestor.invoke(inv);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// @ Result sent back to Client
		return (Medicao) ter.getResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicao> getCincoUltimasMedicoes()
			throws InsufficientMedicoesException {
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

		// information sent to Requestor
		inv.setClientProxy(this);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		// invoke Requestor
		try {
			ter = requestor.invoke(inv);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// @ Result sent back to Client
		return (List<Medicao>) ter.getResult();
	}
}
