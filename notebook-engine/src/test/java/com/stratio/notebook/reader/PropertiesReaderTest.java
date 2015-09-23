/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.stratio.notebook.reader;

import com.stratio.notebook.conf.ConstantsFolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class PropertiesReaderTest {


    private static final String NOT_EXIST_PATH ="not_exit";
    private static final String EXIST_FILE_WITH_DATA ="WithData";
    private String oldValueFolderConfiguration ;
    private Properties result ;



    @Before
    public void setUp(){
        result = new Properties();
        oldValueFolderConfiguration = ConstantsFolder.CT_FOLDER_CONFIGURATION;
        ConstantsFolder.CT_FOLDER_CONFIGURATION ="notebook-engine/src/test/test_resources/";

    }

    @After
    public void tearDown(){
        ConstantsFolder.CT_FOLDER_CONFIGURATION = oldValueFolderConfiguration;
    }


    @Test
    public void whenNotExistPath(){
      assertThat(PropertiesReader.readConfigFrom(NOT_EXIST_PATH), is(result));
    }


    @Test public void whenExistFileAndContainsData(){
        result.put("host","1");
        assertThat(PropertiesReader.readConfigFrom(EXIST_FILE_WITH_DATA), is(result));
    }
}
