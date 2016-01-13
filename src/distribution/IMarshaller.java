package distribution;

import java.io.IOException;

public interface IMarshaller {
	public byte[] marshall (Message msgToBeMarshalled) throws IOException, InterruptedException;

	public Message unmarshall(byte[] msgToBeUnmarshalled) throws IOException, InterruptedException, ClassNotFoundException;
}
