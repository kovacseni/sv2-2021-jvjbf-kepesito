package algorithms;

public class Numbers {

    public boolean isAbundantNumber(int number) {
        validateNumber(number);
        int sumOfDividers = 0;
        for ( int i = 1; i< number; i++) {
            if ( number % i == 0 ) {
                sumOfDividers += i;
            }
        }
        if ( sumOfDividers > number ) {
            return true;
        }
        return false;
    }

    private void validateNumber(int number) {
        if ( number < 0 ) {
            throw new IllegalArgumentException("Number must be positive: "+number);
        }
    }
}
