/*
 * This file is part of null-analysis. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of null-analysis,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.null_analysis;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Utility classes which helps working with legacy (nullable) APIs.
 */
public final class Nullsafe {

    private Nullsafe() {
        // utility class
    }

    /**
     * @param <T>
     *            The type of the reference
     * @param reference
     *            A possible <code>null</code> reference.
     * @return Either the reference itself, or an {@link NullPointerException}, in case the reference was
     *         <code>null</code>.
     */
    public static <T> @NonNull T nullsafe(@Nullable final T reference) {
        if (reference != null) {
            return reference;
        }

        throw new NullPointerException(); // NOPMD - we want to throw NPE here
    }

    /**
     * @param <T>
     *            The type of the reference
     * @param reference
     *            A possible <code>null</code> reference.
     * @param message
     *            The exception message to throw.
     * @return Either the reference itself, or an {@link NullPointerException}, in case the reference was
     *         <code>null</code>.
     */
    public static <T> @NonNull T nullsafe(@Nullable final T reference, final String message) {
        if (reference != null) {
            return reference;
        }

        throw new NullPointerException(message); // NOPMD - we want to throw NPE here
    }

}
