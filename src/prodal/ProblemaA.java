package prodal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
public class ProblemaA {
	public static void main(String[] args)throws Exception{
		ProblemaA instancia= new ProblemaA();

		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();

			while(line!=null && line.length()>0) {
				final String [] dataStr = line.split(" ");
				final double[] numeros = Arrays.stream(dataStr).mapToDouble(f->Double.parseDouble(f)).toArray();
				double respuesta = instancia.convolucionPonderada((int)numeros[0], numeros[1], numeros[2], numeros[3], numeros[4]);
				DecimalFormat df = new DecimalFormat("#.0000");
				System.out.println(df.format(respuesta));
				line = br.readLine();
			}
		}
	}

	public double convolucionPonderada(int n,double A, double B, double C, double D){

		double cp=0,r0,r1,cp1,cp2,cp3,aux;
		int i=0,j=n;
		if(n==1){
			cp=A+B;
		}else if(n==0){
			cp=A;
		}else{
			while(i<j){
				r0=A;
				r1=B;
				cp1=0;
				cp2=0;
				cp3=0;
				aux=0;
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
				i++;
				j--;
			}
		}
		return cp;
	}
}
