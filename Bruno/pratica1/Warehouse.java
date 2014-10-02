import java.util.*;

class Warehouse {

	public HashMap<String,Product> map;

	private class Product {
		public int stock;
	}

	public Warehouse() {
		map = new HashMap<String,Product>();
	}

	public void insert(String k) {
		Product p = map.get(k);
		if(p == null) {
			p = new Product();
			map.put(k,p);
		}
		p.stock++;
	}

	public int stock(String k) {
		Product p = map.get(k);
		if(p == null) return 0;
		else return map.get(k).stock;
	}

	public void remove(String k) {
		Product p = map.get(k);
		if(p != null)
			p.stock--;
	}
}

class Main {
	public static void main(String[] args) {
		Warehouse w = new Warehouse();
		w.insert("banana");
		w.insert("mango");
		w.insert("mango");
		w.insert("mango");
		w.insert("orange");

		System.out.println("Bananas: " + w.stock("banana"));
		System.out.println("Mangos: " + w.stock("mango"));
		System.out.println("Oranges: " + w.stock("orange"));

		w.remove("mango");

		System.out.println("Mangos: " + w.stock("mango"));
	}
}
