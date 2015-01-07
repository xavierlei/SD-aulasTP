import java.net.*;
import java.io.*;

class MultiServerThread extends Thread {
  private Socket socket;

  public MultiServerThread(Socket socket) { this.socket = socket; }

  public void run() {
    try (
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    ) {
      int sum = 0, total = 0;
      while(true) {
        String str = in.readLine();
        if(str == null || str.equals(""))
          break;
        sum += Integer.parseInt(str);
        out.println("Sum until now: " + Integer.toString(sum));
        total++;
        out.flush();
      }
      out.println("Average: " + Double.toString((double) sum / total));
      out.close();
    } catch (IOException e) { e.printStackTrace(); }
  }
}

class Server {
  public static void main(String[] args) throws Exception{
    try (ServerSocket ss = new ServerSocket(50000)) {
      while(true) {
        new MultiServerThread(ss.accept()).start();
      }
    } catch (IOException e) { e.printStackTrace(); }
    }
}
