package HashTable;
//m = numero de posições da tabela
//n = numero de elemntos inseridos
//a = fator de carga: n/m
//  a=n/m   m=n/a
public class HashTable {
	Node[] arr;
	int margem;
	int a;
	int m;
	int tableElementsLen = 0;
	int n;
	
	public HashTable(int n, int margem, int a) {
		int m = n/a;
		if(isPrimo(m)) {
			arr = new Node[m];
		}
		else {
			m = primoMaisProximo(m);
			arr = new Node[m];
		}
		this.n=n;
		this.m = m;
		this.a = a;
		this.margem = margem;
	}
	public void insere(String str) {
		int i = hash(str);
		Node no = new Node(str);
		if(arr[i]==null) {
			arr[i] = no;
		}else {
			Node temp = arr[i];
            while (temp.getProx() != null) {
                temp = temp.getProx();
            }
            temp.setProx(no);
		}
		tableElementsLen++;
		//media de elementos por posição da tabela
		double mediaDaTabela = (double) tableElementsLen/m;
		if(mediaDaTabela > a * (1 + (double) margem / 100)) {
			HashTable novaTabela = new HashTable((int) (n * 1.5), margem, a);
			for(int x = 0; x<m; x++) {
				if(arr[x]!=null) {
					for(Node currentNode = arr[x]; currentNode!=null;currentNode=currentNode.getProx()) {
						novaTabela.insere(currentNode.getInfo());
						System.out.println("valor "+currentNode.getInfo()+" adicionado na nova tabela");
					}
				}
			}
			this.arr = novaTabela.arr;
			this.m=novaTabela.m;
		}
		
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
			sum += (int) str.charAt(i) * i+1;//ao fazer o casting de uma letra pra inteiro resulta em seu equivalente na asc
			//i+1 = um fator pra ajudar na diminuicao de colisoes
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
		HashTable table = new HashTable(3,30,1);
		table.insere("anna");
		table.insere("valentina");
		table.insere("taylor");
		table.insere("steven");
		table.insere("kate");
		for(int i= 0;i<table.arr.length; i++) {
			System.out.println(table.arr[i]);
		}
	}
}
