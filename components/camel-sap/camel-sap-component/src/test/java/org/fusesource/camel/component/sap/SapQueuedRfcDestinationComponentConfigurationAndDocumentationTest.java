/**
 *  Copyright 2005-2015 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.fusesource.camel.component.sap;

import org.apache.camel.ComponentConfiguration;
import org.apache.camel.EndpointConfiguration;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class SapQueuedRfcDestinationComponentConfigurationAndDocumentationTest extends CamelTestSupport {

    @Override
    public boolean isUseRouteBuilder() {
        return false;
    }

    @Test
    public void testComponentConfiguration() throws Exception {
        SapQueuedRfcDestinationComponent comp = context.getComponent("sap-qrfc-destination", SapQueuedRfcDestinationComponent.class);
        EndpointConfiguration conf = comp.createConfiguration("sap-qrfc-destination:destinationName:queueName:rfcName?stateful=true&transacted=false");

        assertEquals("true", conf.getParameter("stateful"));
        assertEquals("false", conf.getParameter("transacted"));

        ComponentConfiguration compConf = comp.createComponentConfiguration();
        String json = compConf.createParameterJsonSchema();
        assertNotNull(json);

        assertTrue(json.contains("\"destination\": { \"kind\": \"path\", \"required\": \"true\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the destination this endpoint sends an SAP request to\" }"));
        assertTrue(json.contains("\"queue\": { \"kind\": \"path\", \"required\": \"true\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the queue this endpoint sends an SAP request to\" }"));
        assertTrue(json.contains("\"rfc\": { \"kind\": \"path\", \"required\": \"true\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the Remote Function Module this endpoint sends an SAP request to\" }"));
        assertTrue(json.contains("\"stateful\": { \"kind\": \"parameter\", \"type\": \"boolean\", \"javaType\": \"boolean\", \"deprecated\": \"false\", \"defaultValue\": \"false\", \"description\": \"When true specifies that this endpoint will initiate an SAP stateful session\" }"));
        assertTrue(json.contains("\"transacted\": { \"kind\": \"parameter\", \"type\": \"boolean\", \"javaType\": \"boolean\", \"deprecated\": \"false\", \"defaultValue\": \"false\", \"description\": \"When true specifies that this endpoint will initiate an SAP transaction\" }"));
    }

}
