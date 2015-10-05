/*
*Licensed to STRATIO (C) under one or more contributor license agreements.
*See the NOTICE file distributed with this work for additional information
*regarding copyright ownership.  The STRATIO (C) licenses this file
*to you under the Apache License, Version 2.0 (the
*"License"); you may not use this file except in compliance
*with the License.  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/
/**
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
package com.stratio.notebook.interpreter.mock;

import java.util.Properties;

import com.stratio.notebook.conf.ExplorerConfiguration;
import com.stratio.notebook.interpreter.Interpreter;
import com.stratio.notebook.interpreter.InterpreterFactory;

public class MockInterpreterFactory extends InterpreterFactory {

	public MockInterpreterFactory(ExplorerConfiguration conf) {
		super(conf);
	}
	
	public Interpreter createRepl(String replName, Properties properties) {
		if("MockRepl1".equals(replName) || replName==null) {
			return new MockInterpreter1(properties);
		} else if("MockRepl2".equals(replName)) {
			return new MockInterpreter2(properties);
		} else {
			return new MockInterpreter1(properties);
		}
	}
}
