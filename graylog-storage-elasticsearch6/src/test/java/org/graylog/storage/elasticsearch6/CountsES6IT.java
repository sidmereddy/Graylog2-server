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
package org.graylog.storage.elasticsearch6;

import org.graylog.storage.elasticsearch6.testing.ElasticsearchInstanceES6;
import org.graylog.testing.elasticsearch.SearchServerInstance;
import org.graylog2.indexer.counts.CountsAdapter;
import org.graylog2.indexer.counts.CountsIT;
import org.junit.Rule;

import static org.graylog.storage.elasticsearch6.testing.TestUtils.jestClient;

public class CountsES6IT extends CountsIT {
    @Rule
    public final SearchServerInstance elasticsearch = ElasticsearchInstanceES6.create();

    @Override
    protected SearchServerInstance elasticsearch() {
        return this.elasticsearch;
    }

    @Override
    protected CountsAdapter countsAdapter() {
        return new CountsAdapterES6(jestClient(elasticsearch));
    }
}
