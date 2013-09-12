import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IAnimalService extends Remote {
    String getMonkeyName() throws RemoteException;
}