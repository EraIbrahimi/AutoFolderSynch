import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server {
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.createRegistry(Constants.SERVER_RMI_PORT);
		FileSync syncObject = new FileSyncImpl();
		registry.rebind(Constants.SERVER_RMI_OBJECT_ID, syncObject );
		System.out.printf("Server started with %s shared.\n Press ENTER to stop\n", Constants.SERVER_RMI_OBJECT_ID);
		System.in.read();
		registry.unbind(Constants.SERVER_RMI_OBJECT_ID);
		UnicastRemoteObject.unexportObject(syncObject, true);
		System.out.printf("%s unshared\n", Constants.SERVER_RMI_OBJECT_ID);
		System.out.println("Server stopped");
	}
}
