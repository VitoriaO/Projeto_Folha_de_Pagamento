import java.util.Scanner;


public class Main{
	
	public static void copiar(Empregado emporiginal, Empregado emp){
		emp.nome = emporiginal.nome;
		emp.endereco = emporiginal.endereco;
		emp.tipo = emporiginal.tipo;
		emp.metodo_pag = emporiginal.metodo_pag;
		emp.agenda_pag = emporiginal.agenda_pag;
		emp.salario = emporiginal.salario;
		emp.comissao = emporiginal.comissao;
		emp.vendas = emporiginal.vendas;
		emp.taxa = emporiginal.taxa;
		emp.taxa_ad = emporiginal.taxa_ad;
		emp.id = emporiginal.id;
		emp.h_trab = emporiginal.h_trab;
		emp.h_extra = emporiginal.h_extra;
		emp.h_entrada = emporiginal.h_entrada;
		emp.d_mes = emporiginal.d_mes;
		emp.d_semana = emporiginal.d_semana;
		emp.sindicato = emporiginal.sindicato;
	}

	public static void imprimirAgenda(Agenda agenda){
		int j;
		System.out.println("Dias do mês disponíveis:");
		for(int i = 0; i < 31; i++){
			if(agenda.d_mes[i] == 1){
				j = i + 1;
				System.out.println(j);
			}
		}
		System.out.println("Dias da semana disponíveis: (1 - segunda, 2 - terça ...)");
		for(int i = 0; i < 5; i++){
			if(agenda.d_semana[i] == 1){
				j = i + 1;
				System.out.println(j);
			}
		}
	}
	
	public static void addEmpregado(Empregado[] emps, Scanner entrada, Empregado eaux, Agenda agenda){
		int dia;
		Empregado emp = new Empregado();
		String aux;
		
		System.out.println("Digite o nome do empregado:");
		emp.nome = entrada.nextLine();
		System.out.println("Digite o endereço:");
		emp.endereco = entrada.nextLine();
		System.out.println("Digite o tipo (horista, assalariado, comissionado):");
		emp.tipo = entrada.nextLine();
		System.out.println("Digite a agenda de pagamento: (mensalmente, semanalmente...)");
		emp.agenda_pag = entrada.nextLine();
		if(emp.agenda_pag.equals("mensalmente") || emp.agenda_pag.equals("Mensalmente")){
			System.out.println("Digite o dia do mês:");
			dia = entrada.nextInt();
			if(agenda.d_mes[dia-1] == 1){
				emp.d_mes = dia;
			}
			else{
				System.out.println("Dia não disponível. Por favor tente novamente.");
				return;
			}
			entrada.nextLine();
		}
		else{
			System.out.println("Digite o dia da semana: (1 - segunda, 2 - terça ...)");
			dia = entrada.nextInt();
			if(agenda.d_semana[dia-1] == 1){
				emp.d_semana = dia;
			}
			else{
				System.out.println("Dia não disponível. Por favor tente novamente.");
				return;
			}
		}
		System.out.println("Digite o salário:");
		emp.salario = entrada.nextFloat();
		entrada.nextLine();
		System.out.println("Digite o método de pagamento:");
		emp.metodo_pag = entrada.nextLine();
		if(emp.tipo.equals("comissionado") || emp.tipo.equals("Comissionado")){
			System.out.println("Digite a comissão em porcentagem:");
			emp.comissao = entrada.nextFloat();
			entrada.nextLine();
		}
		System.out.println("O Empregado faz parte do sindicato? (sim ou não)");
		aux = entrada.nextLine();
		if(aux.equals("sim") || aux.equals("Sim")){
			emp.sindicato = true;
			System.out.println("Digite a taxa fixa a ser paga ao sindicato:");
			emp.taxa = entrada.nextFloat();
			entrada.nextLine();
		}
		else{
			emp.sindicato = false;
		}
		
		for(int i = 0; i < 20; i++){
			if(emps[i] == null){
				emp.id = i;
				copiar(emp, eaux);
				emps[i] = emp;
				break;
			}
		}
	}
	
