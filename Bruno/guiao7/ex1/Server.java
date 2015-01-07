import java.net.*;
import java.io.*;

class Sum {
  private int sum = 0;

  synchronized public void add(int val) {
    sum += val;
  }

  synchronized public int get() {
    return sum;
  }
}

class MultiServerThread extends Thread {
  private Socket socket;
  private Sum s;

  public MultiServerThread(Socket socket, Sum s) { this.socket = socket; this.s = s; }

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
        int val = Integer.parseInt(str);
        sum += val;
        s.add(val);
        out.println("Sum until now: " + s.get());
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
    final Sum s = new Sum();

    try (ServerSocket ss = new ServerSocket(50000)) {
      while(true) {
        new MultiServerThread(ss.accept(), s).start();
      }
    } catch (IOException e) { e.printStackTrace(); }
  }
}
