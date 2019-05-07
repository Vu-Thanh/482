import java.util.*;

public class Cubes
{
    //Data fields
    ArrayList<Cube> cubes = new ArrayList<Cube>();              //Full set of cubes
    ArrayList<Integer> string1 = new ArrayList<Integer>();;     //String for solution 1
    ArrayList<Integer> string2 = new ArrayList<Integer>();;     //String for solution 2

    //Constructor
    // Used to create a list of objects called Cube.
    public Cubes(int i)
    {
        Cube temp;
        int a,b,c,d,e,f;
        int n;
        switch(i)
        {
            case 0:
                for(int j = 0; j < 30; j+=1)
                {
                    n = j*6;
                    a = 1 + ((int)(Math.floor((n+0)*Math.PI) % 30));
                    b = 1 + ((int)(Math.floor((n+1)*Math.PI) % 30));
                    c = 1 + ((int)(Math.floor((n+2)*Math.PI) % 30));
                    d = 1 + ((int)(Math.floor((n+3)*Math.PI) % 30));
                    e = 1 + ((int)(Math.floor((n+4)*Math.PI) % 30));
                    f = 1 + ((int)(Math.floor((n+5)*Math.PI) % 30));
                    temp = new Cube(j,a,b,c,d,e,f);
                    cubes.add(temp);
                }
                break;

            case 1:
                for(int j = 0; j < 30; j+=1)
                {
                    n = j*6;
                    a = 1 + ((int)(Math.floor((n+0)*Math.E) % 30));
                    b = 1 + ((int)(Math.floor((n+1)*Math.E) % 30));
                    c = 1 + ((int)(Math.floor((n+2)*Math.E) % 30));
                    d = 1 + ((int)(Math.floor((n+3)*Math.E) % 30));
                    e = 1 + ((int)(Math.floor((n+4)*Math.E) % 30));
                    f = 1 + ((int)(Math.floor((n+5)*Math.E) % 30));
                    temp = new Cube(j,a,b,c,d,e,f);
                    cubes.add(temp);
                }
                break;

            case 2:
                for(int j = 0; j < 30; j+=1)
                {
                    n = j*6;
                    a = 1 + ((int)(Math.floor((n+0)*Math.sqrt(3)) % 30));
                    b = 1 + ((int)(Math.floor((n+1)*Math.sqrt(3)) % 30));
                    c = 1 + ((int)(Math.floor((n+2)*Math.sqrt(3)) % 30));
                    d = 1 + ((int)(Math.floor((n+3)*Math.sqrt(3)) % 30));
                    e = 1 + ((int)(Math.floor((n+4)*Math.sqrt(3)) % 30));
                    f = 1 + ((int)(Math.floor((n+5)*Math.sqrt(3)) % 30));
                    temp = new Cube(j,a,b,c,d,e,f);
                    cubes.add(temp);
                }
                break;

            case 3:
                for(int j = 0; j < 30; j+=1)
                {
                    n = j*6;
                    a = 1 + ((int)(Math.floor((n+0)*Math.sqrt(5)) % 30));
                    b = 1 + ((int)(Math.floor((n+1)*Math.sqrt(5)) % 30));
                    c = 1 + ((int)(Math.floor((n+2)*Math.sqrt(5)) % 30));
                    d = 1 + ((int)(Math.floor((n+3)*Math.sqrt(5)) % 30));
                    e = 1 + ((int)(Math.floor((n+4)*Math.sqrt(5)) % 30));
                    f = 1 + ((int)(Math.floor((n+5)*Math.sqrt(5)) % 30));
                    temp = new Cube(j,a,b,c,d,e,f);
                    cubes.add(temp);
                }
                break;

            default:
                double irr = Math.sqrt(1);
                for(int j = 0; j < 30; j+=1)
                {
                    n = j*6;
                    a = 1 + ((int)(Math.floor((n+0)*irr) % 30));
                    b = 1 + ((int)(Math.floor((n+1)*irr) % 30));
                    c = 1 + ((int)(Math.floor((n+2)*irr) % 30));
                    d = 1 + ((int)(Math.floor((n+3)*irr) % 30));
                    e = 1 + ((int)(Math.floor((n+4)*irr) % 30));
                    f = 1 + ((int)(Math.floor((n+5)*irr) % 30));
                    temp = new Cube(j,a,b,c,d,e,f);
                    cubes.add(temp);
                }
                break;

        }

    }

