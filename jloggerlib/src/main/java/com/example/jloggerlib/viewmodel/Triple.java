package com.example.jloggerlib.viewmodel;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

/**
 * Container to ease passing around a triple of three objects. This object provides a sensible
 * implementation of equals(), returning true if equals() is true on each of the contained
 * objects.
 */
public class Triple<F, S, T> {
    public final F first;
    public final S second;
    public final T third;
    /**
     * Constructor for a Triple.
     *
     * @param first the first object in the triple
     * @param second the second object in the triple
     * @param third the second object in the triple
     */
    public Triple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Checks the three objects for equality by delegating to their respective
     * {@link Object#equals(Object)} methods.
     *
     * @param o the {@link android.util.Pair} to which this one is to be checked for equality
     * @return true if the underlying objects of the Triple are considered equal
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return Objects.equals(first, triple.first) &&
                Objects.equals(second, triple.second) &&
                Objects.equals(third, triple.third);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    /**
     * Compute a hash code using the hash codes of the underlying objects
     *
     * @return a hashcode of the Pair
     */


    @Override
    public String toString() {
        return "Triple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }

    /**
     * Convenience method for creating an appropriately typed triple.
     * @param a the first object in the triple
     * @param b the second object in the triple
     * @param c the second object in the triple
     * @return a Triple that is templatized with the types of a, b and c
     */
    public static <A, B,C> Triple<A, B, C> create(A a, B b, C c) {
        return new Triple<>(a, b, c);
    }
}
