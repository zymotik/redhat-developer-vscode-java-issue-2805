package com.virtualvenue.model.widget;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@Data
@SuperBuilder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class TextNotificationWidget extends ContentWidget<TextNotificationWidgetConfiguration> {

}
