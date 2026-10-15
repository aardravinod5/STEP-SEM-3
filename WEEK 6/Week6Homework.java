public class Week6Homework {
    static class BookInventory {
        private final String title;
        private final String author;
        private final int copiesAvailable;
        BookInventory(String title, String author, int copiesAvailable) { this.title=title; this.author=author; this.copiesAvailable=copiesAvailable; }
        void printEntry() { System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available"); }
    }
    static class PayrollAccount {
        private double basicSalary;
        private double bonus;
        PayrollAccount(double openingBasicSalary) {
            if (openingBasicSalary < 0) { System.out.println("Warning: negative opening salary; starting at Rs 0.0"); basicSalary=0; }
            else basicSalary=openingBasicSalary;
        }
        void creditBonus(double amount) {
            if (amount <= 0) { System.out.println("Bonus rejected: amount must be positive"); return; }
            bonus += amount; System.out.println("Bonus credited: Rs " + amount);
        }
        void deductTax(double percent) {
            if (percent < 0 || percent > 100) { System.out.println("Tax rejected: percent must be between 0 and 100"); return; }
            basicSalary *= (1 - percent / 100.0); System.out.println("Tax deducted: " + percent + "%");
        }
        double getNetSalary() { return basicSalary + bonus; }
    }
    static class Employee {
        private final String empId, empName;
        private final double salary;
        private boolean isIntern;
        static String companyName="Bright Horizon Technologies";
        static int employeeCount=0;
        Employee(String empId, String empName, double salary) {
            this.empId=empId; this.empName=empName; this.salary=salary; this.isIntern=false; employeeCount++;
        }
        Employee(String empId, String empName) { this(empId, empName, 0); this.isIntern=true; }
        void printProfile() { System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern); }
        static void printCompanyInfo() { System.out.println(companyName); System.out.println("Employees on record: " + employeeCount); }
    }
    static class HallTicket {
        String studentName; int seatNumber;
        HallTicket(String studentName, int seatNumber) { this.studentName=studentName; this.seatNumber=seatNumber; }
    }
    public static void main(String[] args) {
        BookInventory[] books={new BookInventory("Clean Code","Robert C. Martin",3),new BookInventory("Effective Java","Joshua Bloch",5),new BookInventory("Refactoring","Martin Fowler",0),new BookInventory("Design Patterns","GoF",2)};
        for (BookInventory book:books) book.printEntry();
        PayrollAccount payroll=new PayrollAccount(50000); payroll.creditBonus(5000); payroll.deductTax(10); System.out.println("Net salary: Rs " + payroll.getNetSalary());
        Employee permanent=new Employee("E-101","Divya",65000); Employee intern=new Employee("E-102","Arjun"); permanent.printProfile(); intern.printProfile();
        HallTicket priya=new HallTicket("Priya",0); HallTicket copy=priya; copy.seatNumber=45; HallTicket separate=new HallTicket("Priya",45);
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber); System.out.println("copy == priya: " + (copy==priya)); System.out.println("separate == priya: " + (separate==priya));
        Employee third=new Employee("E-103","Ravi",72000); Employee.printCompanyInfo();
    }
}
