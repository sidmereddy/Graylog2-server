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
package org.graylog.storage.elasticsearch6.views.searchtypes.pivot.series;

import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.ExtendedStatsAggregation;
import org.graylog.shaded.elasticsearch6.org.elasticsearch.search.aggregations.AggregationBuilder;
import org.graylog.shaded.elasticsearch6.org.elasticsearch.search.aggregations.AggregationBuilders;
import org.graylog.shaded.elasticsearch6.org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStatsAggregationBuilder;
import org.graylog.storage.elasticsearch6.views.ESGeneratedQueryContext;
import org.graylog.storage.elasticsearch6.views.searchtypes.pivot.ESPivot;
import org.graylog.storage.elasticsearch6.views.searchtypes.pivot.ESPivotSeriesSpecHandler;
import org.graylog.plugins.views.search.searchtypes.pivot.Pivot;
import org.graylog.plugins.views.search.searchtypes.pivot.series.SumOfSquares;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.stream.Stream;

public class ESSumOfSquaresHandler extends ESPivotSeriesSpecHandler<SumOfSquares, ExtendedStatsAggregation> {
    @Nonnull
    @Override
    public Optional<AggregationBuilder> doCreateAggregation(String name, Pivot pivot, SumOfSquares sumOfSquaresSpec, ESPivot searchTypeHandler, ESGeneratedQueryContext queryContext) {
        final ExtendedStatsAggregationBuilder sumOfSquares = AggregationBuilders.extendedStats(name).field(sumOfSquaresSpec.field());
        record(queryContext, pivot, sumOfSquaresSpec, name, ExtendedStatsAggregation.class);
        return Optional.of(sumOfSquares);
    }

    @Override
    public Stream<Value> doHandleResult(Pivot pivot, SumOfSquares pivotSpec,
                                        SearchResult searchResult,
                                        ExtendedStatsAggregation sumOfSquaresAggregation,
                                        ESPivot searchTypeHandler,
                                        ESGeneratedQueryContext esGeneratedQueryContext) {
        return Stream.of(ESPivotSeriesSpecHandler.Value.create(pivotSpec.id(), SumOfSquares.NAME, sumOfSquaresAggregation.getSumOfSquares()));
    }
}
