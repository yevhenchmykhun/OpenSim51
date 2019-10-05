package com.opensim51.simulator.memory.datatype;

public interface UInt<T extends UInt> extends Comparable<T> {

    T inc();

    T add(T data);

    T subtract(T data);

    T shiftLeft(int n);

    T shiftRight(int n);

    T not();

    T xor(T data);

    T or(T data);

    T and(T data);

    int toInt();

}
