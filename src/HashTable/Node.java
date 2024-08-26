package HashTable;

public class Node {
	private String info;
	private Node prox;
	
	public Node(String info) {this.setInfo(info);}
	public Node(String info, Node prox) {
		this.setInfo(info);
		this.prox = prox;	}

	public String getInfo() {return info;}

	public void setInfo(String info) {this.info = info;}

	public Node getProx() {return prox;}

	public void setProx(Node prox) {this.prox = prox;}
}
