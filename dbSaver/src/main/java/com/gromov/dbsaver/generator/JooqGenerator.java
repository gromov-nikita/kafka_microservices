package com.gromov.dbsaver.generator;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JooqGenerator implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String JDBC_DRIVER;

    @Value("${spring.jooq.database.name}")
    private String databaseName;

    @Value("${spring.jooq.database.input-schema}")
    private String inputSchema;

    @Value("${spring.jooq.database.includes}")
    private String includes;

    @Value("${spring.jooq.generator.name}")
    private String generatorName;

    @Value("${spring.jooq.generator.target.package}")
    private String targetPackage;

    @Value("${spring.jooq.generator.target.directory}")
    private String targetDirectory;

    @Override
    public void run(String... args) throws Exception {
        GenerationTool.generate(getConfiguration(
                getJdbc(),
                getGenerator(getDatabase())
        ));
    }
    private Jdbc getJdbc() {
        return new Jdbc()
                .withDriver(JDBC_DRIVER)
                .withUrl(url)
                .withUser(username)
                .withPassword(password);
    }
    private Database getDatabase() {
        return new Database()
                .withName(databaseName)
                .withInputSchema(inputSchema)
                .withIncludes(includes);
    }
    private Generator getGenerator(Database database) {
        return new Generator()
                .withName(generatorName)
                .withDatabase(database)
                .withTarget(getTarget());
    }
    private Target getTarget() {
        return new Target()
                .withPackageName(targetPackage)
                .withDirectory(targetDirectory);
    }
    private Configuration getConfiguration(Jdbc jdbc, Generator generator) {
        return new Configuration()
                .withJdbc(jdbc)
                .withGenerator(generator);
    }
}
