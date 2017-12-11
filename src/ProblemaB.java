import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Programa para calcular el minimo diferencial posible para un grafo BC
 * @author Jorge Gomez
 * @author Fabian Alvarez
 */
public class ProblemaB {
	public static void main(String[] args) {
		ProblemaB program = new ProblemaB();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			
			//Lectura
			while(line != null) {
				int n = Integer.parseInt(line);
				int[] sig = new int[n];
				
				//Lectura de las lineas que describen los grafos
				for(int i = 0; i < sig.length; i++) {
					String[] params = br.readLine().split(" ");
					int v = Integer.parseInt(params[0]);
					int e = Integer.parseInt(params[1]);
					
					//Lista de adyacencia
					ArrayList<Integer>[] arcs = new ArrayList[v];
					for(int j = 0; j < v; j++) arcs[j] = new ArrayList<>();
					
					//Registrar arcos en la lista
					for(int j = 0; j < e; j++) {
						int p = Integer.parseInt(params[2*(j + 1)]);
						int q = Integer.parseInt(params[2*(j+1) + 1]);
						arcs[p].add(q);
						arcs[q].add(p);
					}
					
					//Calcular la diferencia del grafo actual
					sig[i] = program.levelDFS(arcs);
				}
				//Calcular el valor minimo de la suma alternante sobre sig
				System.out.println(program.calcMinSum(sig));
				
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para determinar la diferencia entre la cardinalidad
	 * de los conjuntos disyuntos del grafo BC descrito por
	 * la lista de adyacencia por parametro mediante un BFS que marca en dos conjuntos
	 * diferentes
	 * @param arcs lista de adyacencia del grafo
	 * @return diferencia entre conjuntos disyuntos del grafo BC
	 */
	public int levelDFS(ArrayList<Integer>[] arcs) {
		//Cardinalidad de los conjuntos
		int x = 0;
		int y = 0;

		//Lista de marcados, 0 no esta marcado, 1 esta en x, 2 esta en y
		int marked[] = new int[arcs.length];
		
		//Stack para DFS
		Stack<Integer> stack = new Stack<>();
		
		//Se marca el nodo 0, todos los grafos tienen al menos un nodo
		stack.push(0);
		marked[0] = 1;
		x++;
		
		//DFS
		while(!stack.isEmpty()) {
			int k = stack.pop();
			int mark = (marked[k] == 1? 2 : 1);
			for(Integer j : arcs[k]) {
				if(marked[j] == 0) {
					marked[j] = mark;
					if(mark == 1) x++; 
					else y++;
					stack.push(j);
				}
			}
		}
		
		return Math.abs(x-y);
	}

	/**
	 * Calcula el minimo valor de la suma alternate de numeros naturales
	 * pertenecientes al arreglo dado por parametro
	 * @param sig arreglo de numeros naturales para optimizar
	 * @return minimo valor de la suma alternante del arreglo dado por parametro
	 */
	public int calcMinSum(int[] sig) {
		int alfa = 0;
		//Calcular alfa, maximo valor de la suma
		for (int i : sig) alfa += i;
		
		//Llenar el caso base, distancia a sig[0]
		int[] arr1 = new int[alfa + 1];
		for (int i = 0; i < arr1.length; i++) arr1[i] = Math.abs(i-sig[0]);
		
		//Recursion sobre 2 arreglos, manejando sig[k] y sig[k+1]
		int[] arr2 = new int[alfa + 1];
		int[] last = arr2;
		int[] current = arr1;
		for(int k = 1; k < sig.length; k++) {
			//Definir arreglos sig[k] y sig[k+1] para esta iteracion
			if(k%2 == 1) {
				current = arr2; last = arr1;
			} else {
				current = arr1; last = arr2;
			}
			
			//Llenar sig[k+1] con la info de sig[k]
			for(int i = 0; i < current.length; i++) {
				int min = last[i] + sig[k];
				for(int m = 0; m < last.length; m++) {
					int temp = Math.abs(i - m) + last[m];
					min = Math.min(min, temp >= sig[k]? temp - sig[k] : temp + sig[k]);
				}
				current[i] = min;
			}
		}
		//Distancia minima a sumar 0.
		return current[0];
	}
}
