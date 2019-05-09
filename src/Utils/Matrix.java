package Utils;

import java.io.FileReader;
import java.util.Scanner;

public class Matrix {
    private int n;
    private int m;
    private int mas[][];

    public Matrix() {
        this.n = 3;
        this.m = 3;
        this.mas = new int[n][];
        for(int i = 0; i < m; i++) {
            this.mas[i] = new int[m];
        }
    }

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.mas = new int[n][];
        for(int i = 0; i < this.n; i++) {
            this.mas[i] = new int[this.m];
        }
    }

    public int getN() {
        return this.n;
    }

    public int getM() {
        return this.m;
    }

    public int getIndex(int i, int j) {
        return this.mas[i][j];
    }

    public void setIndex(int i, int j, int value) throws Exception {
        if(i > this.n || j > this.m || i < 0 || j < 0) { throw new Exception("Indexes are out of range."); }
        this.mas[i][j] = value;
    }

    public void add(Matrix b) throws Exception {
        if(this.n != b.getN() || this.m != b.getM()) { throw new Exception("Matrixes have different size."); }

        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.m; j++) {
                this.mas[i][j] += b.getIndex(i, j);
            }
        }
    }

    public void sub(Matrix b) throws Exception {
        if(this.n != b.getN() || this.m != b.getM()) { throw new Exception("Matrixes have different size."); }

        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.m; j++) {
                this.mas[i][j] -= b.getIndex(i, j);
            }
        }
    }

    public void mul(Matrix b) throws Exception {
        if(this.n != b.getM() || this.m != b.getN()) { throw new Exception("Matrixes have incompatible size."); }

        int sum;
        Matrix a = this.clone();

        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.m; j++) {
                sum = 0;
                for (int k = 0; k < this.n; k++)  sum += a.getIndex(i,k) * b.getIndex(k,j);
                this.mas[i][j] = sum;
            }
        }
    }

    public Matrix clone() {
        Matrix a = new Matrix(this.n, this.m);

        try {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++) {
                    a.setIndex(i, j, this.mas[i][j]);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return a;
    }

    public void fillRandom(int min, int max) {
        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.m; j++) {
                this.mas[i][j] = (int) Math.round( Math.random() * (max - min) + min);
            }
        }
    }

    public void fillFromFile(String path) throws Exception {
        try {
            Scanner in = new Scanner(new FileReader(path));

            for(int i = 0; i < this.n; i++) {
                for(int j = 0; j < this.m; j++) {
                    if(!in.hasNextInt()) { throw new Exception("There are not enough elements in the file."); }
                    this.mas[i][j] = in.nextInt();
                }
            }
        }
        catch(Exception e) {}
    }

    public void show() {
        //System.out.println("Массив: ");

        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.m; j++) {
                System.out.print(this.mas[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
