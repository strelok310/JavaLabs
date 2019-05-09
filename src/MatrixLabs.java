import Utils.Matrix;

public class MatrixLabs {
    public static void main(String args[]) throws Exception {
        Matrix a = new Matrix();
        Matrix b = new Matrix();
        Matrix c;

        //a.fillRandom(0, 9);
        //b.fillRandom(0, 9);

        try {
            a.fillFromFile("matrix_A.txt");
            b.fillFromFile("matrix_B.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("A: ");
        a.show();
        System.out.println("B: ");
        b.show();

        try {
            a.add(b);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Результат сложения: ");
        a.show();

        try {
            a.sub(b);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Результат вычитания: ");
        a.show();

        try {
            a.mul(b);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Результат умножения: ");
        a.show();
    }
}
