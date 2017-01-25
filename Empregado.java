
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
	int d_mes;
	int d_semana;
	boolean sindicato;
	
	Empregado(){
		this.nome = null;
		this.endereco = null;
		this.tipo = null;
		this.metodo_pag = null;
		this.agenda_pag = null;
		this.salario = 0;
		this.comissao = 0;
		this.vendas = 0;
		this.taxa = 0;
		this.taxa_ad = 0;
		this.id = 0;
		this.h_trab = 0;
		this.h_extra = 0;
		this.h_entrada = 0;
		this.d_mes = 0;
		this.d_semana = 0;
	}
}
