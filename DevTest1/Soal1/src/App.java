import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        String hasil = "";
        String inputParameter = "";
        String errmessage = "Input hanya bisa angka 1-100";

        Scanner sc = new Scanner(System.in);
        System.out.println("Input Parameter:");
        inputParameter = sc.nextLine();
        System.out.println("Hasil input: " + inputParameter);

        try {
            int inputParameterInt = Integer.parseInt(inputParameter);
            if (inputParameterInt > 0 && inputParameterInt <= 100) {

                for (int i = inputParameterInt; i > 0; i--) { // reverse looping for getting max number
                    for (int j = 1; j <= inputParameterInt; j++) { // normal looping
                        if (j >= i) // check if normal looping = max number from reverse looping
                        {
                            hasil += "#"; // if true, print hashtag
                        } else {
                            hasil += " "; // if false, print whitespace
                        }
                    }
                    hasil += '\n'; // print newline
                }
                System.out.println(hasil); // print hasil
            } else {
                System.out.println(errmessage);
            }
        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
