package com.logicgate.payrollmanagement;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {
    private static MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:8.0.31");
        MY_SQL_CONTAINER.start();
    }
}
