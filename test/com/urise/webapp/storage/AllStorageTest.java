package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageByUuidTest.class,
        MapStorageByResumeTest.class,
        ObjectFileStorageTest.class,
        ObjectPathStorageTest.class,
        XmlPathStorageTest.class,
        JsonPathStorageTest.class,
        SqlStorageTest.class
})
public class AllStorageTest {

}
