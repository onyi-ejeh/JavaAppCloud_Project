package ng.victoriaejeh.javaappawscloud.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.MetricDatum;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;

import java.time.Instant;

public class MetricsService {
    private final CloudWatchClient cloudWatch = CloudWatchClient.builder().region(Region.EU_NORTH_1).build();

    public void publishLoginMetric() {
        MetricDatum datum = MetricDatum.builder()
                .metricName("UserLogin")
                .unit(StandardUnit.COUNT)
                .value(1.0)
                .timestamp(Instant.now())
                .build();

        PutMetricDataRequest request = PutMetricDataRequest.builder()
                .namespace("CustomAppMetrics")
                .metricData(datum)
                .build();

        cloudWatch.putMetricData(request);
    }
}
