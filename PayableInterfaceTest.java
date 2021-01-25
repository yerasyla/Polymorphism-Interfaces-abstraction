// Payable.java
// Payable interface declaration.
public interface Payable {
	public abstract double getPaymentAmount(); // no implementation
}

// Invoice.java
// Invoice class that implements Payable.
public class Invoice implements Payable {
	private final String partNumber;
	private final String partDescription;
	private final int quantity;
	private final double pricePerItem;
	// constructor
	public Invoice(String partNumber, String partDescription, int quantity,
			double pricePerItem) {
		if (quantity < 0) { // validate quantity
			throw new IllegalArgumentException("Quantity must be >= 0");
		}
		if (pricePerItem < 0.0) { // validate pricePerItem
			throw new IllegalArgumentException(
					"Price per item must be >= 0");
		}
		this.quantity = quantity;
		this.partNumber = partNumber;
		this.partDescription = partDescription;
		this.pricePerItem = pricePerItem;
	}
	// get part number
	public String getPartNumber() {return partNumber;}
	// get description
	public String getPartDescription() {return partDescription;}
	// get quantity
	public int getQuantity() {return quantity;}
	// get price per item
	public double getPricePerItem() {return pricePerItem;}
	// return String representation of Invoice object
	@Override
	public String toString() {
		return String.format("%s: %n%s: %s (%s) %n%s: %d %n%s: $%,.2f",
"invoice", "part number", getPartNumber(), getPartDescription(),
"quantity", getQuantity(), "price per item", getPricePerItem());
	}
	// method required to carry out contract with interface Payable
	@Override
	public double getPaymentAmount() {
		return getQuantity() * getPricePerItem(); // calculate total cost
	}
}




// Employee.java
// Employee abstract superclass that implements Payable.
public abstract class Employee implements Payable {
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	// constructor
	public Employee(String firstName, String lastName,
			String socialSecurityNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}
	// return first name
	public String getFirstName() {return firstName;}
	// return last name
	public String getLastName() {return lastName;}
	// return social security number
	public String getSocialSecurityNumber() {return socialSecurityNumber;}
	// return String representation of Employee object
	@Override
	public String toString() {
		return String.format("%s %s%nsocial security number: %s",
				getFirstName(), getLastName(), getSocialSecurityNumber());
	}
	// abstract method must be overridden by concrete subclasses
	public abstract double earnings(); // no implementation here
	// implementing getPaymentAmount here enables the entire Employee
	// class hierarchy to be used in an app that processes Payables
	public double getPaymentAmount() {return earnings();}
}



// SalariedEmployee.java
// SalariedEmployee concrete class extends abstract class Employee.
public class SalariedEmployee extends Employee {
	private double weeklySalary;
	// constructor
	public SalariedEmployee(String firstName, String lastName,
			String socialSecurityNumber, double weeklySalary) {
		super(firstName, lastName, socialSecurityNumber);
		if (weeklySalary < 0.0) {
throw new IllegalArgumentException("Weekly salary must be >= 0.0");
		}
		this.weeklySalary = weeklySalary;
	}
	// set salary
	public void setWeeklySalary(double weeklySalary) {
		if (weeklySalary < 0.0) {
			throw new IllegalArgumentException(
					"Weekly salary must be >= 0.0");
		}
		this.weeklySalary = weeklySalary;
	}
	// return salary
	public double getWeeklySalary() {return weeklySalary;}
	// calculate earnings; override abstract method earnings in Employee
	@Override
	public double earnings() {return getWeeklySalary();}
	// return String representation of SalariedEmployee object
	@Override
	public String toString() {
		return String.format("salaried employee: %s%n%s: $%,.2f",
				super.toString(), "weekly salary", getWeeklySalary());
	}
}


// PayableInterfaceTest.java
// Payable interface test program processing Invoices and
// Employees polymorphically
public class PayableInterfaceTest {
	public static void main(String[] args) {
		// create four-element Payable array
		Payable[] payableObjects = new Payable[] {
				new Invoice("01234", "seat", 2, 375.00),
				new Invoice("56789", "tire", 4, 79.95),
new SalariedEmployee("John", "Smith", "111-11-1111", 800.00),
new SalariedEmployee("Lisa", "Barnes", "888-88-8888", 1200.00)
		};
		System.out.println(
				"Invoices and Employees processed polymorphically:");
		// generically process each element in array payableObjects
		for (Payable currentPayable : payableObjects) {
			// output currentPayable and its appropriate payment amount
			System.out.printf("%n%s %npayment due: $%,.2f%n",
					currentPayable.toString(), 
// could invoke implicitly
					currentPayable.getPaymentAmount());
		}
	}
}
