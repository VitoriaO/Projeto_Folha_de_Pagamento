
public class Agenda {
	boolean[] d_mes = new boolean[31];
	boolean[] d_semana = new boolean[5];
	
	public Agenda(){
		for(int i = 0; i < 30; i++){
			d_mes[i] = false;
		}
		d_mes[30] = true;
		
		for(int i = 0; i < 4; i++){
			d_semana[i] = false;
		}
		d_semana[4] = true;
	}
}
