package sabatino.esercizio2;

public class ThreeColoredFlag {
	/**
	 *  TODO testa se l'ordinamento fatto con {@link #separateColors(int[])} è corretto.
	 * @param array array di interi su cui è stato eseguito {@link #separateColors(int[])}
	 * @return boolean valore booleano true: i colori sono stati separati correttamente,  false: i colori non sono stati separati correttamente.
	 */
	public static boolean checkColors(int[] array){
		int i=0,j=array.length-1;
		while(i<array.length){
			while(array[i]%3==0){
				i++;
			}
			while(array[i]%3==1)i++;
			while(array[i]%3==2){
				i++;
				j--;
			}
			if(i<array.length) return false;
		}
		return true;
	}
	/**
	 * <p>la procedura prende un array di interi e lo ordina parzialmente in tre parti( a mo di bandiera tricolore, nel particolare quella italiana). </p>
	 * <p>se array[i]%3=0 è verde e deve precedere gli elementi bianchi.</p>
	 * <p>se array[i]%3=1 è bianco e deve precedere gli elementi rossi.</p>
	 * <p>se array[i]%3=2 è rosso</p>
	 * 
	 * <p>situazione passo generico</p>
	 * <p allign=center>0______i_______j_____________k_____n-1</p>
	 * <p allign=center>|______|_______|_____________|_______|</p>
	 * <p allign=center>verde   bianco   da esaminare  rosso</p>
	 * 
	 *  <p>situazione finale</p>
	 *  
	 *  <p>0___________i____________j________n-1</p>
	 *  <p>|___________|____________|___________|</p>
	 *  <p>    verde      bianco      rosso      </p> 
	 *  <p> tutti gli elementi verdi(se ci sono) devono precedere gli elementi bianchi, e gli elementi binanchi devono precedere gli elementi rossi</p>  
	 * @param array array di interi in input, deve essere ordinato parzialmente secondo l'algoritmo della bandiera tricolore.
	 */
	public static void separateColors(int[] array){
		int i=0,j=0,k=array.length-1;
		while(j<=k){
			if(array[j]%3==0) {
				swap(array,j,i);
				i++;
				j++;
			}
			if(array[j]%3==1)j++;
			if(array[j]%3==2){
				swap(array,j,k);
				k--;
				j++;
			}
		}
	}
		
	
	private static void swap(int[] array,int i,int j){
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
}
