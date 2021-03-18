package piazza;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ActiveDomainObject {
    public abstract void save(Connection conn) throws SQLException;
}
