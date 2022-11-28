package com.virtualvenue.model.basetypes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Locale;

/**
 * Localization container which can holds a (different) string for every configured {@link Locale}
 */
@Data
@Builder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalizedText {

    /**
     * Locale of this text
     */
    @NonNull
    @XmlAttribute(required = true)
    String locale = "";

    /**
     * Localized text corresponding to the set locale
     */
    @NonNull
    @XmlElement(required = true)
    String text = "";
}
