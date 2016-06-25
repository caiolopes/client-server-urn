package client.util;

public class Validator {
    private static final int[] cpfWeight = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int digitCalculator(String str, int[] weight) {
        int sum = 0;

        for (int index = str.length()-1, digit; index >= 0; index-- ) {
            digit = Integer.parseInt(str.substring(index,index+1));
            sum += digit * weight[weight.length - str.length()+index];
        }

        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public static boolean isValidCPF(String cpf) {
        if (!cpf.contains("[a-zA-Z]+") && cpf.length() != 11)
            return false;

        Integer digit1 = digitCalculator(cpf.substring(0,9), cpfWeight);
        Integer digit2 = digitCalculator(cpf.substring(0,9) + digit1, cpfWeight);

        return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
    }
}
