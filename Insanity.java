/*
* Abdulla Binissa
* 12/06/2018 -  5:00PM -
* 12/10/2018 -  9:30PM - 3:00AM
* 12/10/2018 -  3:45PM - 7:00PM
* 12/15/2018 - 12:30PM - 3:00AM
*
*/

/*
    Main class
    - Creates a list of Cubes called myCubes
    - Uses findSolution() function to see if myCubes has solution
    - If solution, print and end progrm
    - Else, iterate through all subsets ( 2^n ; where n is length of myCubes )
    - Iteration starts with all subsets of size 2, and goes to n
    - Continue through subsets running findSolution() on each subset
    - If an iteration does not have a solution, return subset as minObstacle

    Some choices I made:
    - Starting with subsets of size 2 rather than size n-1 with n being size of
      myCubes. Because once i find a subset that doesn't have a solution. There
      is no need to check any subsets higher as that is the minObstacle. If
      starting with size n-1, all subsets smaller would need to be check
      regardless if a minObstacle was found.
    -
*/

import java.util.*;

public class Insanity
{
    public static void main(String[] args)
    {
        /*
        //    Option dictates the pairs and size of the problem.
        //    1: 1+(floor(n*pi)mod30)
        //    2: 1+(floor(n*e )mod30)
        //    3: 1+(floor(n*sqrt(3))mod30)
        //    4: 1+(floor(n*sqrt(5))mod30)
        //    5 and so on: Any number larger than 4, sets the size of the puzzle
        //                 wtih the same color generating formula as #1. I used
        //                 this to test on various sizes of the puzzle
        */
        int option = 0;

        Cubes myCubes = new Cubes(option);
        System.out.print("Option " + option + "\n");
        System.out.print(myCubes.toString() + "\n");

        if ( myCubes.findSolution() )
        {
            System.out.print("Solution Exists!\n");
            System.out.print(myCubes.strings() + "\n");
            System.out.print("String 1 indexes: " + myCubes.string(0) + "\n");
            System.out.print("String 2 indexes: " + myCubes.string(1) + "\n");
        }

        else
        {
            System.out.print("Solution does not exist.\n");
            System.out.print("Searching for minimum obstacle . . .\n\n");

            Cubes subset;
            outerloop:
            for(int i = 2; i < myCubes.size(); i+=1 )
            {
                //Returns a list of subsets of size i
                ArrayList<Cubes> subsets = myCubes.getSubsets(i);
                System.out.println(String.format(
                        "Checking subsets of size %d: %d", i, subsets.size() ));

                //Loops through all subsets of size i, checks if there is
                // solution. If not - returns minObstacle
                for(int j = 0; j < subsets.size(); j+=1)
                {
                    subset = subsets.get(j);

                    if(!subset.findSolution())
                    {
                        System.out.print("\tMin-Obstacle Found!\n");
                        System.out.print("\nMinimum Obstacle is: \n");
                        System.out.print( subset.toString() + "\n");
                        break outerloop;
                    }
                }
            }


        }


    }
}
