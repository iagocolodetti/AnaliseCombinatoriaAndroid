package iagocolodetti.analisecombinatoria;

import java.math.BigInteger;

public class Funcoes {
    public boolean intTryParse(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public BigInteger fatorial(int n) {
        if (n < 0) return BigInteger.ZERO;
        else if (n == 0) return BigInteger.ONE;
        else if (n == 1) return BigInteger.valueOf(n);
        else return BigInteger.valueOf(n).multiply(fatorial(n - 1));
    }

    public BigInteger permutacaoSimples(int n) {
        return fatorial(n);
    }

    public BigInteger permutacaoRepeticao(int n, int[] p) {
        int s = 0;
        for(int i = 0; i < p.length; i++) s += p[i];
        if(n < s) return BigInteger.ZERO;
        BigInteger resultado, pp = BigInteger.ONE;
        for (int i = 0; i < p.length; i++) pp = pp.multiply(fatorial(p[i]));
        resultado = fatorial(n).divide(pp);
        if (resultado.compareTo(BigInteger.ZERO) == -1) resultado = BigInteger.ZERO;
        return resultado;
    }

    public BigInteger arranjoSimples(int n, int p) {
        if(n < p) return BigInteger.ZERO;
        BigInteger resultado = fatorial(n).divide(fatorial(n - p));
        if (resultado.compareTo(BigInteger.ZERO) == -1) resultado = BigInteger.ZERO;
        return resultado;
    }

    public BigInteger arranjoRepeticao(int n, int p) {
        if (p < 0) return BigInteger.ZERO;
        else if (p == 0) return BigInteger.ONE;
        else if (p == 1) return BigInteger.valueOf(n);
        else return (BigInteger.valueOf(n).multiply(arranjoRepeticao(n, p - 1)));
    }

    public BigInteger combinacaoSimples(int n, int p) {
        if(n < p) return BigInteger.ZERO;
        BigInteger resultado = (fatorial(n).divide(fatorial(n - p).multiply(fatorial(p))));
        if (resultado.compareTo(BigInteger.ZERO) == -1) resultado = BigInteger.ZERO;
        return resultado;
    }

    public BigInteger combinacaoRepeticao(int n, int p) {
        BigInteger resultado = (fatorial(n + p - 1).divide(fatorial(n - 1).multiply(fatorial(p))));
        if (resultado.compareTo(BigInteger.ZERO) == -1) resultado = BigInteger.ZERO;
        return resultado;
    }
}
