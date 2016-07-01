/*
 * Copyright © 2016 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package de.xn__ho_hia.quality.null_analysis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 */
@SuppressWarnings({ "nls", "static-method" })
public class NullsafeTest {

    /** Captures expected exceptions during tests. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test method for {@link Nullsafe#nonNull(Object)}.
     */
    @Test
    public final void shouldConvertNullableIntoNonNull() {
        // given
        @Nullable
        final Object test = "unit test";

        // when
        @NonNull
        final Object nonNullObject = Nullsafe.nonNull(test);

        // then
        Assert.assertNotNull(nonNullObject);
    }

    /**
     * Test method for {@link Nullsafe#nonNull(Object)}.
     */
    @Test
    public final void shouldShowDefaultErrorMessage() {
        // given
        @Nullable
        final Object test = null;

        // when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(CoreMatchers.is("Got unexpected NULL reference"));

        // then
        Nullsafe.nonNull(test);
    }

    /**
     * Test method for {@link Nullsafe#nonNull(Object, String)}.
     */
    @Test
    public final void shouldShowgivenErrorMessage() {
        // given
        @Nullable
        final Object test = null;
        final String errorMessage = "this is a problem";

        // when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(CoreMatchers.is(errorMessage));

        // then
        Nullsafe.nonNull(test, errorMessage);
    }

    /**
     * Test method for {@link Nullsafe#safelyStream(Collection)}.
     */
    @Test
    public final void shouldSafelyStreamCollection() {
        // given
        @Nullable
        final Collection<Object> collection = null;

        // when
        @NonNull
        final Stream<Object> stream = Nullsafe.safelyStream(collection);

        // then
        Assert.assertNotNull(stream);
    }

    /**
     * Test method for {@link Nullsafe#safelyStream(Collection)}.
     */
    @Test
    public final void shouldSafelyStreamNonNullCollection() {
        // given
        @Nullable
        final Collection<Object> collection = new ArrayList<>(1);

        // when
        @NonNull
        final Stream<Object> stream = Nullsafe.safelyStream(collection);

        // then
        Assert.assertNotNull(stream);
    }

    /**
     * Test method for {@link Nullsafe#ensureList(List)}.
     */
    @Test
    public final void shouldEnsureList() {
        // given
        @Nullable
        final List<Object> list = null;

        // when
        @NonNull
        final List<Object> nonNullList = Nullsafe.ensureList(list);

        // then
        Assert.assertNotNull(nonNullList);
    }

    /**
     * Test method for {@link Nullsafe#ensureList(List)}.
     */
    @Test
    public final void shouldEnsureNonNullList() {
        // given
        @Nullable
        final List<Object> list = new ArrayList<>(1);

        // when
        @NonNull
        final List<Object> nonNullList = Nullsafe.ensureList(list);

        // then
        Assert.assertNotNull(nonNullList);
    }

    /**
     * Test method for {@link Nullsafe#ensureSet(Set)}.
     */
    @Test
    public final void shouldEnsureSet() {
        // given
        @Nullable
        final Set<Object> set = null;

        // when
        @NonNull
        final Set<Object> nonNullSet = Nullsafe.ensureSet(set);

        // then
        Assert.assertNotNull(nonNullSet);
    }

    /**
     * Test method for {@link Nullsafe#ensureSet(Set)}.
     */
    @Test
    public final void shouldEnsureNonNullSet() {
        // given
        @Nullable
        final Set<Object> set = new HashSet<>(1);

        // when
        @NonNull
        final Set<Object> nonNullSet = Nullsafe.ensureSet(set);

        // then
        Assert.assertNotNull(nonNullSet);
    }

    /**
     * Test method for {@link Nullsafe#ensureMap(Map)}.
     */
    @Test
    public final void shouldEnsureMap() {
        // given
        @Nullable
        final Map<Object, Object> map = null;

        // when
        @NonNull
        final Map<Object, Object> nonNullMap = Nullsafe.ensureMap(map);

        // then
        Assert.assertNotNull(nonNullMap);
    }

