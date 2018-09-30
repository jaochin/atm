package atm;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AtmTest {
    @Test
    public void testDecrease() throws Exception {
        BankNote bn = new BankNote(50, 10);
        bn.decrease(3);

        assertThat(bn.getBankNoteAmt(), is(7L));
    }

    @Test
    public void testIncrease() throws Exception {
        BankNote bn = new BankNote(50, 10);
        bn.increase(3);

        assertThat(bn.getBankNoteAmt(), is(13L));
    }

    @Test
    public void testCheckEnough() throws Exception {
        BankNote bn = new BankNote(50, 10);
        long number = bn.checkEnough(600);

        assertThat(number, is(10L));
    }

    @Test
    public void TestDecreaseWithdrawAmt() throws Exception {
        BankNote bn = new BankNote(50, 10);
        long number = bn.decreaseWithdrawAmt(500,2);

        assertThat(number, is(400L));
    }

    @Test
    public void TestIncreaseWithdrawAmt() throws Exception {
        BankNote bn = new BankNote(50, 10);
        long number = bn.increaseWithdrawAmt(500,2);

        assertThat(number, is(600L));
    }
}
