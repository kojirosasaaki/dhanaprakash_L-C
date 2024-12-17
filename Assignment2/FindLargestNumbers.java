import java.util.Scanner;

public class FindLargestNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the size of the array : ");
		int n = scanner.nextInt();
		
		int[] numarray = new int[n];
		
		System.out.println("Enter "+n+" numbers into the array : ");
		for(int i = 0 ; i < n ; i++) {
			
			numarray[i] = scanner.nextInt();
			
		}
		int i = 0;
		for(int num : numarray) {
			
			System.out.println(i+"th index element is"+num);
			i++;
		}
		
		int[] result = FirstAndSecondLargestNumbers(numarray);
		System.out.println("1st largest number : "+result[0]);
		System.out.println("2nd largest number : "+result[1]);
	
	}
		
	public static int[] FirstAndSecondLargestNumbers(int[] numarray) {
		int firstlargest = numarray[0];
		int secondlargest = numarray[0];
		
		for(int num : numarray) {
			
			if(num >firstlargest) {
				
				secondlargest = firstlargest;
				firstlargest = num;
				
			}
			else if(num > secondlargest && num != firstlargest ) {
				
				secondlargest = num;
				
			}
		}
		
		return new int[] {firstlargest,secondlargest};
		
	}	

}
