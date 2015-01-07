import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class MultiServerThread extends Thread {
  private Socket socket;

  public MultiServerThread(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try (
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream());
    ) {
      String str = in.readLine();
      while(str != null) {
        out.println(str);
        out.flush();
        str = in.readLine();
        if(str == null)
          break;
      }
    } catch(IOException e) { e.printStackTrace(); }
  }
}

class Server {
  public static void main(String[] args) {

    try (ServerSocket ss = new ServerSocket(50000)) {
      while(true) {
        new MultiServerThread(ss.accept()).start();
      }
    } catch(IOException e) { e.printStackTrace(); }
  }
}
