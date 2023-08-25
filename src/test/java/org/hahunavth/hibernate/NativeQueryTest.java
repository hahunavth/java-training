package org.hahunavth.hibernate;

import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;

public class NativeQueryTest extends BaseDBTest{
    /**
     * Normally, when fetching results in Hibernate using native SQL query, we use the `createNativeQuery()` method,  followed by the `list()` method:
     * <br>
     * In this case, Hibernate uses `ResultSetMetadata` to find column details and returns the list of Object arrays.
     * <br>
     * But, excessive use of `ResultSetMetadata` may result in poor performance, and this is where the `addScalar()` method is useful.
     * <br>
     * By using `addScalar()` method, we can prevent Hibernate from using `ResultSetMetadata`.
     */
    @org.junit.jupiter.api.Test
    void testSelect() {
        Session session = getSessionFactory().openSession();
        List<Object[]> lst = session
                .createNativeQuery("SELECT * FROM accounts")
                .addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("email", StandardBasicTypes.STRING)
                .getResultList();
        lst.forEach(
                item ->
                        System.out.println(
                                item[0] + " - " + item[1]
                        )
        );
    }
}
