package com.emented.weblab3.DAO;

import com.emented.weblab3.DTO.HitDTO;
import com.emented.weblab3.database.LocalDatabaseConnectionProvider;
import com.emented.weblab3.database.jooq.Tables;
import com.emented.weblab3.service.SessionIdGetter;
import lombok.Data;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@ManagedBean
@ApplicationScoped
public class JooqHitsDAO implements HitsDAO {

    @ManagedProperty("#{localDatabaseConnectionProvider}")
    private LocalDatabaseConnectionProvider localDatabaseConnectionProvider;

    @ManagedProperty("#{sessionIdGetterImpl}")
    private SessionIdGetter sessionIdGetter;

    @Override
    public void clearTable() {
        try (Connection connection = localDatabaseConnectionProvider.getConnection()) {

            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            dslContext.delete(Tables.HITS)
                    .where(Tables.HITS.SESSION_ID
                            .eq(sessionIdGetter.getCurrentSessionId()))
                    .execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public List<HitDTO> findAll() {
        try (Connection connection = localDatabaseConnectionProvider.getConnection()) {

            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            return dslContext.select()
                    .from(Tables.HITS)
                    .where(Tables.HITS.SESSION_ID
                            .eq(sessionIdGetter.getCurrentSessionId()))
                    .fetch()
                    .into(HitDTO.class);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public Integer save(HitDTO hit) {
        try (Connection connection = localDatabaseConnectionProvider.getConnection()) {

            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            return dslContext.insertInto(Tables.HITS)
                    .set(dslContext.newRecord(Tables.HITS, hit))
                    .returning(Tables.HITS.ID)
                    .fetchOptional()
                    .orElseThrow(() -> new DataAccessException("Error"))
                    .get(Tables.HITS.ID);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return -1;
    }
}
