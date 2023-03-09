import java.util.Arrays;

public class Encryptor {
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c) {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock() {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str) {
        /* to be implemented in part (a) */
        int counter = 0;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (counter < str.length()) {
                    letterBlock[r][c] = String.valueOf(str.charAt(counter));
                } else {
                    letterBlock[r][c] = "A";
                }
                counter++;
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock() {
        /* to be implemented in part (b) */
        String temp = "";
        for (int c = 0; c < numCols; c++) {
            for (int r = 0; r < numRows; r++) {
                temp += letterBlock[r][c];
            }
        }
        return temp;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message) {
        /* to be implemented in part (c) */
        int start = 0;
        String temp = "";
        while (start < message.length()){
            fillBlock(message.substring(start));
            temp += encryptBlock();
            start += numCols * numRows;
        }
        return temp;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage) {
        /* to be implemented in part (d) */
        int start = 0;
        String temp = "";
        while (start < encryptedMessage.length()){
            fillBlockAlt(encryptedMessage.substring(start));
            temp += decryptBlock();
            start += numCols * numRows;
        }
        while (String.valueOf(temp.charAt(temp.length()-1)).equals("A")){
            temp = temp.substring(0,temp.length()-1);
        }
        return temp;
    }

    /** Places a string into letterBlock in column-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlockAlt(String str) {
        int counter = 0;
        for (int c = 0; c < numCols; c++) {
            for (int r = 0; r < numRows; r++) {
                if (counter < str.length()) {
                    letterBlock[r][c] = String.valueOf(str.charAt(counter));
                } else {
                    letterBlock[r][c] = "A";
                }
                counter++;
            }
        }
    }

    /** Extracts decrypted string from letterBlock in row-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the decrypted string from letterBlock
     */
    public String decryptBlock() {
        String temp = "";
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                temp += letterBlock[r][c];
            }
        }
        return temp;
    }

    /** Shifts the top row to the bottom 2 times
     *
     *   Precondition: letterBlock has been filled
     */
    public void flipRow(){
        for (int r = 0; r < 2; r++) {
            String temp[] = new String[numCols];
            for (int c = 0; c < numCols; c++) {
                temp[c] = letterBlock[r][c];
            }
            for (int i = 0; i < numRows-1; i++) {
                for (int j = 0; j < numCols; j++) {
                    letterBlock[i][j] = letterBlock[i+1][j];
                }
            }
            for (int l = 0; l < numCols; l++) {
                letterBlock[numRows-1][l] = temp[l];
            }
        }
    }
    /** Shifts the leftmost column to the rightmost 2 times
     *
     *   Precondition: letterBlock has been filled
     */
    public void flipCol(){
        for (int c = 0; c < 2; c++) {
            String temp[] = new String[numCols];
            for (int c = 0; c < numCols; c++) {
                temp[c] = letterBlock[r][c];
            }
            for (int i = 0; i < numRows-1; i++) {
                for (int j = 0; j < numCols; j++) {
                    letterBlock[i][j] = letterBlock[i+1][j];
                }
            }
            for (int l = 0; l < numCols; l++) {
                letterBlock[numRows-1][l] = temp[l];
            }
        }
    }
}
