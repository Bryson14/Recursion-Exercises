import java.util.ArrayList;


public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double weights[][] = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < weights.length; row++) {
            for (int column = 0; column < weights[row].length; column++) {
                double weight = computePyramidWeights(weights, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char image[][] = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }

    public static boolean isWordSymmetric(String[] words, int start, int end) {
        if (start >= end) {return true;} // end condition
        if (words[start].toLowerCase().equals(words[end].toLowerCase())) { //so far so good, calls recursion. Ignores uppercase
            return isWordSymmetric(words, start+1, end -1);
        } else {
            return false;
        }
    }

    public static long arraySum(int[] data, int position) {
        if (position < 0 || data.length == 0) {return 0;}
        if (position >= data.length) { //out of bounds range base case
            return 0;
        } else {return data[position] + arraySum(data, position +1);
        }
    }

    public static int arrayMin(int[] data, int position) {
        int min = data[position];
        return arrayMinHelp(data, position, min);
    }
    public static int arrayMinHelp( int[] data , int position, int min){
        if (position >= data.length) { //has checked the entire list
            return min;
        } else{
            if (data[position] < min) {
                min = data[position];
            }
            return arrayMinHelp(data, position +1 , min);
        }
    }

    public static double computePyramidWeights(double [][] weights, int row, int column) {
        if (row < 0 || column < 0) {return 0.0;} //incorrect entries
        if (row >= weights.length  || column >= weights[row].length ) {return 0.0;} //out of bound handling
        if (row == 0) {return weights[row][column]; //base condition
        } else {
            return weights[row][column] + computePyramidWeights(weights,row-1,column)/2 +
                    computePyramidWeights(weights, row - 1,column - 1)/2;
        }
    }
    public static int howManyOrganisms(char[][] image) {
        int organismCount = 0;
        char marker = 'a';
        for (int y = 0; y < image.length; y++) { //scanner
            for (int x = 0; x < image[y].length; x++) {
                if (image[y][x] == '*') { //a undiscovered organism
                    organismCount++;
                    howBigOrganism(image, x, y, marker);
                    marker = (char)(((int) marker) + 1); //changes marker to next character
                }
            }
        } return organismCount;
    }

    public static boolean howBigOrganism(char[][] image, int x, int y, char marker) {
        if (x < 0 || y < 0) {return false;} //out of bounds
        if (y > image.length -1 || x > image[y].length-1 ) {return false;} //out of bounds
        if (image[y][x] == '*') {
            image[y][x] = marker; //change from * to letter
            howBigOrganism(image, x+1, y, marker); //right
            howBigOrganism(image, x-1, y, marker); //left
            howBigOrganism(image, x, y+1, marker); // up
            howBigOrganism(image, x, y-1, marker); //down
            return true;
        } else {return false;}


    }
}
