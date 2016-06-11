/*
 * This file is part of null-analysis. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of null-analysis,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.quality.null_analysis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

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
     * Converts a {@link Nullable} reference into a {@link NonNull} reference. Performs a strict <code>null</code> check
     * that fails in case a <code>null</code> reference is given.
     *
     * @param <TYPE>
     *            The type of the reference
     * @param reference
     *            A possible <code>null</code> reference.
     * @return Either the reference itself, or an {@link NullPointerException}, in case the reference was
     *         <code>null</code>.
     */
    public static <TYPE> @NonNull TYPE nonNull(@Nullable final TYPE reference) {
        return nonNull(reference, "Got unexpected NULL reference"); //$NON-NLS-1$
    }

    /**
     * Converts a {@link Nullable} reference into a {@link NonNull} reference. Performs a strict <code>null</code> check
     * that fails in case a <code>null</code> reference is given.
     *
     * @param <TYPE>
     *            The type of the reference
     * @param reference
     *            A possible <code>null</code> reference.
     * @param message
     *            The exception message to throw.
     * @return Either the reference itself, or an {@link NullPointerException}, in case the reference was
     *         <code>null</code>.
     */
    public static <TYPE> @NonNull TYPE nonNull(@Nullable final TYPE reference, final String message) {
        if (reference != null) {
            return reference;
        }

        throw new IllegalArgumentException(message);
    }

    /**
     * Safely creates a {@link Stream stream} of a nullable {@link Collection collection}. Falls back to an empty stream
     * in case the collection is <code>null</code>.
     *
     * @param <TYPE>
     *            The collection type
     * @param collection
     *            A collection or <code>null</code>
     * @return A stream of the given collection or empty stream
     */
    public static <TYPE> @NonNull Stream<TYPE> safelyStream(@Nullable final Collection<TYPE> collection) {
        return collection != null ? nonNull(collection.stream()) : nonNull(Stream.<TYPE> empty());
    }

    /**
     * Ensures that callers of this method can always work with a {@link NonNull} {@link List list}. Falls back to an
     * empty list in case the given list is <code>null</code>.
     *
     * @param <TYPE>
     *            The list type
     * @param list
     *            A list or <code>null</code>
     * @return The given list or an empty list
     */
    public static <TYPE> @NonNull List<TYPE> ensureList(@Nullable final List<TYPE> list) {
        return list != null ? list : nonNull(Collections.<TYPE> emptyList());
    }

    /**
     * Ensures that callers of this method can always work with a {@link NonNull} {@link Set set}. Falls back to an
     * empty set in case the given set is <code>null</code>.
     *
     * @param <TYPE>
     *            The set type
     * @param set
     *            A {@link Set} or <code>null</code>
     * @return The given set or an empty set
     */
    public static <TYPE> @NonNull Set<TYPE> ensureSet(@Nullable final Set<TYPE> set) {
        return set != null ? set : nonNull(Collections.<TYPE> emptySet());
    }

    /**
     * Ensures that callers of this method can always work with a {@link NonNull} {@link Map map}. Falls back to an
     * empty map in case the given map is <code>null</code>.
     *
     * @param <KEY>
     *            The map key type
     * @param <VALUE>
     *            The map value type
     * @param map
     *            A {@link Map} or <code>null</code>
     * @return The given map or an empty map
     */
    public static <KEY, VALUE> @NonNull Map<KEY, VALUE> ensureMap(@Nullable final Map<KEY, VALUE> map) {
        return map != null ? map : nonNull(Collections.<KEY, VALUE> emptyMap());
    }

    /**
     * Safely checks whether a given {@link Collection collection} is <code>null</code> or empty.
     *
     * @param collection
     *            A collection or <code>null</code>
     * @return <code>true</code> if the collection is null or empty, otherwise <code>false</code>.
     */
    public static boolean isNullOrEmpty(@Nullable final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Safely checks whether a given {@link Map map} is <code>null</code> or empty.
     *
     * @param map
     *            A map or <code>null</code>
     * @return <code>true</code> if the map is null or empty, otherwise <code>false</code>.
     */
    public static boolean isNullOrEmpty(@Nullable final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Safely checks whether a given {@link String string} is <code>null</code> or empty.
     *
     * @param string
     *            A String or <code>null</code>
     * @return <code>true</code> if the string is null or empty, otherwise <code>false</code>.
     */
    public static boolean isNullOrEmpty(@Nullable final String string) {
        return string == null || string.isEmpty();
    }

}
