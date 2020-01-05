package com.harragan.battleshipsboot.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.harragan.battleshipsboot.model.game.Player;

import java.io.IOException;
import java.io.StringWriter;

public class PlayerSerializer extends JsonSerializer<Player> {

  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public void serialize(final Player value,
                        final JsonGenerator gen,
                        final SerializerProvider serializers)
      throws IOException, JsonProcessingException {

    final StringWriter writer = new StringWriter();
    mapper.writeValue(writer, value);
    gen.writeFieldName(writer.toString().replace("\"", ""));
  }
}
