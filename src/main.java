
import java.math.BigDecimal;
import java.lang.Math;

public class main {
    private static BigDecimal[] wt0 = {new BigDecimal("6"), new BigDecimal("3"), new BigDecimal("-2")};
    private static BigDecimal[] wt1 = {new BigDecimal("4.6"), new BigDecimal("1"), new BigDecimal("-2.2")};
    private static BigDecimal[] wt2 = {new BigDecimal("1"), new BigDecimal("-1"), new BigDecimal("-2")};

    private static BigDecimal[] regression(BigDecimal[][] x, BigDecimal[] wt) {
        BigDecimal[] f = new BigDecimal[x.length];
        for (int i = 0; i < x.length; i++) {
            f[i] = new BigDecimal("0.0");
            for (int j = 0; j < wt.length; j++) {
                f[i] = f[i].add(wt[j].multiply(x[i][j]));
            }
        }
        return f;
    }

    private static BigDecimal[] sigmoid(BigDecimal[] f) {
        BigDecimal[] F = new BigDecimal[f.length];
        BigDecimal one = new BigDecimal("1");
        for (int i = 0; i < f.length; i++) {
            String E = String.valueOf(Math.exp(f[i].doubleValue()));
            BigDecimal e = new BigDecimal(E);
            F[i] = one.divide(one.add(e), 10, BigDecimal.ROUND_HALF_UP);
        }
        return F;
    }

    private static BigDecimal likelihood(BigDecimal[] t, BigDecimal[] c) {
        BigDecimal L = new BigDecimal("0");
        BigDecimal one = new BigDecimal("1");
        for (int n = 0; n < t.length; n++) {
            String t1 = String.valueOf(Math.log(t[n].doubleValue()));
            BigDecimal T1 = new BigDecimal(t1);
            String t2 = String.valueOf(Math.log((one.subtract(t[n])).doubleValue()));
            BigDecimal T2 = new BigDecimal(t2);
            L = L.add((c[n].multiply(T1)).add((one.subtract(c[n])).multiply(T2)));
        }
        BigDecimal E = L.negate();

        return E;
    }

    private static void display(BigDecimal[] f) {
        for (int i = 0; i < f.length; i++) {
            System.out.print(f[i] + " \t");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        BigDecimal[][] x = new BigDecimal[10][3];
        BigDecimal[] c = {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"),
                new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("0"), new BigDecimal("1"),
                new BigDecimal("1"), new BigDecimal("1"), new BigDecimal("1"), new BigDecimal("1")};
        for (int i = 0; i < x.length; i++) {
            x[i][0] = new BigDecimal("1.0");
        }
        x[0][1] = new BigDecimal("-0.5");
        x[0][2] = new BigDecimal("-1.0");
        x[1][1] = new BigDecimal("-0.5");
        x[1][2] = new BigDecimal("-0.5");
        x[2][1] = new BigDecimal("0.0");
        x[2][2] = new BigDecimal("-1.0");
        x[3][1] = new BigDecimal("0.0");
        x[3][2] = new BigDecimal("0.5");
        x[4][1] = new BigDecimal("0.5");
        x[4][2] = new BigDecimal("0.0");
        x[5][1] = new BigDecimal("1.0");
        x[5][2] = new BigDecimal("-2.5");
        x[6][1] = new BigDecimal("1.0");
        x[6][2] = new BigDecimal("-1.0");
        x[7][1] = new BigDecimal("1.5");
        x[7][2] = new BigDecimal("-2.0");
        x[8][1] = new BigDecimal("1.5");
        x[8][2] = new BigDecimal("-1.0");
        x[9][1] = new BigDecimal("1.5");
        x[9][2] = new BigDecimal("-0.5");


        BigDecimal[] f0 = regression(x, wt0);
        BigDecimal[] f1 = regression(x, wt1);
        BigDecimal[] f2 = regression(x, wt2);
        System.out.println("\nf0：");
        display(f0);
        System.out.println("\nf1：");
        display(f1);
        System.out.println("\nf2：");
        display(f2);

        BigDecimal[] F0 = sigmoid(f0);
        BigDecimal[] F1 = sigmoid(f1);
        BigDecimal[] F2 = sigmoid(f2);
        System.out.println("\nF0：");
        display(F0);
        System.out.println("\nF1：");
        display(F1);
        System.out.println("\nF2：");
        display(F2);

        BigDecimal E0 = likelihood(F0, c);
        BigDecimal E1 = likelihood(F1, c);
        BigDecimal E2 = likelihood(F2, c);
        System.out.println("\nE(w_1) = " + E0);
        System.out.println("\nE(w_2) = " + E1);
        System.out.println("\nE(w_3) = " + E2);
    }
}
