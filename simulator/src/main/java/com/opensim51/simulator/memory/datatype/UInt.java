package com.opensim51.simulator.memory.datatype;

public interface UInt<T extends UInt> extends Comparable<T> {

    T inc();

    T add(T data);

    T sub(T data);

    T shl(int n);

    T shr(int n);

    T not();

    T xor(T data);

    T or(T data);

    T and(T data);

    int toInt();

}
