package example;

import org.junit.Rule;
import org.junit.Test;

import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.harness.junit.Neo4jRule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class AggregatorTest {
    @Rule
    public Neo4jRule neo4j = new Neo4jRule()
            .withFunction(Aggregator.class );

    @Test
    public void average() throws Throwable
    {
        // This is in a try-block, to make sure we close the driver after the test
        try( Driver driver = GraphDatabase
                .driver( neo4j.boltURI() , Config.build().withEncryptionLevel( Config.EncryptionLevel.NONE ).toConfig() ) )
        {
            Session session = driver.session();

            Double result = session.run(
                    "RETURN example.average(['a1:1, b1:1', 'a2:2, b2:2', 'a3:3, b3:3']) AS result")
                    .single().get("result").asDouble();
            assertThat( result, equalTo( 2.0 ) );
        }
    }

    @Test
    public void max() throws Throwable
    {
        // This is in a try-block, to make sure we close the driver after the test
        try( Driver driver = GraphDatabase
                .driver( neo4j.boltURI() , Config.build().withEncryptionLevel( Config.EncryptionLevel.NONE ).toConfig() ) )
        {
            Session session = driver.session();

            Double result = session.run(
                    "RETURN example.max(['a1:1, b1:1', 'a2:2, b2:2', 'a3:3, b3:3']) AS result")
                    .single().get("result").asDouble();
            assertThat( result, equalTo( 3.0 ) );
        }
    }

    @Test
    public void min() throws Throwable
    {
        // This is in a try-block, to make sure we close the driver after the test
        try( Driver driver = GraphDatabase
                .driver( neo4j.boltURI() , Config.build().withEncryptionLevel( Config.EncryptionLevel.NONE ).toConfig() ) )
        {
            Session session = driver.session();

            Double result = session.run(
                    "RETURN example.min(['a1:1, b1:1', 'a2:2, b2:2', 'a3:3, b3:3']) AS result")
                    .single().get("result").asDouble();
            assertThat( result, equalTo( 1.0 ) );
        }
    }
}