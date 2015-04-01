/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.unifiedpush.rest.util;

import org.jboss.aerogear.unifiedpush.service.HealthService;
import org.jboss.aerogear.unifiedpush.service.impl.health.HealthDetails;
import org.jboss.aerogear.unifiedpush.service.impl.health.HealthStatus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * A class to test 'health' of the server
 */
@Path("/sys/info")
public class HealthCheck {

    @Inject
    private HealthService healthService;

    @GET
    public HealthStatus health() throws ExecutionException, InterruptedException {
        final HealthStatus status = new HealthStatus();

        final Future<HealthDetails> dbStatus = healthService.dbStatus();
        final Future<HealthDetails> networkStatus = healthService.networkStatus();

        status.add(dbStatus.get());
        status.add(networkStatus.get());

        return status;
    }

}
