package si;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class NoFalse extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser p, DeserializationContext x) throws IOException {

            if (p.getCurrentToken().isNumeric()) {
                return p.getDoubleValue();
            } else {
                return null;
            }
    }
}