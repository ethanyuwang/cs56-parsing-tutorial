package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import static edu.ucsb.cs56.pconrad.parsing.DefaultInterpreterInterface.DEFAULT;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/** 
    Go from a string to a Token [] 
*/

public class TestArithmeticTokenizer {
    public static Token[] tokenize(final String input)
	throws TokenizerException {
	return DEFAULT.tokenize(input).toArray(new Token[0]);
    }
    
    public static Token[] tokenizeNoException(final String input) {
        Token[] retval = null;
        try {
	    retval = tokenize(input);
        } catch (TokenizerException e) {
            fail("Unexpected tokenizer exception: " + e.toString());
        }

        assert(retval != null);
        return retval;
    }

    @Test
    public void testOneToken() {
        assertArrayEquals(new Token[] { new CharToken('+') },
                          tokenizeNoException("+"));
    }

    @Test
    public void testTwoSameTokens() {
        assertArrayEquals(new Token[] { new CharToken('+'),
                                        new CharToken('+') },
                          tokenizeNoException("++"));
    }

    @Test
    public void testSingleWhitespace() {
        assertArrayEquals(new Token[] { },
                          tokenizeNoException(" "));
    }

    @Test
    public void testSingleDigit() {
        assertArrayEquals(new Token[] { new IntToken(1) },
                          tokenizeNoException("1"));
    }

    @Test
    public void testTwoDigit() {
        assertArrayEquals(new Token[] { new IntToken(12) },
                          tokenizeNoException("12"));
    }

    // BEGIN TESTS FOR INVALID INPUTS
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testInvalidSingleToken()
        throws TokenizerException {
        thrown.expect(TokenizerException.class);
        tokenize("c");
    }
} // TestArithmeticTokenizer
