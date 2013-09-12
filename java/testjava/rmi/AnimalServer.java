/**
 * 测试RMI
 * 
 * @author  http://www.infoq.com/cn/articles/lp-java-remoting-1
 */
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
public class AnimalServer{
    public final static int port = 8009; //绑定的端口号
    public final static String host = "127.0.0.1"; //本机作为服务host
    public final static String serviceName = "animalService"; //服务名称
    public static IAnimalService obj = new AnimalServiceImp();
    public static IAnimalService stub = null;
    public static void main(String[] args)
    {
        try {
            // Registry registry = LocateRegistry.getRegistry(host, port);
            Registry registry = LocateRegistry.getRegistry();
            stub = (IAnimalService) UnicastRemoteObject.exportObject(obj, port); //端口绑定远程对象
            System.out.println("Server Start 1...");
            // registry.unbind(serviceName);
            registry.bind(serviceName, stub); //注册服务地址
            // 绑定RMI名称 进行发布，即客户端通过这个名字查找的对象就是hello这个实例  
            // Naming.rebind(serviceName, obj);  
            System.out.println("Server Start...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}