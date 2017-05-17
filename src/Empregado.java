
public class Empregado {
	String nome;
	String endereco;
	String tipo;
	String metodo_pag;
	String agenda_pag;
	float salario;
	float comissao;
	float vendas;
	float taxa;
	float taxa_ad;
	int id;
	int h_trab;
	int h_extra;
	int h_entrada;
	int dia;
	int[] d_venda = new int[4];
	boolean sindicato;
	
	Empregado(){
		nome = null;
		endereco = null;
		tipo = null;
		metodo_pag = null;
		agenda_pag = null;
		salario = 0;
		comissao = 0;
		vendas = 0;
		taxa = 0;
		taxa_ad = 0;
		id = -1;
		h_trab = 0;
		h_extra = 0;
		h_entrada = 0;
		dia = 0;
		sindicato = false;
		for(int i = 0; i < 4; i++){
			d_venda[i] = 0;
		}
	}
}