    public Cubes()
    {

    }

    ///////////////////////////// Methods //////////////////////////////////////

    //checks if node at i and j is a conflict to string s, where s can be 0 or 1
    private boolean nodeConflict(int i, int j, int s)
    {
        int x = cubes.get(i).faces[j*2];     //face 1 of pair
        int y = cubes.get(i).faces[j*2+1];   //face 2 of pair
        int xCount = 1;
        int yCount = 1;
        int x2, y2;

        if(x == y)
        {
            xCount +=1;
            yCount +=1;
        }

        ArrayList<Integer> stringX;

        if(s == 0)
            stringX = string1;
        else
        {
            stringX = string2;
            //If node already used in string 1
            if( string1.get(i) == j )
                return true;
        }

        for(int k = 0; k < stringX.size(); k+=1)
        {
            x2 = cubes.get(k).faces[stringX.get(k)*2];
            y2 = cubes.get(k).faces[stringX.get(k)*2+1];


            if(x2 == x)
                xCount +=1;
            if(y2 == x)
                xCount +=1;
            if(x2 == y)
                yCount +=1;
            if(y2 == y)
               yCount +=1;

            if(xCount >= 3 || yCount >= 3)
              return true;
        }

        return false;
    }

    //depth first search algorithm.
    public boolean findSolution()
    {
        int j = 0;
        //s is string 0 or string 1
        for (int s = 0; s < 2; s += 1)
        {
            //loop through all cubes
            for (int i = 0; i < cubes.size(); i += 1)
            {
                //build strings 0 and 1
                if( j <= 2 && i >= 0 )
                {
                    if( !nodeConflict(i,j,s) )
                    {
                        addNode(j,s);
                        j = 0;
                    }
                    else
                    {
                        j += 1;
                        i -= 1;
                    }
                }
                else
                {
                    if (lastNodePositionAtJ(s) != -1)
                        j = lastNodePositionAtJ(s) + 1;
                    else
                        return false;

                    popNode(s);
                    i -= 2;
                }
            }
        }
        return true;
    }

    //returns all subsets of size size
    public ArrayList<Cubes> getSubsets(int size)
    {
        int n = this.cubes.size();
        Cubes temp;
        ArrayList<Cubes> subsets = new ArrayList<Cubes>();

        for (int i = 0; i < (1<<n); i++)
        {
            temp = new Cubes() ;
            for (int j = 0; j < n; j++)
            {
                if ((i & (1 << j)) > 0)
                    temp.add( this.cubes.get(j) );

            }

            if(temp.size() == size)
                subsets.add(temp);
        }
        return subsets;

    }

    //returns the index of that node above in string s
    private int lastNodePositionAtJ(int s)
    {
        if(string1.size() == 0 || string2.size() == 0)
            return -1;

        if(s == 0)
            return string1.get(string1.size() - 1);
        else
            return string2.get(string2.size() - 1);
    }

    //Methods that add or remove from this.string1 or 2
    private void addNode(int j, int s)
    {
        if(s == 0)
            string1.add(j);
        else
            string2.add(j);
    }

    private void popNode(int s)
    {
        if(s == 0)
            string1.remove(string1.size()-1);
        else
            string2.remove(string2.size()-1);
    }

    //Some simplier methods that affect this.cubes
    public void remove(int i)
    {
        cubes.remove(i);
    }

    public int size()
    {
        return cubes.size();
    }

    public Cube get(int i)
    {
        return cubes.get(i);
    }

    public void add(int i, Cube c)
    {
        cubes.add(i, c);
    }

    public void add(Cube c)
    {
        cubes.add(c);
    }

    //Some methods for printing
    @Override
    public String toString()
    {
        String result = "";
        for(Cube cube : cubes)
        {
            result += cube.toString() + "\n";
        }
        return result;
    }

    public String strings()
    {
        String result = "";
        for(int i = 0; i < string1.size(); i+=1)
        {
            result += cubes.get(i).getPair(string1.get(i)) +
                "   " +
                cubes.get(i).getPair(string2.get(i)) +
                "\n";
        }
        return result;
    }

    public String string(int s)
    {
        ArrayList<Integer> stringX;
        String result = "";
        if(s == 0)
            stringX = string1;
        else
            stringX = string2;

        for( int i : stringX)
            result += i + " ";

        return result;
    }
}
