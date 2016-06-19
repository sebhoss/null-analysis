package de.xn__ho_hia.quality.null_analysis;

import java.math.BigInteger;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Utility class to work with @NonNull match operations.
 */
public final class NullsafeMath {

    /**
     * @param value
     *            The value to wrap
     * @return A @NonNull {@link BigInteger}
     */
    public static @NonNull BigInteger asBigInteger(final long value) {
        return Nullsafe.nonNull(BigInteger.valueOf(value));
    }

    /**
     * @param value
     *            The value to wrap
     * @return A @NonNull {@link Long}
     */
    public static @NonNull Long asLong(final long value) {
        return Nullsafe.nonNull(Long.valueOf(value));
    }

    /**
     * @param first
     *            The first value to add
     * @param second
     *            The second value to add
     * @return The sum of both values as a @NonNull {@link BigInteger}
     */
    public static @NonNull BigInteger addNullsafe(final BigInteger first, final BigInteger second) {
        return Nullsafe.nonNull(first.add(second));
    }

    /**
     * @param first
     *            The first value to subtract
     * @param second
     *            The second value to subtract
     * @return The difference of both values as a @NonNull {@link BigInteger}
     */
    public static @NonNull BigInteger subtractNullsafe(final BigInteger first, final BigInteger second) {
        return Nullsafe.nonNull(first.subtract(second));
    }

    /**
     * @param first
     *            The first value to divide
     * @param second
     *            The second value to divide
     * @return The division of both values as a @NonNull {@link BigInteger}
     */
    public static @NonNull BigInteger divideNullsafe(final BigInteger first, final BigInteger second) {
        return Nullsafe.nonNull(first.divide(second));
    }

    /**
     * @param first
     *            The first value to multiply
     * @param second
     *            The second value to multiply
     * @return The multiplication of both values as a @NonNull {@link BigInteger}
     */
    public static @NonNull BigInteger multiplyNullsafe(final BigInteger first, final BigInteger second) {
        return Nullsafe.nonNull(first.multiply(second));
    }

}
