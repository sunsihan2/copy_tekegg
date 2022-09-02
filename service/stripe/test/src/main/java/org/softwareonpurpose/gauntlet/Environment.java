/*Copyright 2017 Craig A. Stockton

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/
package org.softwareonpurpose.gauntlet;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private static final String PAYMENT_SERVICE_PROTOCOL = "https";
    private static Environment environment;
    private final Properties properties = new Properties();

    Environment(InputStream inputStream) {
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * System Property 'env' is used to initialize Environment to resources/[env].properties
     * @return Environment
     */
    public static Environment getInstance() {
        if (environment == null) {
            String env = System.getProperty("env");
            env = (env == null) || env.isBlank() ? "local" : env;
            InputStream inputStream = null;
            String environmentPropertiesFile = String.format("%s.properties", env);
            try {
                inputStream = Environment.class.getClassLoader().getResourceAsStream(environmentPropertiesFile);
            } catch (Exception e) {
                String message = "System Property 'env' set to NON-existent [environment].properties file %s";
                LoggerFactory.getLogger(Environment.class).error(String.format(message, environmentPropertiesFile));
            }
            environment = new Environment(inputStream);
        }
        return environment;
    }

    static void clear() {
        environment=null;
    }

    String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getPaymentAuthorizationKey() {
        return getProperty("api_key_payment_service");
    }

    public String getPaymentServiceDomainUrl() {
        return String.format("%s://%s", PAYMENT_SERVICE_PROTOCOL, getProperty("payment_service_domain"));
    }
}
