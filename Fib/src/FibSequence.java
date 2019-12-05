import java.util.ArrayList;
import java.util.Arrays;

public class FibSequence {
    private long userInput;
    private boolean isFibNumber;
    private ArrayList<Long> sequence;
    private long maxNumber;

    //default constructor
    public FibSequence(){
        this.userInput = 0L;
        this.maxNumber = 0L;
        this.isFibNumber = true;
        this.sequence = new ArrayList<Long>(Arrays.asList(0L));
    }

    //parameterized constructor
    public FibSequence(long userInput){
        /*The fibonacci number that follows 7540113804746346429 is greater than the upper limit of the long data type.
		If the program attempts to find fibonacci numbers greater than this one, it will crash.*/
        if (userInput >= 7540113804746346429L || userInput <= -7540113804746346429L){
            this.maxNumber = 7540113804746346429L;
            this.userInput =  userInput;
        } else{
            this.maxNumber = Math.abs(userInput);
            this.userInput = userInput;
        }

        this.sequence = new ArrayList<Long>(Arrays.asList(0L, 1L));

        if (this.maxNumber == 0){ //remove 1 from the ArrayList
            this.sequence.remove(1);
        } else if (this.maxNumber == 7540113804746346429L){ //prevent the program from generating sum greater than upper limit of long data type
            while(this.sequence.get(this.sequence.size() - 1) != this.maxNumber){
                this.sequence.add((long)this.sequence.get((this.sequence.size() - 2)) + this.sequence.get((sequence.size() - 1)));
            }
        } else {
            while(this.sequence.get((this.sequence.size() - 2)) + this.sequence.get((this.sequence.size() - 1)) <= this.maxNumber){
                this.sequence.add((long)this.sequence.get((this.sequence.size() - 2)) + this.sequence.get((this.sequence.size() - 1)));
            }
        }

        //determine whether the number entered by user is a fibonacci number
        if (Math.abs(this.userInput) == this.sequence.get(this.sequence.size() - 1)){
            this.isFibNumber = true;
        } else{
            this.isFibNumber = false;
        }
    }


    public String toString() {
        //create array from ArrayList
        long[] results = new long[this.sequence.size()];

        for (int i = 0; i < this.sequence.size(); i++) {
            results[i] = this.sequence.get(i);
        }

        String outputString = "";

        //get rid of brackets
        String formattedResults = Arrays.toString(results).replace("[", "").replace("]", "");

        if (userInput < 0) {
            outputString += "The value entered, " + userInput + ", is a negative integer. Its positive equivalent will be used instead.\n\n";
        }

        outputString += "Fibonacci sequence leading up to " + Math.abs(this.userInput) + ": " + formattedResults;

        if (this.isFibNumber) {
            outputString += "\n\n" + Math.abs(this.userInput) + " is a Fibonacci number.";
        } else {
            outputString += "\n\n" + Math.abs(this.userInput) + " is not a Fibonacci number.";
        }
        return outputString;
    }
}
