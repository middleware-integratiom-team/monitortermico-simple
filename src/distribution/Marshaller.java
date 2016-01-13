package distribution;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Marshaller implements IMarshaller{

	public byte[] marshall(Message msgToBeMarshalled) throws IOException,
			InterruptedException {

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		try{
			objectStream.writeObject(msgToBeMarshalled);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return byteStream.toByteArray();
	}

	public Message unmarshall(byte[] msgToBeUnmarshalled) throws IOException, InterruptedException, ClassNotFoundException {

		ByteArrayInputStream byteStream = new ByteArrayInputStream(
				msgToBeUnmarshalled);
		ObjectInputStream objectStream = null;
		try {
			objectStream = new ObjectInputStream(byteStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			return (Message) objectStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}