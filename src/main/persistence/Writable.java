package persistence;

import org.json.JSONObject;

//Represents a Writable object
//This class is based on the CPSC 210 Json Serialization Demo:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
