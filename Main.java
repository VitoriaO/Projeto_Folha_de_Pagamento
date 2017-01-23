import java.util.Scanner;

public class Main{

	public static void printEmployees(Employee[] empls){
		for(int i = 0; i < 20; i++){
			if(empls[i] != null){
				System.out.println(empls[i].name);
				System.out.println(empls[i].adress);
				System.out.println(empls[i].type);
				System.out.println(empls[i].union);
				if(empls[i].union == true){
					System.out.println(empls[i].tax);
				}
				System.out.println(empls[i].pmethod);
				System.out.println(empls[i].pschedule);
				if(empls[i].type.equals("commissioned") || empls[i].type.equals("Commissioned")){
					System.out.println(empls[i].salary);
					System.out.println(empls[i].commission);
				}
				else{
					System.out.println(empls[i].salary);
				}
				System.out.println(empls[i].id);
			}
		}
	}
	
	public static void addEmployee(Employee[] empls, Scanner entry) {
		Employee emp = new Employee();
		
		System.out.println("Enter the name of the employee:");
		emp.name = entry.nextLine();
		System.out.println("Enter the adress:");
		emp.adress = entry.nextLine();
		System.out.println("Enter the type of employee (hourly, salaried, commissioned):");
		emp.type = entry.nextLine();
		System.out.println("Part of the Union (true or false)?");
		emp.union = entry.nextBoolean();
		if(emp.union == true){
			System.out.println("Enter the tax");
			emp.tax = entry.nextFloat();
		}
		entry.nextLine();
		System.out.println("Enter the payment method:");
		emp.pmethod = entry.nextLine();
		System.out.println("Enter the payment schedule:");
		emp.pschedule = entry.nextLine();
		
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
	}
	
	public static void rmEmployee(Employee[] empls, Scanner entry){
		int ind;
		
		System.out.println("Enter the ID number of the employee you want to remove:");
		ind = entry.nextInt();
		
		empls[ind] = null;
	}
	
	public static int consultId(Employee[] empls, Scanner entry){
		String empname;
		int i;
		
		System.out.println("Enter the name of the employee");
		empname = entry.nextLine();
		
		for(i = 0; i < 20; i++){
			String newstring = empls[i].name;
			if(newstring.equals(empname)){
				break;
			}
		}
		
		return i;
	}
	
	public static void saleResult(Employee[] empls, Scanner entry){
		int id;
		float sale;
		
		System.out.println("Enter the id:");
		id = entry.nextInt();
		
		System.out.println("Enter the sale result:");
		sale = entry.nextFloat();
		
		if(empls[id].sales == 0.0){
			empls[id].sales = sale;
		}
		else{
			empls[id].sales = empls[id].sales + sale;
		}
	}
	
	public static void adTaxes(Employee[] empls, Scanner entry){
		int id;
		float ch;
		
		System.out.println("Enter the id of the employee:");
		id = entry.nextInt();
		System.out.println("Enter the service tax:");
		ch = entry.nextFloat();
		
		if(empls[id].adtax == 0.0){
			empls[id].adtax = ch;
		}
		else{
			empls[id].adtax = empls[id].adtax + ch;
		}
	}
	
	public static void changeInfo(Employee[] empls, Scanner entry){
		int id, option;
		System.out.println("Enter the id:");
		id = entry.nextInt();
		System.out.println("What information do you want to change?");
		System.out.println("1. Name");
		System.out.println("2. Adress");
		System.out.println("3. Type");
		System.out.println("4. Payment method");
		System.out.println("5. Information related to the Union");
		option = entry.nextInt();
		entry.nextLine();
		
		switch(option){
			case 1:
				String newname;
				System.out.println("Enter the new name");
				newname = entry.nextLine();
				empls[id].name = newname;
				break;
				
			case 2:
				String newadress;
				System.out.println("Enter the new adress");
				newadress = entry.nextLine();
				empls[id].adress = newadress;
				break;
				
			case 3:
				String newtype;
				System.out.println("Enter the new type");
				newtype = entry.nextLine();
				empls[id].type = newtype;
				break;
				
			case 4:
				String newmet;
				System.out.println("Enter the new payment method");
				newmet = entry.nextLine();
				empls[id].pmethod = newmet;
				break;
				
			case 5:
				System.out.println("What do you want to change");
				System.out.println("1 - The employee is/isn't a part of the Union");
				System.out.println("2 - Add taxes");
				int choice = entry.nextInt();
				
				switch(choice){
					case 1:
						if(empls[id].union == true){
							empls[id].union = false;
						}
						else{
							empls[id].union = true;
						}
						break;
					case 2:
						adTaxes(empls, entry);
						break;
					default:
						System.out.println("Invalid option");
				}
				break;
				
			default:
				System.out.println("Invalid option");
		}
	}
	
