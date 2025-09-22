package com.example.project.stock.data.demo_project_stock_data.lib;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateSerializer extends JsonSerializer<Long> {


  @Override
  public void serialize(Long num, JsonGenerator gen,
      SerializerProvider serializers) throws IOException {

    // DateTimeFormatter custFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    Long numNilli = num * 1000L;
    Instant instant = Instant.ofEpochMilli(numNilli);
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime newsTime = LocalDateTime.ofInstant(instant, zoneId);
    LocalDateTime today = LocalDateTime.now();

    Duration duration = Duration.between(newsTime, today);

    Long seconds = duration.getSeconds();
    Long minutes = seconds / 60;
    Long hours = minutes / 60;
    Long days = hours / 24;

    String timeAgo;
    if (days > 0) {
      timeAgo = days + " days ago";
    } else if (hours > 0) {
      timeAgo = hours + " hours ago";
    } else if (minutes > 0) {
      timeAgo = minutes + " minutes ago";
    } else {
      timeAgo = "just now";
    }

    gen.writeString(timeAgo);
  }
}
