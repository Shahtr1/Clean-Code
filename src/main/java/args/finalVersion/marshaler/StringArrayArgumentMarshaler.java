package args.finalVersion.marshaler;

import args.finalVersion.exception.ArgsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static args.finalVersion.exception.ArgsException.ErrorCode.MISSING_STRING;


public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    private final List<String> stringArray = new ArrayList<>();

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        if (currentArgument.hasNext()) {
            while (currentArgument.hasNext()) {
                stringArray.add(currentArgument.next());
            }
        } else {
            throw new ArgsException(MISSING_STRING, "Expected one or more strings but got none.");
        }
    }

    public static String[] getValue(ArgumentMarshaler am) {
        if (am instanceof StringArrayArgumentMarshaler) {
            return ((StringArrayArgumentMarshaler) am).stringArray.toArray(new String[0]);
        } else {
            return new String[0];
        }
    }
}
