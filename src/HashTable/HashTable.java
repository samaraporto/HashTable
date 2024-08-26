package HashTable;
//m = numero de posições da tabela
//n = numero de elemntos inseridos
//a = fator de carga: n/m
//  a=n/m   m=n/a
public class HashTable {
	Node[] arr;
	int margem;
	int media;
	int m;
	int tableElementsLen = 0;
	
	public HashTable(int n, int margem, int a) {
		int m = n/a;
		if(isPrimo(m)) {
			arr = new Node[m];
		}
		else {
			m = primoMaisProximo(m);
			arr = new Node[m];
		}
		this.m = m;
		this.media = a;
		this.margem = margem;
	}
	public void insere(String str) {
		int i = hash(str);
		Node no = new Node(str);
		if(arr[i]==null) {
			arr[i] = no;
		}else {
			for(Node temp = arr[i];temp != null; temp = temp.getProx()) {
				if(temp.getProx()==null) {
					temp.setProx(no);
					break;
				}
			}
			
		}
		tableElementsLen++;
	}
	
	public boolean busca() {
		return false;
	}
	
	public void remove(String str) {
		
	}
	
	private int hash(String str) {
		str = str.toUpperCase();//letras maiusculas tem numeros asc menores
		int sum = 0;
		int len = str.length();
		for(int i = 0; i < len; i++) {
			sum += (int) str.charAt(i);//ao fazer o casting de uma letra pra inteiro resulta em seu equivalente na asc 
		}
		return sum % m;
	}
	
	public static boolean isPrimo(int num) {
        if (num <= 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
	
	public static int primoMaisProximo(int num) {
        int maiorPrimo = num + 1;
        while (!isPrimo(maiorPrimo)) {
            maiorPrimo++;
        }
        return maiorPrimo;
    }
	
	public static void main(String[] args) {
		HashTable table = new HashTable(9,30,3);
		table.insere("samara");
		table.insere("wanesa");
		table.insere("emmmanuel");
		table.insere("emmanuel");
		table.insere("katia");
		for(int i= 0;i<table.arr.length; i++) {
			System.out.println(table.arr[i]);
		}
	}
}