    /**
     * Test method for {@link Nullsafe#ensureMap(Map)}.
     */
    @Test
    public final void shouldEnsureNonNullMap() {
        // given
        @Nullable
        final Map<Object, Object> map = new HashMap<>(1);

        // when
        @NonNull
        final Map<Object, Object> nonNullMap = Nullsafe.ensureMap(map);

        // then
        Assert.assertNotNull(nonNullMap);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(Collection)}.
     */
    @Test
    public final void shouldCheckNullCollectionForNullOrEmpty() {
        // given
        @Nullable
        final Collection<Object> collection = null;

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(collection);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(Collection)}.
     */
    @Test
    public final void shouldCheckEmptyCollectionForNullOrEmpty() {
        // given
        @Nullable
        final Collection<Object> collection = new ArrayList<>(1);

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(collection);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(Collection)}.
     */
    @Test
    public final void shouldCheckCollectionForNullOrEmpty() {
        // given
        @Nullable
        final Collection<Object> collection = new ArrayList<>(1);
        collection.add("test");

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(collection);

        // then
        Assert.assertFalse(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(Map)}.
     */
    @Test
    public final void shouldCheckNullMapForNullOrEmpty() {
        // given
        @Nullable
        final Map<Object, Object> map = null;

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(map);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(Map)}.
     */
    @Test
    public final void shouldCheckEmptyMapForNullOrEmpty() {
        // given
        @Nullable
        final Map<Object, Object> map = new HashMap<>(1);

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(map);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(Map)}.
     */
    @Test
    public final void shouldCheckMapForNullOrEmpty() {
        // given
        @Nullable
        final Map<Object, Object> map = new HashMap<>(1);
        map.put("test", "abc");

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(map);

        // then
        Assert.assertFalse(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(String)}.
     */
    @Test
    public final void shouldCheckNullStringForNullOrEmpty() {
        // given
        @Nullable
        final String string = null;

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(string);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(String)}.
     */
    @Test
    public final void shouldCheckEmptyStringForNullOrEmpty() {
        // given
        @Nullable
        final String string = "";

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(string);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#isNullOrEmpty(String)}.
     */
    @Test
    public final void shouldCheckStringForNullOrEmpty() {
        // given
        @Nullable
        final String string = "test";

        // when
        final boolean nullOrEmpty = Nullsafe.isNullOrEmpty(string);

        // then
        Assert.assertFalse(nullOrEmpty);
    }

    /**
     * Test method for {@link Nullsafe#asBigInteger(long)}.
     */
    @Test
    public final void shouldCreateNonNullBigIntegerFromLong() {
        // given
        final long value = 123;

        // when
        @NonNull
        final BigInteger result = Nullsafe.asBigInteger(value);

        // then
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link Nullsafe#asLong(long)}.
     */
    @Test
    public final void shouldCreateNonNullLongFromLong() {
        // given
        final long value = 123;

        // when
        @NonNull
        final Long result = Nullsafe.asLong(value);

        // then
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link Nullsafe#addNullsafe(BigInteger, BigInteger)}.
     */
    @Test
    public final void shouldAddBigIntegers() {
        // given
        @Nullable
        final BigInteger value1 = BigInteger.valueOf(123);
        @Nullable
        final BigInteger value2 = BigInteger.valueOf(567);

        // when
        @NonNull
        final BigInteger result = Nullsafe.addNullsafe(value1, value2);

        // then
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link Nullsafe#subtractNullsafe(BigInteger, BigInteger)}.
     */
    @Test
    public final void shouldSubtractBigIntegers() {
        // given
        @Nullable
        final BigInteger value1 = BigInteger.valueOf(123);
        @Nullable
        final BigInteger value2 = BigInteger.valueOf(567);

        // when
        @NonNull
        final BigInteger result = Nullsafe.subtractNullsafe(value1, value2);

        // then
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link Nullsafe#divideNullsafe(BigInteger, BigInteger)}.
     */
    @Test
    public final void shouldDivideBigIntegers() {
        // given
        @Nullable
        final BigInteger value1 = BigInteger.valueOf(123);
        @Nullable
        final BigInteger value2 = BigInteger.valueOf(567);

        // when
        @NonNull
        final BigInteger result = Nullsafe.divideNullsafe(value1, value2);

        // then
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link Nullsafe#multiplyNullsafe(BigInteger, BigInteger)}.
     */
    @Test
    public final void shouldMultiplyBigIntegers() {
        // given
        @Nullable
        final BigInteger value1 = BigInteger.valueOf(123);
        @Nullable
        final BigInteger value2 = BigInteger.valueOf(567);

        // when
        @NonNull
        final BigInteger result = Nullsafe.multiplyNullsafe(value1, value2);

        // then
        Assert.assertNotNull(result);
    }

    /**
     * Ensures that the constructor of the {@link Nullsafe} class is private.
     * <p>
     * The class should never be instantiated. Instead use the static factory methods to construct storage units.
     *
     * @throws NoSuchMethodException
     *             Should not fail in case the StorageUnits class has a constructor..
     * @throws IllegalAccessException
     *             Should not fail in case the StorageUnits class has a constructor..
     * @throws InvocationTargetException
     *             Should not fail in case the StorageUnits class has a constructor..
     * @throws InstantiationException
     *             Should not fail in case the StorageUnits class has a constructor..
     */
    @Test
    public void shouldDeclarePrivateConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Given
        final Constructor<Nullsafe> constructor = Nullsafe.class.getDeclaredConstructor();

        // When
        final boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());

        // Then
        Assert.assertTrue("Constructor is not private", isPrivate);
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
