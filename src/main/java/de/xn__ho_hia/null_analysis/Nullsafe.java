/*
 * Copyright © 2013 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
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
