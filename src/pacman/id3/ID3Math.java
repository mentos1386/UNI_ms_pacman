package pacman.id3;

public class ID3Math {

    public static double logb( double a, double b )
    {
        return Math.log(a) / Math.log(b);
    }

    public static double log2( double a )
    {
        if(a==0.0) {
            return 0;
        }
        return logb(a,2);
    }

}
