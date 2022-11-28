package com.virtualvenue.model.widget;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduledInstance {
  /**
     * Start datetime of this content or null if not restricted
     */
    @XmlSchemaType(name = "dateTime")
    @Nullable
    OffsetDateTime begin;

    /**
     * End datetime of this content or null if not restricted
     */
    @XmlSchemaType(name = "dateTime")
    @Nullable
    OffsetDateTime end;
}
