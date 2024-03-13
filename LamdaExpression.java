package practice;


public class LamdaExpression {
	
    // Define the functional interface
  
    interface Receive {
        void input(String... args);
    }

    
    // Define the Print class
    static class Print {
        Receive r;

        public Print(Receive r) {
            this.r = r;
        }
     //Constructor
        public void display() {
            r.input("A", "B", "C", "D");
//For user Input in terminal
/*
Scanner sc = new Scanner(System.in);
            System.out.print("Enter input separated by spaces: ");
            String input = sc.nextLine();
            String[] args1 = input.split("\\s+");
            r.input(args1);
            sc.close();
*/
		
        }}
    public static void main(String[] args) {
    	
        // instance of Print with a lamda expression
        Print p = new Print((String... args1) -> {
            for (String container : args1) {
                System.out.println(container);
            }});
        // Calling display method to trigger the lambda expression
        p.display();
    }}
