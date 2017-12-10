package prodal;

public class ProblemaA {
	public static void main(String[] args)throws Exception{
		ProblemaA instancia= new ProblemaA();
		System.out.println(instancia.convolucionPonderada(15,2, 0.7, 0.45, 0.55));
	}

	public double convolucionPonderada(int n,double A, double B, double C, double D){

		double cp=0,r0=0,r1=0,cp1=0,cp2=0,cp3=0,aux;
		int i=0,j=n;
		while(i<j){
			r0=A;
			r1=B;
			if(i==0){
				cp1=r0;
			}else if(i==1){
				cp1=r1;
			}
			for(int k=2;k<=j;k++){
				aux=C*r0+D*r1;
				r0=r1;
				r1=aux;
				if(k==i){
					cp1=r1;
				}
				if(k==j){
					cp2=r1;
				}
				if(k==n/2 && n%2==0){
					cp3=r1;
				}
			}
			cp+=2*cp1*cp2+cp3;
			cp1=0;
			cp2=0;
			cp3=0;
			aux=0;
			i++;
			j--;
		}
		return cp;
	}



}