	public static void rmEmpregado(Empregado[] emps, Scanner entrada, Empregado eaux){
		int ind;
		
		System.out.println("Digite o id do empregado que deseja remover:");
		ind = entrada.nextInt();
		entrada.nextLine();
		if(emps[ind] == null){
			System.out.println("Id incorreto. Por favor, tente novamente");
			return;
		}
		
		copiar(emps[ind], eaux);
		emps[ind] = null;
	}
	
	public static int consultarId(Empregado[] emps, Scanner entrada){
		String nome;
		int i = 0;
		
		System.out.println("Digite o nome do empregado:");
		nome = entrada.nextLine();
		
		for(i = 0; i < 20; i++){
			if(emps[i] != null && emps[i].nome.equals(nome)){
				break;
			}
		}
		
		return i;
	}
	
	public static void resultadoVendas(Empregado[] emps, Scanner entrada, Empregado eaux){
		int id;
		float venda;
		
		System.out.println("Digite o id para o qual a venda será associada:");
		id = entrada.nextInt();
		if(emps[id] == null){
			System.out.println("Id incorreto. Por favor, tente novamente");
			return;
		}
		
		System.out.println("Digite o valor da venda:");
		venda = entrada.nextFloat();
		entrada.nextLine();
		
		eaux.vendas = emps[id].vendas;
		
		if(emps[id].vendas == 0){
			emps[id].vendas = venda;
		}
		else{
			emps[id].vendas = emps[id].vendas + venda;
		}
	}
	
	public static void addTaxas(Empregado[] emps, Scanner entrada){
		int id;
		float taxa;
		
		System.out.println("Digite o id do empregado:");
		id = entrada.nextInt();
		if(emps[id] == null){
			System.out.println("Id incorreto. Por favor, tente novamente");
			return;
		}
		if(emps[id].sindicato == false){
			System.out.println("O empregado selecionado não pertence ao sindicato. Tenha certeza de que o id digitado seja o certo e tente novamente");
			return;
		}
		
		System.out.println("Digite o valor da taxa: ");
		taxa = entrada.nextFloat();
		entrada.nextLine();
		
		if(emps[id].taxa_ad == 0){
			emps[id].taxa_ad = taxa;
		}
		else{
			emps[id].taxa_ad = emps[id].taxa_ad + taxa;
		}
	}
	
	public static void alterarInfo(Empregado[] emps, Scanner entrada, Empregado eaux){
		int id, opcao;
		
		System.out.println("Digite o id:");
		id = entrada.nextInt();
		if(emps[id] == null){
			System.out.println("Id incorreto. Por favor, tente novamente");
			return;
		}
		
		do{
			System.out.println("Qual informação você deseja modificar?");
			System.out.println("1. Nome");
			System.out.println("2. Endereço");
			System.out.println("3. Tipo");
			System.out.println("4. Método de pagamento");
			System.out.println("5. Informações relacionadas ao sindicato");
			opcao = entrada.nextInt();
			entrada.nextLine();
			switch(opcao){
				case 1:
					System.out.println("Digite o novo nome:");
					emps[id].nome = entrada.nextLine();
					break;
					
				case 2:
					System.out.println("Digite o novo endereço:");
					emps[id].endereco = entrada.nextLine();
					break;
					
				case 3:
					System.out.println("Digite o novo tipo:");
					emps[id].tipo = entrada.nextLine();
					break;
					
				case 4:
					System.out.println("Digite o novo método de pagamento:");
					emps[id].metodo_pag = entrada.nextLine();
					break;
					
				case 5:
					System.out.println("O que você deseja modificar?");
					System.out.println("1 - O empregado é/não é parte do sindicato");
					System.out.println("2 - Adicionar taxas");
					
					int escolha = entrada.nextInt();
					
					switch(escolha){
						case 1:
							if(emps[id].sindicato == true){
								emps[id].sindicato = false;
							}
							else{
								emps[id].sindicato = true;
								System.out.println("Digite a taxa a ser paga ao sindicato:");
								emps[id].taxa = entrada.nextFloat();
							}
							break;
						case 2:
							addTaxas(emps, entrada);
							break;
						default:
							System.out.println("Opção Inválida. Por favor tente novamente.");
					}
					break;
					
				default:
					System.out.println("Opção Inválida. Por favor tente novamente.");
			}
			
			System.out.println("Deseja alterar outra informação? (1 - Sim/0 - Não)");
			opcao = entrada.nextInt();
		}while(opcao != 0);
		entrada.nextLine();
	}
	
