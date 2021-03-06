/**
 * Copyright (C) 2015 Stratio (http://stratio.com)
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
package com.stratio.decision;

import com.stratio.explorer.interpreter.Interpreter;
import com.stratio.explorer.interpreter.InterpreterResult;
import com.stratio.decision.api.StratioStreamingAPI;
import com.stratio.decision.commons.exceptions.StratioStreamingException;
import com.stratio.decision.utils.DecisionApiWrapper;
import com.stratio.decision.utils.DecisionSyntaxParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * Created by idiaz on 23/06/15.
 */

public class DecisionInterpreter extends Interpreter {
    /**
     * The Log.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    static {
        Interpreter.register("str", DecisionInterpreter.class.getName());
    }

    StratioStreamingAPI api;
    DecisionApiWrapper wrapper;
    DecisionSyntaxParser parser;

    public DecisionInterpreter(Properties property) {
        super(property);
        api = new StratioStreamingAPI();
        wrapper = new DecisionApiWrapper(api);
        parser = new DecisionSyntaxParser(wrapper);

    }

    @Override
    public void open() {
        String[] kafka = System.getenv("KAFKA").split(":");
        String kafkaServer = kafka[0];
        int kafkaPort = Integer.parseInt(kafka[1]);
        String[] zookeeper = System.getenv("ZOOKEEPER").split(":");
        String zkServer = zookeeper[0];
        int zkPort = Integer.parseInt(zookeeper[1]);

        if (kafkaServer != null && kafkaPort >= 0 && zkServer != null && zkPort >= 0) { // if there is no
            // configuration set, it won't initialize
            api.initializeWithServerConfig(kafkaServer, kafkaPort, zkServer, zkPort);
            logger.info("Streaming connection established");
        }
        if (!api.isInit()) {
            logger.info("Streaming not initialized");
        }
    }

    @Override
    public void close() {
        api.close();
    }

    @Override
    public Object getValue(String name) {
        return null;
    }

    @Override
    public InterpreterResult interpret(String st) {
        try {
            return new InterpreterResult(InterpreterResult.Code.SUCCESS, parser.parse(st));
        } catch (StratioStreamingException e) {
            return new InterpreterResult(InterpreterResult.Code.ERROR, "%text ".concat(e.getMessage()));
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void bindValue(String name, Object o) {

    }

    @Override
    public FormType getFormType() {
        return null;
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public List<String> completion(String buf, int cursor) {
        return null;
    }
}
