package atm;

import java.util.Scanner;

public class Atm {
    public static void main (String[] args) {

        BankNote bn20   = new BankNote(20,   1000L);
        BankNote bn50   = new BankNote(50,   1000L);
        BankNote bn100  = new BankNote(100,  1000L);
        BankNote bn500  = new BankNote(500,  1000L);
        BankNote bn1000 = new BankNote(1000, 1000L);

        long withdrawAmt = 0;
        long initWithdrawAmt = 0;

        long initTotAmt = ( bn20.getBankNoteId()   * bn20.getBankNoteAmt() )
                + ( bn50.getBankNoteId()   * bn50.getBankNoteAmt() )
                + ( bn100.getBankNoteId()  * bn100.getBankNoteAmt() )
                + ( bn500.getBankNoteId()  * bn500.getBankNoteAmt() )
                + ( bn1000.getBankNoteId() * bn1000.getBankNoteAmt() );
        long currTotAmt = initTotAmt;

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        while( bn1000.getBankNoteAmt()    > 0
                || bn500.getBankNoteAmt() > 0
                || bn100.getBankNoteAmt() > 0
                || bn50.getBankNoteAmt()  > 0
                || bn20.getBankNoteAmt()  > 0) {
            System.out.println("Current amount: " + currTotAmt);
            do {
                System.out.print("Enter an amount: ");
                while(!reader.hasNextLong()) {
                    String input = reader.next();
                    System.out.printf("\"%s\" is not a valid number.\n", input);
                    System.out.print("Enter an amount: ");
                }
                initWithdrawAmt = reader.nextLong();
            } while (initWithdrawAmt <= 0);
            System.out.println("");

            withdrawAmt = initWithdrawAmt;

            if( currTotAmt < withdrawAmt ) {
                System.out.println("The System amount = $" +  currTotAmt + " is less than withdraw amount = $" + withdrawAmt);
                System.out.println("");
                System.out.println("=================================================");
                continue;
            }

            long dispense1000 = 0;
            long dispense500  = 0;
            long dispense100  = 0;
            long dispense50   = 0;
            long dispense20   = 0;

            if( withdrawAmt >= bn1000.getBankNoteId() && bn1000.getBankNoteAmt() > 0 ) {
                dispense1000 = bn1000.checkEnough(withdrawAmt);
                bn1000.decrease(dispense1000);
                withdrawAmt = bn1000.decreaseWithdrawAmt(withdrawAmt, dispense1000);
            }

            if( withdrawAmt >= bn500.getBankNoteId() && bn500.getBankNoteAmt() > 0 ) {
                dispense500 = bn500.checkEnough(withdrawAmt);
                bn500.decrease(dispense500);
                withdrawAmt = bn500.decreaseWithdrawAmt(withdrawAmt, dispense500);
            }

            if (withdrawAmt >= bn100.getBankNoteId() && bn100.getBankNoteAmt() > 0) {
                dispense100 = bn100.checkEnough(withdrawAmt);
                bn100.decrease(dispense100);
                withdrawAmt = bn100.decreaseWithdrawAmt(withdrawAmt, dispense100);
            }

            if (withdrawAmt >= bn50.getBankNoteId() && bn50.getBankNoteAmt() > 0) {
                dispense50 = bn50.checkEnough(withdrawAmt);
                bn50.decrease(dispense50);
                withdrawAmt = bn50.decreaseWithdrawAmt(withdrawAmt, dispense50);

                // if an amount after using $50 isn't compat to $20, skip to use $20 instead
                if (withdrawAmt % bn20.getBankNoteId() != 0) {
                    bn50.increase(dispense50);
                    withdrawAmt = bn50.increaseWithdrawAmt(withdrawAmt, dispense50);
                    dispense50 = 0;
                }
            }

            if (withdrawAmt >= bn20.getBankNoteId() && bn20.getBankNoteAmt() > 0) {
                dispense20 = bn20.checkEnough(withdrawAmt);
                bn20.decrease(dispense20);
                withdrawAmt = bn20.decreaseWithdrawAmt(withdrawAmt, dispense20);
            }

            // Cannot dispense
            if (withdrawAmt > 0) {
                if (dispense1000 > 0) {
                    bn1000.increase(dispense1000);
                }
                if (dispense500 > 0) {
                    bn500.increase(dispense500);
                }
                if (dispense100 > 0) {
                    bn100.increase(dispense100);
                }
                if (dispense50 > 0) {
                    bn50.increase(dispense50);
                }
                if (dispense20 > 0) {
                    bn20.increase(dispense20);
                }

                //System.out.println("Withdraw amount $" + withdrawAmt + ".");
                System.out.println("The banknotes are not compatibility to dispense $" + initWithdrawAmt + ".");
                System.out.println("");
            }
            // able to dispense
            else {
                currTotAmt = currTotAmt - initWithdrawAmt;

                if (dispense1000 > 0) {
                    System.out.println("Dispense $1000 = " + dispense1000 + " note(s)");
                }
                if (dispense500 > 0) {
                    System.out.println("Dispense $500  = " + dispense500 + " note(s)");
                }
                if (dispense100 > 0) {
                    System.out.println("Dispense $100  = " + dispense100 + " note(s)");
                }
                if (dispense50 > 0) {
                    System.out.println("Dispense $50   = " + dispense50 + " note(s)");
                }
                if (dispense20 > 0) {
                    System.out.println("Dispense $20   = " + dispense20 + " note(s)");
                }
                System.out.println("");

                System.out.println("Available note(s)");
                System.out.println("$1000 = " + bn1000.getBankNoteAmt());
                System.out.println("$500  = " + bn500.getBankNoteAmt());
                System.out.println("$100  = " + bn100.getBankNoteAmt());
                System.out.println("$50   = " + bn50.getBankNoteAmt());
                System.out.println("$20   = " + bn20.getBankNoteAmt());
                System.out.println("");
            }

            System.out.println("=================================================");
        }

        reader.close();
    }
}
