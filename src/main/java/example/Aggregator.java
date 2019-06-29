package example;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Aggregator {
    /*
    collection of mem_cache strings- Eg: ["job1_t1:m1, job1_t2:m2, job1_t3:m3", "job2_t1:m1, job2_t2:m2, job2_t3:m3" ]
    */
    @UserFunction
    @Description("example.join(['job1_t1:m1, job1_t2:m2, job1_t3:m3', 'job2_t1:m1, job2_t2:m2, job2_t3:m3']) - returns the average of memory")
    public Double average(@Name("List") List<String> list){
        if(list == null || list.isEmpty()) return 0d;

        List<Double> result = new ArrayList<>();
        for (String memCacheString : list) {

            System.out.println("item = " + memCacheString.replace("\"", ""));
            result.add(Arrays.stream(memCacheString.replace("\"", "").split(","))
                    .map(s -> s.split(":"))
                    .mapToDouble(a-> Double.parseDouble(a[1]))
                    .average()
                    .getAsDouble());
        }
        return result.stream().mapToDouble(a->a).average().getAsDouble();
    }


    /*
    collection of mem_cache strings- Eg: ["job1_t1:m1, job1_t2:m2, job1_t3:m3", "job2_t1:m1, job2_t2:m2, job2_t3:m3" ]
    */
    @UserFunction
    @Description("example.join(['job1_t1:m1, job1_t2:m2, job1_t3:m3', 'job2_t1:m1, job2_t2:m2, job2_t3:m3']) - returns the average of memory")
    public Double max(@Name("List") List<String> list){
        if(list == null || list.isEmpty()) return 0d;

        List<Double> result = new ArrayList<>();
        for (String memCacheString : list) {
            result.add(Arrays.stream(memCacheString.split(","))
                    .map(s -> s.split(":"))
                    .mapToDouble(a-> Double.parseDouble(a[1]))
                    .max()
                    .getAsDouble());
        }
        return result.stream().mapToDouble(a->a).max().getAsDouble();
    }


    /*
    collection of mem_cache strings- Eg: ["job1_t1:m1, job1_t2:m2, job1_t3:m3", "job2_t1:m1, job2_t2:m2, job2_t3:m3" ]
    */
    @UserFunction
    @Description("example.join(['job1_t1:m1, job1_t2:m2, job1_t3:m3', 'job2_t1:m1, job2_t2:m2, job2_t3:m3']) - returns the average of memory")
    public Double min(@Name("List") List<String> list){
        if(list == null || list.isEmpty()) return 0d;

        List<Double> result = new ArrayList<>();
        for (String memCacheString : list) {
            result.add(Arrays.stream(memCacheString.split(","))
                    .map(s -> s.split(":"))
                    .mapToDouble(a-> Double.parseDouble(a[1]))
                    .min()
                    .getAsDouble());
        }
        return result.stream().mapToDouble(a->a).min().getAsDouble();
    }
}
