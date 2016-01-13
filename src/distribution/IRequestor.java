package distribution;

import java.io.IOException;
import java.net.UnknownHostException;

public interface IRequestor {
	
	public Termination invoke(Invocation inv) throws UnknownHostException, IOException, Throwable;
}
