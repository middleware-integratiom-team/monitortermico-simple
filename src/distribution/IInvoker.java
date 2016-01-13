package distribution;

import java.io.IOException;

public interface IInvoker {
	public void invoke(ClientProxy clientProxy) throws IOException, Throwable;
}
