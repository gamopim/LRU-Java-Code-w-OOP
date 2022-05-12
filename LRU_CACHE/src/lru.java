import java.util.Scanner;

public class lru {

   
	    public static void main(String[] args) {
	    	
	    	Scanner sc = new Scanner (System.in);
	    	
	    
	    	System.out.println("Bem-vindo à simulação de LRU na memória cache!");
	    	System.out.println("1º) Digite o tamanho de sua memória cache: ");
	    	int tamanhoCache = sc.nextInt();
	    	
	    	System.out.println("2º) Agora, digite quantos valores você gostaria de passar pela memória cache: ");
	    	int x = sc.nextInt();
	    	
	    	System.out.println("3º) Por fim, digite os valores que passarão pela cache: ");
	    	int cacheValores[]= new int[x];
	    	
	    	for (int z = 0; z<x; z++) {
	    		cacheValores[z] = sc.nextInt();
	    	}
	        
	        // variáveis que serão usadas ao longo do código 
	        int atual, numSubst=0 /*vezes que um valor é tirado da memCache */, cacheHit=0 /*quando o valor já estiver na MemCache*/, indice=0, r = 0;
	        boolean comEspaco = false;// boolean para acusar se tem ou não espaço na memCache
	       
	        int memCache[] = new int[tamanhoCache]; //vetor que representa a  memória cache
	        int contador[] = new int [tamanhoCache];// vetor contador de acerto
	        int maior = contador[0]; // variável "maior" serve para comparar  com o contador e achar o  maior valor. Quanto maior o valor do contador na posição, mais próxima ela está do LRU (de sair fora)
	        
	        // criamos um contador que começa em zero e acrescenta até o fim da extensão da fila
	        for(int i = 0; i < cacheValores.length; i++) {// nesta estrutura de repetição for, compara-se cada um dos valores do vetor de entrada cacheValores
	        
	        atual = (cacheValores[i]);// este é o primeiro dado no começo da fila (primeiro valor inserido pelo usuário), a seguir verifica se o dado está na memória cache
	      
	            
	            for (int j = 0; j < tamanhoCache; j++) {
	            	                 
	                 if (atual == memCache[j]) {// verifica se o dado está na memória cache
	                   
	                      cacheHit++; // quando o dado já se encontra na cache, soma-se um acerto
	                      
	                      for (int c = 0; c < tamanhoCache; c++) {
	                    	  contador[c]++;
	                    	  
	                      }
	                      
	                      contador[j] = 1;// o contador da posição do cachehit é fixado em 1 para ser o mais referenciado (mais longe do LRU)
	                      break;  
	                  }
	            
	             }

	            // caso o valor não esteja na memória cache:
	            if (cacheHit == 0) {
	             
	              // Verificamos se há espaço vazio na memória cache
	              for (int l=0; l< tamanhoCache; l++) {	                  
	              
	                  if (memCache[l] == 0) {// havendo espaço vazio, então
	                  
	                      comEspaco = true;//informa que ha um espaço vazio
	                      indice = l;// guarda o índice para saber qual lugar da memória está disponível
	                      break;//interrompe quando acha um espaço vazio (valor = 0)
	                  }
	              }
	              
	              //se houver espaço vazio
	              if (comEspaco == true) {//vem do brake, porque há um espaço vazio
	              
	                  memCache[indice] = atual;// usando o índice, carrega o valor inserido pelo usuário (atual) no espaço de memória referenciado
	                  
	                  for (int c = 0; c < tamanhoCache; c++) {
	                      
	                          if (contador[c] == 0) { // se a memória está vazia o contador não faz nada
	                              break;
	                          }
	                      contador[c]++;// caso a memória tenha dados, acrescenta o contador em +1
	                  }
	                  
	                  contador[indice] = 1;// É inicializado o contador onde foi acrescentado o dado, passando a ser o mais referenciado (mais longe do LRU)
	              }
	                 
	              // Não havendo espaço vazio, então:
	              else {
	            	  for (int k = 0; k < contador.length; k++) {// procuramos o dado menos referenciado em memória ( o LRU)
	            		  if(contador[k] > maior) { // obtemos o maior dado do contador com esta estrutura condicional
	            			  maior = contador[k];
	                      }
	                  }     
	            	  for (int n = 0; n < contador.length; n++) {
	                            
	                            	if(contador[n] == maior) { // obtido o valor do maior nº no contador, localizamos a posição na memória em que ele se encontra, variável "r" armazena esta posição
	                                r = n; 
	                                }
	            	  }
	                  memCache[r] = atual; // atualiza o dado na memória
	                  numSubst++; // acrescentamos o nº de substituições realizadas com +1
	                  
	                  for (int c = 0; c < tamanhoCache; c++) {
	                	  contador[c]++; // acresenta ao contador de posições em +1
	                  }
	                      
	                  contador[r] = 1;// deixamos inicializado o contador do último dado carregado na memória, ou seja, o mais referenciado.
	                  maior = 0; // zeramos o valor da variável "maior" utilizada para achar a posição do dado retirado na memoria por ser o LRU
	                                 
	               }     
	            } //grande if fim
	            	            
	            //Zerar o contador de acertos do cache e boolean que indica se há espaço na cache
	            cacheHit=0;
	            comEspaco = false;
	            
	        }
	        // Laço de repetição que imprime a Memória armazenada na cache ao final da execução do algoritmo LRU
	        System.out.println("Memória cache: \n");
	        System.out.print("(");
	        for (int i = 0; i < tamanhoCache; i++) {
	        	System.out.print(" " + memCache[i]+ " ");
	        }
	        System.out.println(")");
	        System.out.println("Nº de substituições: " + numSubst + "\n"); // Imprime o Nº de substituições feitas
	        
	        //laço de repetição para imprimir o valor do contador e o valor inserido na memória cache por posição. 
	        for (int k = 0; k < contador.length; k++ ) {
	        	System.out.println("Valor do contador na posição " + k + " da Memória Cache: " + contador[k] + ", conteúdo da memoria: " + memCache[k]);
	            }
	           
	            sc.close(); 
	 	 }
	    
}

