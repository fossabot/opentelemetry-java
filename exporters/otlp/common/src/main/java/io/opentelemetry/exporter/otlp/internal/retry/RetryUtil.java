/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.exporter.otlp.internal.retry;

import io.opentelemetry.exporter.otlp.internal.grpc.GrpcStatusUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is internal and is hence not for public use. Its APIs are unstable and can change at
 * any time.
 */
public class RetryUtil {

  private static final Set<String> RETRYABLE_GRPC_STATUS_CODES;
  private static final Set<Integer> RETRYABLE_HTTP_STATUS_CODES =
      Collections.unmodifiableSet(new HashSet<>(Arrays.asList(429, 502, 503, 504)));

  static {
    Set<String> retryableGrpcStatusCodes = new HashSet<>();
    retryableGrpcStatusCodes.add(GrpcStatusUtil.GRPC_STATUS_CANCELLED);
    retryableGrpcStatusCodes.add(GrpcStatusUtil.GRPC_STATUS_DEADLINE_EXCEEDED);
    retryableGrpcStatusCodes.add(GrpcStatusUtil.GRPC_STATUS_RESOURCE_EXHAUSTED);
    retryableGrpcStatusCodes.add(GrpcStatusUtil.GRPC_STATUS_ABORTED);
    retryableGrpcStatusCodes.add(GrpcStatusUtil.GRPC_STATUS_OUT_OF_RANGE);
    retryableGrpcStatusCodes.add(GrpcStatusUtil.GRPC_STATUS_UNAVAILABLE);
    retryableGrpcStatusCodes.add(GrpcStatusUtil.GRPC_STATUS_DATA_LOSS);
    RETRYABLE_GRPC_STATUS_CODES = Collections.unmodifiableSet(retryableGrpcStatusCodes);
  }

  private RetryUtil() {}

  /** Returns the retryable gRPC status codes. */
  public static Set<String> retryableGrpcStatusCodes() {
    return RETRYABLE_GRPC_STATUS_CODES;
  }

  /** Returns the retryable HTTP status codes. */
  public static Set<Integer> retryableHttpResponseCodes() {
    return RETRYABLE_HTTP_STATUS_CODES;
  }
}
