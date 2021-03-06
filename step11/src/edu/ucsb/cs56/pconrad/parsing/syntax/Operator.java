package edu.ucsb.cs56.pconrad.parsing.syntax;

public interface Operator {
    public boolean equals(Object other);
    public int hashCode();
    public String toString();

    public int evaluate(int left, int right) throws EvaluatorException;
}
