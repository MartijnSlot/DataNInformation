package nl.utwente.di.bookQuote;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** *  Tests the Quoter */
public class TestQuoter {

    Quoter quoter;

    @Before
    public void init() {
        quoter = new Quoter();
        quoter.fillMap();
    }

    @Test
    public void testBook1() throws Exception {
        double price = quoter.getBookPrice("1");
        Assert.assertEquals("Price of book 1", 10.0, price, 00.0);
    }
}