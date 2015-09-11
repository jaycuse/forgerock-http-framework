/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.http.context;

import org.forgerock.http.Context;
import org.forgerock.json.JsonValue;

/**
 * A context for audit information for an incoming request. Strictly speaking, this is independent
 * of the HTTP transport, but it is added here as it lives with the Context interface.
 */
public class RequestAuditContext extends AbstractContext {
    private static final String NAME = "requestAudit";
    /** The time of the request. */
    private static final String RECEIVED_TIME = "receivedTime";

    /**
     * Constructs a new context using the specified parent and the current time as the request received time.
     * @param parent The parent context.
     */
    public RequestAuditContext(Context parent) {
        super(parent, NAME);
        data.put(RECEIVED_TIME, System.currentTimeMillis());
    }

    /**
     * Restores a saved context.
     * @param savedContext The saved state.
     * @param classLoader The {@code ClassLoader} to use.
     */
    public RequestAuditContext(JsonValue savedContext, ClassLoader classLoader) {
        super(savedContext, classLoader);
    }

    /**
     * Get the time in milliseconds since the epoch that the request was received.
     * @return The request received time.
     */
    public long getRequestReceivedTime() {
        return data.get(RECEIVED_TIME).asLong();
    }
}