package distribution;

import infrastructure.ServerRequestHandler;

import java.io.IOException;

import aplication.Medicao;
import aplication.TipoGrandeza;
import aplication.exceptions.InsufficientMedicoesException;
import aplication.server.MonitorImpl;

public class MonitorInvoker extends AbstractInvoker{

	public MonitorInvoker(){
		super();
	}
	
	public void invoke(ClientProxy clientProxy) throws IOException, InsufficientMedicoesException, Throwable {
		
		ServerRequestHandler srh = new ServerRequestHandler(
				clientProxy.getPort());
		
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		
		Message msgUnmarshalled = new Message();
		Marshaller mrsh = new Marshaller();
		Termination ter = new Termination();

		// create remote object
		MonitorImpl rObj = new MonitorImpl();

		// inversion loop
		while (true) {

			// @ Receive Message
			msgToBeUnmarshalled = srh.receive();

			// @ Unmarshall received message
			msgUnmarshalled = mrsh.unmarshall(msgToBeUnmarshalled);
			String operation = msgUnmarshalled.getBody().getRequestHeader()
					.getOperation();

			if (operation.contains("getMedicao")) {
				TipoGrandeza tipo = (TipoGrandeza) msgUnmarshalled.getBody()
						.getRequestBody().getParameters().get(0);

				ter.setResult(rObj.getMedicao(tipo));

				Message _add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, false, 0, 0), new MessageBody(null,
						null, new ReplyHeader("", 0, 0), new ReplyBody(
								ter.getResult())));

				// @ Marshall the response
				msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled);

				// @ Send response
				srh.send(msgMarshalled);
			} else if (operation.contains("setMedicao")) {
				Medicao m = (Medicao) msgUnmarshalled.getBody()
						.getRequestBody().getParameters().get(0);
				rObj.setmedicao(m);

				ter.setResult(rObj);

				Message _add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, false, 0, 0), new MessageBody(null,
						null, new ReplyHeader("", 0, 0), new ReplyBody(
								ter.getResult())));

				// @ Marshall the response
				msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled);

				// @ Send response
				srh.send(msgMarshalled);
			} else if (operation.contains("getCincoUltimasMedicoes")) {
				Message _add_msgToBeMarshalled = null;
				try {
					ter.setResult(rObj.getCincoUltimasMedicoes());

					_add_msgToBeMarshalled = new Message(new MessageHeader(
							"protocolo", 0, false, 0, 0), new MessageBody(null,
							null, new ReplyHeader("", 0, 0), new ReplyBody(
									ter.getResult())));
				} catch (InsufficientMedicoesException e) {
					_add_msgToBeMarshalled = new Message(new MessageHeader(
							"InsufficientMedicoesException", 0, false, 1, 0),
							new MessageBody(null, null, new ReplyHeader("", 0,
									0), new ReplyBody(null)));
				}

				// @ Marshall the response
				msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled);

				// @ Send response
				srh.send(msgMarshalled);
			}

		}
	}
}
