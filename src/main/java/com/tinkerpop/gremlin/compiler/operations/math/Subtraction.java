package com.tinkerpop.gremlin.compiler.operations.math;

import com.tinkerpop.gremlin.compiler.operations.BinaryOperation;
import com.tinkerpop.gremlin.compiler.operations.Operation;
import com.tinkerpop.gremlin.compiler.Atom;

/**
 * @author Pavel A. Yaskevich
 */
public class Subtraction extends BinaryOperation {

	public Subtraction(final Operation... operands) {
		super(operands);
	}
	
    public Atom compute() {
        Double a = (Double)this.operands[0].compute().getValue();
        Double b = (Double)this.operands[1].compute().getValue();

        return new Atom(a - b);
    }

    public Type getType() {
        return Type.MATH;
    }
    
}