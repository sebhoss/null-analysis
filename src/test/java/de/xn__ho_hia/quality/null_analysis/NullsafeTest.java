/*
 * Copyright © 2016 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package de.xn__ho_hia.quality.null_analysis;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
@SuppressWarnings({ "nls", "static-method" })
public class NullsafeTest {

    /**
     * Test method for {@link de.xn__ho_hia.quality.null_analysis.Nullsafe#nonNull(java.lang.Object)}.
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

}
