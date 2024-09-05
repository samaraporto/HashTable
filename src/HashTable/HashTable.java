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
	
	public boolean busca(String str) {
		int i = hash(str);  //calcula o índice do elemento
	    Node currentNode = arr[i];  // inicia a busca no início da lista 
	    while (currentNode != null) {  // percorre a lista
	        if (currentNode.getInfo().equals(str)) {  //compara o valor armazenado com o valor buscado
	            return true;  // quando encontra retorna true
	        }
	        currentNode = currentNode.getProx();  //vai pro prox nó
	    }
	    return false;  //se nn acha, retorna false
	}

	
	public void remove(String str) {
		int i = hash(str);
		Node noAtual = arr[i];
		Node noAnterior = null;
		while (noAtual != null) {
			if(noAtual.getInfo().equals(str)){
				if(noAnterior == null) {
					arr[i] = noAtual.getProx();
				} else {
					noAnterior.setProx(noAtual.getProx());
				}
				tableElementsLen--;
				return;
			}
			noAnterior = noAtual;
			noAtual = noAtual.getProx();
		}

	}
	
	private int hash(String str) {
	    str = str.toUpperCase();
	    long sum = 0;
	    int len = str.length();
	    for (int i = 0; i < len; i++) {
	        sum += 33 * sum + str.charAt(i);
	    }
	    sum = Math.abs(sum % m); 
	    return (int) sum; 
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
}
