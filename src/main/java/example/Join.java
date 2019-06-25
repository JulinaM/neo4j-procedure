package example;

import java.util.List;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

/**
 * This is an example how you can create a simple user-defined function for Neo4j.
 */
public class Join
{
    @UserFunction
    @Description("example.join(['s1','s2',...], delimiter) - join the given strings with the given delimiter.")
    public String join(
            @Name("strings") List<String> strings,
            @Name(value = "delimiter", defaultValue = ",") String delimiter) {
        if (strings == null || delimiter == null) {
            return null;
        }
        return String.join(delimiter, strings);
    }

//    @UserFunction
//    @Description("example.join(['s1','s2',...], delimiter) - join the given strings with the given delimiter.")
//    public String average(
//            @Name("List") List<Map> list){
//        if(list == null || list.isEmpty())
//            return "0.00";
//        else {
////            JSONArray array = new JSONArray(inputString);
//            System.out.println(list.getClass());
//            int size = 0;
//            System.out.println(list );
//            Double result = (double) 0;
//            for (Map<String, Map> item : list) {
//                System.out.println("item = " + item.toString());
//                for(Map.Entry<String, Map> entry : item.entrySet()){
//                    result += (Double) entry.getValue().get("memory");
//                    size++;
//                }
//            }
//            return String.valueOf(result/size);
//        }
//    }
}