// Dieter Archer 
//Samuel Ford
package com.w012dja.assingment1;

import java.util.stream.IntStream;

import com.jit.assignment1checker.Assignment1Tasks;
import com.jit.assignment1checker.Checker;

public class AssigmentOne implements Assignment1Tasks {

	public static void main(String[] args) {
			
		AssigmentOne myAssignment = new AssigmentOne();
		Checker.checkProfitMargin(myAssignment);
		Checker.checkGetGrade(myAssignment);
		Checker.checkSumOfDigits(myAssignment);
		Checker.checkMonthsSaved(myAssignment);
		Checker.checkFindAverage(myAssignment);
		
//I HAVE A CATTO AND DOGGO
		//they are both lazy
		
// I am a kitty! :3
		//yaaaaaaaaaaaaaaaaaaaay
		
	}
	/**
	 * @param participantAges The age age of the people participating
	 * @return returns the average age as a double
	 */
	public double findAverageAge(int[] participantAges) {
		// IntStream is a new to Java8 and is a simple way of performing Statistics on an array, .sum provides a simple way to add up all the numbers in an array
		int sum = IntStream.of(participantAges).sum();
		
		// accesses the parameters of the array with .length to determine how long the array is (this only works if the amount of numbers is the same
			// as the array length, if the amount of numbers in the array is unkown, the a counter must be set to determine where they stop by comparing 
			// each space in the array to null and stop the counter when it hits a null space
		int length = participantAges.length;
		
		// casts the ints to double and divides the sum by the length to find the average
		double age = (double) sum/length;
		
		//prints and returns the average found to where it was being called to
		System.out.println(age);
		 return age;
	}
	/** 
	 * @param number with X length 
	 * @return returns an int with the sum of the number
	 */
	public int sumOfDigits(int number) {
		//Figure out the length of the int
		int length = String.valueOf(number).length();
		
		// use the now known length to create an array
		int[] numArray = new int [length];
		
		
		//create loop that will "extract" each number out of the int
		for (int times = 0; times < length; times++) {
				//Extraction: the number is divided by 1, 10, 100 increasing based on how many times the loop is completed, then it % by 10
					// each time the number is divided by 10^times it will increase to "move" the decimal point over in the number, then the % 10
					// will leave just the remainder, which contains a decimal, however since this is just an int, only the whole number will be stored
					//therefore for 6789, we will get 9.0 the first time, 8.9 the second time, 7.89 the third and 6.789 the last. 
			double currentNum = (number / (Math.pow(10, times))) % 10;
			// this last part of the loop adds the number found by the previous "extraction" and dumps that into its respective place in the array, starting at 0
			numArray[times] = (int) currentNum;
		}
		
		// The sum of the whole array is calculated, this utilizes IntStream, which was recently added to Java 8 as a simple way to do various math on 
			// a array, such as getting the sum, average, total and so much more!
		int sum = IntStream.of(numArray).sum();
		
		// prints and returns the sum figured out from the IntStream calculation
		System.out.println(sum);
		return sum;
	}
	@Override
	/** 
	 * @param principal The total amount borrowed
	 * @param rate The percentage of interest the borrower needs to pay for the loan
	 * @param period The amount of time, in years, that the person is borrowing for
	 * @param monthlyExtraPrincipal The amount of money that was being paid extra going towards paying the loan off each month
	 * @return returns an int with the amount of months that money was being saved due to the loan being paid off
	 */
	public int monthsSaved(float principal, float rate, int period, float monthlyExtraPrincipal) {
			// Calculate the Mortgage Principal and Interest
			int n = period *12;
			float i = rate / 100 / 12;
			float monthlyPayment = (float) (principal * i / (1 - Math.pow(1+i, -n)));
			// define principal within the scope of this method
			float currentPrincipal = principal;
				
			// used yearly interest rate to find monthly
			float monthlyRate = rate / 12 / 100;
			
			//Initiate monthsPaid, to see how many months out of the whole period were used to pay back the loan
			int monthsPaid = 0;
		
		outerloop:
		for (int month = 1; month <= period * 12; month++) {
			// Figure out how much interest is paid every single month
			float monthInterest =  currentPrincipal * monthlyRate;
			// figures out how much principal is needed to be paid each month, this changes because the payment is going down, to change this
				// the interest is subtracted from the monthly payment
			float monthPrincipal = monthlyPayment - monthInterest;
			//Brings everything together by subtracting the monthPrincipal from the currentPrincipal to find out what the new principal is after paying that month
				// on top of that, it adds that someone may pay extra each month, this extra will reduce the amount of months they need to pay
			currentPrincipal = currentPrincipal - (monthPrincipal) - monthlyExtraPrincipal;
				//increments each time a payment is made 
				monthsPaid++;
				// nested if statement that will break if the loan is paid back
					if (currentPrincipal <= 0){
						break outerloop;
				}
		}
		// finds how many months were saved through the total period that could be spent to pay back the loan minus the months actually used to pay back loan
		int monthsSaved = (period * 12) - monthsPaid;
		// prints and returns the months that were money was saved from the line above
		System.out.println(monthsSaved);
		return monthsSaved;
	}

	@Override
	/**
	 * 
	 * @param costOfGoods How much the goods cost to make 
	 * @param costOfLabor How much the labor for each item costs
	 * @param salesPrice The cost to buy the good
	 * @param discountPercent an added discount to the sales item
	 * @return
	 */	
	public double profitMargin(double costOfGoods, double costOfLabor, double salesPrice, double discountPercent) {
		// Add the cost of the goods and the labor together
			double productionCost = costOfGoods + costOfLabor;
			
		// Discount the discountPercentage from the salesPrice
			// First find discount
			double priceWithDiscount = salesPrice * (discountPercent/100);
			
			  //then subtract discount from salesPrice
				priceWithDiscount = salesPrice - priceWithDiscount;
			
		// subtract the productionCost from the priceWithDiscount to see is a profit was made
			double profit = priceWithDiscount - productionCost;
			
		// prints and returns the profit	
		System.out.println(profit);
		return profit;
	}
	/**
	 * @param maxScore The Max score that a student could get in the class
	 * @param studentScore	The score the student got in the class
	 * @param gracePercent The amount of a buffer a teacher gives a student for the final grade in a class
	 * @return
	 */
	public String getGrade(int maxScore, int studentScore, float gracePercent) {
		//Initialize the String Grade
			String grade = null;
		// All the point values need to be standardized to a 0 - 100 (or more) scale
			double percentGrade = ((double) studentScore)/maxScore * 100;
			
		// Students score needs to have the grade point added in
			percentGrade = percentGrade + gracePercent;
			
		//If statement needs to be created to check to see what letter grade the student got 
			// Greater than 90, its an A
			if (percentGrade >=90)	{
				grade = "A";
				//Between 80 and 90, its a B
			} else if ((percentGrade >= 80)&&( percentGrade < 90 )) {
				grade = "B";
				//Between 70 and 80, its a C
			} else if ((percentGrade >= 70 )&&(percentGrade < 80))	{
				grade = "C";
				// between 60 and 70, its a D
			}else if ((percentGrade >= 60 )&&(percentGrade < 70)){
				grade = "D";
				// Lower than 60, its an F--
			}else if (percentGrade < 60 ){
				grade = "F";
			}
			// prints and Returns the grade determined with the IF else statement
			System.out.println(grade);
			return grade;
			
			

					
	
	}

}
