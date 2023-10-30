package args.marshaler;

import args.exception.ArgsException;

import java.util.Iterator;

import static args.exception.ArgsException.ErrorCode.*;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
    private double value = 0;


    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        if (currentArgument.hasNext()) {
            String parameter = currentArgument.next();
            try {
                value = Double.parseDouble(parameter);
            } catch (NumberFormatException e) {
                throw new ArgsException(INVALID_DOUBLE, "Expected a double but got: " + parameter);
            }
        } else {
            throw new ArgsException(MISSING_DOUBLE, "Missing double parameter.");
        }
    }

    public static double getValue(ArgumentMarshaler am) {
        if (am instanceof DoubleArgumentMarshaler) {
            return ((DoubleArgumentMarshaler) am).value;
        } else {
            return 0.0;
        }
    }
}

