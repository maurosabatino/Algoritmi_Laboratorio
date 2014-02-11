package sabatino.esercizio7;

public class Cronometro {
	private static long elapsedTime;
	private static long startTime;
	private static boolean isRunning;
	public Cronometro(){
		reset();
	}
	public static void start(){
		if(isRunning) return;
		isRunning=true;
		startTime=System.currentTimeMillis();
	}
	
	public static void stop(){
		if(!isRunning)return;
		isRunning=false;
		long endTime = System.currentTimeMillis();
		elapsedTime = elapsedTime + endTime - startTime;
	}
	public static long getElapsedTime(){
		if(isRunning){
			long endTime = System.currentTimeMillis();
			return elapsedTime + endTime - startTime;
		}
		return elapsedTime;
	}
	public static void reset(){
		elapsedTime = 0;
		isRunning = false;
	}
}