	public static void cartaoPonto(Empregado[] emps, Scanner entrada, Empregado eaux){
		int id, hora;
		
		System.out.println("Digite o id:");
		id = entrada.nextInt();
		System.out.println("Digite a hora:");
		hora = entrada.nextInt();
		entrada.nextLine();
		
		eaux.h_entrada = emps[id].h_entrada;
		eaux.h_extra = emps[id].h_extra;
		eaux.h_trab = emps[id].h_trab;
		
		if(emps[id].h_entrada == 0){
			emps[id].h_entrada = hora;
		}
		else{
			if(hora > emps[id].h_entrada){
				if(hora - emps[id].h_entrada > 8){
					emps[id].h_extra = (hora - emps[id].h_entrada) - 8;
					emps[id].h_trab = emps[id].h_trab + 8;
				}
				else{
					emps[id].h_trab = emps[id].h_trab + (hora - emps[id].h_entrada);
				}
			}
			else{
				emps[id].h_trab = emps[id].h_trab + ((24 - emps[id].h_entrada) + hora);
			}
			emps[id].h_entrada = 0;
		}
	}

	public static void pagamento(Empregado[] emps, float salario, int i){
		switch(emps[i].metodo_pag){
			case "Cheque pelos correios":
				System.out.println("O empregado " + emps[i].nome + " está recebendo " + salario + " através de um cheque entregue pelos correios");
				break;
			case "cheque pelos correios":
				System.out.println("O empregado " + emps[i].nome + " está recebendo " + salario + " através de um cheque entregue pelos correios");
				break;
			case "Cheque em mãos":
				System.out.println("O empregado " + emps[i].nome + " está recebendo " + salario + " em cheque entregue em mãos");
				break;
			case "cheque em mãos":
				System.out.println("O empregado " + emps[i].nome + " está recebendo " + salario + " em cheque entregue em mãos");
				break;
			case "Depósito":
				System.out.println("O empregado " + emps[i].nome + " está recebendo " + salario + " por depósito");
				break;
			case "depósito":
				System.out.println("O empregado " + emps[i].nome + " está recebendo " + salario + " por depósito");
				break;
			default:
				System.out.println("Erro no sistema. Tente novamente depois");
		}
	}
	
