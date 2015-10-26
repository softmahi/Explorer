<<<<<<< HEAD:engine/src/main/java/com/stratio/explorer/functions/SearchFileFunction.java
/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
=======
package com.stratio.explorer.reader;
>>>>>>> [EXPLORER-113][Configuration]:engine/src/main/java/com/stratio/explorer/reader/PathCalculatorListBuilder.java

package com.stratio.explorer.functions;

import java.io.File;

public class SearchFileFunction implements SearcherFunction<File> {


    private String fileName;


    public SearchFileFunction(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean isValid(File file) {
        return fileName.equals(file.getName());
    }

}

