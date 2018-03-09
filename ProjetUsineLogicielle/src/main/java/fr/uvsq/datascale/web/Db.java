package fr.uvsq.datascale.web;

import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;
import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.FileSystemUtils;

import fr.uvsq.datascale.model.Diplome;

import java.nio.file.Paths;
import javax.annotation.PostConstruct;

/**
 * Adds the two account types needed.
 */
@Component
public class Db {

    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @Autowired
    private Repository repository;

    @Autowired
    private javax.sql.DataSource dataSource;

    @PostConstruct
    private void init(){
        // init the event store

        // delete previous events on startup
        FileSystemUtils.deleteRecursively(Paths.get("./events").toFile());

        TransactionTemplate transactionTmp = new TransactionTemplate(txManager);
        transactionTmp.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                UnitOfWork uow = DefaultUnitOfWork.startAndGet();
                repository.add(new Diplome("Master"));
                repository.add(new Diplome("Master 2"));
                uow.commit();
            }
        });

        // init the tables for query/view
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("create table diplome_view (diplome_id VARCHAR , vol_horaire integer )");
        jdbcTemplate.update("insert into diplome_view (diplome_id, vol_horaire) values (?, ?)", new Object[]{"Master 1", 0});
        jdbcTemplate.update("insert into diplome_view (diplome_id, vol_horaire) values (?, ?)", new Object[]{"Master 2", 0});
    }

}
