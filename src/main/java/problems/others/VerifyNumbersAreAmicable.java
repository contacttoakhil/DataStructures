package main.java.problems.others;

public class VerifyNumbersAreAmicable {
    public static void main(String[] args) {
        verifyNumbersAreAmicable(220, 284);
        verifyNumbersAreAmicable(1184, 1210);
        verifyNumbersAreAmicable(2620, 2924);
        verifyNumbersAreAmicable(5020, 5564);
    }
    private static void verifyNumbersAreAmicable(Integer numOne, Integer numTwo)
    {
        if (sumOfDivisors(numOne) == sumOfDivisors(numTwo))
            System.out.println("The numbers " + numOne + " and " + numTwo + "  are amicable.");
        else
            System.out.println("The numbers " + numOne + " and " + numTwo + " are not amicable.");
    }
    private static int sumOfDivisors(Integer num)
    {
        int sumOfNum = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0)
                sumOfNum += i;
        }
        return sumOfNum;
    }
}
