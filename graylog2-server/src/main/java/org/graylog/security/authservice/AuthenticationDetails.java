/*
 * Copyright (C) 2020 Graylog, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
package org.graylog.security.authservice;

import com.google.auto.value.AutoValue;

import java.util.Collections;
import java.util.Map;

@AutoValue
public abstract class AuthenticationDetails {

    public abstract UserDetails userDetails();

    public abstract Map<String, Object> sessionAttributes();

    public static Builder builder() {
        return Builder.create();
    }

    public abstract AuthenticationDetails.Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {

        public static Builder create() {
            return new AutoValue_AuthenticationDetails.Builder()
                    .sessionAttributes(Collections.emptyMap());
        }

        public abstract Builder userDetails(UserDetails userDetails);

        public abstract Builder sessionAttributes(Map<String, Object> sessionData);

        public abstract AuthenticationDetails build();
    }
}
