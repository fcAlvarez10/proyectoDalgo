

public class ProblemaA {
	public static void main(String[] args)throws Exception{
		ProblemaA instancia= new ProblemaA();
		System.out.println(instancia.convolucionPonderada(10,0, 1, 1, 1));
	}

	public double convolucionPonderada(int n,int A, int B, int C, int D){

		double cp=0,r0=0,r1=0,cp1=0,cp2=0,cp3,aux;
		int i=0,j=n;
		while(i<j){
			r0=A;
			r1=B;
			cp1=0;
			cp2=0;
			cp3=0;

			
			i++;
			j--;
		}
		return cp;
	}



}
