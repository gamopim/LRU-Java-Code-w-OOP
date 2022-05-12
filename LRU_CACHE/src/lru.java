import java.util.Scanner;

public class lru {

   
	    public static void main(String[] args) {
	    	
	    	Scanner sc = new Scanner (System.in);
	    	
	    
	    	System.out.println("Bem-vindo � simula��o de LRU na mem�ria cache!");
	    	System.out.println("1�) Digite o tamanho de sua mem�ria cache: ");
	    	int tamanhoCache = sc.nextInt();
	    	
	    	System.out.println("2�) Agora, digite quantos valores voc� gostaria de passar pela mem�ria cache: ");
	    	int x = sc.nextInt();
	    	
	    	System.out.println("3�) Por fim, digite os valores que passar�o pela cache: ");
	    	int cacheValores[]= new int[x];
	    	
	    	for (int z = 0; z<x; z++) {
	    		cacheValores[z] = sc.nextInt();
	    	}
	        
	        // vari�veis que ser�o usadas ao longo do c�digo 
	        int atual, numSubst=0 /*vezes que um valor � tirado da memCache */, cacheHit=0 /*quando o valor j� estiver na MemCache*/, indice=0, r = 0;
	        boolean comEspaco = false;// boolean para acusar se tem ou n�o espa�o na memCache
	       
	        int memCache[] = new int[tamanhoCache]; //vetor que representa a  mem�ria cache
	        int contador[] = new int [tamanhoCache];// vetor contador de acerto
	        int maior = contador[0]; // vari�vel "maior" serve para comparar  com o contador e achar o  maior valor. Quanto maior o valor do contador na posi��o, mais pr�xima ela est� do LRU (de sair fora)
	        
	        // criamos um contador que come�a em zero e acrescenta at� o fim da extens�o da fila
	        for(int i = 0; i < cacheValores.length; i++) {// nesta estrutura de repeti��o for, compara-se cada um dos valores do vetor de entrada cacheValores
	        
	        atual = (cacheValores[i]);// este � o primeiro dado no come�o da fila (primeiro valor inserido pelo usu�rio), a seguir verifica se o dado est� na mem�ria cache
	      
	            
	            for (int j = 0; j < tamanhoCache; j++) {
	            	                 
	                 if (atual == memCache[j]) {// verifica se o dado est� na mem�ria cache
	                   
	                      cacheHit++; // quando o dado j� se encontra na cache, soma-se um acerto
	                      
	                      for (int c = 0; c < tamanhoCache; c++) {
	                    	  contador[c]++;
	                    	  
	                      }
	                      
	                      contador[j] = 1;// o contador da posi��o do cachehit � fixado em 1 para ser o mais referenciado (mais longe do LRU)
	                      break;  
	                  }
	            
	             }

	            // caso o valor n�o esteja na mem�ria cache:
	            if (cacheHit == 0) {
	             
	              // Verificamos se h� espa�o vazio na mem�ria cache
	              for (int l=0; l< tamanhoCache; l++) {	                  
	              
	                  if (memCache[l] == 0) {// havendo espa�o vazio, ent�o
	                  
	                      comEspaco = true;//informa que ha um espa�o vazio
	                      indice = l;// guarda o �ndice para saber qual lugar da mem�ria est� dispon�vel
	                      break;//interrompe quando acha um espa�o vazio (valor = 0)
	                  }
	              }
	              
	              //se houver espa�o vazio
	              if (comEspaco == true) {//vem do brake, porque h� um espa�o vazio
	              
	                  memCache[indice] = atual;// usando o �ndice, carrega o valor inserido pelo usu�rio (atual) no espa�o de mem�ria referenciado
	                  
	                  for (int c = 0; c < tamanhoCache; c++) {
	                      
	                          if (contador[c] == 0) { // se a mem�ria est� vazia o contador n�o faz nada
	                              break;
	                          }
	                      contador[c]++;// caso a mem�ria tenha dados, acrescenta o contador em +1
	                  }
	                  
	                  contador[indice] = 1;// � inicializado o contador onde foi acrescentado o dado, passando a ser o mais referenciado (mais longe do LRU)
	              }
	                 
	              // N�o havendo espa�o vazio, ent�o:
	              else {
	            	  for (int k = 0; k < contador.length; k++) {// procuramos o dado menos referenciado em mem�ria ( o LRU)
	            		  if(contador[k] > maior) { // obtemos o maior dado do contador com esta estrutura condicional
	            			  maior = contador[k];
	                      }
	                  }     
	            	  for (int n = 0; n < contador.length; n++) {
	                            
	                            	if(contador[n] == maior) { // obtido o valor do maior n� no contador, localizamos a posi��o na mem�ria em que ele se encontra, vari�vel "r" armazena esta posi��o
	                                r = n; 
	                                }
	            	  }
	                  memCache[r] = atual; // atualiza o dado na mem�ria
	                  numSubst++; // acrescentamos o n� de substitui��es realizadas com +1
	                  
	                  for (int c = 0; c < tamanhoCache; c++) {
	                	  contador[c]++; // acresenta ao contador de posi��es em +1
	                  }
	                      
	                  contador[r] = 1;// deixamos inicializado o contador do �ltimo dado carregado na mem�ria, ou seja, o mais referenciado.
	                  maior = 0; // zeramos o valor da vari�vel "maior" utilizada para achar a posi��o do dado retirado na memoria por ser o LRU
	                                 
	               }     
	            } //grande if fim
	            	            
	            //Zerar o contador de acertos do cache e boolean que indica se h� espa�o na cache
	            cacheHit=0;
	            comEspaco = false;
	            
	        }
	        // La�o de repeti��o que imprime a Mem�ria armazenada na cache ao final da execu��o do algoritmo LRU
	        System.out.println("Mem�ria cache: \n");
	        System.out.print("(");
	        for (int i = 0; i < tamanhoCache; i++) {
	        	System.out.print(" " + memCache[i]+ " ");
	        }
	        System.out.println(")");
	        System.out.println("N� de substitui��es: " + numSubst + "\n"); // Imprime o N� de substitui��es feitas
	        
	        //la�o de repeti��o para imprimir o valor do contador e o valor inserido na mem�ria cache por posi��o. 
	        for (int k = 0; k < contador.length; k++ ) {
	        	System.out.println("Valor do contador na posi��o " + k + " da Mem�ria Cache: " + contador[k] + ", conte�do da memoria: " + memCache[k]);
	            }
	           
	            sc.close(); 
	 	 }
	    
}

