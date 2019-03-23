package de.homberger.christopher.dawn;

import org.junit.Test;

import org.json.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest {

    class UTest {
        public UTest(JSONObject obj) {
            for(Object l : obj.getJSONArray("succeed")) {
                succeed.add((String)l);
            }
            for(Object l : obj.getJSONArray("fails")) {
                fails.add((String)l);
            }
        }
        public List<String> succeed;
        public List<String> fails;
    }

    /**
     * Rigorous Test.
     */
    @Test
    public void testApp() throws IOException {
        // System.setIn(new InputStream(){
        
        //     @Override
        //     public int read() throws IOException {
        //         return 0;
        //     }
        // });
        // JSONObject obj = new JSONObject(Files.readString(Paths.get("test2.json")));
        // UTest stateless = new UTest(obj.getJSONObject("stateless"));
        // for(Object l : obj.getJSONArray("statefull")) {
        //     for(String f : stateless.fails) {
                
        //     }
        //     UTest t = new UTest((JSONObject)l);
        // }
    }
}