	public static void folhaPag(Empregado[]emps, Scanner entrada, Empregado eaux){
		int data, dia;
		
		System.out.println("Digite a data: (somente o dia)");
		data = entrada.nextInt();
		System.out.println("Digite o dia da semana:");
		System.out.println("1 para segunda - 5 para sexta");
		dia = entrada.nextInt();
		entrada.nextLine();
		
		for(int i = 0; i < 20; i++){
			float sal;
			if(emps[i] == null){
				continue;
			}
			
			if(emps[i].tipo.equals("horista") || emps[i].tipo.equals("Horista")){
				sal = (emps[i].salario * emps[i].h_trab)  + ((emps[i].salario + (emps[i].salario / 2)) * emps[i].h_extra);
				if(emps[i].sindicato == true){
					sal = sal - emps[i].taxa - emps[i].taxa_ad;
				}
			}
			else{
				sal = (emps[i].h_trab / 8) * emps[i].salario;
				if(emps[i].tipo.equals("Comissionado") || emps[i].tipo.equals("comissionado")){
					sal = ((emps[i].comissao * emps[i].vendas) / 100) + sal;
				}
			}
			
			if(emps[i].agenda_pag.equals("semanalmente") || emps[i].agenda_pag.equals("Semanalmente")){
				if(emps[i].d_semana == dia){
					pagamento(emps, sal, i);
				}
			}
			if(emps[i].agenda_pag.equals("bisemanalmente") || emps[i].agenda_pag.equals("bisemanalmente")){
				if(dia == 5){
					if((data >= 8 && data <= 14) || (data >= 22 && data <= 28)){
						pagamento(emps, sal, i);
					}
				}
			}
			if(emps[i].agenda_pag.equals("mensalmente") || emps[i].agenda_pag.equals("mensalmente")){
				if(emps[i].d_mes == data){
					System.out.println("entra");
					pagamento(emps, sal, i);
				}
			}
			emps[i].h_extra = 0;
			emps[i].h_trab = 0;
			emps[i].vendas = 0;
		}
	}
	
	public static void novaAgenda(Scanner entrada, Agenda agenda){
		int dia;
		String aux;
		
		System.out.println("Qual a frequência do pagamento? (mensal, semanal)");
		aux = entrada.nextLine();
		if(aux.equals("mensal") || aux.equals("Mensal")){
			System.out.println("Em que dia o funcionário deverá ser pago?");
			dia = entrada.nextInt();
			agenda.d_mes[dia-1] = 1;
		}
		else{
			System.out.println("Em que dia da semana o funcionário deverá ser pago? (1 - segunda, 2 - terça ...");
			dia = entrada.nextInt();
			agenda.d_semana[dia-1] = 1;
		}
	}
	
	public static void desfazer(Empregado[] emps, int func, Empregado emp){
		int id = emp.id;
		Empregado empaux = new Empregado();
		switch(func){
			case 1:
				emps[id] = null;
				break;
				
			case 2:
				emps[id] = emp;
				break;
				
			case 3:
				empaux.h_trab = emps[id].h_trab;
				empaux.h_extra = emps[id].h_extra;
				empaux.h_entrada = emps[id].h_entrada;
				
				emps[id].h_trab = emp.h_trab;
				emps[id].h_extra = emp.h_extra;
				emps[id].h_entrada = emp.h_entrada;
				
				emp.h_trab = empaux.h_trab;
				emp.h_extra = empaux.h_extra;
				emp.h_entrada = empaux.h_entrada;
				break;
				
			case 4:
				empaux.vendas = emps[id].vendas;
				emps[id].vendas = emp.vendas;
				emp.vendas = empaux.vendas;
				break;
				
			case 5:
				if(!emps[id].nome.equals(emp.nome)){
					empaux.nome = emps[id].nome;
					emps[id].nome = emp.nome;
					emp.nome = empaux.nome;
				}
				else if(!emps[id].endereco.equals(emp.endereco)){
					empaux.endereco = emps[id].endereco;
					emps[id].endereco = emp.endereco;
					emp.endereco = empaux.endereco;
				}
				else if(!emps[id].tipo.equals(emp.tipo)){
					empaux.tipo = emps[id].tipo;
					emps[id].tipo = emp.tipo;
					emp.tipo = empaux.tipo;
				}
				else if(!emps[id].metodo_pag.equals(emp.metodo_pag)){
					empaux.metodo_pag = emps[id].metodo_pag;
					emps[id].metodo_pag = emp.metodo_pag;
					emp.metodo_pag = empaux.metodo_pag;
				}
				else if(emps[id].sindicato != emp.sindicato){
					empaux.sindicato = emps[id].sindicato;
					emps[id].sindicato = emp.sindicato;
					emp.sindicato = empaux.sindicato;
				}
				else if(emps[id].taxa_ad != emp.taxa_ad){
					empaux.taxa_ad = emps[id].taxa_ad;
					emps[id].taxa_ad = emp.taxa_ad;
					emp.taxa_ad = empaux.taxa_ad;
				}
				break;
				
			default:
				System.out.println("Não há nada a ser desfeito");
		}
	}
	
