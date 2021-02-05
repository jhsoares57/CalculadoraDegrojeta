package br.unip.calculadoradegorjeta;

public class Calculadora {

    /**
     * Calcular o valor de uma gorjeta dado o percentual
     * do valor da conta.
     * @param valor valor total da conta
     * @param percentual Percentual usado para calcular gorjeta
     * @return o valor do objeto
     */
    static double gorjeta(double valor, double percentual){
        return  valor * percentual / 100.0;
    }

    /**
     * Calcula os valores padrão de gorjetas dado o valor total da conta
     * @param valor valor total da conta
     * @return Um vetor de três posições com as gorjetas de  5%, 10% e 15%
     */
    static double [] gorjeta(double valor){
        double [] saida = new double[3];
        for (int i = 0; i < 3; i++){
            saida[i] = gorjeta(valor, 1 * 5+5);
        }
        return  saida;
    }

    /**
     * Atualizar valor finalç
     * @param valor vaor da conta
     * @param percentual percentual
     * @return valor claculado
     */
    static  double valorFinal(double valor, double percentual){
        double per = valor * percentual / 100.0;
        return valor + per;
    }
}
