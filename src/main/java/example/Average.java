package example;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Average {
    // collection of mem_cache strings []
    // ["job1_t1:m1, job1_t2:m2, job1_t3:m3", "job2_t1:m1, job2_t2:m2, job2_t3:m3" ]
    @UserFunction
    @Description("example.join(['s1','s2',...], delimiter) - join the given strings with the given delimiter.")
    public String average(
            @Name("List") List<String> list){
        if(list == null || list.isEmpty())
            return "0.00";
        else {
            System.out.println(list.getClass());
            int size = 0;
            System.out.println(list );
            double result = (double) 0;
            for (String memCacheString : list) {
                System.out.println("item = " + memCacheString.toString());
                Map<String, String> memCacheMap = Arrays.stream(memCacheString.split(","))
                        .map(s -> s.split(":"))
                        .collect(Collectors.toMap(
                                a -> a[0],  //key
                                a -> a[1]   //value
                        ));
                for(Map.Entry<String, String> entry : memCacheMap.entrySet()){
                    result += Double.parseDouble(entry.getValue());
                    size++;
                }
            }
            return String.valueOf(result/size);
        }
    }
}
