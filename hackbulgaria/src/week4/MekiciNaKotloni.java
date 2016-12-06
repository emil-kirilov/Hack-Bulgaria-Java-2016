package week4;

import java.util.Scanner;

public class MekiciNaKotloni {
	int time = 0;
	int mekici = 0;
	int[] kotloni;
	
	public MekiciNaKotloni(int nM, int nK) {
		kotloni = new int[nK];
		mekici = nM;
	}
	
	public void setKotloni(String timersKotloni) {
		String[] timers= timersKotloni.split(" ");
		int n = timers.length;
		for (int i = 0; i < n; i++) {
			kotloni[i] = Integer.parseInt( timers[i] );
		}
	}
	
	public void print() {
		for (int i = 0; i < kotloni.length; i++) {
			System.out.println(kotloni[i]);
		}
	}
	
	private int getMinKotloni(int[] timers){
		int min = timers[0];
		int n = timers.length;
		for (int i = 1; i < n; i++) {
			if(timers[i] < min) {
				min = timers[i];
			}
		}
		
		return min;
	}
	
	private int refreshTimers(int[] timers, int elapsedTime) {
		int n = timers.length;
		int gotoviMekici = 0;
		for (int i = 0; i < n; i++) {
			if(timers[i] - elapsedTime < 1) {
				timers[i] = kotloni[i];
				gotoviMekici += 1;
			} else {
				timers[i] -= elapsedTime;
			}
		}
		return gotoviMekici;
	}
	
	/**
	 *  complexity < n^2, no n^2 po porqdak.... fuck
	 */
	
	public int timeNeeded() {
		int[] currentTimeNeeded = new int[kotloni.length];
		for (int i = 0; i < kotloni.length; i++) {
			currentTimeNeeded[i] = kotloni[i];
		}
		
		while(mekici > 0) {
			int min = getMinKotloni(currentTimeNeeded);
			mekici -= refreshTimers(currentTimeNeeded, min);
			time += min;
		}
		return time;
	}
	
	public String guessTime() {
		int guess = (mekici*(kotloni[kotloni.length-1]+kotloni[0]))/(2*kotloni.length);
		int gotoviMekici = 0;
		for (int i = 0; i < kotloni.length; i++) {
			gotoviMekici += guess/kotloni[i];
		}
		
		String res ="guess:" + guess + "\nnapraveni mekici v poveche:" + (gotoviMekici-mekici) + "\ntime to return:" + (guess/kotloni[0])*kotloni[0]; 
		return res;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String mekiciKotloni = s.nextLine();
		String[] temp = mekiciKotloni.split(" ");
		int nMekici = Integer.parseInt( temp[0] );
		int nKotloni = Integer.parseInt( temp[1] );
		
		MekiciNaKotloni mk = new MekiciNaKotloni(nMekici, nKotloni);
		String timersKotloni= s.nextLine();
		mk.setKotloni(timersKotloni);
		System.out.println(mk.guessTime());
		//System.out.println(mk.timeNeeded());
		s.close();
	}

}
