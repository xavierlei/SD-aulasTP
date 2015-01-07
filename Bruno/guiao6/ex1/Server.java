import java.net.*;
import java.io.*;

class Server {
  public static void main(String[] args) {

    try {
      ServerSocket ss = new ServerSocket(50000);
      while(true) {

        try (Socket s = ss.accept()) {
          while(true) {
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            String str = br.readLine();
            if(str == null)
              break;
            pw.println(str);
            pw.flush();
          }
        }
      }
    } catch(IOException e) { e.printStackTrace(); }
  }
}
