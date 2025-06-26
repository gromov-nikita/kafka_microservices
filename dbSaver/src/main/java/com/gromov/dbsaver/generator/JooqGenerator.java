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

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_NAME = "org.jooq.meta.postgres.PostgresDatabase";
    private static final String INPUT_SCHEMA = "public";
    private static final String INCLUDES = ".*";
    private static final String GENERATOR_NAME = "org.jooq.codegen.DefaultGenerator";
    private static final String TARGET_PACKAGE = "com.example.jooq.generated";
    private static final String TARGET_DIRECTORY = "target/generated-sources/jooq";

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
                .withName(DATABASE_NAME)
                .withInputSchema(INPUT_SCHEMA)
                .withIncludes(INCLUDES);
    }
    private Generator getGenerator(Database database) {
        return new Generator()
                .withName(GENERATOR_NAME)
                .withDatabase(database)
                .withTarget(getTarget());
    }
    private Target getTarget() {
        return new Target()
                .withPackageName(TARGET_PACKAGE)
                .withDirectory(TARGET_DIRECTORY);
    }
    private Configuration getConfiguration(Jdbc jdbc, Generator generator) {
        return new Configuration()
                .withJdbc(jdbc)
                .withGenerator(generator);
    }
}
