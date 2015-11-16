/**
 * Copyright (C) 2013 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.explorer.reader;


import com.stratio.explorer.conf.ConstantsFolder;
import com.stratio.explorer.exceptions.FileConfNotExisException;
import org.slf4j.LoggerFactory;

/**
 * Calculate path
 */
public class PathFileCalculator {

    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(PathFileCalculator.class);


    public PathFileCalculator(){

    }
    /**
     * Obtain complete path to file.
     * @param nameFile without extension
     * @return complete path
     */
    public String getPath(String nameFile,String extensionFile){

         String path = new LocatorsRunner().locate(nameFile,extensionFile);
         if ("".equals(path)){
             String message = "File configuration "+nameFile+" not exist";
             Logger.error(message);
             throw new FileConfNotExisException(message);
         }
         return path;
    }
}
