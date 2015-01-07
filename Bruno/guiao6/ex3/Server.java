import java.net.*;
import java.io.*;

class Server {
  public static void main(String[] args) throws Exception{
    try {
      ServerSocket ss = new ServerSocket(50000);
      while(true) {
        try (Socket s = ss.accept()) {
          InputStreamReader isr = new InputStreamReader(s.getInputStream());
          BufferedReader br = new BufferedReader(isr);
          PrintWriter pw = new PrintWriter(s.getOutputStream());
          int sum = 0, total = 0;
          while(true) {
            String str = br.readLine();
            if(str == null || str.equals(""))
              break;
            sum += Integer.parseInt(str);
            pw.println("Sum until now: " + Integer.toString(sum));
            total++;
            pw.flush();
          }
          pw.println("Average: " + Double.toString((double) sum / total));
          pw.close();
        }
      }
    } catch(IOException e) { e.printStackTrace(); }
  }
}
