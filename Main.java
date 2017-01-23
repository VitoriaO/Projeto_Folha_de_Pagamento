import java.util.Scanner;

public class Main{

	public static void addEmployee(Employee[] empls) {
		Scanner entry = new Scanner(System.in);

		Employee emp = new Employee();
		
		System.out.println("Enter the name of the employee:");
		emp.name = entry.nextLine();
		System.out.println("Enter the adress:");
		emp.adress = entry.nextLine();
		System.out.println("Enter the type of employee (hourly, salaried, commissioned):");
		emp.type = entry.nextLine();
		System.out.println("Part of the Union (true or false)?");
		System.out.println("Enter the payment method:");
		emp.pmethod = entry.nextLine();
		System.out.println("Enter the payment schedule:");
		emp.pschedule = entry.nextLine();
		System.out.println("Part of the Union (true or false)?");
		emp.union = entry.nextBoolean();
		
		if(emp.type.equals("commissioned") || emp.type.equals("Commissioned")){
			System.out.println("Enter the salary:");
			emp.salary = entry.nextFloat();
			System.out.println("Enter the commission percentage:");
			emp.commission = entry.nextFloat();
		}
		else{
			System.out.println("Enter the salary:");
			emp.salary = entry.nextFloat();
		}
		
		for(int i = 0; i < 20; i++){
			if(empls[i] == null){
				emp.id = i;
				empls[i] = emp;
				break;
			}
		}
		
		entry.close();
	}
	
	public static void rmEmployee(Employee[] empls){
		int ind;
		Scanner entry = new Scanner(System.in);
		
		System.out.println("Enter the ID number of the employee you want to remove:");
		ind = entry.nextInt();
		
		empls[ind] = null;
		
		entry.close();
	}
	
	public static int consultId(Employee[] empls){
		String empname;
		Scanner entry = new Scanner(System.in);
		
		System.out.println("Enter the name of the employee");
		empname = entry.nextLine();
		entry.close();
		
		for(int i = 0; i < 20; i++){
			if(empls[i].name == empname){
				return i;
			}
		}
		
		return -1;
	}
	
	public static void saleResult(Employee[] empls){
		int id;
		float sale;
		Scanner entry = new Scanner(System.in);
		
		System.out.println("Enter the id:");
		id = entry.nextInt();
		
		System.out.println("Enter the sale result:");
		sale = entry.nextFloat();
		entry.close();
		
		if(empls[id].sales == 0.0){
			empls[id].sales = sale;
		}
		else{
			empls[id].sales = empls[id].sales + sale;
		}
	}
	
	public static void charges(Employee[] empls){
		int id;
		float ch;
		Scanner entry = new Scanner(System.in);
		
		System.out.println("Enter the id of the employee:");
		id = entry.nextInt();
		System.out.println("Enter the service charge:");
		ch = entry.nextFloat();
		entry.close();
		
		if(empls[id].charge == 0.0){
			empls[id].charge = ch;
		}
		else{
			empls[id].charge = empls[id].charge + ch;
		}
	}
	
	public static void changeInfo(Employee[] empls){
		int id, option;
		Scanner entry = new Scanner(System.in);
		System.out.println("Enter the id:");
		id = entry.nextInt();
		System.out.println("What information do you want to change?");
		System.out.println("1. Name");
		System.out.println("2. Adress");
		System.out.println("3. Type");
		System.out.println("4. Payment method");
		System.out.println("5. Information related to the Union");
		option = entry.nextInt();
		entry.close();
		
		Scanner newsc = new Scanner(System.in);
		switch(option){
			case 1:
				String newname;
				System.out.println("Enter the new name");
				newname = newsc.nextLine();
				empls[id].name = newname;
				break;
				
			case 2:
				String newadress;
				System.out.println("Enter the new adress");
				newadress = newsc.nextLine();
				empls[id].adress = newadress;
				break;
				
			case 3:
				String newtype;
				System.out.println("Enter the new type");
				newtype = newsc.nextLine();
				empls[id].type = newtype;
				break;
				
			case 4:
				String newmet;
				System.out.println("Enter the new preferred payment method");
				newmet = newsc.nextLine();
				empls[id].pmethod = newmet;
				break;
				
			case 5:
				if(empls[id].union == true){
					empls[id].union = false;
				}
				else{
					empls[id].union = true;
				}
				break;
				
			default:
				System.out.println("Invalid option");
		}
		newsc.close();
	}
	
	public static void timeCard(Employee[] empls){
		int id, hour;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the id:");
		id = sc.nextInt();
		System.out.println("Enter the hour: (24-hour format)");
		hour = sc.nextInt();
		
		sc.close();
		
		if(empls[id].entryt == -1){
			empls[id].entryt = hour;
		}
		else{
			if(hour > empls[id].entryt){
				if(hour - empls[id].entryt > 8){
					empls[id].extrah = (hour - empls[id].entryt) - 8;
					empls[id].workedh = 8;
				}
				else{
					empls[id].workedh = hour - empls[id].entryt;
				}
			}
			else{
				empls[id].workedh = (24 - empls[id].entryt) + hour;
			}
			empls[id].entryt = -1;
		}
	}

	public static void payroll(Employee[]empls){
		
	}
	
	public static void main(String[] args){
		Employee[] empls = new Employee[20];
		String choice;
		int func;
		Scanner entry = new Scanner(System.in);
		
		do{
			System.out.println("What do you want to do?");
			System.out.println("1. Add a new employee");
			System.out.println("2. Remove an employee");
			System.out.println("3. Upload a timecard");
			System.out.println("4. Upload a sale result");
			System.out.println("5. Upload a service charge");
			System.out.println("6. Change an employee information");
			System.out.println("7. Run payroll");
			System.out.println("8. Create a new payment schedule");
			System.out.println("9. Check an employee id");
			
			func = entry.nextInt();
			
			switch(func){
				case 1:
					addEmployee(empls);
					break;
					
				case 2:
					rmEmployee(empls);
					break;
					
				case 3:
					timeCard(empls);
					break;
					
				case 4:
					saleResult(empls);
					break;
					
				case 5:
					charges(empls);
					break;
					
				case 6:
					changeInfo(empls);
					break;
					
				case 7:
					break;
					
				case 8:
					break;
					
				case 9:
					int id;
					id = consultId(empls);
					if(id != -1){
						System.out.print(id);
					}
					else{
						System.out.println("Employee not found");
					}
					break;
					
				default:
					System.out.println("Invalid option");
			}
			entry = new Scanner(System.in);
			
			System.out.println("Do you want to do another operation?");
			choice = entry.nextLine();
			entry.close();
			if(choice.equals("no") || choice.equals("No")){
				func = 0;
			}
			
		} while(func != 0);
	}
}
