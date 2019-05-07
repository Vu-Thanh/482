public class Cube
{
    public int[] faces;
    int index;

    //Constructor
    public Cube(int i, int a, int b, int c, int d, int e, int f)
    {
        index = i+1;
        faces = new int[6];
        faces[0] = a;
        faces[1] = b;
        faces[2] = c;
        faces[3] = d;
        faces[4] = e;
        faces[5] = f;
    }

    //Methods
    
    //returns a string pair of the pair at i
    public String getPair(int i)
    {
        return faces[i*2] + "-" +
                faces[i*2 +1];
    }

    //returns a string to print all pairs and index
    @Override
    public String toString()
    {
        return String.format("%2d   %2s-%2s   %2s-%2s   %2s-%2s",
                index,
                faces[0],
                faces[1],
                faces[2],
                faces[3],
                faces[4],
                faces[5]);
    }

}
