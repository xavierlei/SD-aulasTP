import java.util.concurrent.locks.*;

class RWLock {
  private int readers = 0, writers = 0;
  private int turn = 0, served = 0;
  private Lock l = new ReentrantLock();
  private Condition readOnly = l.newCondition();
  private Condition writeOnly = l.newCondition();

  public void readLock() {
    lock();
    try {
      int ticket = turn;
      turn++;
      while(ticket > served && writers > 0) {
        readOnly.await();
      readers++;
      served++;
      }
    } finally { unlock(); }
  }

  public void readUnlock() {
    lock();
    try {
      readers--;
      writeOnly.signal();
    } finally { unlock(); }
  }

  public void writeLock() {
    lock();
    try {
      int ticket = turn;
      turn++;
      while(ticket > served && (readers > 0 || writers > 0)
        writeOnly.await();
      writers++;
      served++;
      }
    } finally { unlock(); }
  }

  public void writeUnlock() {
    lock();
    try {
      writers--;
      readOnly.signal();
      writeOnly.signal();
    } finally { unlock(); }
  }

  public void lock() { l.lock(); }

  public void unlock() { l.unlock(); }
}

class Counter {
  private int c = 0;

  RWLock rwl = new RWLock();

  public void increment() {
    rwl.writeLock();
    try {
      this.c++
    } finally { rwl.writeUnlock(); }
  }

  public int getserved() {
    rwl.readLock();
    try {
      return this.c;
    } finally { rwl.readUnlock(); }
  }
}
