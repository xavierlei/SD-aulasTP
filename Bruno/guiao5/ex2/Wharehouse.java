import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

class Wharehouse {
  Hashtable<String,Item> supplies = new Hashtable<String,Item>();
  Lock l = new ReentrantLock();

  class Item {
    int quantity;
    Condition notAvailable = l.newCondition();
  }

  private Item getItem(String s) {
    Item i = supplies.get(s);
    if(i != null)
      return i;
    i = new Item();
    supplies.put(s,i);
    return i;
  }

  public void supply(String item, int quantity) throws InterruptedException {
    l.lock();
    try {
      Item it = getItem(item);
      it.quantity += quantity;
      it.notAvailable.signal();
      } finally { l.unlock(); }
  }

  /*
  * Solucao basica: consome se estiver disponivel. Mantem em posse os itens que adquire
  * mesmo que esteja a aguardar que um deles fique disponivel
  public void consume(String[] items) throws InterruptedException {
    l.lock();
    try {
      for(String s : items) {
        Item i = getItem(s);
        while(i.quantity == 0)
          i.notAvailable.await();
        i.quantity--;
        }
    } finally { l.unlock(); }
  }
  */

  /*
  * solucao complexa: so consome os itens desejados se estiverem todos disponiveis
  */
  public void consume(String[] items) throws InterruptedException {
      Item a = new Item();
      boolean allAvailable = false;
      l.lock();
      try {
        while(!allAvailable) {
          allAvailable = true;
          for(String s : items) {
            a = supplies.get(s);

            if(a.quantity == 0) {
              allAvailable = false;
              a.notAvailable.await();
            }
          }
        }
        if(allAvailable) {
          for(String s : items) {
            a = supplies.get(s);
            a.quantity--;
          }
        }
      } finally { l.unlock(); }
  }

  public void consult() throws InterruptedException {
    l.lock();
    try {
      for(String s : supplies.keySet()) {
        System.out.println(s + ": " + supplies.get(s).quantity);
        // System.out.println("supplies total: " + w.supplies.size());
      }
    } finally { l.unlock(); }
  }
}

class Main {
  public static void main(String args[]) throws InterruptedException {
    final Wharehouse w = new Wharehouse();

    w.supply("Hammer",10);
    w.supply("Screw Driver",10);
    w.supply("Screw",10);

    class Supplier extends Thread {
      public void run() {
        try {
          while(true) {
            // sleep(1000);
            w.supply("Hammer",1); w.supply("Screw",1); w.supply("Screw Driver",1);
          }
        } catch(InterruptedException e) {}
      }
    }

    class Consumer extends Thread {
      String[] array = new String[] { "Hammer", "Screw", "Screw Driver" };
      public void run() {
        try {
          while(true) {
            w.consume(array);
            array = new String[] { "Hammer", "Screw", "Screw Driver" };
          }
        } catch(InterruptedException e) {}
      }
    }

    class Consulter extends Thread {
      public void run() {
        try {
          while(true) {
            w.consult();
            sleep(1000);
          }
        } catch(InterruptedException e) {}
      }
    }

    Supplier s = new Supplier();
    Consumer c = new Consumer();
    Consulter con = new Consulter();
    s.start(); c.start(); con.start();
    s.join(); c.join(); con.join();
  }
}
