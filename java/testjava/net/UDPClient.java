import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.SocketException;
import java.io.IOException;

public class UDPClient {
  private DatagramSocket client;
  public UDPClient() throws Exception{
    client = new DatagramSocket();
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    InetAddress ip = InetAddress.getByName("localhost");

    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];
    
    String sentence = inFromUser.readLine();
    
    sendData = sentence.getBytes();
    
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 8888);
    client.send(sendPacket);
    
    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    client.receive(receivePacket);
    String modifiedSentence = new String(receivePacket.getData());
    System.out.println("FROM SERVER:" + modifiedSentence);
    client.close();
  }

  public static void main(String args[]) throws Exception
  {
    try{
      new UDPClient();
    }catch(UnknownHostException e){
      e.printStackTrace();
    }catch(SocketException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}