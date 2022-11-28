package com.virtualvenue.model.basetypes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * A content is a locale specific {@link Asset} with an locale specific asset description
 */
@Data
@SuperBuilder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalizedContent {

    /**
     * Locale of this content
     */
    @NonNull
    @XmlAttribute(required = true)
    String locale = "";

    /**
     * Localized text (corresponding to the locale) of this content
     */
    @NonNull
    String description = "";

    /**
     * Localized Assets (must exist in {@link com.virtualvenue.model.Venue#getAssets()}
     * && {@link Asset#getArchitectureId()} must equal this contents parent architecture id)
     */
    @NonNull
    String assetId = "";

}
