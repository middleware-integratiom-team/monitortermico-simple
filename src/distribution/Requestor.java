package distribution;

import infrastructure.ClientRequestHandler;

import java.io.IOException;
import java.net.UnknownHostException;
import aplication.exceptions.InsufficientMedicoesException;
import aplication.exceptions.ServerNotFoundException;

public class Requestor implements IRequestor {

	public Requestor() {
		super();
	}

	private ClientRequestHandler crh;

	public Termination invoke(Invocation inv) throws UnknownHostException,
			IOException, InsufficientMedicoesException, Throwable {

		ClientRequestHandler crh = new ClientRequestHandler(inv.getClientProxy().getHost(), inv.getClientProxy().getPort());

		Marshaller marshaller = new Marshaller();
		Termination termination = new Termination();
		byte[] msgMarshalled = new byte[1000];
		byte[] msgToBeUnmarshalled = new byte[1000];

		Message msgUnmarshalled = new Message(); // TODO actual marshalling

		// map Invocation into a Message
		RequestHeader requestHeader = new RequestHeader("", 0, true, 0, inv.getOperationName());
		RequestBody requestBody = new RequestBody(inv.getParameters());

		MessageHeader messageHeader = new MessageHeader("MIOP", 0, false, 0, 0);
		MessageBody messageBody = new MessageBody(requestHeader, requestBody, null, null);

		Message msgToBeMarshalled = new Message(messageHeader, messageBody);

		try {
			// marshall request message
			msgMarshalled = marshaller.marshall(msgToBeMarshalled);
			// send marshalled message
			crh.send(msgMarshalled);

			try {
				// receive reply message
				msgToBeUnmarshalled = crh.receive();
			} catch (InsufficientMedicoesException e) {
				throw e;
			}

			// unmarshall reply message
			msgUnmarshalled = (Message) marshaller.unmarshall(msgToBeUnmarshalled);
		} catch (IOException e) {
			throw new ServerNotFoundException("Servidor Indispon�vel");
		} catch (ClassNotFoundException e) {
			throw new ServerNotFoundException("Servidor Indispon�vel 2.");
		} catch (InterruptedException e) {
			throw new ServerNotFoundException(
					"O servidor est� demorando muito para responder, verifique sua conex�o e tente novamente.");
		} catch (Throwable e) {
			throw new ServerNotFoundException("Servidor Indispon�vel 3.");
		}

		// return result to Client Proxy
		termination.setResult(msgUnmarshalled.getBody().getReplyBody()
				.getOperationResult());

		return termination;
	}

	public ClientRequestHandler getCrh() {
		return crh;
	}

	public void setCrh(ClientRequestHandler crh) {
		this.crh = crh;
	}
}