	public static void refazer(Empregado[] emps, int func, Empregado emp){
		int id = emp.id;
		switch(func){
		case 1:
			emps[id] = emp;
			break;
			
		case 2:
			emps[id]  = null;
			break;
			
		case 3:
			emps[id].h_trab = emp.h_trab;
			emps[id].h_extra = emp.h_extra;
			emps[id].h_entrada = emp.h_entrada;
			break;
			
		case 4:
			emps[id].vendas = emp.vendas;
			break;
			
		case 5:
			if(!emps[id].nome.equals(emp.nome)){
				emps[id].nome = emp.nome;
			}
			else if(!emps[id].endereco.equals(emp.endereco)){
				emps[id].endereco = emp.endereco;
			}
			else if(!emps[id].tipo.equals(emp.tipo)){
				emps[id].tipo = emp.tipo;
			}
			else if(!emps[id].metodo_pag.equals(emp.metodo_pag)){
				emps[id].metodo_pag = emp.metodo_pag;
			}
			else if(emps[id].sindicato != emp.sindicato){
				emps[id].sindicato = emp.sindicato;
			}
			else if(emps[id].taxa_ad != emp.taxa_ad){
				emps[id].taxa_ad = emp.taxa_ad;
			}
			break;
			
		default:
			System.out.println("Não há nada a ser desfeito");
		}
	}
	
	public static void main(String[] args){
		Empregado[] emps = new Empregado[20];
		Empregado emp_aux;
		Agenda novagenda = new Agenda();
		int func, a = 0;
		Scanner entrada = new Scanner(System.in);
		
		emp_aux = new Empregado();
		
		do{
			System.out.println("O que você deseja fazer?");
			System.out.println("1. Adicionar um novo empregado");
			System.out.println("2. Remover um empregado");
			System.out.println("3. Lançar cartão de ponto");
			System.out.println("4. Lançar resultado de vendas");
			System.out.println("5. Alterar as informações de um empregado");
			System.out.println("6. Rodar folha de pagamento");
			System.out.println("7. Criar uma nova agenda de pagamento");
			System.out.println("8. Imprimir a atual agenda de pagamento");
			System.out.println("9. Consultar o id de um funcionário");
			System.out.println("10. Desfazer");
			System.out.println("11. Refazer");
						
			func = entrada.nextInt();
			entrada.nextLine();
			
			switch(func){
				case 1:
					a = func;
					addEmpregado(emps, entrada, emp_aux, novagenda);
					break;
					
				case 2:
					a = func;
					rmEmpregado(emps, entrada, emp_aux);
					break;
					
				case 3:
					a = func;
					cartaoPonto(emps, entrada, emp_aux);
					break;
					
				case 4:
					a = func;
					resultadoVendas(emps, entrada, emp_aux);
					break;
					
				case 5:
					a = func;
					alterarInfo(emps, entrada, emp_aux);
					break;
					
				case 6:
					a = func;
					folhaPag(emps, entrada, emp_aux);
					break;
					
				case 7:
					novaAgenda(entrada, novagenda);
					break;
					
				case 8:
					imprimirAgenda(novagenda);
					break;
					
				case 9:
					int id;
					id = consultarId(emps, entrada);
					if(id < 20){
						System.out.println(id);
					}
					else{
						System.out.println("Empregado não encontrado");
					}
					break;
					
				case 10:
					desfazer(emps, a, emp_aux);
					break;
					
				case 11:
					refazer(emps, a, emp_aux);
					break;
					
				default:
					System.out.println("Opção Inválida");
			}
			
			System.out.println("Deseja fazer outra operação?");
			System.out.println("1 - Sim/0 - Não");
			func = entrada.nextInt();
			
		} while(func != 0);
		entrada.close();
	}
}
