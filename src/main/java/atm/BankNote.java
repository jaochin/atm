package atm;

public class BankNote {
    private int bankNoteId;
    private long bankNoteAmt;

    public BankNote(int id, long amt){
        this.bankNoteId = id;
        this.bankNoteAmt = amt;
    }

    public int getBankNoteId() {
        return bankNoteId;
    }

    public void setBankNoteId(int bankNoteId) {
        this.bankNoteId = bankNoteId;
    }

    public long getBankNoteAmt() {
        return bankNoteAmt;
    }

    public void setBankNoteAmt(long bankNoteAmt) {
        this.bankNoteAmt = bankNoteAmt;
    }

    public void decrease(long dispense) {
        this.bankNoteAmt = this.bankNoteAmt - dispense;
    }

    public void increase(long dispense) {
        this.bankNoteAmt = this.bankNoteAmt + dispense;
    }

    public long checkEnough(long withdrawAmt) {
        long dispense = withdrawAmt / this.getBankNoteId();

        if( dispense > this.getBankNoteAmt() ) {
            dispense = this.getBankNoteAmt();
        }

        return dispense;
    }

    public long decreaseWithdrawAmt(long withdrawAmt, long dispense) {
        return withdrawAmt - (dispense * this.getBankNoteId());
    }

    public long increaseWithdrawAmt(long withdrawAmt, long dispense) {
        return withdrawAmt + (dispense * this.getBankNoteId());
    }
}
