import java.util.Scanner;
public class EncryptorRunner {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        System.out.println("How many rows?");
        int r = Integer.parseInt(user.nextLine());
        System.out.println("How many columns?");
        int c = Integer.parseInt(user.nextLine());
        Encryptor test = new Encryptor(r,c);

        System.out.println("(E)ncrypt or (D)ecrypt?");
        String a = user.nextLine();
        System.out.println("What is the message?");
        String m = user.nextLine();
        if (a.equalsIgnoreCase("e")){
            System.out.println(test.superEncryptor(m,2,2));
        } else {
            System.out.println(test.superDecryptor(m,2,2));
        }
    }
}
