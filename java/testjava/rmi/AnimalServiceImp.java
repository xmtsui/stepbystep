import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
public class AnimalServiceImp implements IAnimalService {
    public AnimalServiceImp() {
    }

    @Override
    public String getMonkeyName() throws RemoteException {
        return "I'm Jacky";
    }
}