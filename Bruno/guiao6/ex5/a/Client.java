import java.net.*;
import java.io.*;

class Client {
  public static void main(String[] args) {
    try {
      Socket s = new Socket("localhost",50000);
      BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
      BufferedReader userBr = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter pw = new PrintWriter(s.getOutputStream());

      while(true) {
        String str = userBr.readLine();
        if(str == null)
          break;
        pw.println(str);
        pw.flush();
        String response = br.readLine();
        System.out.println("Response: " + response);
      }
      s.close();
    } catch(IOException e) { }
  }
}
