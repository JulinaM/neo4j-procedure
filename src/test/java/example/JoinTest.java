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

public class JoinTest
{
    // This rule starts a Neo4j instance
    @Rule
    public Neo4jRule neo4j = new Neo4jRule()

            // This is the function we want to test
            .withFunction( Join.class );

    @Test
    public void shouldAllowIndexingAndFindingANode() throws Throwable
    {
        // This is in a try-block, to make sure we close the driver after the test
        try( Driver driver = GraphDatabase
                .driver( neo4j.boltURI() , Config.build().withEncryptionLevel( Config.EncryptionLevel.NONE ).toConfig() ) )
        {
            // Given
            Session session = driver.session();

            // When
            String result = session.run( "RETURN example.join(['Hello', 'World']) AS result").single().get("result").asString();

            // Then
            assertThat( result, equalTo( "Hello,World" ) );
        }
    }

//    @Test
//    public void shouldAllowIndexingAndFindingANodes() throws Throwable
//    {
//        // This is in a try-block, to make sure we close the driver after the test
//        try( Driver driver = GraphDatabase
//                .driver( neo4j.boltURI() , Config.build().withEncryptionLevel( Config.EncryptionLevel.NONE ).toConfig() ) )
//        {
//            // Given
//            Session session = driver.session();
//
//            // When
//            String result = session.run(
////                    "RETURN example.average([{id_225:{timestamp : 1541187380 , memory : 920004.0}, id_104:{timestamp :1542606110 , memory : 1309404.0}}])AS result")
//            "RETURN example.average([{'id_225':\"[timestamp = 1541187380 , memory = 920004.0]\",\"id_104\":\"[timestamp = 1542606110 , memory = 1309404.0]\"}])AS result")
//                    .single().get("result").asString();
//            // Then
//            assertThat( result, equalTo( "1114704.0" ) );
//        }
//    }
}