	public static void timeCard(Employee[] empls, Scanner sc){
		int id, hour;
		
		System.out.println("Enter the id:");
		id = sc.nextInt();
		System.out.println("Enter the hour: (24-hour format)");
		hour = sc.nextInt();
		
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

	public static void Payment(Employee[] empls, int i){
		switch(empls[i].pmethod){
			case "check via mail":
				break;
			case "check in hands":
				break;
			case "deposit":
				break;
			default:
				System.out.println("Error");
		}
	}
	
	public static void payroll(Employee[]empls, Scanner sc){
		int date, day;
		
		System.out.println("Enter the date: (only the day)");
		date = sc.nextInt();
		System.out.println("Enter the day of the week(1 to 5):");
		System.out.println("1 for monday - 5 for friday");
		day = sc.nextInt();
		
		for(int i = 0; i < 20; i++){
			float sal;
			sal = (empls[i].salary * empls[i].workedh)  + ((empls[i].salary + (empls[i].salary / 2)) * empls[i].extrah);
			if(empls[i].pschedule.equals("weekly") || empls[i].pschedule.equals("Weekly")){
				switch(day){
					case 1:
						if(empls[i].dweek.equals("Monday") || empls[i].dweek.equals("monday")){
							Payment(empls, i);
						}
						break;
					case 2:
						if(empls[i].dweek.equals("Tuesday") || empls[i].dweek.equals("tuesday")){
							Payment(empls, i);
						}
						break;
					case 3:
						if(empls[i].dweek.equals("Wednesday") || empls[i].dweek.equals("wednesday")){
							Payment(empls, i);
						}
						break;
					case 4:
						if(empls[i].dweek.equals("Thursday") || empls[i].dweek.equals("thursday")){
							Payment(empls, i);
						}
						break;
					case 5:
						if(empls[i].dweek.equals("Friday") || empls[i].dweek.equals("friday")){
							Payment(empls, i);
						}
						break;
					default:
						System.out.println("Invalid day of the week. Please try again.");
				}
			}
			if(empls[i].pschedule.equals("biweekly") || empls[i].pschedule.equals("Biweekly")){
				
			}
			if(empls[i].pschedule.equals("monthly") || empls[i].pschedule.equals("Monthly")){
				if(empls[i].dmonth == date){
					
				}
			}
			if(empls[i].pschedule.equals("daily") || empls[i].pschedule.equals("Daily")){
				
			}
		}
	}
	
	public static void main(String[] args){
		Employee[] empls = new Employee[20];
		int func;
		Scanner entry = new Scanner(System.in);
		do{
			System.out.println("What do you want to do?");
			System.out.println("1. Add a new employee");
			System.out.println("2. Remove an employee");
			System.out.println("3. Upload a timecard");
			System.out.println("4. Upload a sale result");
			System.out.println("5. Change an employee information");
			System.out.println("6. Run payroll");
			System.out.println("7. Create a new payment schedule");
			System.out.println("8. Check an employee id");
			
			func = entry.nextInt();
			entry.nextLine();
			
			switch(func){
				case 1:
					addEmployee(empls, entry);
					break;
					
				case 2:
					rmEmployee(empls, entry);
					break;
					
				case 3:
					timeCard(empls, entry);
					break;
					
				case 4:
					saleResult(empls, entry);
					break;
					
				case 5:
					changeInfo(empls, entry);
					break;
					
				case 6:
					payroll(empls, entry);
					break;
					
				case 7:
					break;
					
				case 8:
					int id;
					id = consultId(empls, entry);
					if(id != 20){
						System.out.println(id);
					}
					else{
						System.out.println("Employee not found");
					}
					break;
					
				default:
					System.out.println("Invalid option");
			}
			
			System.out.println("Do you want to do another operation?");
			System.out.println("1 - Yes\n0 - No\n");
			func = entry.nextInt();
			
		} while(func != 0);
		entry.close();
		
		printEmployees(empls);
	}
}
