package expression.generic;

import java.math.BigInteger;
import expression.exceptions.DivisionByZero;

public class BigIntegerType implements Operation<BigInteger> {
    @Override public BigInteger add(BigInteger x, BigInteger y) { return x.add(y); }
    @Override public BigInteger subtract(BigInteger x, BigInteger y) { return x.subtract(y); }
    @Override public BigInteger multiply(BigInteger x, BigInteger y) { return x.multiply(y); }
    @Override public BigInteger divide(BigInteger x, BigInteger y) {return x.divide(y);}
    @Override public BigInteger negate(BigInteger x) { return x.negate(); }

    @Override public BigInteger min(BigInteger x, BigInteger y) { return x.min(y); }
    @Override public BigInteger max(BigInteger x, BigInteger y) { return x.max(y); }
    @Override public BigInteger parseNumber(String n) { return new BigInteger(n); }
    @Override public BigInteger valueOf(int n) { return BigInteger.valueOf(n); }
}