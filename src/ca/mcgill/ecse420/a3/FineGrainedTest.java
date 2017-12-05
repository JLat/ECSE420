package ca.mcgill.ecse420.a3;

public class FineGrainedTest {
    public static void main (String [] args){
        FineGrained fg = new FineGrained();

        fg.add(new Integer(1));
        fg.add(new Integer(2));
        Integer test = new Integer(3);
        fg.add(test);

        String out = fg.contain(test) ? "YES" : "NO";
        System.out.println(out);
    }
}
